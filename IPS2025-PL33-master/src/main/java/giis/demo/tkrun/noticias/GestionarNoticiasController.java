package giis.demo.tkrun.noticias;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import giis.demo.tkrun.AbstractController;
import giis.demo.tkrun.logger.AuditService;
import giis.demo.ui.noticias.CreacionNoticias;
import giis.demo.ui.noticias.GestionNoticias;
import giis.demo.ui.noticias.PanelNoticia;
import giis.demo.ui.noticias.PanelNoticia.ModoNoticia;

public class GestionarNoticiasController extends AbstractController {

	private GestionNoticias view;
	private MetodosNoticias nModel = new MetodosNoticias();

	public GestionarNoticiasController(GestionNoticias view, AuditService audit) {
		super(audit);
		this.view = view;
		initView();
	}

	private void initView() {
		cargarNoticias();
		view.setVisible(true);
		addListeners();
	}

	private void cargarNoticias() {
		view.getPnListaNoticias().removeAll();

		for (NoticiaDto n : nModel.getNoticias()) {
			PanelNoticia panel = new PanelNoticia(n, ModoNoticia.ADMIN);

			// panel.addListeners(() -> cargarNoticias(), null);

			addLoggedAction(panel.getBtEditar(), "Editada noticia con id=" + n.getId(), () -> {
				new EditarNoticiaController(new CreacionNoticias(), panel.getNoticia(), audit,
						() -> cargarNoticias());

			});


			panel.getBtEliminar().addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					int opcion = JOptionPane.showConfirmDialog(null, "¿Seguro que deseas eliminar esta noticia?",
							"Eliminar", JOptionPane.YES_NO_OPTION);

					if (opcion == JOptionPane.YES_OPTION) {
						new MetodosNoticias().eliminarNoticia(panel.getNoticia().getId());
						audit.log("BOTON_CLICK", "Eliminada noticia con id=" + n.getId());
						cargarNoticias();
					}
				}
			});

			view.getPnListaNoticias().add(panel);
		}


		view.getPnListaNoticias().revalidate();
		view.getPnListaNoticias().repaint();
	}

	private void addListeners() {
		addLoggedAction(view.getBtCrear(), "Crear nueva Noticia",
				() ->  {
					new CrearNoticiaController(new CreacionNoticias(), audit);
					view.dispose();
				});
	}
}
