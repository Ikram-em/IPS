package giis.demo.ui.partidos.entradas;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import giis.demo.tkrun.partidos.PartidoDTO;

public class EntradasView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel sPnMatchSelector;
	private JComboBox<giis.demo.tkrun.partidos.PartidoDTO> matchSelector;
	private JButton btnComprarEntradas;

	public EntradasView() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(430, 260, 500, 200);
		contentPane = new JPanel(new BorderLayout(10, 10));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		getContentPane().add(getSPnMatchSelector(), BorderLayout.CENTER);
		
		JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		southPanel.add(getBtnComprarEntradas());
		getContentPane().add(southPanel, BorderLayout.SOUTH);
	}
	
	private JPanel getSPnMatchSelector() {
		if (sPnMatchSelector == null) {
			sPnMatchSelector = new JPanel();
			sPnMatchSelector.setLayout(new BoxLayout(sPnMatchSelector, BoxLayout.Y_AXIS));
			
			JLabel selectionLabel = createLabel("Seleccionar partido", Font.BOLD, 16);
			selectionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
	        getMatchSelector().setAlignmentX(Component.LEFT_ALIGNMENT);
			sPnMatchSelector.add(selectionLabel);
			sPnMatchSelector.add(getMatchSelector());
		}
		return sPnMatchSelector;
	}

	public JComboBox<PartidoDTO> getMatchSelector() {
		if (matchSelector == null) {
			matchSelector = new JComboBox<PartidoDTO>();
			matchSelector.setPreferredSize(new Dimension(300, 50));
			matchSelector.setMaximumSize(new Dimension(800, 50));
		}
		return matchSelector;
	}
	
	public JButton getBtnComprarEntradas() {
		if (btnComprarEntradas == null) {
			btnComprarEntradas = new JButton("Comprar entradas");
			btnComprarEntradas.setPreferredSize(new Dimension(150, 30));
		}
		return btnComprarEntradas;
	}
	
	private JLabel createLabel(String text, int style, int size) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif", style, size));
        label.setBorder(BorderFactory.createEmptyBorder(8,0,8,0));
        return label;
    }
}
