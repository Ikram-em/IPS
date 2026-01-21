package giis.demo.tkrun.accion.compra;

import java.awt.Color;
import java.awt.Dimension;
import java.util.List;

import javax.swing.JOptionPane;

import giis.demo.tkrun.AbstractController;
import giis.demo.tkrun.accion.AccionDTO;
import giis.demo.tkrun.logger.AuditService;
import giis.demo.ui.acciones.misacciones.PanelAccion;
import giis.demo.ui.acciones.misacciones.compra.ComprarAccionesAjenas;

public class CompraAccionesController extends AbstractController {

	private ComprarAccionesAjenas view;
	private int idAccionista;

	private MetodosCompraAcciones cModel = new MetodosCompraAcciones();

	public CompraAccionesController(ComprarAccionesAjenas ventanaCompra, int idAccionista, AuditService audit) {
		super(audit);
		this.view = ventanaCompra;
		this.idAccionista = idAccionista;
		initView();
	}

	private void initView() {
		cargarAccionesDisponibles();
		view.setVisible(true);
	}

	private void cargarAccionesDisponibles() {
		List<AccionDTO> lista = cModel.listarAccionesEnVentaDeOtros(idAccionista);

		view.getPanelLista().removeAll();

		// Si la lista está vacía, simplemente no agregamos nada al panel
		for (AccionDTO a : lista) {
			PanelAccion panel = new PanelAccion(a.getIdAccion(), a.getNombreVendedor(), "Comprar", e -> {
				int confirm = JOptionPane.showConfirmDialog(
						null, "¿Desea confirmar la compra de la acción " + a.getIdAccion()
								+ " del accionista " + a.getNombreVendedor() + "?",
						"Confirmar compra", JOptionPane.YES_NO_OPTION);

				if (confirm == JOptionPane.YES_OPTION) {
					cModel.comprarAccion(a.getIdAccion(), idAccionista, a.getIdAccionista());
					audit.log("INFO", "Comprada acción con id=" + a.getIdAccion());
					JOptionPane.showMessageDialog(null, "Compra realizada con éxito.", "Éxito",
							JOptionPane.INFORMATION_MESSAGE);
					cargarAccionesDisponibles(); // Refrescar
				}
			}, new Color(173, 216, 230));
			panel.setPreferredSize(new Dimension(550, 60));
			view.getPanelLista().add(panel);
		}

		view.getPanelLista().revalidate();
		view.getPanelLista().repaint();
	}
}
