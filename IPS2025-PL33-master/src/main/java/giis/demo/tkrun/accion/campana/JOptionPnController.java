package giis.demo.tkrun.accion.campana;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import giis.demo.tkrun.AbstractController;
import giis.demo.tkrun.logger.AuditService;
import giis.demo.ui.acciones.campana.CrearCampanaOptionPanel;
import giis.demo.ui.acciones.campana.GestionarCampanaView;

public class JOptionPnController extends AbstractController {
	
	CrearCampanaOptionPanel opPn;
	GestionarCampanaView view;
	CampanaModel campanaModel = new CampanaModel();
	

	public JOptionPnController(CrearCampanaOptionPanel crearCampanaOptionPanelView, GestionarCampanaView view2,
			AuditService audit) {
		super(audit);
		this.opPn = crearCampanaOptionPanelView;
		this.view = view2;
		initView();
	}

	private void initView() {
		opPn.setVisible(true);
		addActionListeners();
	}

	private void addActionListeners() {
		opPn.getOkButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Integer id = campanaModel.setNAccionesCampana((int) opPn.getSpinner().getValue());
				JOptionPane.showMessageDialog(view, "La campaña se ha configurado correctamente.", "Éxito",
						JOptionPane.INFORMATION_MESSAGE);
				opPn.dispose();
				view.dispose();

				if (id != null) {
					audit.log("INFO", "Campaña creada con id=" + id);
				}
			}
		});
		opPn.getCancelButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				opPn.dispose();
			}
		});
	}
	
	

}
