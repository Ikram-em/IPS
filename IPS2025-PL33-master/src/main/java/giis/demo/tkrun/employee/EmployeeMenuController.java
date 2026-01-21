package giis.demo.tkrun.employee;

import giis.demo.tkrun.AbstractController;
import giis.demo.tkrun.employee.schedule.WorkScheduleController;
import giis.demo.tkrun.jardineria.JardineriaController;
import giis.demo.tkrun.logger.AuditService;
import giis.demo.ui.employee.AddEmployeeView;
import giis.demo.ui.employee.EmployeeMenuView;
import giis.demo.ui.employee.UpdateDeleteEmployeeView;
import giis.demo.ui.employee.schedule.ScheduleMenuView;
import giis.demo.ui.jardineria.JardineriaMainMenuView;

public class EmployeeMenuController extends AbstractController {
	
	private EmployeeMenuView view;
	
	public EmployeeMenuController(EmployeeMenuView employeeMenu, AuditService audit) {
		super(audit);
		this.view = employeeMenu;
		this.view.setVisible(true);
		addListeners();
	}
	
	private void addListeners() {
		
		addLoggedAction(view.getBtnAddEmpleado(), "Acceso menú añadir empleado",
				() -> new AddEmployeeController(new AddEmployeeView(), audit));
		
		addLoggedAction(view.getBtnConsultarEmpleados(), "Acceso menú consultar empleados",
				() -> new UpdateDeleteEmployeeController(new UpdateDeleteEmployeeView(), audit));
		
		addLoggedAction(view.getBtnAddHorarios(), "Acceso menú horarios",
				() -> new WorkScheduleController(new ScheduleMenuView(), audit));

		addLoggedAction(view.getBtnTareasJardineria(), "Acceso menú tareas de jardinería",
				() -> new JardineriaController(new JardineriaMainMenuView(), audit));

	}
	

}
