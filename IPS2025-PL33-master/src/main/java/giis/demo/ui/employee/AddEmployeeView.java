package giis.demo.ui.employee;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import giis.demo.tkrun.employee.PositionDTO;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import java.awt.Dimension;

public class AddEmployeeView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel pnPrincipal;
	private JPanel pnTituloAddEmpleado;
	private JPanel pnContenidoPrincipal;
	private JPanel pnGuardarSalir;
	private JLabel lbTituloAddEmpleado;
	private JButton btnGuardar;
	private JButton btnSalir;
	private JPanel pnDatos;
	private JPanel pnBlanco2;
	private JPanel pnBlanco1;
	private JPanel pnLbNombre;
	private JPanel pnTxNombre;
	private JPanel pnLbApellido;
	private JPanel pnTxApellido;
	private JPanel pnLbDni;
	private JPanel pnTxDni;
	private JPanel pnLbTelefono;
	private JPanel pnTxTelefono;
	private JPanel pnLbFechaNacimiento;
	private JPanel pnDtFechaNacimiento;
	private JPanel pnTxTipoEmpleado;
	private JPanel pnCbTipoEmpleado;
	private JPanel pnLbPosicionEmpleado;
	private JPanel pnCbPosicionLaboral;
	private JPanel pnLbSalarioAnual;
	private JPanel pnTxSalarioAnual;
	private JPanel panel_17;
	private JPanel panel_19;
	private JLabel lbNombre;
	private JTextField txNombre;
	private JLabel lbApellido;
	private JTextField TxApellido;
	private JLabel lbDni;
	private JTextField txDni;
	private JLabel lbTelefono;
	private JTextField txTelefono;
	private JLabel lbFechaNacimiento;
	private JDateChooser dtFechaNacimiento;
	private JLabel lbTipoEmpleado;
	private JComboBox<String> cbTipoEmpleado;
	private JLabel lblNewLabel;
	private JComboBox<PositionDTO> cbPosicionEmpleado;
	private JLabel lbSalarioAnual;
	private JTextField txSalarioAnual;
	private JPanel pnChDni;
	private JPanel pnDisponeDni;
	private JLabel lbDisponeDni;
	private JCheckBox cbDisponeDni;

	public AddEmployeeView() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 715, 451);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPnPrincipal(), BorderLayout.CENTER);

	}

	private JPanel getPnPrincipal() {
		if (pnPrincipal == null) {
			pnPrincipal = new JPanel();
			pnPrincipal.setBackground(Color.WHITE);
			pnPrincipal.setLayout(new BorderLayout(0, 0));
			pnPrincipal.add(getPnTituloAddEmpleado(), BorderLayout.NORTH);
			pnPrincipal.add(getPnContenidoPrincipal(), BorderLayout.CENTER);
			pnPrincipal.add(getPnGuardarSalir(), BorderLayout.SOUTH);
		}
		return pnPrincipal;
	}
	private JPanel getPnTituloAddEmpleado() {
		if (pnTituloAddEmpleado == null) {
			pnTituloAddEmpleado = new JPanel();
			pnTituloAddEmpleado.setBackground(Color.WHITE);
			pnTituloAddEmpleado.add(getLbTituloAddEmpleado());
		}
		return pnTituloAddEmpleado;
	}
	private JPanel getPnContenidoPrincipal() {
		if (pnContenidoPrincipal == null) {
			pnContenidoPrincipal = new JPanel();
			pnContenidoPrincipal.setBackground(Color.WHITE);
			pnContenidoPrincipal.setLayout(new BorderLayout(0, 0));
			pnContenidoPrincipal.add(getPnDatos(), BorderLayout.CENTER);
			pnContenidoPrincipal.add(getPnBlanco2(), BorderLayout.NORTH);
			pnContenidoPrincipal.add(getPnBlanco1(), BorderLayout.SOUTH);
		}
		return pnContenidoPrincipal;
	}
	private JPanel getPnGuardarSalir() {
		if (pnGuardarSalir == null) {
			pnGuardarSalir = new JPanel();
			pnGuardarSalir.setBackground(Color.WHITE);
			pnGuardarSalir.add(getBtnGuardar());
			pnGuardarSalir.add(getBtnSalir());
		}
		return pnGuardarSalir;
	}
	private JLabel getLbTituloAddEmpleado() {
		if (lbTituloAddEmpleado == null) {
			lbTituloAddEmpleado = new JLabel("Añadir nuevo empleado");
			lbTituloAddEmpleado.setFont(new Font("Calibri", Font.BOLD, 25));
		}
		return lbTituloAddEmpleado;
	}
	public JButton getBtnGuardar() {
		if (btnGuardar == null) {
			btnGuardar = new JButton("Guardar Empleado");
			btnGuardar.setBackground(new Color(176, 196, 222));
			btnGuardar.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return btnGuardar;
	}
	public JButton getBtnSalir() {
		if (btnSalir == null) {
			btnSalir = new JButton("Salir");
			btnSalir.setForeground(Color.WHITE);
			btnSalir.setBackground(new Color(128, 0, 0));
			btnSalir.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return btnSalir;
	}
	private JPanel getPnDatos() {
		if (pnDatos == null) {
			pnDatos = new JPanel();
			pnDatos.setBackground(Color.WHITE);
			pnDatos.setLayout(new GridLayout(10, 2, 1, 0));
			pnDatos.add(getPnLbNombre());
			pnDatos.add(getPnTxNombre());
			pnDatos.add(getPnLbApellido());
			pnDatos.add(getPnTxApellido());
			pnDatos.add(getPnDisponeDni());
			pnDatos.add(getPnChDni());
			pnDatos.add(getPnLbDni());
			pnDatos.add(getPnTxDni());
			pnDatos.add(getPnLbTelefono());
			pnDatos.add(getPnTxTelefono());
			pnDatos.add(getPnLbFechaNacimiento());
			pnDatos.add(getPnDtFechaNacimiento());
			pnDatos.add(getPnTxTipoEmpleado());
			pnDatos.add(getPnCbTipoEmpleado());
			pnDatos.add(getPnLbPosicionEmpleado());
			pnDatos.add(getPnCbPosicionLaboral());
			pnDatos.add(getPnLbSalarioAnual());
			pnDatos.add(getPnTxSalarioAnual());
			pnDatos.add(getPanel_17());
			pnDatos.add(getPanel_19());
		}
		return pnDatos;
	}
	private JPanel getPnBlanco2() {
		if (pnBlanco2 == null) {
			pnBlanco2 = new JPanel();
		}
		return pnBlanco2;
	}
	private JPanel getPnBlanco1() {
		if (pnBlanco1 == null) {
			pnBlanco1 = new JPanel();
		}
		return pnBlanco1;
	}
	private JPanel getPnLbNombre() {
		if (pnLbNombre == null) {
			pnLbNombre = new JPanel();
			pnLbNombre.setBackground(Color.WHITE);
			pnLbNombre.add(getLbNombre());
		}
		return pnLbNombre;
	}
	private JPanel getPnTxNombre() {
		if (pnTxNombre == null) {
			pnTxNombre = new JPanel();
			pnTxNombre.setBackground(Color.WHITE);
			pnTxNombre.add(getTxNombre());
		}
		return pnTxNombre;
	}
	private JPanel getPnLbApellido() {
		if (pnLbApellido == null) {
			pnLbApellido = new JPanel();
			pnLbApellido.setBackground(Color.WHITE);
			pnLbApellido.add(getLbApellido());
		}
		return pnLbApellido;
	}
	private JPanel getPnTxApellido() {
		if (pnTxApellido == null) {
			pnTxApellido = new JPanel();
			pnTxApellido.setBackground(Color.WHITE);
			pnTxApellido.add(getTxApellido());
		}
		return pnTxApellido;
	}
	private JPanel getPnLbDni() {
		if (pnLbDni == null) {
			pnLbDni = new JPanel();
			pnLbDni.setBackground(Color.WHITE);
			pnLbDni.add(getLbDni());
		}
		return pnLbDni;
	}
	private JPanel getPnTxDni() {
		if (pnTxDni == null) {
			pnTxDni = new JPanel();
			pnTxDni.setBackground(Color.WHITE);
			pnTxDni.add(getTxDni());
		}
		return pnTxDni;
	}
	private JPanel getPnLbTelefono() {
		if (pnLbTelefono == null) {
			pnLbTelefono = new JPanel();
			pnLbTelefono.setBackground(Color.WHITE);
			pnLbTelefono.add(getLbTelefono());
		}
		return pnLbTelefono;
	}
	private JPanel getPnTxTelefono() {
		if (pnTxTelefono == null) {
			pnTxTelefono = new JPanel();
			pnTxTelefono.setBackground(Color.WHITE);
			pnTxTelefono.add(getTxTelefono());
		}
		return pnTxTelefono;
	}
	private JPanel getPnLbFechaNacimiento() {
		if (pnLbFechaNacimiento == null) {
			pnLbFechaNacimiento = new JPanel();
			pnLbFechaNacimiento.setBackground(Color.WHITE);
			pnLbFechaNacimiento.add(getLbFechaNacimiento());
		}
		return pnLbFechaNacimiento;
	}
	private JPanel getPnDtFechaNacimiento() {
		if (pnDtFechaNacimiento == null) {
			pnDtFechaNacimiento = new JPanel();
			pnDtFechaNacimiento.setBackground(Color.WHITE);
			pnDtFechaNacimiento.add(getFechaNacimiento());
		}
		return pnDtFechaNacimiento;
	}
	
	public JDateChooser getFechaNacimiento() {
		if (dtFechaNacimiento == null) {
			dtFechaNacimiento = new JDateChooser();
			dtFechaNacimiento.setPreferredSize(new Dimension(100, 25));
		}
		return dtFechaNacimiento;
	}
	
	private JPanel getPnTxTipoEmpleado() {
		if (pnTxTipoEmpleado == null) {
			pnTxTipoEmpleado = new JPanel();
			pnTxTipoEmpleado.setBackground(Color.WHITE);
			pnTxTipoEmpleado.add(getLbTipoEmpleado());
		}
		return pnTxTipoEmpleado;
	}
	private JPanel getPnCbTipoEmpleado() {
		if (pnCbTipoEmpleado == null) {
			pnCbTipoEmpleado = new JPanel();
			pnCbTipoEmpleado.setBackground(Color.WHITE);
			pnCbTipoEmpleado.add(getCbTipoEmpleado());
		}
		return pnCbTipoEmpleado;
	}
	private JPanel getPnLbPosicionEmpleado() {
		if (pnLbPosicionEmpleado == null) {
			pnLbPosicionEmpleado = new JPanel();
			pnLbPosicionEmpleado.setBackground(Color.WHITE);
			pnLbPosicionEmpleado.add(getLblNewLabel());
		}
		return pnLbPosicionEmpleado;
	}
	private JPanel getPnCbPosicionLaboral() {
		if (pnCbPosicionLaboral == null) {
			pnCbPosicionLaboral = new JPanel();
			pnCbPosicionLaboral.setBackground(Color.WHITE);
			pnCbPosicionLaboral.add(getCbPosicionEmpleado());
		}
		return pnCbPosicionLaboral;
	}
	private JPanel getPnLbSalarioAnual() {
		if (pnLbSalarioAnual == null) {
			pnLbSalarioAnual = new JPanel();
			pnLbSalarioAnual.setBackground(Color.WHITE);
			pnLbSalarioAnual.add(getLbSalarioAnual());
		}
		return pnLbSalarioAnual;
	}
	private JPanel getPnTxSalarioAnual() {
		if (pnTxSalarioAnual == null) {
			pnTxSalarioAnual = new JPanel();
			pnTxSalarioAnual.setBackground(Color.WHITE);
			pnTxSalarioAnual.add(getTxSalarioAnual());
		}
		return pnTxSalarioAnual;
	}
	private JPanel getPanel_17() {
		if (panel_17 == null) {
			panel_17 = new JPanel();
			panel_17.setBackground(Color.WHITE);
		}
		return panel_17;
	}
	private JPanel getPanel_19() {
		if (panel_19 == null) {
			panel_19 = new JPanel();
			panel_19.setBackground(Color.WHITE);
		}
		return panel_19;
	}
	
	private JLabel getLbNombre() {
		if (lbNombre == null) {
			lbNombre = new JLabel("Nombre:");
			lbNombre.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return lbNombre;
	}
	public JTextField getTxNombre() {
		if (txNombre == null) {
			txNombre = new JTextField();
			txNombre.setBackground(Color.WHITE);
			txNombre.setFont(new Font("Calibri", Font.PLAIN, 15));
			txNombre.setColumns(10);
		}
		return txNombre;
	}
	private JLabel getLbApellido() {
		if (lbApellido == null) {
			lbApellido = new JLabel("Apellido:");
			lbApellido.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return lbApellido;
	}
	public JTextField getTxApellido() {
		if (TxApellido == null) {
			TxApellido = new JTextField();
			TxApellido.setBackground(Color.WHITE);
			TxApellido.setFont(new Font("Calibri", Font.PLAIN, 15));
			TxApellido.setColumns(10);
		}
		return TxApellido;
	}
	private JLabel getLbDni() {
		if (lbDni == null) {
			lbDni = new JLabel("DNI:");
			lbDni.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return lbDni;
	}
	public JTextField getTxDni() {
		if (txDni == null) {
			txDni = new JTextField();
			txDni.setEditable(false);
			txDni.setEnabled(false);
			txDni.setFont(new Font("Calibri", Font.PLAIN, 15));
			txDni.setBackground(Color.LIGHT_GRAY);
			txDni.setColumns(10);
		}
		return txDni;
	}
	private JLabel getLbTelefono() {
		if (lbTelefono == null) {
			lbTelefono = new JLabel("Teléfono de contacto:");
			lbTelefono.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return lbTelefono;
	}
	public JTextField getTxTelefono() {
		if (txTelefono == null) {
			txTelefono = new JTextField();
			txTelefono.setBackground(Color.WHITE);
			txTelefono.setFont(new Font("Calibri", Font.PLAIN, 15));
			txTelefono.setColumns(10);
		}
		return txTelefono;
	}
	private JLabel getLbFechaNacimiento() {
		if (lbFechaNacimiento == null) {
			lbFechaNacimiento = new JLabel("Fecha de nacimiento:");
			lbFechaNacimiento.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return lbFechaNacimiento;
	}
	private JLabel getLbTipoEmpleado() {
		if (lbTipoEmpleado == null) {
			lbTipoEmpleado = new JLabel("Tipo de empleado:");
			lbTipoEmpleado.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return lbTipoEmpleado;
	}
	public JComboBox<String> getCbTipoEmpleado() {
		if (cbTipoEmpleado == null) {
			cbTipoEmpleado = new JComboBox<String>();
			cbTipoEmpleado.setBackground(Color.WHITE);
			cbTipoEmpleado.setModel(new DefaultComboBoxModel<String>(new String[] {"Empleado Deportivo", "Empleado No Deportivo"}));
			cbTipoEmpleado.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return cbTipoEmpleado;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Posición laboral:");
			lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return lblNewLabel;
	}
	public JComboBox<PositionDTO> getCbPosicionEmpleado() {
		if (cbPosicionEmpleado == null) {
			cbPosicionEmpleado = new JComboBox<PositionDTO>();
			cbPosicionEmpleado.setEnabled(false);
			cbPosicionEmpleado.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return cbPosicionEmpleado;
	}
	private JLabel getLbSalarioAnual() {
		if (lbSalarioAnual == null) {
			lbSalarioAnual = new JLabel("Salario anual bruto:");
			lbSalarioAnual.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return lbSalarioAnual;
	}
	public JTextField getTxSalarioAnual() {
		if (txSalarioAnual == null) {
			txSalarioAnual = new JTextField();
			txSalarioAnual.setFont(new Font("Calibri", Font.PLAIN, 15));
			txSalarioAnual.setColumns(10);
		}
		return txSalarioAnual;
	}
	private JPanel getPnChDni() {
		if (pnChDni == null) {
			pnChDni = new JPanel();
			pnChDni.setBackground(new Color(255, 255, 255));
			pnChDni.add(getCbDisponeDni());
		}
		return pnChDni;
	}
	private JPanel getPnDisponeDni() {
		if (pnDisponeDni == null) {
			pnDisponeDni = new JPanel();
			pnDisponeDni.setBackground(new Color(255, 255, 255));
			pnDisponeDni.add(getLbDisponeDni());
		}
		return pnDisponeDni;
	}
	private JLabel getLbDisponeDni() {
		if (lbDisponeDni == null) {
			lbDisponeDni = new JLabel("¿Dispone de DNI?");
			lbDisponeDni.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return lbDisponeDni;
	}
	public JCheckBox getCbDisponeDni() {
		if (cbDisponeDni == null) {
			cbDisponeDni = new JCheckBox("Marcar si dispone de DNI");
			cbDisponeDni.setFont(new Font("Calibri", Font.PLAIN, 15));
			cbDisponeDni.setBackground(Color.WHITE);
		}
		return cbDisponeDni;
	}
}
