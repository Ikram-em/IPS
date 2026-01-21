package giis.demo.tkrun;

import java.awt.Window;

import javax.swing.JOptionPane;

import giis.demo.tkrun.abonos.MenuAbonosController;
import giis.demo.tkrun.accion.AccionMainMenuController;
import giis.demo.tkrun.employee.EmployeeMenuController;
import giis.demo.tkrun.estadisticas.EstadisticasMenuController;
import giis.demo.tkrun.estadisticasPartido.EstadisticasJugadorController;
import giis.demo.tkrun.estadisticasPartido.EstadisticasPartidoController;
import giis.demo.tkrun.ingresos.HistorialIngresosController;
import giis.demo.tkrun.interviewSlot.InterviewSlotMenuController;
import giis.demo.tkrun.logger.AuditService;
import giis.demo.tkrun.logger.view.LoggerViewController;
import giis.demo.tkrun.login.Session;
import giis.demo.tkrun.medico.PortalMedicoController;
import giis.demo.tkrun.noticias.MenuNoticiasController;
import giis.demo.tkrun.partidos.MenuPartidosController;
import giis.demo.tkrun.reserva.ReservaController;
import giis.demo.tkrun.teammanagement.TeamManagementController;
import giis.demo.tkrun.teammanagement.entrenamiento.TrainingCreationController;
import giis.demo.tkrun.tienda.MenuTiendaController;
import giis.demo.tkrun.tienda.stock.StockController;
import giis.demo.ui.MainMenuView;
import giis.demo.ui.StartingView;
import giis.demo.ui.abonos.MenuAbonosView;
import giis.demo.ui.acciones.AccionesMainMenuView;
import giis.demo.ui.employee.EmployeeMenuView;
import giis.demo.ui.estadisticas.EstadisticasMenuView;
import giis.demo.ui.estadisticasPartido.EstadisticasJugadorView;
import giis.demo.ui.estadisticasPartido.EstadisticasPartidoView;
import giis.demo.ui.ingresos.HistorialIngresosView;
import giis.demo.ui.interviewSlot.InterviewSlotMenu;
import giis.demo.ui.logger.LoggerView;
import giis.demo.ui.medico.PortalMedicoView;
import giis.demo.ui.noticias.MenuNoticiasView;
import giis.demo.ui.partidos.MenuPartidosView;
import giis.demo.ui.reserva.ReservaView;
import giis.demo.ui.teammanagment.TeamManagementView;
import giis.demo.ui.teammanagment.entrenamiento.TrainingCreationView;
import giis.demo.ui.tienda.MenuTiendaView;
import giis.demo.ui.tienda.stock.StockView;

public class MainMenuController extends AbstractController {
	
	private MainMenuView mainMenuView;
	
	public MainMenuController(MainMenuView mainMenu, AuditService audit) {
		super(audit);
		this.mainMenuView = mainMenu;
		addButtons();
		addActionListeners();
		this.mainMenuView.setVisible(true);
	}
	
	private void addButtons() {
		String roleName = Session.get().getRoleName();
		
		if (roleName.equals("Gerente")) {
			mainMenuView.getContentPane().add(mainMenuView.getBtnEmpleados());
			mainMenuView.getContentPane().add(mainMenuView.getBtnEquipos());
			mainMenuView.getContentPane().add(mainMenuView.getBtnReservas());
			mainMenuView.getContentPane().add(mainMenuView.getBtnTienda());
			mainMenuView.getContentPane().add(mainMenuView.getBtnPartidos());
			mainMenuView.getContentPane().add(mainMenuView.getBtnAbonos());
			mainMenuView.getContentPane().add(mainMenuView.getBtnIngresos());
			mainMenuView.getContentPane().add(mainMenuView.getBtnNoticiasMenu());
			mainMenuView.getContentPane().add(mainMenuView.getBtnAcciones());
			mainMenuView.getContentPane().add(mainMenuView.getBtnEstadisticas());
			mainMenuView.getContentPane().add(mainMenuView.getBtnStock());
			mainMenuView.getContentPane().add(mainMenuView.getBtnVerLog());
			
		} else if (roleName.equals("PersonalDeVentas")) {
			mainMenuView.getContentPane().add(mainMenuView.getBtnTienda());
			mainMenuView.getContentPane().add(mainMenuView.getBtnAbonos());
			mainMenuView.getContentPane().add(mainMenuView.getBtnStock());
		} else if (roleName.equals("PersonalDeReservas")) {
			mainMenuView.getContentPane().add(mainMenuView.getBtnReservas());
		} else if (roleName.equals("Entrenador")) {
			mainMenuView.getContentPane().add(mainMenuView.getBtnFranjas());
			mainMenuView.getContentPane().add(mainMenuView.getBtnEntrenamientos());
			mainMenuView.getContentPane().add(mainMenuView.getBtnEstadisticasPartido());
			mainMenuView.getContentPane().add(mainMenuView.getBtnEstadisticasJugador());
		} else if (roleName.equals("Comunicacion")) {
			mainMenuView.getContentPane().add(mainMenuView.getBtnGestionNoticias());
		} else if (roleName.equals("GestorDeAcciones")) {
			mainMenuView.getContentPane().add(mainMenuView.getBtnAcciones());
		} else if (roleName.equals("Medico")) {
			mainMenuView.getContentPane().add(mainMenuView.getBtnPortalMedico());
		}		
		mainMenuView.getContentPane().add(mainMenuView.getSpacing());
		mainMenuView.getContentPane().add(mainMenuView.getBtnLogout());
		mainMenuView.pack();
		mainMenuView.repaint();
		mainMenuView.setLocationRelativeTo(null);
	}
	
	private void addActionListeners() {
		// Helper: añade ActionListener con logging
		
		// Para acciones con ventana

		addLoggedAction(mainMenuView.getBtnEquipos(), "Gestionar Equipos",
				() -> new TeamManagementController(new TeamManagementView(), audit));

		addLoggedAction(mainMenuView.getBtnTienda(), "Acceso a Tienda",
				() -> new MenuTiendaController(new MenuTiendaView(), audit));

		addLoggedAction(mainMenuView.getBtnEmpleados(), "Gestionar Empleados",
				() -> new EmployeeMenuController(new EmployeeMenuView(), audit));

		addLoggedAction(mainMenuView.getBtnIngresos(), "Consultar Historial de Ingresos", () -> {
	        HistorialIngresosView view = new HistorialIngresosView();
			HistorialIngresosController controller = new HistorialIngresosController(view, audit);
			controller.run();
	    });

		addLoggedAction(mainMenuView.getBtnPartidos(), "Menu Partidos",
				() -> new MenuPartidosController(new MenuPartidosView(), audit));

		addLoggedAction(mainMenuView.getBtnReservas(), "Reserva Instalaciones",
				() -> new ReservaController(new ReservaView(), audit));

		addLoggedAction(mainMenuView.getBtnAcciones(), "Portal Acciones",
				() -> new AccionMainMenuController(new AccionesMainMenuView(), audit));

		addLoggedAction(mainMenuView.getBtnEstadisticas(), "Estadísticas del Club",
				() -> new EstadisticasMenuController(new EstadisticasMenuView(), audit));

		addLoggedAction(mainMenuView.getBtnNoticiasMenu(), "Menú de Noticias",
				() -> new MenuNoticiasController(new MenuNoticiasView(), audit));

		addLoggedAction(mainMenuView.getBtnEntrenamientos(), "Añadir entrenamiento",
				() -> new TrainingCreationController(new TrainingCreationView(), audit));

		addLoggedAction(mainMenuView.getBtnStock(), "Comprobar Stock de productos",
				() -> new StockController(new StockView(), audit));

		addLoggedAction(mainMenuView.getBtnAbonos(), "Menu Abonos",
				() -> new MenuAbonosController(new MenuAbonosView(), audit));

		// addLoggedAction(mainMenuView.getBtnFranjas(), "Gestionar Franjas", () -> new
		// InterviewSlotMenu()

		// );

		addLoggedAction(mainMenuView.getBtnFranjas(), "Gestionar Franjas",
				() -> new InterviewSlotMenuController(new InterviewSlotMenu(), audit));
		
		addLoggedAction(mainMenuView.getBtnPortalMedico(), "Portal Médico",
				() -> new PortalMedicoController(new PortalMedicoView(), audit));
		
		addLoggedAction(mainMenuView.getBtnVerLog(), "Consultar log",
				() -> new LoggerViewController(new LoggerView(), audit));
		
		addLoggedAction(mainMenuView.getBtnEstadisticasPartido(), "Estadisticas Partido",
				() -> new EstadisticasPartidoController(new EstadisticasPartidoView(), audit));
		
		addLoggedAction(mainMenuView.getBtnEstadisticasJugador(), "Estadisticas Jugador",
				() -> new EstadisticasJugadorController(new EstadisticasJugadorView(), audit));
		
		
		// Logout especial
		mainMenuView.getBtnLogout().addActionListener(e -> logout());

	}

	public MainMenuView getMainMenuView() {
		return mainMenuView;
	}

	public void setMainMenuView(MainMenuView mainMenuView) {
		this.mainMenuView = mainMenuView;
	}

	private void logout() {
		int result = JOptionPane.showConfirmDialog(
		        null,
		        "¿Estás seguro de que quieres cerrar sesión?",
		        "Confirmar cierre de sesión",
		        JOptionPane.OK_CANCEL_OPTION
		);

		if (result == JOptionPane.OK_OPTION) {

			// log
			audit.endSession();

			Session.get().logout();
			for (Window window : Window.getWindows()) {
			    window.dispose();
			}

			new StartingView().setVisible(true);
		}
	}
	
	


}