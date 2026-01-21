package giis.demo.ui.employee.schedule;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.BorderLayout;

import giis.demo.tkrun.employee.EmployeeViewDTO;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;

public class PnNonSportEmployeeData extends JPanel {

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
	private JButton btnAddHorarioSemanal;
	private JButton btnAddHorarioEspecifico;
	private JButton btnConsultarHorario;
	
	private EmployeeViewDTO employee;
	private JSeparator separator;

	/**
	 * Create the panel.
	 */
	public PnNonSportEmployeeData(EmployeeViewDTO employee) {
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
			addPosicionEmployeeField();
			
		}
		return pnDatosEmpleado;
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
			pnOpcionesEmpleado.add(getBtnAddHorarioSemanal());
			pnOpcionesEmpleado.add(getBtnAddHorarioEspecifico());
			pnOpcionesEmpleado.add(getBtnConsultarHorario());
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
	private JTextField getTxNombre() {
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
	private JTextField getTxApellido() {
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
	private JTextField getTxDni() {
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
	private JTextField getTxPosicion() {
		if (txPosicion == null) {
			txPosicion = new JTextField();
			txPosicion.setFocusable(false);
			txPosicion.setFocusTraversalPolicyProvider(true);
			txPosicion.setFont(new Font("Calibri", Font.PLAIN, 15));
			txPosicion.setBackground(Color.LIGHT_GRAY);
			txPosicion.setEditable(false);
			txPosicion.setColumns(10);
		}
		return txPosicion;
	}
	public JButton getBtnAddHorarioSemanal() {
		if (btnAddHorarioSemanal == null) {
			btnAddHorarioSemanal = new JButton("Añadir un nuevo Horario Semanal");
			btnAddHorarioSemanal.setBackground(new Color(176, 196, 222));
			btnAddHorarioSemanal.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return btnAddHorarioSemanal;
	}
	public JButton getBtnAddHorarioEspecifico() {
		if (btnAddHorarioEspecifico == null) {
			btnAddHorarioEspecifico = new JButton("Añadir un nuevo Horario Específico");
			btnAddHorarioEspecifico.setBackground(new Color(176, 196, 222));
			btnAddHorarioEspecifico.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return btnAddHorarioEspecifico;
	}
	public JButton getBtnConsultarHorario() {
		if (btnConsultarHorario == null) {
			btnConsultarHorario = new JButton("Consultar Horario");
			btnConsultarHorario.setBackground(new Color(176, 196, 222));
			btnConsultarHorario.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return btnConsultarHorario;
	}
	private JSeparator getSeparator() {
		if (separator == null) {
			separator = new JSeparator();
		}
		return separator;
	}
}
