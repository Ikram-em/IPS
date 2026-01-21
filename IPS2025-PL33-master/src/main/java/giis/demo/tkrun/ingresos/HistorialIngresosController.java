package giis.demo.tkrun.ingresos;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import giis.demo.tkrun.AbstractController;
import giis.demo.tkrun.logger.AuditService;
import giis.demo.ui.ingresos.HistorialIngresosView;
import giis.demo.util.ApplicationException;
import giis.demo.util.Util;

/**
 * Controlador del módulo de Historial de Ingresos.
 * 
 * Se encarga de:
 * - Gestionar las interacciones del usuario con la vista HistorialIngresosView.
 * - Validar y obtener el rango de fechas para la búsqueda de ingresos.
 * - Solicitar los ingresos al DAO (IngresoDAO) y actualizar la tabla de la vista.
 */
public class HistorialIngresosController extends AbstractController {
	 

    private final HistorialIngresosView view;
    private final IngresoDAO ingresoDAO;
    private List<IngresoDTO> ingresosActuales;

	public HistorialIngresosController(HistorialIngresosView view, AuditService audit) {
		super(audit);
        this.view = view;
        this.ingresoDAO = new IngresoDAO();
        this.ingresosActuales = new ArrayList<>();
        initializeController();
        this.view.setVisible(true);
    }


    private void initializeController() {
        view.getBtnBuscar().addActionListener(e -> {
            try {
                buscarIngresos();
            } catch (ApplicationException ex) {
                JOptionPane.showMessageDialog(view, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    
    public void run() {
        view.setVisible(true);
    }
    private void buscarIngresos() {
        Date fechaInicio = view.getFechaInicioSeleccionada();
        Date fechaFin = view.getFechaFinSeleccionada();

        if (fechaInicio == null || fechaFin == null) {
            throw new ApplicationException("Debe seleccionar un rango de fechas válido.");
        }
        if (fechaInicio.after(fechaFin)) {
            throw new ApplicationException("La fecha de inicio no puede ser posterior a la fecha de fin.");
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(fechaFin);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        fechaFin = cal.getTime();

        ingresosActuales = ingresoDAO.findHistorialIngresos(fechaInicio, fechaFin);
        actualizarTablaHistorial(ingresosActuales);
        double sumaTotal = ingresosActuales.stream()
                                           .mapToDouble(IngresoDTO::getCuantiaTotal)
                                           .sum();
        view.setSumaTotal(sumaTotal);
        if (ingresosActuales.isEmpty()) {
            view.setMensajeInfo("No hay ingresos para el rango de fechas seleccionado.");
        } else {
            view.setMensajeInfo("");
        }

		// Log del filtrado
		audit.log("INFO", "Filtrando ingresos desde " + Util.dateToIsoString(fechaInicio) + " hasta "
				+ Util.dateToIsoString(fechaFin));

    }

    private void actualizarTablaHistorial(List<IngresoDTO> ingresos) {
        DefaultTableModel model = new DefaultTableModel(
            new Object[] { "Tipo", "Concepto", "Fecha y Hora", "Total (€)" }, 0
        ) {
			private static final long serialVersionUID = 1L;

			@Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (IngresoDTO i : ingresos) {
            model.addRow(new Object[] {
                i.getTipo(),
                i.getConcepto(),
                (i.getFechaHora() != null ? i.getFechaHora() : "Sin fecha"),
                String.format("%.2f", i.getCuantiaTotal())
            });
        }

        view.getTablaIngresos().setModel(model);
        view.revalidate();
        view.repaint();
    }


    public void refrescarHistorial() {
        buscarIngresos();
    }
}
