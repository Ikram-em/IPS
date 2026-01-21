package giis.demo.tkrun.partidos.entradas;

import java.awt.Color;
import java.awt.event.ItemEvent;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

import giis.demo.tkrun.AbstractController;
import giis.demo.tkrun.factura.FacturaDatos;
import giis.demo.tkrun.factura.FacturaItem;
import giis.demo.tkrun.factura.FacturaPDFGenerator;
import giis.demo.tkrun.ingresos.Ingreso;
import giis.demo.tkrun.ingresos.IngresoDAO;
import giis.demo.tkrun.ingresos.TipoIngreso;
import giis.demo.tkrun.logger.AuditService;
import giis.demo.tkrun.login.Session;
import giis.demo.tkrun.partidos.PartidoDTO;
import giis.demo.ui.partidos.entradas.SeleccionAsientosView;
import giis.demo.ui.partidos.entradas.TicketPurchaseView;

public class SeleccionAsientosController extends AbstractController {
    private SeleccionAsientosView seleccionAsientosView;
    private PartidoDTO partido;
    private EntradasModel entradasModel;

	public SeleccionAsientosController(SeleccionAsientosView seleccionAsientosView, PartidoDTO partido,
			AuditService audit) {
		super(audit);
        this.seleccionAsientosView = seleccionAsientosView;
        this.partido = partido;
        this.entradasModel = new EntradasModel();
        initView();
    }

    private void initView() {
        loadZonas();
        addButtonListeners();
        this.seleccionAsientosView.setVisible(true);
    }

    private void loadZonas() {
        List<ZoneDTO> tribunas = this.entradasModel.getTribunes();
        for (ZoneDTO tribuna : tribunas) {
            seleccionAsientosView.getTribuneSelector().addItem(tribuna);
        }
        List<ZoneDTO> secciones = this.entradasModel.getSections();
        for (ZoneDTO seccion : secciones) {
            seleccionAsientosView.getSectionSelector().addItem(seccion);
        }
        loadSeatMap(tribunas.get(0), secciones.get(0));
    }
    
    

    private void addButtonListeners() {
        seleccionAsientosView.getTribuneSelector().addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                ZoneDTO tribuna = (ZoneDTO) e.getItem();
                ZoneDTO seccion = (ZoneDTO) seleccionAsientosView.getSectionSelector().getSelectedItem();
                reloadSeatMap(tribuna, seccion);
            }
        });

        seleccionAsientosView.getSectionSelector().addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                ZoneDTO seccion = (ZoneDTO) e.getItem();
                ZoneDTO tribuna = (ZoneDTO) seleccionAsientosView.getTribuneSelector().getSelectedItem();
                reloadSeatMap(tribuna, seccion);
            }
        });

        seleccionAsientosView.getBtnComprarEntradas().addActionListener(e -> {
            String nombre = seleccionAsientosView.getTxtNombre().getText().trim();
            String telefono = seleccionAsientosView.getTxtTelefono().getText().trim();
            String email = seleccionAsientosView.getTxtEmail().getText().trim();

            if (nombre.isEmpty() || !nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
                JOptionPane.showMessageDialog(seleccionAsientosView,
                    "El nombre no puede estar vacío ni contener números.",
                    "Error de validación",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (telefono.isEmpty() || !telefono.matches("\\d+")) {
                JOptionPane.showMessageDialog(seleccionAsientosView,
                    "El teléfono no puede estar vacío y solo debe contener números.",
                    "Error de validación",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (email.isEmpty() || !email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) {
                JOptionPane.showMessageDialog(seleccionAsientosView,
                    "Debe ingresar un email válido.",
                    "Error de validación",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }

            int seatsRequested = (Integer) seleccionAsientosView.getNumberSelector().getValue();
            if (seatsRequested > 15 || seatsRequested < 1) {
                JOptionPane.showMessageDialog(seleccionAsientosView, 
                    "La cantidad de asientos debe ser mayor a 0 y menor o igual a 15",
                    "Error de validación",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }

            ZoneDTO tribuna = (ZoneDTO) seleccionAsientosView.getTribuneSelector().getSelectedItem();
            ZoneDTO seccion = (ZoneDTO) seleccionAsientosView.getSectionSelector().getSelectedItem();

            FilaDisponibleDTO availableRow = entradasModel.getAvailableRow(
                partido.getId_partido(), tribuna, seccion, seatsRequested
            );

            if (availableRow == null) {
                JOptionPane.showMessageDialog(seleccionAsientosView, 
                    "No hay ninguna fila en la sección elegida con la cantidad de asientos pedidos disponibles",
                    "Asientos no disponibles",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }

            int asientoInicial = availableRow.getAsientos_ocupados() + 1;

            int userId = Session.get().getUserId();

            double totalCompra = entradasModel.saveSeats(
                partido.getId_partido(),
                tribuna.getId(),
                seccion.getId(),
                availableRow.getFila(),
                seatsRequested,
                asientoInicial,
                userId
            );

            Ingreso ingreso = new Ingreso(
            	    TipoIngreso.ENTRADA_PARTIDO, // <-- tipo usando enum
            	    "Compra de " + seatsRequested + " asientos para " + partido.getNombreEquipoInterno() +
            	    " vs " + partido.getNombreEquipoExterno(),
            	    new java.util.Date(),
            	    totalCompra
            	);
            	new IngresoDAO().insertIngreso(ingreso);
            new IngresoDAO().insertIngreso(ingreso);

            AsientosReservadosDTO asientosReservados = new AsientosReservadosDTO(
                partido.toString(),
                tribuna.getNombre(),
                seccion.getNombre(),
                availableRow.getFila(),
                seatsRequested,
                asientoInicial,
                totalCompra
            );

			// --- LOG ---
			String detalleLog = String.format(
					"Compra asiento realizada, usuarioId=%d, partidoId=%d, %d asientos, tribuna=%s, seccion=%s, fila=%d, total=%.2f",
					Session.get().getUserId(), partido.getId_partido(), seatsRequested, tribuna.getNombre(),
					seccion.getNombre(), availableRow.getFila(), totalCompra);
			audit.log("ENTRADA_COMPRADA", detalleLog);

            // --- Mostrar TicketPurchaseView ---
			new TicketPurchaseController(new TicketPurchaseView(), asientosReservados, nombre, telefono,
					seleccionAsientosView.getTxtDireccion().getText().trim(), email, audit);
            TicketPurchaseView ticketView = new TicketPurchaseView();
            ticketView.getSPnDetailedPurchase().add(ticketView.getDetails(asientosReservados));

            // Botón Ver Factura
            ticketView.getBtnVerFactura().addActionListener(ev -> {
                try {
                    // Datos cliente
                    FacturaDatos datosCliente = new FacturaDatos(nombre,
                            seleccionAsientosView.getTxtDireccion().getText().trim(),
                            telefono,
                            email);

                    // Item de factura
                    FacturaItem item = new FacturaItem(
                        "Entrada - " + asientosReservados.getMatchName() + 
                        " (Fila " + asientosReservados.getRow() + ")",
                        asientosReservados.getTotalPrice(),
                        1
                    );

                    // Generar PDF y abrirlo
                    FacturaPDFGenerator pdfGen = new FacturaPDFGenerator();
                    String facturaPath = pdfGen.generarFacturaPDF( datosCliente,
                                                java.util.Collections.singletonList(item));
                    java.awt.Desktop.getDesktop().open(new java.io.File(facturaPath));

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(ticketView,
                        "No se pudo generar la factura PDF: " + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            });

            seleccionAsientosView.dispose();
        });

    }

    private void loadSeatMap(ZoneDTO tribune, ZoneDTO section) {
        seleccionAsientosView.getSeatMap().getGrid().removeAll();
        Map<Integer, Boolean> ocupados = entradasModel.getSeatsOccupiedCombined(tribune, section, partido.getId_partido());

        for (int fila = 1; fila <= 10; fila++) {
            for (int asiento = 1; asiento <= 15; asiento++) {
                JPanel p = new JPanel();

                int clave = (fila - 1) * 15 + asiento;

                if (ocupados.getOrDefault(clave, false)) {
                    p.setBackground(Color.GRAY); 
                } else {
                    p.setBackground(Color.BLUE); 
                }

                seleccionAsientosView.getSeatMap().getGrid().add(p);
            }
        }

        seleccionAsientosView.getSeatMap().getGrid().revalidate();
        seleccionAsientosView.getSeatMap().getGrid().repaint();
    }

    
    private void reloadSeatMap(ZoneDTO tribuna, ZoneDTO seccion) {
    	seleccionAsientosView.getTribuneSelector().setEnabled(false);
        seleccionAsientosView.getSectionSelector().setEnabled(false);

        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() {
                loadSeatMap(tribuna, seccion);
                return null;
            }

            @Override
            protected void done() {
                seleccionAsientosView.getTribuneSelector().setEnabled(true);
                seleccionAsientosView.getSectionSelector().setEnabled(true);
                seleccionAsientosView.getSeatMap().revalidate();
                seleccionAsientosView.getSeatMap().repaint();
            }
        }.execute();
    }
}
