package giis.demo.tkrun.teammanagement.entrenamiento;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import giis.demo.tkrun.AbstractController;
import giis.demo.tkrun.instalacion.InstalacionDAO;
import giis.demo.tkrun.instalacion.InstalacionDTO;
import giis.demo.tkrun.logger.AuditService;
import giis.demo.tkrun.login.Session;
import giis.demo.tkrun.teammanagement.MetodosEquipo;
import giis.demo.ui.teammanagment.entrenamiento.TrainingCreationView;

public class TrainingCreationController extends AbstractController {

	private TrainingCreationView trainingCreationView;
	private MetodosEquipo equipoDAO;
	private InstalacionDAO instalacionDAO;
	private EntrenamientoDAO entrenamientoDAO;
	
	public TrainingCreationController(TrainingCreationView trainingCreationView, AuditService audit) {
		super(audit);
		this.trainingCreationView = trainingCreationView;
		this.equipoDAO = new MetodosEquipo();
		this.instalacionDAO = new InstalacionDAO();
		this.entrenamientoDAO = new EntrenamientoDAO();
		loadSelectors();
		addActionListeners();
		trainingCreationView.setVisible(true);
	}
	
	private void loadSelectors() {
		String roleName = Session.get().getRoleName();
		List<EquipoSimpleDTO> equipos;
		if (roleName.equals("Entrenador")) {
			equipos = this.equipoDAO.getTeamNamesByTrainer(Session.get().getUserId());
		} else {
			equipos = this.equipoDAO.getTeamNames();			
		}
		for (EquipoSimpleDTO equipo : equipos) {
			this.trainingCreationView.getTeamSelector().addItem(equipo);			
		}
		
		List<InstalacionDTO> instalaciones = this.instalacionDAO.getInstalaciones();
		for (InstalacionDTO instalacion : instalaciones) {
			this.trainingCreationView.getInstallationSelector().addItem(instalacion);			
		}		
	}
	
	private void addActionListeners() {

		addLoggedAction(trainingCreationView.getAcceptButton(), "Guardar Nuevo Entrenamiento",
				() -> {
					Integer id = validateAndAddTraining();

					if (id != null) {
						audit.log("INFO", "Entrenamiento añadido con id=" + id);
					}
				});

	}
	
	private Integer validateAndAddTraining() {
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
		Object selectedTeam = this.trainingCreationView.getTeamSelector().getSelectedItem();
		Object selectedFacility = this.trainingCreationView.getInstallationSelector().getSelectedItem();
		Date date = this.trainingCreationView.getDate().getDate();
		Date startTimeDate = (Date) this.trainingCreationView.getStartTime().getValue();
		Date endTimeDate = (Date) this.trainingCreationView.getEndTime().getValue();
		LocalTime start = startTimeDate.toInstant()
			    .atZone(ZoneId.systemDefault())
			    .toLocalTime();
		LocalTime end = endTimeDate.toInstant()
			    .atZone(ZoneId.systemDefault())
			    .toLocalTime();
		
		if (selectedTeam == null || selectedFacility == null || date == null) {
			JOptionPane.showMessageDialog(this.trainingCreationView, "Todos los campos deben estar llenos");
			return null;
		}
				
		if (start.isAfter(end)) {
			JOptionPane.showMessageDialog(this.trainingCreationView, "El horario de inicio debe ser anterior al horario de fin del entrenamiento");
			return null;
		}
		
		String startTimeString = timeFormat.format(startTimeDate);
		String endTimeString = timeFormat.format(endTimeDate);
		
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		
		InstalacionDTO facility = (InstalacionDTO) selectedFacility;
		EquipoSimpleDTO equipo = (EquipoSimpleDTO) selectedTeam;
		String overlapReason = entrenamientoDAO.hasOverlap(sqlDate.toString(), startTimeString, endTimeString, facility.getId(), equipo.getId_equipo());
		
		if (overlapReason != null) {
			JOptionPane.showMessageDialog(this.trainingCreationView, overlapReason);
			return null;
		}
		
		int id_entreno = entrenamientoDAO.createTraining(equipo.getId_equipo(), sqlDate.toString(), startTimeString,
				endTimeString, facility.getId());
		trainingCreationView.dispose();
		JOptionPane.showMessageDialog(null, "El entrenamiento fue creado correctamente");
		return id_entreno;
	}
}
