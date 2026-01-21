package giis.demo.ui.partidos;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class PartidosView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JScrollPane sPnMatchesList;
	private JPanel pnMatchesList;

	public PartidosView() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 775, 443);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());
		contentPane.add(getSPnMatchesList());
	}
	
	private JScrollPane getSPnMatchesList() {
		if (sPnMatchesList == null) {
			sPnMatchesList = new JScrollPane(getPnMatchesList());
			sPnMatchesList.setBorder(new TitledBorder(null, "Productos", TitledBorder.LEADING, TitledBorder.TOP, null, Color.DARK_GRAY));
			sPnMatchesList.setBackground(Color.WHITE);
			sPnMatchesList.getVerticalScrollBar().setUnitIncrement(16);
			sPnMatchesList.setViewportView(getPnMatchesList());
		}
		return sPnMatchesList;
	}
	
	public JPanel getPnMatchesList() {
		if (pnMatchesList == null) {
			pnMatchesList = new JPanel();
			pnMatchesList.setBackground(Color.WHITE);
			pnMatchesList.setLayout(new BoxLayout(pnMatchesList, BoxLayout.Y_AXIS));
		}
		return pnMatchesList;
	}

}
