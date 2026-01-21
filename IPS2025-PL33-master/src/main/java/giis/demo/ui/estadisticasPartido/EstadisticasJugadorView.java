
package giis.demo.ui.estadisticasPartido;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import giis.demo.tkrun.estadisticasPartido.EventoPartidoDAO.StatsTemporadaDTO;
import giis.demo.tkrun.teammanagement.PersonDTO;

public class EstadisticasJugadorView extends JFrame {
	private static final long serialVersionUID = 1L;

	// Controles
	private JList<PersonDTO> listaJugadores;
	private JButton btnAgregarGrafico;
	private JComboBox<String> cbTemporada;

	// Panel único de gráfico
	private JPanel panelGraficoUnico;

	public EstadisticasJugadorView() {
		setTitle("Comparativa (1 temporada) - Rojas / Amarillas / Goles / Lesiones");
		setSize(1100, 700);
		setLayout(new BorderLayout());

		// Barra superior: selector de temporada + botón agregar
		JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
		top.add(new JLabel("Temporada:"));
		cbTemporada = new JComboBox<>();
		top.add(cbTemporada);

		top.add(new JLabel("   Selecciona jugadores (múltiple) y pulsa 'Agregar al gráfico':"));
		btnAgregarGrafico = new JButton("Agregar al gráfico");
		top.add(btnAgregarGrafico);
		add(top, BorderLayout.NORTH);

		// Lista de jugadores a la izquierda
		listaJugadores = new JList<>();
		listaJugadores.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		add(new JScrollPane(listaJugadores), BorderLayout.WEST);

		// Panel gráfico en el centro
		panelGraficoUnico = new JPanel(new BorderLayout());
		add(panelGraficoUnico, BorderLayout.CENTER);

		setVisible(true);
		setLocationRelativeTo(null);
	}

	// Getters para el controlador
	public JList<PersonDTO> getListaJugadores() {
		return listaJugadores;
	}

	public JButton getBtnAgregarGrafico() {
		return btnAgregarGrafico;
	}

	public JComboBox<String> getCbTemporada() {
		return cbTemporada;
	}

	// Cargar temporadas en el combo
	public void setTemporadas(List<String> temporadas) {
		cbTemporada.removeAllItems();
		for (String t : temporadas)
			cbTemporada.addItem(t);
		if (cbTemporada.getItemCount() > 0)
			cbTemporada.setSelectedIndex(0);
	}

	/**
	 * Pinta un gráfico único de líneas para UNA temporada. Eje X: categorías fijas
	 * ["Rojas", "Amarillas", "Goles", "Lesiones"] Eje Y: cantidad Cada jugador es
	 * una serie con 4 puntos (uno por categoría) en la temporada seleccionada.
	 */
	public void pintarGraficoTemporadaUnica(String temporada, List<String> nombres,
			List<List<StatsTemporadaDTO>> statsPorJugador) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		for (int i = 0; i < nombres.size(); i++) {
			String jugador = nombres.get(i);
			List<StatsTemporadaDTO> stats = statsPorJugador.get(i);

			// Buscar stats de esa temporada; si no hay, usar ceros
			int rojas = 0, amarillas = 0, goles = 0, lesiones = 0;
			for (StatsTemporadaDTO s : stats) {
				if (temporada != null && temporada.equals(s.temporada)) {
					rojas = s.rojas;
					amarillas = s.amarillas;
					goles = s.goles;
					lesiones = s.lesiones;
					break;
				}
			}

			// Añadir 4 puntos (categorías fijas en el eje X)
			dataset.addValue(rojas, jugador, "Rojas");
			dataset.addValue(amarillas, jugador, "Amarillas");
			dataset.addValue(goles, jugador, "Goles");
			dataset.addValue(lesiones, jugador, "Lesiones");
		}

		// Gráfico de líneas (categorías en X: rojas/amarillas/goles/lesiones)
		JFreeChart chart = ChartFactory.createLineChart("Comparativa en " + (temporada == null ? "" : temporada),
				"Categoría", "Cantidad", dataset);

		// Mejoras visuales

		CategoryPlot plot = (CategoryPlot) chart.getPlot();
		NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
		yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

		LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
		renderer.setDefaultShapesVisible(true);
		renderer.setDefaultStroke(new BasicStroke(4.0f));
		// Colores alternos para facilitar la lectura (cíclicos si hay muchos jugadores)
		Color[] palette = new Color[] { new Color(33, 150, 243), new Color(255, 193, 7), new Color(244, 67, 54),
				new Color(76, 175, 80), new Color(156, 39, 176), new Color(0, 188, 212), new Color(121, 85, 72),
				new Color(63, 81, 181), new Color(0, 150, 136) };
		for (int i = 0; i < nombres.size(); i++) {
			renderer.setSeriesPaint(i, palette[i % palette.length]);
		}

		// Panel
		panelGraficoUnico.removeAll();
		panelGraficoUnico.add(new ChartPanel(chart), BorderLayout.CENTER);
		panelGraficoUnico.revalidate();
		panelGraficoUnico.repaint();
	}

}
