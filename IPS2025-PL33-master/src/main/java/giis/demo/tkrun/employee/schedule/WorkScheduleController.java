package giis.demo.tkrun.employee.schedule;

import java.util.ArrayList;
import java.util.List;

import giis.demo.tkrun.AbstractController;
import giis.demo.tkrun.employee.EmployeeModel;
import giis.demo.tkrun.employee.EmployeeViewDTO;
import giis.demo.tkrun.events.schedule.ProcessShowScheduleButton;
import giis.demo.tkrun.events.schedule.ProcessSpecificScheduleButton;
import giis.demo.tkrun.events.schedule.ProcessWeeklyScheduleButton;
import giis.demo.tkrun.logger.AuditService;
import giis.demo.ui.employee.schedule.PnNonSportEmployeeData;
import giis.demo.ui.employee.schedule.ScheduleMenuView;

public class WorkScheduleController extends AbstractController {
	
	private ScheduleMenuView scheduleView;
	private EmployeeModel eModel;
	private List<EmployeeViewDTO> employees = new ArrayList<EmployeeViewDTO>();
	
	
	public WorkScheduleController(ScheduleMenuView scheduleView, AuditService audit) {
		super(audit);
		this.scheduleView = scheduleView;
		initView();
	}
	
	
	private void initView() {
		initModel();
		loadEmployees();
		scheduleView.setVisible(true);
	}
	
	
	private void initModel() {
		eModel = new EmployeeModel();
	}
	
	
	private void loadEmployees() {
		employees = eModel.getNonSportEmployees();
		scheduleView.getPnListaEmpleados().removeAll();
		for (EmployeeViewDTO employee: employees) {
			PnNonSportEmployeeData pnEmployee = new PnNonSportEmployeeData(employee);
			scheduleView.getPnListaEmpleados().add(pnEmployee);
			addButtonsListeners(pnEmployee, employee);
		}
		
		scheduleView.getPnListaEmpleados().revalidate(); // Revalidar panel
		scheduleView.getPnListaEmpleados().repaint(); // Redibujar panel
	}
	
	private void addButtonsListeners(PnNonSportEmployeeData pn, EmployeeViewDTO employee) {

		addLoggedAction(pn.getBtnAddHorarioSemanal(),
				"Acceso menú agregar horario semanal para empleado con id=" + employee.id_empleado,
				() -> new ProcessWeeklyScheduleButton(scheduleView, employee, audit).actionPerformed(null));
		addLoggedAction(pn.getBtnAddHorarioEspecifico(),
				"Acceso menú agregar horario específico para empleado con id=" + employee.id_empleado,
				() -> new ProcessSpecificScheduleButton(scheduleView, employee, audit).actionPerformed(null));
		addLoggedAction(pn.getBtnConsultarHorario(),
				"Acceso al horario completo para empleado con id=" + employee.id_empleado,
				() -> new ProcessShowScheduleButton(scheduleView, employee, audit).actionPerformed(null));
	}

}
