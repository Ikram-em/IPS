package giis.demo.tkrun.accion.venta;

import java.awt.Color;
import java.util.List;

import giis.demo.tkrun.AbstractController;
import giis.demo.tkrun.accion.AccionDTO;
import giis.demo.tkrun.logger.AuditService;
import giis.demo.ui.acciones.misacciones.PanelAccion;
import giis.demo.ui.acciones.misacciones.venta.VentaAccionesPropias;

public class VentaAccionesController extends AbstractController {

	private VentaAccionesPropias view;
	private int idAccionista;
	private MetodosVentaAcciones vModel = new MetodosVentaAcciones();

	public VentaAccionesController(VentaAccionesPropias ventanaVenta, int idAccionista, AuditService audit) {
		super(audit);
		this.view = ventanaVenta;
		this.idAccionista = idAccionista;
		initView();
	}

	private void initView() {
		cargarAcciones();
		this.view.setVisible(true);
	}

	private void cargarAcciones() {
		view.getPnMisAcciones().removeAll();
		view.getPnEnVenta().removeAll();

		// Acciones NO en venta
		List<AccionDTO> acciones = vModel.mostrarAccionesDeAccionista(idAccionista);
		for (AccionDTO dto : acciones) {
			PanelAccion panel = new PanelAccion(dto.getIdAccion(), null, "Poner en Venta",
					e -> ponerEnVenta(dto.getIdAccion()), new Color(144, 238, 144) // verde claro
			);

			view.getPnMisAcciones().add(panel);
		}

		// Acciones EN venta
		List<AccionDTO> ventas = vModel.mostrarAccionesEnVentaDeAccionista(idAccionista);
		for (AccionDTO dto : ventas) {
			PanelAccion panel = new PanelAccion(dto.getIdAccion(), null, "Quitar Venta",
					e -> quitarVenta(dto.getIdAccion()), new Color(255, 182, 193) // rojo rosado claro
			);
			view.getPnEnVenta().add(panel);
		}

		view.getPnMisAcciones().revalidate();
		view.getPnMisAcciones().repaint();
		view.getPnEnVenta().revalidate();
		view.getPnEnVenta().repaint();
	}

	private void ponerEnVenta(int idAccion) {
		vModel.ponerEnVenta(idAccion);
		audit.log("INFO", "La acción con id=" + idAccion + " se ha puesto a la venta");
		cargarAcciones();
	}

	private void quitarVenta(int idAccion) {
		vModel.quitarVenta(idAccion);
		audit.log("INFO", "La acción con id=" + idAccion + " ya no está a la venta");
		cargarAcciones();
	}

}
