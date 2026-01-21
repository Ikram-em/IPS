package giis.demo.tkrun.accion.campana;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import giis.demo.tkrun.AbstractController;
import giis.demo.tkrun.logger.AuditService;
import giis.demo.ui.acciones.campana.GestionarCampanaView;
import giis.demo.ui.acciones.campana.GestionarFasesView;

public class GestionarFasesController extends AbstractController {
	
	private GestionarFasesView view;
	private GestionarCampanaView gCampanaView;
	private CampanaModel campanaModel = new CampanaModel();
	private int faseActual;

	public GestionarFasesController(GestionarCampanaView gCampanaView, GestionarFasesView gestionarFasesView,
			AuditService audit) {
		super(audit);
		this.view = gestionarFasesView;
		this.gCampanaView = gCampanaView;
		initView();
	}

	private void initView() {
		view.setVisible(true);
		setFaseActual();
		addActionListeners();
	}

	private void setFaseActual() {

		this.faseActual = campanaModel.getCampana().getFase();
		view.getTxFaseActual().setText(String.format("Fase %d", faseActual));
		if (faseActual == 3) {
			view.getBtnAdelantar().setText("Terminar campaña");
		}
	}

	private void addActionListeners() {

		view.getBtnAdelantar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				adelantarFase();
			}
		});

		view.getBtnCancelar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				view.dispose();
			}
		});

	}

	private void adelantarFase() {
		if (faseActual < 3) {
			// Pedir confirmacion
			int opcion = JOptionPane.showConfirmDialog(view, "¿Está seguro de querer adelantar la fase?", "Confirmar",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			// En caso afirmativo, adelantar la fase y confirmar operacion.
			if (opcion == JOptionPane.YES_OPTION) {
				campanaModel.adelantarFaseCampana();
				setFaseActual();
				JOptionPane.showMessageDialog(view, "La fase se ha adelantado correctamente.", "Éxito",
						JOptionPane.INFORMATION_MESSAGE);
				audit.log("INFO", "Campaña con id=" + campanaModel.getCampana().getId_campana() + " adelantada a fase="
						+ faseActual);
			}
		} else {
			// Pedir confirmacion
			int opcion = JOptionPane.showConfirmDialog(view, "¿Está seguro de que quiere terminar la campaña?",
					"Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			// En caso afirmativo, adelantar la fase y confirmar operacion.
			if (opcion == JOptionPane.YES_OPTION) {
				campanaModel.finalizarCampana();
				view.getBtnAdelantar().setEnabled(false);
				JOptionPane.showMessageDialog(view, "La campaña ha sido finalizada.", "Éxito",
						JOptionPane.INFORMATION_MESSAGE);
				view.dispose();
				gCampanaView.dispose();
				
				audit.log("INFO", "Campaña con id=" + campanaModel.getCampana().getId_campana() + " terminada");
			}
		}
	}
}
