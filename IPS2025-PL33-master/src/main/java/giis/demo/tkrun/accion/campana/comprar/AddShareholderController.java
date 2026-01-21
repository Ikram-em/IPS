package giis.demo.tkrun.accion.campana.comprar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;

import giis.demo.tkrun.accion.AccionistaDAO;
import giis.demo.ui.acciones.campana.AddShareholderView;

public class AddShareholderController {
	private AddShareholderView addShareholderView;
	private AccionistaDAO accionistaDAO;
	
	public AddShareholderController(AddShareholderView addShareholderView) {
		this.addShareholderView = addShareholderView;
		this.accionistaDAO = new AccionistaDAO();
		loadActionListeners();
		this.addShareholderView.setVisible(true);
	}
	
	private void loadActionListeners() {
		this.addShareholderView.getCreateBtn().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String nombre = addShareholderView.getTxNombre().getText();
				String apellido = addShareholderView.getTxApellido().getText();
				String dni = addShareholderView.getTxDni().getText();
				Date fechaNacimiento = addShareholderView.getFechaNacimiento().getDate();
				if (!validatePersonalData(nombre, apellido, dni, fechaNacimiento)) {
					return;
				}
				
				String username = addShareholderView.getLoginForm().getUsernameTx().getText();
				String password = addShareholderView.getLoginForm().getPasswordTx().getText();
				if (!validateUserFields(username, password)) {
					return;
				}
				
				accionistaDAO.createShareholder(nombre, apellido, dni, fechaNacimiento, username, password);
				JOptionPane.showMessageDialog(null, "Se ha registrado correctamente el nuevo accionista");
				addShareholderView.dispose();
			}
		});
	}
	
	private boolean validatePersonalData(String nombre, String apellido, String dni, Date fechaNacimiento) {
		if (nombre == null || apellido == null || dni == null || fechaNacimiento == null) {
			JOptionPane.showMessageDialog(addShareholderView, "Todos los campos deben estar llenos");
			return false;
		}
		
		if (nombre.trim().isEmpty() || apellido.trim().isEmpty() || dni.trim().isEmpty()) {
			JOptionPane.showMessageDialog(addShareholderView, "Todos los campos deben estar llenos");
			return false;
		}
		
		if (!isValidDni(dni)) {
			JOptionPane.showMessageDialog(addShareholderView, "El campo DNI es incorrecto. Debe tener 8 números y una letra mayúscula");
			return false;
		}
		
		if (!esMayorDeEdad(fechaNacimiento)) {
			JOptionPane.showMessageDialog(addShareholderView, "Debes ser mayor de edad para registrarte como accionista");
			return false;
		}
		
		return true;
	}
	
	private boolean validateUserFields(String username, String password) {
		if (username == null || username.trim().isEmpty()) {
			JOptionPane.showMessageDialog(addShareholderView, "El nombre de usuario no puede estar vacío");
			return false;
		}
		
		if (password == null || password.trim().isEmpty()) {
			JOptionPane.showMessageDialog(addShareholderView, "La contraseña no puede estar vacía");
			return false;
		}
		if (password.length() < 8) {
			JOptionPane.showMessageDialog(addShareholderView, "La contraseña debe contener 8 o más caracteres");
			return false;
		}
		boolean repeatedUsername = accionistaDAO.getUsernameInUse(username);
		if (repeatedUsername) {
			JOptionPane.showMessageDialog(addShareholderView, "El nombre de usuario ingresado ya se encuentra en uso");
			return false;
		}
		
		return true;
	}
	
	private boolean esMayorDeEdad(Date date) {
		if (date == null) 
	        return false; 
	   
	    Calendar nacimiento = Calendar.getInstance();
	    nacimiento.setTime(date);
	    
	    Calendar hoy = Calendar.getInstance();
	    
	    
	    int edad = hoy.get(Calendar.YEAR) - nacimiento.get(Calendar.YEAR);

	    if (hoy.get(Calendar.MONTH) < nacimiento.get(Calendar.MONTH) || 
	        (hoy.get(Calendar.MONTH) == nacimiento.get(Calendar.MONTH) && hoy.get(Calendar.DAY_OF_MONTH) < nacimiento.get(Calendar.DAY_OF_MONTH))) {
	        edad--;
	    }

	    return edad >= 18;
	}
	
	private boolean isValidDni(String dni) {
		if (dni == null || dni.trim().isEmpty())
			return false;
		if (dni.length() != 9)
			return false;
		String numeros = dni.substring(0, 8);
	    char letra = dni.charAt(8);
	    if (!numeros.chars().allMatch(Character::isDigit))
	        return false;
	    if (!Character.isUpperCase(letra))
	        return false;
	    return true;
	}
}
