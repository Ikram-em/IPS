package giis.demo.tkrun.employee;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;

import javax.swing.JComboBox;

import giis.demo.tkrun.AbstractController;
import giis.demo.tkrun.employee.createUser.CreateUserController;
import giis.demo.tkrun.logger.AuditService;
import giis.demo.tkrun.validator.FormValidator;
import giis.demo.ui.employee.AddEmployeeView;
import giis.demo.ui.employee.CreateUserView;
import giis.demo.util.SwingUtil;

public class AddEmployeeController extends AbstractController {
	
	private AddEmployeeView view;
	private EmployeeModel eModel;	
	
	public AddEmployeeController(AddEmployeeView view, AuditService audit) {
		super(audit);
		this.view = view;
		this.eModel = new EmployeeModel();
		view.setVisible(true);
		addListeners();
	}
	
	private void addListeners() {
	    for (ActionListener listener: view.getBtnGuardar().getActionListeners()) {
	    	view.getBtnGuardar().removeActionListener(listener);
	    }
	    
		addLoggedAction(view.getBtnGuardar(), "Añadir nuevo empleado", () -> {
			Integer id = saveEmployee();

			if (id != null) {
				audit.log("INFO", "Empleado añadido con id=" + id);
			}
		});

	    view.getBtnSalir().addActionListener(e -> SwingUtil.exceptionWrapper(() -> {
			view.dispose();
		}));
	    
	    view.getCbDisponeDni().addActionListener(e -> SwingUtil.exceptionWrapper(() -> { 
	    	if(!view.getCbDisponeDni().isSelected()) { 
	    		view.getTxDni().setEditable(false);
	    		view.getTxDni().setEnabled(false);
	    		view.getTxDni().setBackground(Color.LIGHT_GRAY);
	    		view.getTxDni().setText(""); 
	    	} else {
	    		view.getTxDni().setEditable(true);
	    		view.getTxDni().setEnabled(true);
	    		view.getTxDni().setBackground(Color.WHITE);
	    		view.getTxDni().requestFocusInWindow();
	    	} 
	    }));

	    view.getCbTipoEmpleado().addActionListener(e -> SwingUtil.exceptionWrapper(() -> {
	    	String tipo = (String) view.getCbTipoEmpleado().getSelectedItem();
	        JComboBox<PositionDTO> cbPosicion = view.getCbPosicionEmpleado();
	        
	        cbPosicion.removeAllItems(); // limpia las opciones previas
	        cbPosicion.setEnabled(true);
	        
	        List<PositionDTO> posiciones;
	        if ("Empleado Deportivo".equals(tipo)) {
	        	posiciones = eModel.getPositionsByType("Deportiva");
	        } else if ("Empleado No Deportivo".equals(tipo)) {
	            posiciones = eModel.getPositionsByType("No Deportiva");
	        } else {
	            cbPosicion.setEnabled(false);
	            return;
	        }
	        for (PositionDTO p : posiciones) {
	        	cbPosicion.addItem(p);
	        }
	        
	    }));
	}
	
	
	private Integer saveEmployee() {
		FormValidator validator = new FormValidator(view);
		if (!validator.validate()) {
			return null;
		}
		PositionDTO posicion = (PositionDTO) view.getCbPosicionEmpleado().getSelectedItem();
		EmployeeViewDTO employee = new EmployeeViewDTO();
		employee.dni = view.getTxDni().getText();
		employee.nombre = view.getTxNombre().getText();
		employee.apellido = view.getTxApellido().getText();
		employee.telefono = Integer.parseInt(view.getTxTelefono().getText());
		employee.fecha_nacimiento = new Date(view.getFechaNacimiento().getDate().getTime()).toString();
		employee.id_posicion = posicion.getId_posicion();
		employee.salario_anual = Double.parseDouble(view.getTxSalarioAnual().getText());
		
		int id_empleado = eModel.saveEmployee(employee, view.getCbTipoEmpleado().getSelectedItem().toString());
		employee.id_empleado = id_empleado;
		
		if (posicion.getId_rol() != null) {
			RoleDTO rol = eModel.getRoleById(posicion.getId_rol());
			new CreateUserController(new CreateUserView(employee, rol), employee, rol, audit);
		}
		view.dispose();
		return id_empleado;
	}

}
