package giis.demo.tkrun.medico;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComboBox;

import giis.demo.tkrun.AbstractController;
import giis.demo.tkrun.employee.EmployeeViewDTO;
import giis.demo.tkrun.logger.AuditService;
import giis.demo.tkrun.teammanagement.EquipoViewDTO;
import giis.demo.ui.medico.AddLesionView;
import giis.demo.ui.medico.LesionView;
import giis.demo.ui.medico.PnJugadorMedico;
import giis.demo.ui.medico.PortalMedicoView;

public class PortalMedicoController extends AbstractController {

	private PortalMedicoView view;
	LesionesModel model = new LesionesModel();

	public PortalMedicoController(PortalMedicoView view, AuditService audit) {
		super(audit);
		this.view = view;
		initView();
	}

	private void initView() {
		view.setVisible(true);
		cargarJugadoresLesionados();
		cargarEquiposCb();
		cargarJugadores();
		addListeners();
	}

	private void cargarJugadoresLesionados() {
		List<InjuredPlayerViewDTO> jugadores = model.getInjuredPlayers();
		if (!jugadores.isEmpty()) {
			for (InjuredPlayerViewDTO j : jugadores) {
				List<LesionDTO> lesiones = model.getLesionesJugador(j.getId_empleado());
				for (LesionDTO l : lesiones) {
					PnJugadorMedico pnJugador = new PnJugadorMedico();
					pnJugador.getLbNombre().setText(j.getNombre() + " " + j.getApellido());
					pnJugador.getLbPrioridad().setText(j.getPrioridad());
					pnJugador.getLbAltaMedica().setText("¿Alta médica?  " + (l.getAlta_medica() == 0 ? "NO" : "SÍ"));
					addActionListenerPn(pnJugador, j, l);
					view.getPnContenedorJugadores().add(pnJugador);
				}

			}
		}
	}

	private void cargarEquiposCb() {
		List<EquipoViewDTO> equipos = model.getAllEquipos();
		JComboBox<EquipoViewDTO> combo = view.getCbSeleccionarEquipo();
		combo.removeAllItems();
		for (EquipoViewDTO e : equipos) {
			combo.addItem(e);
		}
	}

	private void cargarJugadores() {
		List<EmployeeViewDTO> jugadores = model.getAllJugadores();
		JComboBox<EmployeeViewDTO> combo = view.getComboBox();
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
				mostrarJugadoresEquipo(equipo);
			}

		});

		view.getComboBox().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Si no hay nada seleccionado, no hago nada
				if (view.getComboBox().getSelectedItem() == null)
					return;
				EmployeeViewDTO jugador = (EmployeeViewDTO) view.getComboBox().getSelectedItem();
				mostrarJugadorLesionado(jugador);
			}
		});
		view.getBtnQuitarFiltro().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				view.getCbSeleccionarEquipo().setSelectedItem(null);
				view.getComboBox().setSelectedItem(null);
				// cargar equipos y jugadores
				cargarEquiposCb();
				cargarJugadores();

				view.getPnContenedorJugadores().removeAll();
				cargarJugadoresLesionados();

				view.getPnContenedorJugadores().revalidate();
				view.getPnContenedorJugadores().repaint();
			}
		});

		view.getBtnAddLesion().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new AddLesionController(new AddLesionView(), audit);
			}
		});
	}

	private void filtrarJugadores(EquipoViewDTO equipo) {
		List<EmployeeViewDTO> jugadores = model.getJugadoresEquipo(equipo.getId_equipo());
		JComboBox<EmployeeViewDTO> combo = view.getComboBox();
		combo.removeAllItems();
		for (EmployeeViewDTO j : jugadores) {
			combo.addItem(j);
		}
	}

	private void mostrarJugadoresEquipo(EquipoViewDTO equipo) {
		view.getPnContenedorJugadores().removeAll();
		List<InjuredPlayerViewDTO> jugadores = model.getInjuredPlayersFromEquipo(equipo.getId_equipo());
		if (!jugadores.isEmpty()) {
			for (InjuredPlayerViewDTO j : jugadores) {
				List<LesionDTO> lesiones = model.getLesionesJugador(j.getId_empleado());
				for (LesionDTO l : lesiones) {
					PnJugadorMedico pnJugador = new PnJugadorMedico();
					pnJugador.getLbNombre().setText(j.getNombre() + " " + j.getApellido());
					pnJugador.getLbPrioridad().setText(j.getPrioridad());
					pnJugador.getLbAltaMedica().setText("¿Alta médica?  " + (l.getAlta_medica() == 0 ? "NO" : "YES"));
					addActionListenerPn(pnJugador, j, l);
					view.getPnContenedorJugadores().add(pnJugador);
				}

			}
		}
		view.getPnContenedorJugadores().revalidate();
		view.getPnContenedorJugadores().repaint();
	}

	private void mostrarJugadorLesionado(EmployeeViewDTO jugador) {
		view.getPnContenedorJugadores().removeAll();
		List<InjuredPlayerViewDTO> jugadores = model.getInjuredPlayersById(jugador.getId_empleado());
		for (InjuredPlayerViewDTO j : jugadores) {
			List<LesionDTO> lesiones = model.getLesionesJugador(j.id_empleado);
			for (LesionDTO l : lesiones) {
				PnJugadorMedico pnJugador = new PnJugadorMedico();
				pnJugador.getLbNombre().setText(j.getNombre() + " " + j.getApellido());
				pnJugador.getLbPrioridad().setText(j.getPrioridad());
				pnJugador.getLbAltaMedica().setText("¿Alta médica?  " + (l.getAlta_medica() == 0 ? "NO" : "SÍ"));
				addActionListenerPn(pnJugador, j, l);
				view.getPnContenedorJugadores().add(pnJugador);
			}
		}
		view.getPnContenedorJugadores().revalidate();
		view.getPnContenedorJugadores().repaint();
	}

	private void addActionListenerPn(PnJugadorMedico pn, InjuredPlayerViewDTO jugador, LesionDTO lesion) {
		addLoggedAction(pn.getBtnEntrar(), "Acceso lesión con id=" + lesion.getId_lesion(), () -> {
			LesionView lesionView = new LesionView();
			new LesionController(lesionView, jugador, lesion, audit);

			// Añadimos listener para cuando se cierre la ventana
			lesionView.addWindowListener(new java.awt.event.WindowAdapter() {
				@Override
				public void windowClosed(java.awt.event.WindowEvent e) {
					// Limpiamos el contenedor antes de recargar
					view.getPnContenedorJugadores().removeAll();
					// Volvemos a cargar los jugadores lesionados
					cargarJugadoresLesionados();
					// Forzar refresco de la UI
					view.getPnContenedorJugadores().revalidate();
					view.getPnContenedorJugadores().repaint();
				}
			});

			lesionView.setVisible(true);
		});
	}
}
