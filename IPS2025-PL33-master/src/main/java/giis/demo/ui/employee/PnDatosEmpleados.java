package giis.demo.ui.employee;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.toedter.calendar.JDateChooser;

import giis.demo.tkrun.employee.EmployeeViewDTO;

public class PnDatosEmpleados extends JPanel {


	private static final long serialVersionUID = 1L;
	private JPanel pnDatosEmpleado;
	private JPanel pnOpcionesEmpleado;
	private JLabel lbIdEmpleado;
	private JLabel lbNombreEmpleado;
	private JLabel lbApellido;
	private JLabel lbDni;
	private JLabel lbPosicion;
	private JTextField txIdEmpleado;
	private JTextField txNombre;
	private JTextField txApellido;
	private JTextField txDni;
	private JTextField txPosicion;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JButton btnCrearUsuario;
	
	private EmployeeViewDTO employee;
	private JSeparator separator;
	private JLabel lbTelefono;
	private JTextField txTelefono;
	private JLabel lbFechaNacimiento;
	private JDateChooser txFechaNacimiento;
	private JLabel lbSalario;
	private JTextField txSalario;
	private JLabel lbUsername;
	private JTextField txUsername;


	public PnDatosEmpleados(EmployeeViewDTO employee) {
		this.employee = employee;
		setBorder(null);
		setBackground(new Color(255, 255, 255));
		setLayout(new BorderLayout(10, 10));
		add(getPnDatosEmpleado(), BorderLayout.CENTER);
		add(getPnOpcionesEmpleado(), BorderLayout.SOUTH);
		add(getSeparator(), BorderLayout.NORTH);

	}

	private JPanel getPnDatosEmpleado() {
		if (pnDatosEmpleado == null) {
			pnDatosEmpleado = new JPanel();
			pnDatosEmpleado.setBackground(Color.WHITE);
			pnDatosEmpleado.setLayout(new GridLayout(0, 2, 5, 5));
			
			addIdEmployeeField();
			addNameEmployeeField();
			addSurnameEmployeeField();
			addDniEmployeeField();
			addTelefonoEmployeeField();
			addFechaNacimientoEmployeeField();
			addPosicionEmployeeField();
			addSalarioAnualEmployeeField();
			if(this.employee.getId_rol() != null) {
				addUsernameField();
			}
			
		}
		return pnDatosEmpleado;
	}
	


	private void addSalarioAnualEmployeeField() {
		JLabel lb = getLbSalario();
		JTextField tx = getTxSalario();
		tx.setText(String.format("%f", this.employee.salario_anual).replace(',', '.'));
		getPnDatosEmpleado().add(lb);
		getPnDatosEmpleado().add(tx);
	}
	
	private void addUsernameField() {
		JLabel lb = getLbUsername();
		JTextField tx = getTxUsername();
		tx.setText( this.employee.getUsername());
		getPnDatosEmpleado().add(lb);
		getPnDatosEmpleado().add(tx);
	}

	private void addFechaNacimientoEmployeeField() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse(this.employee.fecha_nacimiento);
		} catch (ParseException e) {
			System.out.println(e);
		}
		JLabel lb = getLbFechaNacimiento();
		JDateChooser tx = getTxFechaNacimiento();
		tx.setDate(date);
		//tx.setDate(this.employee.fecha_nacimiento);
		getPnDatosEmpleado().add(lb);
		getPnDatosEmpleado().add(tx);
	}

	private void addTelefonoEmployeeField() {
		JLabel lbTel = getLbTelefono();
		JTextField txTel = getTxTelefono();
		txTel.setText(String.format("%d", this.employee.telefono));
		getPnDatosEmpleado().add(lbTel);
		getPnDatosEmpleado().add(txTel);
	}

	private void addIdEmployeeField() {
		JLabel lbId = getLbIdEmpleado();
		JTextField txId = getTxIdEmpleado();
		txId.setText(String.format("%d", this.employee.id_empleado));
		getPnDatosEmpleado().add(lbId);
		getPnDatosEmpleado().add(txId);
	}
	
	private void addNameEmployeeField() {
		JLabel lbName = getLbNombreEmpleado();
		JTextField txName = getTxNombre();
		txName.setText(String.format("%s", this.employee.nombre));
		getPnDatosEmpleado().add(lbName);
		getPnDatosEmpleado().add(txName);
	}
	
	private void addSurnameEmployeeField() {
		JLabel lbSurname = getLbApellido();
		JTextField txSurname = getTxApellido();
		txSurname.setText(String.format("%s", this.employee.apellido));
		getPnDatosEmpleado().add(lbSurname);
		getPnDatosEmpleado().add(txSurname);
	}
	
	private void addDniEmployeeField() {
		JLabel lbDni = getLbDni();
		JTextField txDni = getTxDni();
		txDni.setText(String.format("%s", this.employee.dni));
		getPnDatosEmpleado().add(lbDni);
		getPnDatosEmpleado().add(txDni);
	}
	
	private void addPosicionEmployeeField() {
		JLabel lbPos = getLbPosicion();
		JTextField txPos = getTxPosicion();
		txPos.setText(String.format("%s", this.employee.nombre_posicion));

		getPnDatosEmpleado().add(lbPos);
		getPnDatosEmpleado().add(txPos);
	}
	
	private JPanel getPnOpcionesEmpleado() {
		if (pnOpcionesEmpleado == null) {
			pnOpcionesEmpleado = new JPanel();
			pnOpcionesEmpleado.setBackground(Color.WHITE);
			pnOpcionesEmpleado.add(getBtnModificar());
			pnOpcionesEmpleado.add(getBtnEliminar());
			if (this.employee.getId_rol() != null && this.employee.getUsername() == null) {
				pnOpcionesEmpleado.add(getBtnCrearUsuario());
			}
		}
		return pnOpcionesEmpleado;
	}
	private JLabel getLbIdEmpleado() {
		if (lbIdEmpleado == null) {
			lbIdEmpleado = new JLabel("Id del empleado:");
			lbIdEmpleado.setFont(new Font("Calibri", Font.PLAIN, 15));
			lbIdEmpleado.setBackground(Color.WHITE);
		}
		return lbIdEmpleado;
	}
	private JLabel getLbNombreEmpleado() {
		if (lbNombreEmpleado == null) {
			lbNombreEmpleado = new JLabel("Nombre:");
			lbNombreEmpleado.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return lbNombreEmpleado;
	}
	private JLabel getLbApellido() {
		if (lbApellido == null) {
			lbApellido = new JLabel("Apellido:");
			lbApellido.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return lbApellido;
	}
	private JLabel getLbDni() {
		if (lbDni == null) {
			lbDni = new JLabel("DNI:");
			lbDni.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return lbDni;
	}
	private JLabel getLbPosicion() {
		if (lbPosicion == null) {
			lbPosicion = new JLabel("Posición:");
			lbPosicion.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return lbPosicion;
	}
	private JTextField getTxIdEmpleado() {
		if (txIdEmpleado == null) {
			txIdEmpleado = new JTextField();
			txIdEmpleado.setFocusable(false);
			txIdEmpleado.setFocusTraversalPolicyProvider(true);
			txIdEmpleado.setHorizontalAlignment(SwingConstants.CENTER);
			txIdEmpleado.setFont(new Font("Calibri", Font.PLAIN, 15));
			txIdEmpleado.setBackground(Color.LIGHT_GRAY);
			txIdEmpleado.setEditable(false);
			txIdEmpleado.setColumns(10);
		}
		return txIdEmpleado;
	}
	public JTextField getTxNombre() {
		if (txNombre == null) {
			txNombre = new JTextField();
			txNombre.setFocusable(false);
			txNombre.setFocusTraversalPolicyProvider(true);
			txNombre.setFont(new Font("Calibri", Font.PLAIN, 15));
			txNombre.setBackground(Color.LIGHT_GRAY);
			txNombre.setEditable(false);
			txNombre.setColumns(10);
		}
		return txNombre;
	}
	public JTextField getTxApellido() {
		if (txApellido == null) {
			txApellido = new JTextField();
			txApellido.setFocusable(false);
			txApellido.setFocusTraversalPolicyProvider(true);
			txApellido.setFont(new Font("Calibri", Font.PLAIN, 15));
			txApellido.setBackground(Color.LIGHT_GRAY);
			txApellido.setEditable(false);
			txApellido.setColumns(10);
		}
		return txApellido;
	}
	public JTextField getTxDni() {
		if (txDni == null) {
			txDni = new JTextField();
			txDni.setFocusable(false);
			txDni.setFocusTraversalPolicyProvider(true);
			txDni.setFont(new Font("Calibri", Font.PLAIN, 15));
			txDni.setBackground(Color.LIGHT_GRAY);
			txDni.setEditable(false);
			txDni.setColumns(10);
		}
		return txDni;
	}
	public JTextField getTxPosicion() {
		if (txPosicion == null) {
			txPosicion = new JTextField();
			txPosicion.setFocusable(false);
			txPosicion.setFocusTraversalPolicyProvider(true);
			txPosicion.setFont(new Font("Calibri", Font.PLAIN, 15));
			txPosicion.setBackground(Color.LIGHT_GRAY);
			txPosicion.setColumns(10);
		}
		return txPosicion;
	}
	public JButton getBtnModificar() {
		if (btnModificar == null) {
			btnModificar = new JButton("Modificar Empleado");
			btnModificar.setBackground(new Color(176, 196, 222));
			btnModificar.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return btnModificar;
	}
	public JButton getBtnEliminar() {
		if (btnEliminar == null) {
			btnEliminar = new JButton("Eliminar Empleado");
			btnEliminar.setBackground(new Color(176, 196, 222));
			btnEliminar.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return btnEliminar;
	}
	public JButton getBtnCrearUsuario() {
		if (btnCrearUsuario == null) {
			btnCrearUsuario = new JButton("Crear usuario");
			btnCrearUsuario.setBackground(new Color(176, 196, 222));
			btnCrearUsuario.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return btnCrearUsuario;
	}
	private JSeparator getSeparator() {
		if (separator == null) {
			separator = new JSeparator();
		}
		return separator;
	}
	private JLabel getLbTelefono() {
		if (lbTelefono == null) {
			lbTelefono = new JLabel("Teléfono:");
			lbTelefono.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return lbTelefono;
	}
	public JTextField getTxTelefono() {
		if (txTelefono == null) {
			txTelefono = new JTextField();
			txTelefono.setFocusable(false);
			txTelefono.setEditable(false);
			txTelefono.setBackground(Color.LIGHT_GRAY);
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
	public JDateChooser  getTxFechaNacimiento() {
		if (txFechaNacimiento == null) {
			txFechaNacimiento = new JDateChooser ();
			txFechaNacimiento.setFocusable(false);
			txFechaNacimiento.setBackground(Color.LIGHT_GRAY);
			txFechaNacimiento.setDateFormatString("yyyy-MM-dd");
			txFechaNacimiento.setFont(new Font("Calibri", Font.PLAIN, 15));
			txFechaNacimiento.setEnabled(false);
			txFechaNacimiento.getDateEditor().setEnabled(false);
		}
		return txFechaNacimiento;
	}

	private JLabel getLbSalario() {
		if (lbSalario == null) {
			lbSalario = new JLabel("Salario anual bruto:");
			lbSalario.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return lbSalario;
	}
	public JTextField getTxSalario() {
		if (txSalario == null) {
			txSalario = new JTextField();
			txSalario.setFocusable(false);
			txSalario.setBackground(Color.LIGHT_GRAY);
			txSalario.setFont(new Font("Calibri", Font.PLAIN, 15));
			txSalario.setColumns(10);
		}
		return txSalario;
	}
	
	private JLabel getLbUsername() {
		if (lbUsername == null) {
			lbUsername = new JLabel("Usuario:");
			lbUsername.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return lbUsername;
	}
	public JTextField getTxUsername() {
		if (txUsername == null) {
			txUsername = new JTextField();
			txUsername.setFocusable(false);
			txUsername.setBackground(Color.LIGHT_GRAY);
			txUsername.setFont(new Font("Calibri", Font.PLAIN, 15));
			txUsername.setColumns(10);
		}
		return txUsername;
	}
}
