package giis.demo.tkrun.medico;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import giis.demo.tkrun.AbstractController;
import giis.demo.tkrun.employee.EmployeeViewDTO;
import giis.demo.tkrun.logger.AuditService;
import giis.demo.tkrun.partidos.Partido;
import giis.demo.tkrun.partidos.PartidoDAO;
import giis.demo.tkrun.teammanagement.EquipoViewDTO;
import giis.demo.tkrun.teammanagement.entrenamiento.EntrenamientoDAO;
import giis.demo.tkrun.teammanagement.entrenamiento.EntrenamientoDTO;
import giis.demo.ui.medico.AddLesionView;

public class AddLesionController extends AbstractController {

	private AddLesionView view;
	private EmployeeViewDTO jugador;
	private LesionesModel model = new LesionesModel();
	private PartidoDAO pModel = new PartidoDAO();
	private EntrenamientoDAO eModel = new EntrenamientoDAO();

	public AddLesionController(AddLesionView addLesionView, AuditService audit) {
		super(audit);
		this.view = addLesionView;
		initView();
	}

	private void initView() {
		view.setVisible(true);
		cargarEquiposCb();
		cargarJugadoresCb();
		addListeners();
		addRadioListeners();
	}

	private void cargarEquiposCb() {
		List<EquipoViewDTO> equipos = model.getAllEquipos();
		JComboBox<EquipoViewDTO> combo = view.getCbSeleccionarEquipo();
		combo.removeAllItems();
		for (EquipoViewDTO e : equipos) {
			combo.addItem(e);
		}
	}

	private void cargarJugadoresCb() {
		List<EmployeeViewDTO> jugadores = model.getAllJugadores();
		JComboBox<EmployeeViewDTO> combo = view.getCbSeleccionarJugador();
		combo.removeAllItems();
		for (EmployeeViewDTO j : jugadores) {
			combo.addItem(j);
		}
	}

	private void addListeners() {
		view.getCbSeleccionarEquipo().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (view.getCbSeleccionarEquipo().getSelectedItem() == null)
					return;
				EquipoViewDTO equipo = (EquipoViewDTO) view.getCbSeleccionarEquipo().getSelectedItem();
				filtrarJugadores(equipo);
			}

		});

		view.getCbSeleccionarJugador().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Si no hay nada seleccionado, no hago nada
				if (view.getCbSeleccionarJugador().getSelectedItem() == null)
					return;
				EmployeeViewDTO jugador = (EmployeeViewDTO) view.getCbSeleccionarJugador().getSelectedItem();
				asignarJugador(jugador);
			}
		});
		
		view.getBtnAdd().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				guardarLesion();
			}
		});
	}

	private void addRadioListeners() {
		ActionListener radioListener = e -> {
			view.getCbPartidoEntreno().removeAllItems(); // limpiar items del combo

			if (view.getRdBtPartido().isSelected()) {
				// Lista de partidos de ejemplo (puedes cambiar por tus objetos reales)
				List<Partido> partidos = pModel.obtenerTodos();
				for (Partido p : partidos) {
					view.getCbPartidoEntreno().addItem(p);
				}
			} else if (view.getRdBtEntrenamiento().isSelected()) {
				// Lista de entrenamientos de ejemplo
				List<EntrenamientoDTO> entrenos = eModel.getTodosEntrenamientos();
				for (EntrenamientoDTO t : entrenos) {
					view.getCbPartidoEntreno().addItem(t);
				}
			}
		};

		view.getRdBtPartido().addActionListener(radioListener);
		view.getRdBtEntrenamiento().addActionListener(radioListener);
	}

	private void filtrarJugadores(EquipoViewDTO equipo) {
		List<EmployeeViewDTO> jugadores = model.getJugadoresEquipo(equipo.getId_equipo());
		JComboBox<EmployeeViewDTO> combo = view.getCbSeleccionarJugador();
		combo.removeAllItems();
		for (EmployeeViewDTO j : jugadores) {
			combo.addItem(j);
		}
	}

	private void asignarJugador(EmployeeViewDTO e) {
		this.jugador = e;
	}
	
	private void guardarLesion() {
		// Datos
		this.jugador = (EmployeeViewDTO) view.getCbSeleccionarJugador().getSelectedItem();
		String prioridad = (String) view.getCbPrioridad().getSelectedItem();
		String diagnostico = view.getTextArea().getText();
		EntrenamientoDTO entreno = null;
		Partido partido = null;
		if (view.getRdBtEntrenamiento().isSelected()) {
			entreno = (EntrenamientoDTO) view.getCbPartidoEntreno().getSelectedItem();
		}
		else {
			partido = (Partido) view.getCbPartidoEntreno().getSelectedItem();
		}
		
		if (entreno != null || partido != null) {
			Integer idLesion = model.addLesion(jugador.getId_empleado(), entreno, partido, prioridad, diagnostico);
			audit.log("BOTON_CLICK", "Insertada nueva lesión con id=" + idLesion + " para el jugador con id="
					+ jugador.getId_empleado());
			model.addSesionLesion(idLesion, "CREAR", prioridad, diagnostico, 0);
			JOptionPane.showMessageDialog(null, "Se ha añadido correctamente");
			view.dispose();
		}
		else {
			JOptionPane.showMessageDialog(null, "Debe seleccionar correctamente todos los datos");
		}
	}

}
