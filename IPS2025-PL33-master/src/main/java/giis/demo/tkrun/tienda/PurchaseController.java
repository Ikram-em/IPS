package giis.demo.tkrun.tienda;

import java.awt.Desktop;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import giis.demo.tkrun.AbstractController;
import giis.demo.tkrun.factura.FacturaDatos;
import giis.demo.tkrun.factura.FacturaItem;
import giis.demo.tkrun.factura.FacturaPDFGenerator;
import giis.demo.tkrun.logger.AuditService;
import giis.demo.ui.tienda.MerchPurchaseView;

public class PurchaseController extends AbstractController {
    private MerchPurchaseView purchaseView;
    private Map<Integer, UserProductViewDTO> userProducts;
    private double total;

	public PurchaseController(MerchPurchaseView purchaseView, Map<Integer, UserProductViewDTO> userProducts,
			AuditService audit) {
		super(audit);
        this.purchaseView = purchaseView;
        this.userProducts = userProducts;
        this.total = calculateTotal();
        initView();
    }

    private void initView() {
        loadDetails();
        addSendEmailListener();
        addVerFacturaListener(); 
        this.purchaseView.setVisible(true);
    }

    private void loadDetails() {
        DefaultTableModel model = (DefaultTableModel) this.purchaseView.getPnPurchaseTable().getModel();
        model.setRowCount(0);

        for (UserProductViewDTO userProductView : userProducts.values()) {
            model.addRow(new Object[]{
                    userProductView.getProduct().getNombre(),
                    userProductView.getQuantity(),
                    userProductView.getProduct().getPrecio(),
                    userProductView.getProduct().getPrecio() * userProductView.getQuantity()
            });
        }
    }

    private String validarCamposUsuario() {
        StringBuilder errores = new StringBuilder();
        String nombre = purchaseView.getTxtNombre().getText().trim();
        String direccion = purchaseView.getTxtDireccion().getText().trim();
        String telefono = purchaseView.getTxtTelefono().getText().trim();
        String email = purchaseView.getTxtEmail().getText().trim();

        if (nombre.isEmpty() || !nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+"))
            errores.append("- Nombre inválido. Solo letras y espacios.\n");
        if (direccion.isEmpty() || !direccion.matches("[\\w\\s.,#-]+"))
            errores.append("- Dirección inválida. No puede estar vacía.\n");
        if (telefono.isEmpty() || !telefono.matches("\\+?\\d{9,15}"))
            errores.append("- Teléfono inválido. Solo números (9-15 dígitos).\n");
        if (email.isEmpty() || !email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$"))
            errores.append("- Email inválido. Debe tener un formato correcto.\n");

        return errores.toString();
    }
    private void addVerFacturaListener() {
		purchaseView.getBtnVerFactura().addActionListener(e -> {
            String errores = validarCamposUsuario();
            if (!errores.isEmpty()) {
                JOptionPane.showMessageDialog(purchaseView, 
                    "Corrige los siguientes errores antes de ver la factura:\n" + errores,
                    "Error en los datos", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Preparar datos para la factura
            String nombre = purchaseView.getTxtNombre().getText().trim();
            String direccion = purchaseView.getTxtDireccion().getText().trim();
            String telefono = purchaseView.getTxtTelefono().getText().trim();
            String email = purchaseView.getTxtEmail().getText().trim();

            FacturaDatos datosCliente = new FacturaDatos(nombre, direccion, telefono, email);
            List<FacturaItem> items = new ArrayList<>();
            for (UserProductViewDTO p : userProducts.values()) {
                items.add(new FacturaItem(p.getProduct().getNombre(), p.getProduct().getPrecio(), p.getQuantity()));
            }

			// Log de generación de factura
			audit.log("GENERAR_FACTURA", "Factura generada para " + nombre);

            FacturaPDFGenerator pdfGen = new FacturaPDFGenerator();
            try {
                String pathFactura = pdfGen.generarFacturaPDF( datosCliente, items);
                Desktop.getDesktop().open(new java.io.File(pathFactura));

				// Log de apertura exitosa del PDF
				audit.log("FACTURA_ABIERTA", "Factura abierta en ruta: " + pathFactura);
            } catch (Exception ex) {
                ex.printStackTrace();
				audit.log("FACTURA_ERROR", "Error al abrir la factura: " + ex.getMessage());
                JOptionPane.showMessageDialog(purchaseView, 
                    "No se pudo abrir la factura PDF.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }


    private double calculateTotal() {
        double sum = 0;
        for (UserProductViewDTO p : userProducts.values()) {
            sum += p.getProduct().getPrecio() * p.getQuantity();
        }
        return sum;
    }
    private void addSendEmailListener() {
        purchaseView.getBtnSendEmail().addActionListener(e -> {

            String email = purchaseView.getTxtEmail().getText().trim();

            boolean formatoValido = email.matches("^[\\w.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
            boolean doblePunto = email.contains("..");
            boolean direccionValida = false;
            if (formatoValido && !doblePunto) {
                try {
                    new jakarta.mail.internet.InternetAddress(email, true); // valida
                    direccionValida = true;
                } catch (jakarta.mail.internet.AddressException ex) {
                    direccionValida = false;
                }
            }

            if (!direccionValida) {
                JOptionPane.showMessageDialog(
                        purchaseView,
                        "Introduce un correo válido (ej: usuario@dominio.com)",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            // Log del intento
            audit.log("EMAIL_COMPRA_INTENTO", "Enviar email a " + email + ", productos=" + userProducts.size());

            try {
                EmailSender sender = new EmailSender(
                        "ikramaaa57@gmail.com",
                        "zxdc qqvg adyp stzl",
                        "smtp.gmail.com",
                        587
                );

                List<UserProductViewDTO> productsList = new ArrayList<>(userProducts.values());
                sender.sendPurchaseEmail(email, productsList, total);

                audit.log("EMAIL_COMPRA_OK", "Email enviado a " + email);

                JOptionPane.showMessageDialog(
                        purchaseView,
                        "Correo enviado correctamente",
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE
                );

            } catch (Exception ex) {
                ex.printStackTrace();
                audit.log("EMAIL_COMPRA_ERROR", "Error al enviar email a " + email + ": " + ex.getMessage());

                JOptionPane.showMessageDialog(
                        purchaseView,
                        "Error al enviar el correo: " + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });
    }


    }





