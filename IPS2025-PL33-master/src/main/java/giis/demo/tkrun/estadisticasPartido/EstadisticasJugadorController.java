
package giis.demo.tkrun.estadisticasPartido;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import giis.demo.tkrun.AbstractController;
import giis.demo.tkrun.estadisticasPartido.EventoPartidoDAO.StatsTemporadaDTO;
import giis.demo.tkrun.logger.AuditService;
import giis.demo.tkrun.teammanagement.MetodosJugador;
import giis.demo.tkrun.teammanagement.PersonDTO;
import giis.demo.ui.estadisticasPartido.EstadisticasJugadorView;

public class EstadisticasJugadorController extends AbstractController {
	private EstadisticasJugadorView view;
	private EventoPartidoDAO eventoDAO = new EventoPartidoDAO();
	private MetodosJugador metodosJugador = new MetodosJugador();

	// Acumulador de jugadores añadidos al gráfico
	private Set<Integer> jugadoresEnGrafico = new HashSet<>();

	public EstadisticasJugadorController(EstadisticasJugadorView view, AuditService audit) {
		super(audit);
		this.view = view;
		init();
	}

	private void init() {
		cargarJugadores();
		cargarTemporadasUnicas();
		view.getBtnAgregarGrafico().addActionListener(e -> agregarSeleccionYRepintar());
		view.getCbTemporada().addActionListener(e -> repintarConTemporadaActual());
	}

	private void cargarJugadores() {
		List<PersonDTO> jugadores = metodosJugador.getAllPlayersIncludingAssigned();

		// Ordenar alfabéticamente por nombre
		jugadores.sort((j1, j2) -> j1.getNombre().compareToIgnoreCase(j2.getNombre()));

		// Cargar en la lista (JList)
		view.getListaJugadores().setListData(jugadores.toArray(new PersonDTO[0]));
	}

	private void cargarTemporadasUnicas() {
		// Recorremos todos los jugadores y recopilamos temporadas únicas
		Set<String> temporadas = new LinkedHashSet<>();
		int size = view.getListaJugadores().getModel().getSize();
		for (int i = 0; i < size; i++) {
			PersonDTO j = view.getListaJugadores().getModel().getElementAt(i);
			List<StatsTemporadaDTO> stats = eventoDAO.getStatsJugadorPorTemporada(j.getId());
			for (StatsTemporadaDTO s : stats)
				temporadas.add(s.temporada);
		}
		view.setTemporadas(new ArrayList<>(temporadas));
	}

	private void agregarSeleccionYRepintar() {
		List<PersonDTO> seleccionados = view.getListaJugadores().getSelectedValuesList();
		if (seleccionados.isEmpty())
			return;

		// Acumular ids
		for (PersonDTO p : seleccionados)
			jugadoresEnGrafico.add(p.getId());
		repintarConTemporadaActual();
	}

	private void repintarConTemporadaActual() {
		String temporada = (String) view.getCbTemporada().getSelectedItem();
		if (temporada == null)
			return;

		List<String> nombres = new ArrayList<>();
		List<List<StatsTemporadaDTO>> statsPorJugador = new ArrayList<>();

		// Construir datos para todos los acumulados
		int size = view.getListaJugadores().getModel().getSize();
		for (int i = 0; i < size; i++) {
			PersonDTO j = view.getListaJugadores().getModel().getElementAt(i);
			if (jugadoresEnGrafico.contains(j.getId())) {
				nombres.add(j.getNombre());
				statsPorJugador.add(eventoDAO.getStatsJugadorPorTemporada(j.getId()));
			}
		}

		view.pintarGraficoTemporadaUnica(temporada, nombres, statsPorJugador);
	}
}
