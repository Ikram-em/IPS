package giis.demo.ui.teammanagment;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import giis.demo.tkrun.teammanagement.PersonDTO;

public class PlayerPaneE extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txNombre;
	private JTextField txtDni;
	private JTextField txtTelefono;
	private JTextField txtFecha;
	private JButton btAgregar;
	@SuppressWarnings("unused")
	private int id;
	private ExistingTeamManagement etm;
	private PersonDTO person;
	
	private boolean isAdded = false;

	/**
	 * Create the panel.
	 */
	public PlayerPaneE(ExistingTeamManagement etm,int id,String name,String surname , String dni, String telefono, String fecha) {
		this.etm = etm;
		this.id = id;
		setBackground(Color.WHITE);
		setLayout(new GridLayout(1, 5, 0, 0));
		add(getTxNombre());
		add(getTxtDni());
		add(getTxtTelefono());
		add(getTxtFecha());
		add(getBtAgregar());
		getTxNombre().setText(name + " " + surname);
		getTxtDni().setText(dni);
		getTxtTelefono().setText(telefono);
		getTxtFecha().setText(fecha);

	}
	
	/**
	 * Create the panel.
	 */
	public PlayerPaneE(ExistingTeamManagement etm, PersonDTO p) {
		this.person = p;
		this.etm = etm;
		this.id = p.getId();
		setBackground(Color.WHITE);
		setLayout(new GridLayout(1, 5, 0, 0));
		add(getTxNombre());
		add(getTxtDni());
		add(getTxtTelefono());
		add(getTxtFecha());
		add(getBtAgregar());
		getTxNombre().setText(p.getNombre() + " " + p.getApellido());
		getTxtDni().setText(p.getDni());
		getTxtTelefono().setText(String.valueOf(p.getTelefono()));
		getTxtFecha().setText(p.getFechaNacimiento().toString());
		setEstado(false);

	}
	
	private JTextField getTxNombre() {
		if (txNombre == null) {
			txNombre = new JTextField();
			txNombre.setEditable(false);
			txNombre.setBackground(Color.WHITE);
			txNombre.setHorizontalAlignment(SwingConstants.CENTER);
			txNombre.setText("Nombre y apellidos");
			txNombre.setColumns(10);
		}
		return txNombre;
	}
	private JTextField getTxtDni() {
		if (txtDni == null) {
			txtDni = new JTextField();
			txtDni.setEditable(false);
			txtDni.setBackground(Color.WHITE);
			txtDni.setText("dni");
			txtDni.setHorizontalAlignment(SwingConstants.CENTER);
			txtDni.setColumns(10);
		}
		return txtDni;
	}
	private JTextField getTxtTelefono() {
		if (txtTelefono == null) {
			txtTelefono = new JTextField();
			txtTelefono.setEditable(false);
			txtTelefono.setText("telefono");
			txtTelefono.setBackground(Color.WHITE);
			txtTelefono.setHorizontalAlignment(SwingConstants.CENTER);
			txtTelefono.setColumns(10);
		}
		return txtTelefono;
	}
	private JTextField getTxtFecha() {
		if (txtFecha == null) {
			txtFecha = new JTextField();
			txtFecha.setEditable(false);
			txtFecha.setText("fecha de nacimiento");
			txtFecha.setBackground(Color.WHITE);
			txtFecha.setHorizontalAlignment(SwingConstants.CENTER);
			txtFecha.setColumns(10);
		}
		return txtFecha;
	}
	JButton getBtAgregar() {
		if (btAgregar == null) {
			btAgregar = new JButton("Agregar");
			btAgregar.addActionListener(e -> {
			    if (!isAdded) {
			        // Añadir a la lista visible
			        if (!etm.jugadoresDelEquipo.contains(person))
			        	etm.jugadoresDelEquipo.add(person);
			        btAgregar.setText("Eliminar");
			        btAgregar.setBackground(Color.RED);
			        isAdded = true;
			    } else {
			        // Eliminar de la lista visible
			    	etm.jugadoresDelEquipo.remove(person);
			        btAgregar.setText("Agregar");
			        btAgregar.setBackground(new Color(50, 205, 50));
			        isAdded = false;
			    }
			});
			btAgregar.setBackground(new Color(50, 205, 50)); // verde inicial
		}
		return btAgregar;
	}
	
	public void cambiarFondo(Color color) {
		getTxNombre().setBackground(color);
		getTxtTelefono().setBackground(color);
		getTxtFecha().setBackground(color);
		getBtAgregar().setBackground(color);
		getTxtDni().setBackground(color);
	}
	
	public void setEstado(boolean added) {
	    isAdded = added;
	    if (added) {
	        btAgregar.setText("Eliminar");
	        btAgregar.setBackground(Color.RED);
	    } else {
	        btAgregar.setText("Agregar");
	        btAgregar.setBackground(new Color(50, 205, 50));
	    }
	}
}
