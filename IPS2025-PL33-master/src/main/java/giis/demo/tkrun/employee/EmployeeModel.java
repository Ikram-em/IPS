package giis.demo.tkrun.employee;

import java.util.List;

import giis.demo.util.Database;

public class EmployeeModel {

	private Database dataBase = new Database();

	private final static String SQL_FIND_NO_SPORT_EMPLOYEES = "SELECT em.id_empleado, em.dni, em.nombre, em.apellido, em.fecha_nacimiento,"
			+ " em.telefono, em.salario_anual, p.id_posicion AS id_posicion, p.nombre AS nombre_posicion FROM Empleado em JOIN EmpleadoNoDeportivo n ON em.id_empleado = n.id_empleado" 
			+ " JOIN Posicion p ON n.id_posicion = p.id_posicion;";


	private final static String SQL_FIND_ALL_EMPLOYEES = "SELECT em.id_empleado, em.dni, em.nombre, em.apellido, em.fecha_nacimiento, "
			+ "em.telefono, em.salario_anual, p.id_posicion AS id_posicion, p.nombre AS nombre_posicion, u.username, r.id_rol " + "FROM Empleado em "
			+ "JOIN EmpleadoNoDeportivo n ON em.id_empleado = n.id_empleado JOIN Posicion p ON p.id_posicion = n.id_posicion " 
			+ "LEFT JOIN User u ON u.id_empleado = em.id_empleado LEFT JOIN Rol r ON p.id_rol = r.id_rol " + "UNION ALL "
			+ "SELECT em.id_empleado, em.dni, em.nombre, em.apellido, em.fecha_nacimiento, "
			+ "em.telefono, em.salario_anual, p.id_posicion AS id_posicion, p.nombre AS nombre_posicion, u.username, r.id_rol " + "FROM Empleado em "
			+ "JOIN EmpleadoDeportivo d ON em.id_empleado = d.id_empleado JOIN Posicion p ON p.id_posicion = d.id_posicion LEFT JOIN User u ON u.id_empleado = em.id_empleado "
			+ "LEFT JOIN Rol r ON p.id_rol = r.id_rol;";
	
	private final static String FIND_EMPLOYEE_BY_DNI = "SELECT em.id_empleado, em.dni, em.nombre, em.apellido, em.fecha_nacimiento, "
            + "em.telefono, em.salario_anual, p.id_posicion AS id_posicion, p.nombre AS nombre_posicion "
            + "FROM Empleado em "
            + "LEFT JOIN EmpleadoDeportivo d ON em.id_empleado = d.id_empleado "
            + "LEFT JOIN EmpleadoNoDeportivo n ON em.id_empleado = n.id_empleado "
            + "JOIN Posicion p ON p.id_posicion = COALESCE(d.id_posicion, n.id_posicion) "
            + "WHERE em.dni LIKE ?";

	private final static String FIND_EMPLOYEE_BY_NAME = "SELECT em.id_empleado, em.dni, em.nombre, em.apellido, em.fecha_nacimiento, "
            + "em.telefono, em.salario_anual, p.id_posicion AS id_posicion, p.nombre AS nombre_posicion "
            + "FROM Empleado em "
            + "LEFT JOIN EmpleadoDeportivo d ON em.id_empleado = d.id_empleado "
            + "LEFT JOIN EmpleadoNoDeportivo n ON em.id_empleado = n.id_empleado "
            + "JOIN Posicion p ON p.id_posicion = COALESCE(d.id_posicion, n.id_posicion) "
            + "WHERE em.nombre = ?";

	private final static String ADD_EMPLOYEE = "INSERT INTO Empleado(dni, nombre, apellido, fecha_nacimiento, telefono, salario_anual)"
			+ " VALUES (?, ?, ?, ?, ?, ?)";

	private final static String ADD_EMPLOYEE_DEPORTIVO = "INSERT INTO EmpleadoDeportivo (id_empleado, id_posicion) VALUES (?, ?)";

	private final static String ADD_EMPLOYEE_NO_DEPORTIVO = "INSERT INTO EmpleadoNoDeportivo (id_empleado, id_posicion) VALUES (?, ?)";

	private static final String DELETE_EMPLOYEE_DEPORTIVO = "DELETE FROM EmpleadoDeportivo WHERE id_empleado = ?";

	private static final String DELETE_EMPLOYEE_NO_DEPORTIVO = "DELETE FROM EmpleadoNoDeportivo WHERE id_empleado = ?";

	private static final String DELETE_EMPLOYEE = "DELETE FROM Empleado WHERE id_empleado = ?";

	private static final String SQL_UPDATE_EMPLOYEE = "UPDATE Empleado SET dni = ?, nombre = ?, apellido = ?, "
			+ "fecha_nacimiento = ?, telefono = ?, salario_anual = ? " + "WHERE id_empleado = ?";
	
	private static final String SQL_FIND_POSITIONS_BY_TYPE = "SELECT * FROM Posicion WHERE tipo = ?";
	
	private static final String SQL_FIND_ROLE_BY_ID = "SELECT * FROM Rol WHERE id_rol = ?";

	public List<EmployeeViewDTO> getNonSportEmployees() {
		return dataBase.executeQueryPojo(EmployeeViewDTO.class, SQL_FIND_NO_SPORT_EMPLOYEES);
	}

	public List<EmployeeViewDTO> getEmployees() {
		return dataBase.executeQueryPojo(EmployeeViewDTO.class, SQL_FIND_ALL_EMPLOYEES);
	}

	public int saveEmployee(EmployeeViewDTO employee, String tipo_empleado) {
		Number id = dataBase.executeInsertAndReturnId(ADD_EMPLOYEE, employee.dni, employee.nombre, employee.apellido,
				employee.fecha_nacimiento, employee.telefono, employee.salario_anual);

		if (tipo_empleado.equals("Empleado Deportivo")) {
			dataBase.executeUpdate(ADD_EMPLOYEE_DEPORTIVO, id, employee.id_posicion);
		} else {
			dataBase.executeUpdate(ADD_EMPLOYEE_NO_DEPORTIVO, id, employee.id_posicion);
		}

		return id.intValue();
	}

	public void removeEmployee(EmployeeViewDTO employee) {
		try {
			if ("Jugador".equalsIgnoreCase(employee.nombre_posicion) || "Entrenador".equalsIgnoreCase(employee.nombre_posicion)) {
				dataBase.executeUpdate(DELETE_EMPLOYEE_DEPORTIVO, employee.id_empleado);
			} else {
				dataBase.executeUpdate(DELETE_EMPLOYEE_NO_DEPORTIVO, employee.id_empleado);
			}

			dataBase.executeUpdate(DELETE_EMPLOYEE, employee.id_empleado);
		} catch (Exception e) {
			throw new RuntimeException("Error eliminando empleado");
		}

	}

	public void updateEmployee(EmployeeViewDTO employee) {
		dataBase.executeUpdate(SQL_UPDATE_EMPLOYEE, employee.dni, employee.nombre, employee.apellido,
				employee.fecha_nacimiento, employee.telefono, employee.salario_anual, employee.id_empleado);

	}

	public List<EmployeeViewDTO> getEmployeeByDni(String texto) {
		return dataBase.executeQueryPojo(EmployeeViewDTO.class, FIND_EMPLOYEE_BY_DNI, texto);
	}

	public List<EmployeeViewDTO> getEmployeeByName(String texto) {
		return dataBase.executeQueryPojo(EmployeeViewDTO.class, FIND_EMPLOYEE_BY_NAME, texto);
	}

	public List<PositionDTO> getPositionsByType(String type) {
		List<PositionDTO> pos = dataBase.executeQueryPojo(PositionDTO.class, SQL_FIND_POSITIONS_BY_TYPE, type);
		return pos;
	}
	
	public RoleDTO getRoleById(int id) {
		List<RoleDTO> roleList = dataBase.executeQueryPojo(RoleDTO.class, SQL_FIND_ROLE_BY_ID, id);
		return roleList.get(0);
	}
}
