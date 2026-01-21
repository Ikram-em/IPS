package giis.demo.tkrun.abonos;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

import javax.swing.JPanel;

import giis.demo.tkrun.AbstractController;
import giis.demo.tkrun.factura.FacturaDatos;
import giis.demo.tkrun.factura.FacturaItem;
import giis.demo.tkrun.factura.FacturaPDFGenerator;
import giis.demo.tkrun.logger.AuditService;
import giis.demo.tkrun.partidos.entradas.ZoneDTO;
import giis.demo.ui.abonos.AbonoTemporadaView;
import giis.demo.util.SwingUtil;

public class AbonoTemporadaController extends AbstractController {

    private final AbonoTemporadaView view;
    private final AbonoModel model;
    private String ultimaFacturaPath;

	public AbonoTemporadaController(AbonoTemporadaView view, AuditService audit) {
		super(audit);
        this.view = view;
        this.model = new AbonoModel();
        initializeView();
        addListeners();
        this.view.setController(this);
        this.view.setVisible(true);
    }

    private void initializeView() {
        view.getComboTribuna().removeAllItems();
        view.getComboSeccion().removeAllItems();

        ZoneDTO opcionDefault = new ZoneDTO();
        opcionDefault.setNombre("-- Selecciona --");
        view.getComboTribuna().addItem(opcionDefault);
        view.getComboSeccion().addItem(opcionDefault);

        for (ZoneDTO tribuna : model.getTribunes())
            view.getComboTribuna().addItem(tribuna);
        for (ZoneDTO seccion : model.getSections())
            view.getComboSeccion().addItem(seccion);

        actualizarSeatMap();
        view.actualizarPrecioAbono(0.0);
    }

    private void addListeners() {

        view.getBtnConfirmar().addActionListener(e -> SwingUtil.exceptionWrapper(this::onConfirmarAbono));
        view.getBtnVerFactura().addActionListener(e -> abrirFactura());
        view.getComboTribuna().addActionListener(e -> { actualizarPrecio(); actualizarSeatMap(); });
        view.getComboSeccion().addActionListener(e -> { actualizarPrecio(); actualizarSeatMap(); });
    }

    private void actualizarPrecio() {
        String tribuna = view.getTribunaSeleccionada();
        String seccion = view.getSeccionSeleccionada();
        if (tribuna.equals("-- Selecciona --") || seccion.equals("-- Selecciona --")) {
            view.actualizarPrecioAbono(0.0);
            return;
        }
        double precio = model.calcularPrecioAbono(tribuna, seccion);
        view.actualizarPrecioAbono(precio);
    }

    public void onConfirmarAbono() {
        String dniCliente = view.getDniCliente();
        String nombre = view.getNombreCliente();
        String direccion = view.getDireccion();
        String telefono = view.getTelefono();
        String email = view.getEmail();

        int fila = view.getFilaSeleccionada();
        int idAsiento = view.getAsientoSeleccionado();
        String tribunaStr = view.getTribunaSeleccionada();
        String seccionStr = view.getSeccionSeleccionada();

      
        if (dniCliente.isEmpty() || !dniCliente.matches("[0-9A-Za-z]+")) {
            view.mostrarError("Debe introducir un DNI válido.");
            view.getBtnVerFactura().setEnabled(false);
            return;
        }
        if (nombre.isEmpty() || !nombre.matches("[A-Za-z\\s]+")) {
            view.mostrarError("Debe introducir un nombre válido (solo letras).");
            view.getBtnVerFactura().setEnabled(false);
            return;
        }
        if (direccion.isEmpty()) {
            view.mostrarError("Debe introducir una dirección.");
            view.getBtnVerFactura().setEnabled(false);
            return;
        }
        if (telefono.isEmpty() || !telefono.matches("\\d+")) {
            view.mostrarError("Debe introducir un teléfono válido (solo números).");
            view.getBtnVerFactura().setEnabled(false);
            return;
        }
        if (email.isEmpty() || !email.matches(".+@.+\\..+")) {
            view.mostrarError("Debe introducir un email válido.");
            view.getBtnVerFactura().setEnabled(false);
            return;
        }

        if (tribunaStr.equals("-- Selecciona --") || seccionStr.equals("-- Selecciona --")) {
            view.mostrarError("Debe seleccionar tribuna y sección antes de confirmar el abono.");
            view.getBtnVerFactura().setEnabled(false);
            return;
        }

        ZoneDTO tribuna = new ZoneDTO(); tribuna.setNombre(tribunaStr);
        ZoneDTO seccion = new ZoneDTO(); seccion.setNombre(seccionStr);

        AbonoDTO abono = model.crearAbono(nombre, dniCliente, fila, idAsiento, tribuna, seccion);



        if (abono == null) {
            view.getBtnVerFactura().setEnabled(false); 
            return;
        }

		// LOG
		audit.log("BOTON_CLICK", "Abono comprado con id=" + abono.getIdAbono());

        Date fechaCompra = new Date();

        FacturaItem item = new FacturaItem(
                "Abono Temporada - Fila " + fila + ", Tribuna " + tribuna.getNombre() + ", Sección " + seccion.getNombre(),
                abono.getPrecioTotal(),
                1
        );

        FacturaDatos datosCliente = new FacturaDatos(nombre, direccion, telefono, email);

       
        FacturaPDFGenerator pdfGen = new FacturaPDFGenerator();
        ultimaFacturaPath = pdfGen.generarFacturaPDF( datosCliente, Collections.singletonList(item));

      
        view.getBtnVerFactura().setEnabled(true);
        view.mostrarResumenAbono(
                dniCliente,
                tribunaStr,
                seccionStr,
                fila,
                idAsiento,
                abono.getPrecioTotal(),
                giis.demo.util.Util.dateTimeToString(fechaCompra)
        );

        actualizarSeatMap();
    }

    private void abrirFactura() {
        if (ultimaFacturaPath != null) {
            try {
                Desktop.getDesktop().open(new File(ultimaFacturaPath));
				// LOG
				audit.log("BOTON_CLICK", "Se ha abierto la factura de compra");

            } catch (Exception e) {
                view.mostrarError("No se pudo abrir la factura PDF.");
                e.printStackTrace();
            }
        }
    }

    private void actualizarSeatMap() {
        String tribunaStr = view.getTribunaSeleccionada();
        String seccionStr = view.getSeccionSeleccionada();

        JPanel grid = view.getSeatMap().getGrid();
        grid.removeAll();

        int filas = 10, columnas = 15;
        grid.setLayout(new GridLayout(filas, columnas, 2, 2));

        ZoneDTO tribuna = new ZoneDTO(); tribuna.setNombre(tribunaStr);
        ZoneDTO seccion = new ZoneDTO(); seccion.setNombre(seccionStr);

        for (int fila = 1; fila <= filas; fila++) {
            Map<Integer, Boolean> ocupados = model.getAsientosOcupadosExactos(tribuna, seccion, fila);
            for (int asiento = 1; asiento <= columnas; asiento++) {
                boolean estaOcupado = ocupados.getOrDefault(asiento, false);
                JPanel p = new JPanel();
                p.setBackground(estaOcupado ? Color.GRAY : Color.BLUE);
                p.setPreferredSize(new Dimension(20, 20));
                grid.add(p);
            }
        }
        grid.revalidate(); grid.repaint();
    }
}


