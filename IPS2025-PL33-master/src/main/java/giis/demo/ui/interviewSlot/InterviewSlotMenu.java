package giis.demo.ui.interviewSlot;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class InterviewSlotMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btgestionFranjas;
	private JButton btAsignarEntrevista;


	/**
	 * Create the frame.
	 */
	public InterviewSlotMenu() {
		setTitle("Menu de gestion de franjas");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 451, 348);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		contentPane.add(getBtgestionFranjas());
		contentPane.add(getBtAsignarEntrevista());
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public JButton getBtgestionFranjas() {
		if (btgestionFranjas == null) {
			btgestionFranjas = new JButton("Gestionar Franjas");
		}
		return btgestionFranjas;
	}

	public JButton getBtAsignarEntrevista() {
		if (btAsignarEntrevista == null) {
			btAsignarEntrevista = new JButton("Asignar Entrevista");
		}
		return btAsignarEntrevista;
	}
}
