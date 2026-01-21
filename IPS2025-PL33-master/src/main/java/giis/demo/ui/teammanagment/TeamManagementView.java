package giis.demo.ui.teammanagment;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class TeamManagementView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btCrear;
	private JButton btVer;
	private JButton btEntrenamientos;
	private JButton btGestionarFranjas;

	public TeamManagementView() {
		setTitle("Gestion Equipos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 508, 389);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		contentPane.add(getBtCrear());
		contentPane.add(getBtVer());
		contentPane.add(getBtnEntrenamientos());
		contentPane.add(getBtnGestionarFranjas());
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public JButton getBtCrear() {
		if (btCrear == null) {
			btCrear = new JButton("Crear Equipo");
			btCrear.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return btCrear;
	}
	
	public JButton getBtVer() {
		if (btVer == null) {
			btVer = new JButton("Gestionar Equipos Creados");
			btVer.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return btVer;
	}
	
	public JButton getBtnEntrenamientos() {
		if (btEntrenamientos == null) {
			btEntrenamientos = new JButton("Añadir entrenamiento");
			btEntrenamientos.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return btEntrenamientos;
	}
	
	public JButton getBtnGestionarFranjas() {
		if (btGestionarFranjas == null) {
			btGestionarFranjas = new JButton("Gestionar franjas");
			btGestionarFranjas.setFont(new Font("Calibri", Font.PLAIN, 15));			
		}
		return btGestionarFranjas;
	}
}
