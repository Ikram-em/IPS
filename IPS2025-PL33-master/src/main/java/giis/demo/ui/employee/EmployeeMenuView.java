package giis.demo.ui.employee;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class EmployeeMenuView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel pnTitulo;
	private JPanel pnOpciones;
	private JPanel pnSalir;
	private JLabel lbTituloGestorEmpleados;
	private JButton btnConsultarEmpleados;
	private JButton btnAddEmpleado;
	private JButton btnAddHorarios;
	private JButton btnSalir;
	private JButton btnTareasJardineria;

	public EmployeeMenuView() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 556, 401);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPnTitulo(), BorderLayout.NORTH);
		contentPane.add(getPnOpciones(), BorderLayout.CENTER);
		contentPane.add(getPnSalir(), BorderLayout.SOUTH);

	}

	private JPanel getPnTitulo() {
		if (pnTitulo == null) {
			pnTitulo = new JPanel();
			pnTitulo.setBackground(Color.WHITE);
			pnTitulo.add(getLbTituloGestorEmpleados());
		}
		return pnTitulo;
	}
	private JPanel getPnOpciones() {
		if (pnOpciones == null) {
			pnOpciones = new JPanel();
			pnOpciones.setBackground(Color.WHITE);
			pnOpciones.setLayout(new GridLayout(4, 1, 0, 0));
			pnOpciones.add(getBtnAddEmpleado());
			pnOpciones.add(getBtnConsultarEmpleados());
			pnOpciones.add(getBtnAddHorarios());
			pnOpciones.add(getBtnTareasJardineria());
		}
		return pnOpciones;
	}
	private JPanel getPnSalir() {
		if (pnSalir == null) {
			pnSalir = new JPanel();
			pnSalir.setBackground(Color.WHITE);
			pnSalir.add(getBtnSalir());
		}
		return pnSalir;
	}
	private JLabel getLbTituloGestorEmpleados() {
		if (lbTituloGestorEmpleados == null) {
			lbTituloGestorEmpleados = new JLabel("Gestión de Empleados:");
			lbTituloGestorEmpleados.setFont(new Font("Calibri", Font.BOLD, 25));
			lbTituloGestorEmpleados.setBackground(Color.WHITE);
		}
		return lbTituloGestorEmpleados;
	}
	public JButton getBtnConsultarEmpleados() {
		if (btnConsultarEmpleados == null) {
			btnConsultarEmpleados = new JButton("Consultar o modificar Empleados");
			btnConsultarEmpleados.setBackground(Color.WHITE);
			btnConsultarEmpleados.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return btnConsultarEmpleados;
	}
	public JButton getBtnAddEmpleado() {
		if (btnAddEmpleado == null) {
			btnAddEmpleado = new JButton("Añadir un nuevo Empleado");
			btnAddEmpleado.setBackground(Color.WHITE);
			btnAddEmpleado.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return btnAddEmpleado;
	}

	public JButton getBtnAddHorarios() {
		if (btnAddHorarios == null) {
			btnAddHorarios = new JButton("Consultar/Añadir/Modificar horarios de empleados NO deportivos");
			btnAddHorarios.setBackground(Color.WHITE);
			btnAddHorarios.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return btnAddHorarios;
	}
	
	private JButton getBtnSalir() {
		if (btnSalir == null) {
			btnSalir = new JButton("Salir");
			btnSalir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnSalir.setForeground(new Color(255, 255, 255));
			btnSalir.setBackground(new Color(128, 0, 0));
			btnSalir.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return btnSalir;
	}

	public JButton getBtnTareasJardineria() {
		if (btnTareasJardineria == null) {
			btnTareasJardineria = new JButton("Añadir tareas de jardinería");
			btnTareasJardineria.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return btnTareasJardineria;
	}
}
