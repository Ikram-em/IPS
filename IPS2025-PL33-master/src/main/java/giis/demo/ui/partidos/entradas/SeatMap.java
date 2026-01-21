package giis.demo.ui.partidos.entradas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SeatMap extends JPanel {
	private static final long serialVersionUID = 1L;
	private JPanel gridPn;
	
	public SeatMap() {
		JPanel gridContainer = new JPanel(new BorderLayout(0, 10));
		gridContainer.setPreferredSize(new Dimension(360, 220));
		gridContainer.add(getGrid(), BorderLayout.CENTER);
		gridContainer.add(getDefinitionsPanel(), BorderLayout.SOUTH);
		add(gridContainer);
	}

	public JPanel getGrid() {
		if (gridPn == null) {
			gridPn = new JPanel();
			gridPn.setLayout(new GridLayout(10, 15, 5, 5));
		}
		return gridPn;
	}
	
	public JPanel getDefinitionsPanel() {
		JPanel definitionsPn = new JPanel();
		definitionsPn.setLayout(new BorderLayout());
		//definitionsPn.setPreferredSize(new Dimension(200, 10));
		definitionsPn.add(getDefinitions(), BorderLayout.CENTER);
		return definitionsPn;
	}
	
	public JPanel getDefinitions() {
		//JPanel definitions = new JPanel();
		//definitions.setLayout(new BoxLayout(definitions, BoxLayout.X_AXIS));
		JPanel definitions = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0)); 
		definitions.add(getColorDefinition("Asiento libre", Color.BLUE));
		definitions.add(getColorDefinition("Asiento ocupado", Color.GRAY));
		return definitions;
	}
	
	public JPanel getColorDefinition(String text, Color color) {
		JPanel definition = new JPanel();
		definition.setLayout(new BoxLayout(definition, BoxLayout.X_AXIS));
		JPanel colorPn = new JPanel();
		colorPn.setBackground(color);
		colorPn.setPreferredSize(new Dimension(10, 10));
		colorPn.setMaximumSize(new Dimension(10, 10));
		definition.add(colorPn);
		definition.add(Box.createRigidArea(new Dimension(5, 0)));
		definition.add(new JLabel(text));
		return definition;
	}
}
