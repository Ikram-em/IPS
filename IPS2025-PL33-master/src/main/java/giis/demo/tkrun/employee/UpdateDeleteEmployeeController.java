package giis.demo.tkrun.employee;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import giis.demo.tkrun.AbstractController;
import giis.demo.tkrun.employee.createUser.CreateUserController;
import giis.demo.tkrun.logger.AuditService;
import giis.demo.tkrun.validator.FormValidator;
import giis.demo.ui.employee.CreateUserView;
import giis.demo.ui.employee.PnDatosEmpleados;
import giis.demo.ui.employee.UpdateDeleteEmployeeView;
import giis.demo.util.SwingUtil;

public class UpdateDeleteEmployeeController extends AbstractController {
	
	private UpdateDeleteEmployeeView view;
	private EmployeeModel eModel;
	private PnDatosEmpleados pnEditable;
	private EmployeeViewDTO employee;
	private List<EmployeeViewDTO> employees = new ArrayList<EmployeeViewDTO>();
	
	public UpdateDeleteEmployeeController(UpdateDeleteEmployeeView view, AuditService audit) {
		super(audit);
		this.view = view;
		initView();
	}
	
	private void initView() {
		initModel();
		loadEmployees();
		addFilterActionListeners();
		// añadir action listeners a los botones de guardar cambios y descartar cambios
		addActionListenersWhileSavingChanges();
		view.setVisible(true);
	}
	
	// Se añade el Action Listener para el boton de filtrar.
	private void addFilterActionListeners() {

		addLoggedAction(view.getBtnFiltrar(), "Filtrar Empleado", () -> {
			String textoAFiltrar = filtrarEmpleados();

			if (textoAFiltrar != null && !textoAFiltrar.isBlank()) {
				audit.log("INFO", "Empleados filtrados por " + textoAFiltrar);
			}
		});

		addLoggedAction(view.getBtnEliminarFiltro(), "Eliminar filtro", () -> {
			loadEmployees();
			view.getTxFiltro().setText("");
		});
	}

	private String filtrarEmpleados() {
		String texto = view.getTxFiltro().getText();
		List<EmployeeViewDTO> empleadosFiltrados = null;
		// si es un numero lo primero, se busca por dni:
		if (Character.isDigit(texto.charAt(0))) {
			empleadosFiltrados = eModel.getEmployeeByDni(texto);
			if (empleadosFiltrados != null) mostrarEmpleadoFiltrado(empleadosFiltrados);
			else JOptionPane.showMessageDialog(view, "No se ha encontrado ningún empleado con ese DNI");
		}
		else if (Character.isAlphabetic(texto.charAt(0))) {
			empleadosFiltrados = eModel.getEmployeeByName(texto);
			if (empleadosFiltrados != null) mostrarEmpleadoFiltrado(empleadosFiltrados);
			else JOptionPane.showMessageDialog(view, "No se ha encontrado ningún empleado con ese nombre");
		}
		else {
			JOptionPane.showMessageDialog(view,
					"Debe introducir un DNI o un nombre para buscar un empleado.");
		}
		
		return texto;

	}
	
	// muestra solo el empleado que se ha filtrado.
	private void mostrarEmpleadoFiltrado(List<EmployeeViewDTO> filteredEmployee) {
		view.getPnEmpleados().removeAll();
		for (EmployeeViewDTO e: filteredEmployee) {
			PnDatosEmpleados panelEmpleadoFiltrado = new PnDatosEmpleados(e);
			view.getPnEmpleados().add(panelEmpleadoFiltrado);
			addButtonsListeners(panelEmpleadoFiltrado, e);
		}
		
		view.revalidate();
		view.repaint();
	}

	private void addActionListenersWhileSavingChanges() {

		addLoggedAction(view.getBtnGuardarCambios(), "Guardar modificaciones", () -> {
			Integer id = guardarCambios();
			
			if (id != null) {
				audit.log("INFO", "Empleado modificado con id=" + id);
			}
		});

		view.getBtnCancelarCambios().addActionListener(e -> SwingUtil.exceptionWrapper(() -> {
			view.dispose();
		}));
	}

	private void initModel() {
		eModel = new EmployeeModel();
	}
	
	private void loadEmployees() {
		employees = eModel.getEmployees();
		view.getPnEmpleados().removeAll();
		for (EmployeeViewDTO employee: employees) {
			PnDatosEmpleados pnEmployee = new PnDatosEmpleados(employee);
			view.getPnEmpleados().add(pnEmployee);
			addButtonsListeners(pnEmployee, employee);
		}
		
		view.getPnEmpleados().revalidate(); // Revalidar panel
		view.getPnEmpleados().repaint(); // Redibujar panel
	}
	
	private void addButtonsListeners(PnDatosEmpleados pn, EmployeeViewDTO employee) {
		for (ActionListener listener: pn.getBtnModificar().getActionListeners()) {
	    	pn.getBtnModificar().removeActionListener(listener);
	    }
		
		view.getBtSalir().addActionListener(e -> SwingUtil.exceptionWrapper(() -> {
			view.dispose();
		}));

	    
		pn.getBtnModificar().addActionListener(e -> SwingUtil.exceptionWrapper(() -> {
			this.employee = employee;
			modificarEmployee(employee);
		}));
		
		addLoggedAction(pn.getBtnEliminar(), "Eliminar empleado con id=" + employee.id_empleado,
				() -> eliminar(pn, employee));
		addLoggedAction(pn.getBtnCrearUsuario(), "Crear usuario de empleado con id=" + employee.id_empleado,
				() -> crearUsuario(employee));
		
	}
	
	private Integer guardarCambios() {
		FormValidator validator = new FormValidator(pnEditable);
		if (!validator.validateChanges()) {
			return null;
		}
		EmployeeViewDTO employee = new EmployeeViewDTO();
		employee.dni = pnEditable.getTxDni().getText();
		employee.nombre = pnEditable.getTxNombre().getText();
		employee.apellido = pnEditable.getTxApellido().getText();
		employee.telefono = Integer.parseInt(pnEditable.getTxTelefono().getText());
		employee.fecha_nacimiento = new Date(pnEditable.getTxFechaNacimiento().getDate().getTime()).toString();
		employee.salario_anual = Double.parseDouble(pnEditable.getTxSalario().getText());
		employee.id_empleado = this.employee.id_empleado;
		eModel.updateEmployee(employee);
		view.dispose();

		return employee.id_empleado;
	}
	
	private void crearUsuario(EmployeeViewDTO employee) {
		RoleDTO rol = eModel.getRoleById(employee.id_rol);
		new CreateUserController(new CreateUserView(employee, rol), employee, rol, audit);
	}

	private void modificarEmployee(EmployeeViewDTO employee) {
		view.getCardLayout().show(view.getMainPanel(), "pnModificar");
		
		view.getPnDatosModificables().removeAll();
		
		this.pnEditable = new PnDatosEmpleados(employee);
		
		// habilitar edición
		pnEditable.getTxNombre().setEditable(true);
		pnEditable.getTxNombre().setFocusable(true);
		pnEditable.getTxNombre().setBackground(Color.WHITE);

		pnEditable.getTxApellido().setEditable(true);
		pnEditable.getTxApellido().setFocusable(true);
		pnEditable.getTxApellido().setBackground(Color.WHITE);

		pnEditable.getTxDni().setEditable(true);
		pnEditable.getTxDni().setFocusable(true);
		pnEditable.getTxDni().setBackground(Color.WHITE);

		pnEditable.getTxFechaNacimiento().setEnabled(true);
		pnEditable.getTxFechaNacimiento().getDateEditor().setEnabled(true);
		pnEditable.getTxFechaNacimiento().setFocusable(true);
		pnEditable.getTxFechaNacimiento().setBackground(Color.WHITE);

		pnEditable.getTxTelefono().setEditable(true);
		pnEditable.getTxTelefono().setFocusable(true);
		pnEditable.getTxTelefono().setBackground(Color.WHITE);

		pnEditable.getTxSalario().setEditable(true);
		pnEditable.getTxSalario().setFocusable(true);
		pnEditable.getTxSalario().setBackground(Color.WHITE);

		// hacer botones invisibles
		pnEditable.getBtnModificar().setVisible(false);
		pnEditable.getBtnEliminar().setVisible(false);
		
		view.getPnDatosModificables().add(pnEditable);
		view.getPnDatosModificables().revalidate();
		view.getPnDatosModificables().repaint();
	}

	private void eliminar(PnDatosEmpleados pn, EmployeeViewDTO employee) {
		int opcion = JOptionPane.showConfirmDialog(null, "Está seguro/a de que desea eliminar este empleado?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
		if (opcion == JOptionPane.YES_OPTION) {
			eModel.removeEmployee(employee);
			view.getPnEmpleados().remove(pn);
			view.getPnEmpleados().revalidate();
			view.getPnEmpleados().repaint();
		}
	}

}
