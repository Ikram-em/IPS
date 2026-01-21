package giis.demo.tkrun.validator;

import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;

import giis.demo.ui.employee.AddEmployeeView;
import giis.demo.ui.employee.PnDatosEmpleados;

public class FormValidator {

	private AddEmployeeView view;
	private PnDatosEmpleados view2;

	public FormValidator(AddEmployeeView view) {
		this.view = view;
	}

	public FormValidator(PnDatosEmpleados view2) {
		this.view2 = view2;
	}

	public boolean validate() {
		if (view.getCbDisponeDni().isSelected()) {
			if (!validarDni(view.getTxDni().getText())) {
				JOptionPane.showMessageDialog(view,
					"El campo DNI es incorrecto. Debe tener 8 números y una letra mayúscula");
				return false;
			}
		}
		if (!validarTexto(view.getTxNombre().getText())) {
			JOptionPane.showMessageDialog(view,
					"El campo Nombre es incorrecto. No puede contener caracteres especiales ni números.");
			return false;
		}
		if (!validarTexto(view.getTxApellido().getText())) {
			JOptionPane.showMessageDialog(view,
					"El campo Apellido es incorrecto. No puede contener caracteres especiales ni números.");
			return false;
		}
		if (!validarTelefono(view.getTxTelefono().getText())) {
			JOptionPane.showMessageDialog(view,
					"El campo Teléfono es incorrecto. Debe contener 9 números.");
			return false;
		}
		if (view.getCbPosicionEmpleado().getSelectedItem() != null) {
			if(!validarFecha(view.getFechaNacimiento().getDate(), view.getCbPosicionEmpleado().getSelectedItem().toString())) {
				JOptionPane.showMessageDialog(view,
					"El empleado debe ser mayor de edad si es No Deportivo o entrenador.");

				return false;
			}
		}
		else {
			JOptionPane.showMessageDialog(view,
					"El campo posición del empleado es incorrecto. Debe seleccionar un puesto de trabajo");
				return false;
		}
		if (!validarSalarioAnual(view.getTxSalarioAnual().getText())) {
			JOptionPane.showMessageDialog(view,
					"El campo Salario anual bruto es incorrecto. Debe ser un número positivo.");
			return false;
		}
		JOptionPane.showMessageDialog(view,
				"El empleado ha sido guardado correctamente.");
		return true;
	}
	
	
	public boolean validateChanges() {
		if (!view2.getTxDni().getText().isEmpty()) {
			if (!validarDni(view2.getTxDni().getText())) {
				JOptionPane.showMessageDialog(view2,
						"El campo DNI es incorrecto. Debe tener 8 números y una letra mayúscula");
				return false;
			}
		}
		if (!validarTexto(view2.getTxNombre().getText())) {
			JOptionPane.showMessageDialog(view2,
					"El campo Nombre es incorrecto. No puede contener caracteres especiales ni números.");
			return false;
		}
		if (!validarTexto(view2.getTxApellido().getText())) {
			JOptionPane.showMessageDialog(view2,
					"El campo Apellido es incorrecto. No puede contener caracteres especiales ni números.");
			return false;
		}
		if (!validarTelefono(view2.getTxTelefono().getText())) {
			JOptionPane.showMessageDialog(view2,
					"El campo Teléfono es incorrecto. Debe contener 9 números.");
			return false;
		}
		if (view2.getTxPosicion().getText() != null) {
			if(!validarFecha(view2.getTxFechaNacimiento().getDate(), view2.getTxPosicion().getText() )) {
				JOptionPane.showMessageDialog(view2,
					"El empleado debe ser mayor de edad si es No Deportivo o entrenador.");
				return false;
			}
		}
		else {
			JOptionPane.showMessageDialog(view2,
					"El campo posición del empleado es incorrecto. Debe seleccionar un puesto de trabajo");
				return false;
		}
		if (!validarSalarioAnual(view2.getTxSalario().getText())) {
			JOptionPane.showMessageDialog(view2,
					"El campo Salario anual bruto es incorrecto. Debe ser un número positivo.");
			return false;
		}
		JOptionPane.showMessageDialog(view2,
				"El empleado ha sido guardado correctamente.");
		return true;
	}

	private boolean validarSalarioAnual(String text) {
		if (text == null)
			return false;
		if (text.isBlank())
			return false;
		if (Double.parseDouble(text) < 0) return false;
		return true;
	}

	private boolean validarFecha(Date date, String posicion) {
		if (date == null) 
	        return false; 
	   
		// Obtener el año de nacimiento. (solo en caso de jugador puede ser <18)
	    Calendar nacimiento = Calendar.getInstance();
	    nacimiento.setTime(date);
	    
	    Calendar hoy = Calendar.getInstance();
	    
	    // Los jugadores no tienen restricción de edad
	    if ("Jugador".equalsIgnoreCase(posicion)) {
	        return true; 
	    } else {
	        // Comprobar que sean mayores de edad
	        int edad = hoy.get(Calendar.YEAR) - nacimiento.get(Calendar.YEAR);

	        if (hoy.get(Calendar.MONTH) < nacimiento.get(Calendar.MONTH) || 
	            (hoy.get(Calendar.MONTH) == nacimiento.get(Calendar.MONTH) && hoy.get(Calendar.DAY_OF_MONTH) < nacimiento.get(Calendar.DAY_OF_MONTH))) {
	            edad--;
	        }

	        return edad >= 18;
	    }

	}

	private boolean validarTelefono(String text) {
		if (text == null)
			return false;
		if (text.isBlank())
			return false;
		if (text.length() != 9 || !text.chars().allMatch(Character::isDigit))
			return false;
		return true;
	}

	private boolean validarTexto(String text) {
		if (text == null)
			return false;
		if (text.isBlank())
			return false;
		if (!text.matches("[a-zA-ZáÁéÉíÍóÓúÚñÑ ]+"))
			return false;
		return true;
	}

	private boolean validarDni(String dni) {
		if (dni == null)
			return false;
		if (dni.isBlank())
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
