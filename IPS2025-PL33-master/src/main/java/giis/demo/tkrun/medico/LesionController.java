package giis.demo.tkrun.medico;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import giis.demo.tkrun.AbstractController;
import giis.demo.tkrun.logger.AuditService;
import giis.demo.tkrun.partidos.Partido;
import giis.demo.tkrun.partidos.PartidoDAO;
import giis.demo.tkrun.teammanagement.entrenamiento.EntrenamientoDAO;
import giis.demo.tkrun.teammanagement.entrenamiento.EntrenamientoDTO;
import giis.demo.ui.medico.HistorialLesionView;
import giis.demo.ui.medico.LesionView;

public class LesionController extends AbstractController {

	private LesionView view;
	private LesionDTO lesion;
	private InjuredPlayerViewDTO jugador;
	private EntrenamientoDAO eModel = new EntrenamientoDAO();
	private PartidoDAO pModel = new PartidoDAO();
	private LesionesModel model = new LesionesModel();

	public LesionController(LesionView lesionView, InjuredPlayerViewDTO jugador, LesionDTO lesion, AuditService audit) {
		super(audit);
		this.view = lesionView;
		this.lesion = lesion;
		this.jugador = jugador;
		initView();
	}

	private void initView() {
		view.setVisible(true);
		cargarDatosLesion();
		addListeners();
	}
	
	private void cargarDatosLesion() {
		view.getLbTitulo().setText("Editar lesión: " + jugador.nombre + " " + jugador.apellido);
		String texto = "";

	    if (lesion.getId_entreno() != null) {

	        EntrenamientoDTO entrenamiento = eModel.getEntrenamiento(lesion.getId_entreno());
	        texto = (entrenamiento != null)
	                ? entrenamiento.toString()
	                : "Entrenamiento no encontrado";

	    } else {

	        Partido partido = pModel.getPartido(lesion.getId_partido());
	        texto = (partido != null)
	                ? partido.toString()
	                : "Partido no encontrado";
	    }

	    view.getTxEntrenamientoPartido().setText(texto);
		view.getTextArea().setText(lesion.getDiagnostico());
		view.getCbPrioridad().setSelectedItem(lesion.getPrioridad());

		// DESHABILITAR BOTÓN SI YA ESTÁ DADA DE ALTA
		if (lesion.getAlta_medica() == 1) {
			view.getBtnAlta().setEnabled(false);
			view.getBtnAlta().setText("Lesión ya dada de alta");
			view.getBtnActualizar().setEnabled(false);
		}
	}

	private void addListeners() {
		view.getBtnAlta().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (lesion.getAlta_medica() == 1) {
					JOptionPane.showMessageDialog(null, "Esta lesión ya está dada de alta.");
					return;
				}

				int opcion = JOptionPane.showConfirmDialog(null, "¿Seguro que quiere dar de alta esta lesión?");
				if (opcion == JOptionPane.YES_OPTION) {
					model.darDeAlta(lesion.getId_lesion());
					// Registrar en SesionLesion
					model.addSesionLesion(lesion.getId_lesion(), "DAR_ALTA", lesion.getPrioridad(),
							lesion.getDiagnostico(), 1);
					audit.log("BOTON_CLICK", "Dada de alta lesión con id=" + lesion.getId_lesion());
					JOptionPane.showMessageDialog(null, "Lesión dada de alta correctamente");
					view.getBtnAlta().setEnabled(false);
					view.getBtnActualizar().setEnabled(false);
					view.dispose();
				}

			}
		});

		view.getBtnActualizar().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String nuevoDiagnostico = view.getTextArea().getText();
				String nuevaPrioridad = (String) view.getCbPrioridad().getSelectedItem();
				int altaMedica = lesion.getAlta_medica();

				int opcion = JOptionPane.showConfirmDialog(null, "¿Seguro que quiere actualizar esta lesión?",
						"Confirmar actualización", JOptionPane.YES_NO_OPTION);

				if (opcion == JOptionPane.YES_OPTION) {
					model.actualizarLesion(lesion.getId_lesion(), nuevaPrioridad, nuevoDiagnostico);
					model.addSesionLesion(lesion.getId_lesion(), "ACTUALIZAR", nuevaPrioridad, nuevoDiagnostico,
							altaMedica);

					audit.log("BOTON_CLICK", "Actualizada lesión con id=" + lesion.getId_lesion());
					JOptionPane.showMessageDialog(null, "Lesión actualizada correctamente");
					view.dispose();
				}


			}
		});

		addLoggedAction(view.getBtnHistorial(), "Acceso al historial de la lesión",
				() -> new HistorialLesionController(new HistorialLesionView(), lesion.getId_lesion(), audit));


	}

}
