package giis.demo.tkrun.events.schedule;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import giis.demo.tkrun.employee.EmployeeViewDTO;
import giis.demo.tkrun.employee.schedule.GeneralScheduleController;
import giis.demo.tkrun.logger.AuditService;
import giis.demo.ui.employee.schedule.ScheduleMenuView;

public class ProcessSpecificScheduleButton implements ActionListener {

	private EmployeeViewDTO employee;
	private ScheduleMenuView scheduleView;
	private AuditService audit;
	
	public ProcessSpecificScheduleButton(ScheduleMenuView scheduleView, EmployeeViewDTO employee, AuditService audit) {
		this.employee = employee;
		this.scheduleView = scheduleView;
		this.audit = audit;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		GeneralScheduleController scheduleController = new GeneralScheduleController(scheduleView, employee, audit);
		scheduleController.showSpecificSchedule();
	}

}
