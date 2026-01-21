package giis.demo.ui.partidos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import giis.demo.tkrun.partidos.PartidoDTO;

public class PnMatchDetails extends JPanel {

	private static final long serialVersionUID = 1L;
	private PartidoDTO partido;
	private JButton btnGestionarPrecios;

	public PnMatchDetails(PartidoDTO partido) {
		this.partido = partido;
		
		setBorder(new LineBorder(Color.GRAY, 1, true));
		setBackground(Color.WHITE);
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(200, 80));
		
		add(createLabel(partido.toString(), Font.BOLD, 14), BorderLayout.WEST);
		add(getBtnGestionarPrecios(), BorderLayout.EAST);
	}
	
	public JButton getBtnGestionarPrecios() {
		if (btnGestionarPrecios == null) {
			btnGestionarPrecios = new JButton("Gestionar precios");
			btnGestionarPrecios.setPreferredSize(new Dimension(150, 30));
			btnGestionarPrecios.setEnabled(!partido.isPriced());
		}
		return btnGestionarPrecios;
	}
	
	private JLabel createLabel(String text, int style, int size) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif", style, size));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        label.setBorder(BorderFactory.createEmptyBorder(8,8,0,0));
        return label;
    }

}
