package giis.demo.tkrun.suplemento;

import java.awt.Desktop;
import java.io.File;
import java.util.Collections;

import javax.swing.JOptionPane;

import giis.demo.tkrun.AbstractController;
import giis.demo.tkrun.factura.FacturaDatos;
import giis.demo.tkrun.factura.FacturaItem;
import giis.demo.tkrun.factura.FacturaPDFGenerator;
import giis.demo.tkrun.ingresos.Ingreso;
import giis.demo.tkrun.ingresos.IngresoDAO;
import giis.demo.tkrun.ingresos.TipoIngreso;
import giis.demo.tkrun.logger.AuditService;
import java.util.Date;
import giis.demo.tkrun.ui.suplemento.SuplementoPartidoView;

public class SuplementoController extends AbstractController {

    private SuplementoPartidoView view;
    private SuplementoModel model;
    private String ultimaFacturaPath; // ruta del PDF
    private FacturaPDFGenerator pdfGen = new FacturaPDFGenerator();

	public SuplementoController(SuplementoPartidoView view, AuditService audit) {
		super(audit);
        this.view = view;
        this.model = new SuplementoModel();
        initView();
        initController();
        this.view.setVisible(true);
    }

    private void initView() {
        view.getComboAbonados().removeAllItems();
        for (String abono : model.getAbonos()) {
            view.getComboAbonados().addItem(abono);
        }

        view.getComboPartidos().removeAllItems();
        for (String partido : model.getPartidosConSuplemento()) {
            view.getComboPartidos().addItem(partido);
        }

        view.getBtnVerFactura().setEnabled(false); // deshabilitado hasta pagar
    }

    private void initController() {
        view.getBtnPagar().addActionListener(e -> pagarSuplemento());
        view.getBtnVerFactura().addActionListener(e -> generarYAbrirFactura());
        view.getBtnVolver().addActionListener(e -> view.dispose());
    }

    private void pagarSuplemento() {
        try {
            String abonoStr = (String) view.getComboAbonados().getSelectedItem();
            String partidoStr = (String) view.getComboPartidos().getSelectedItem();
            String telefono = view.getTxtTelefono().getText().trim();
            String direccion = view.getTxtDireccion().getText().trim();
            String email = view.getTxtEmail().getText().trim();

            if (abonoStr == null || partidoStr == null) {
                JOptionPane.showMessageDialog(view, "Selecciona un abono y un partido antes de pagar.");
                return;
            }

            if (telefono.isEmpty() || direccion.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Completa los campos de teléfono, dirección y email.");
                return;
            }

            int idAbono = Integer.parseInt(abonoStr.split(" - ")[0].trim());
            int idPartido = Integer.parseInt(partidoStr.split(" - ")[0].trim());

            double precio = 20.0; // precio del suplemento
            boolean ok = model.pagarSuplemento(idAbono, idPartido, precio);

            if (ok) {
                // Mostrar resumen en JTextArea
                String resumen = "Suplemento pagado correctamente.\n" +
                        "Abono: " + abonoStr +
                        "\nPartido: " + partidoStr +
                        "\nTeléfono: " + telefono +
                        "\nDirección: " + direccion +
                        "\nEmail: " + email +
                        "\nPrecio: " + precio + " €";

                view.mostrarResumen(resumen);
                view.setPrecioSuplemento(precio);

                // LOG
                audit.log("BOTON_CLICK", "Pago de suplemento realizado para partido con id=" + idPartido);

                // Habilitar el botón "Ver Factura"
                view.getBtnVerFactura().setEnabled(true);

                // -------------------------------
                // INSERTAR INGRESO AUTOMÁTICO
                // -------------------------------
                Ingreso ingreso = new Ingreso(
                    TipoIngreso.SUPLEMENTO,   // o TipoIngreso.SUPLEMENTO si tienes ese tipo definido
                    "Suplemento partido " + partidoStr,
                    new Date(),
                    precio
                );

                IngresoDAO ingresoDAO = new IngresoDAO();
                ingresoDAO.insertIngreso(ingreso);

            } else {
                JOptionPane.showMessageDialog(view,
                        "Este abono ya tiene un suplemento para este partido.");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view,
                    "Formato de ID incorrecto. Asegúrate de que el combo tiene formato '1 - Abono'.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Error inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }


    private void generarYAbrirFactura() {
        try {
            String abonoStr = (String) view.getComboAbonados().getSelectedItem();
            String partidoStr = (String) view.getComboPartidos().getSelectedItem();
            String telefono = view.getTxtTelefono().getText().trim();
            String direccion = view.getTxtDireccion().getText().trim();
            String email = view.getTxtEmail().getText().trim();

            // --- Validaciones ---
            if ( telefono.isEmpty() || direccion.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Completa todos los campos para generar la factura.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!telefono.matches("\\d+")) {
                JOptionPane.showMessageDialog(view, "El teléfono solo puede contener números.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (direccion.trim().isEmpty()) {
                JOptionPane.showMessageDialog(view, "La dirección no puede estar vacía.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!email.matches("^.+@.+\\..+$")) {
                JOptionPane.showMessageDialog(view, "El email no tiene un formato válido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

        
            FacturaDatos cliente = new FacturaDatos(
                    abonoStr != null ? abonoStr : "Abonado",
                    telefono,
                    direccion,
                    email
            );

            FacturaItem item = new FacturaItem(
                    "Suplemento: " + (partidoStr != null ? partidoStr : "Partido"),
                    20.0, 
                    1
            );

            ultimaFacturaPath = pdfGen.generarFacturaPDF( cliente, Collections.singletonList(item));

            Desktop.getDesktop().open(new File(ultimaFacturaPath));

			// LOG
			audit.log("BOTON_CLICK", "Se ha abierto la factura de compra");

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(view, "Error al generar o abrir la factura.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}


