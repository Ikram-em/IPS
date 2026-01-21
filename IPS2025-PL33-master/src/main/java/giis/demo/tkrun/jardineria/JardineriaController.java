package giis.demo.tkrun.jardineria;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import giis.demo.tkrun.AbstractController;
import giis.demo.tkrun.employee.EmployeeViewDTO;
import giis.demo.tkrun.logger.AuditService;
import giis.demo.ui.jardineria.JardineriaMainMenuView;
import giis.demo.ui.jardineria.PlanificarJardineroView;
import giis.demo.ui.jardineria.PnJardineroView;

public class JardineriaController extends AbstractController {

	private JardineriaMainMenuView view;
	JardineriaModel jM = new JardineriaModel();

	public JardineriaController(JardineriaMainMenuView view, AuditService audit) {
		super(audit);
		this.view = view;
		initView();
	}

	private void initView() {
		this.view.setVisible(true);
		cargarJardineros();
		addActionListener();
	}

	private void addActionListener() {
		view.getBtnSalir().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				view.dispose();
			}
		});
	}

	private void cargarJardineros() {
		view.getPnJardineros().removeAll();
		List<EmployeeViewDTO> empleadosJardineria = jM.getJardineros();
		for (EmployeeViewDTO j : empleadosJardineria) {
			PnJardineroView pnJardinero = new PnJardineroView();
			pnJardinero.getLbNombre().setText(j.nombre + " " + j.apellido);
			addActionListenersJardineros(pnJardinero, j);
			view.getPnJardineros().add(pnJardinero);
		}
		view.revalidate();
		view.repaint();
	}
	
	private void addActionListenersJardineros(PnJardineroView pnJ, EmployeeViewDTO jardinero) {

		addLoggedAction(pnJ.getBtnPlanificar(), "Acceso planificación jardinero con id=" + jardinero.id_empleado,
				() -> new PlanificarJardineroController(new PlanificarJardineroView(), jardinero, audit));

	}

}
