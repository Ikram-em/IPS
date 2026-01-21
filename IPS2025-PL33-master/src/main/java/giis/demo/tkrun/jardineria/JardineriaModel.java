package giis.demo.tkrun.jardineria;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import giis.demo.tkrun.employee.EmployeeViewDTO;
import giis.demo.util.Database;

public class JardineriaModel {

	private Database dataBase = new Database();

	private static final String FIND_JARDINEROS = "SELECT em.id_empleado, em.dni, em.nombre, em.apellido, em.fecha_nacimiento, "
			+ "em.telefono, em.salario_anual, p.id_posicion AS id_posicion, p.nombre AS nombre_posicion FROM Empleado em "
			+ "JOIN EmpleadoNoDeportivo n ON em.id_empleado = n.id_empleado "
			+ "JOIN Posicion p ON p.id_posicion = n.id_posicion "
			+ "WHERE p.nombre = 'Jardinería'";

	/******************************************************************************************************************************************/

	private final static String INSERT_LABOR_JARDINERIA = "INSERT INTO LaborJardineria (id_empleado, fecha, hora_inicio, hora_fin, id_instalacion) "
			+ "VALUES (?, ?, ?, ?, ?)";

	/******************************************************************************************************************************************/



	public List<EmployeeViewDTO> getJardineros() {
		List<EmployeeViewDTO> jardineros = dataBase.executeQueryPojo(EmployeeViewDTO.class, FIND_JARDINEROS);
		return jardineros;
	}


	public Integer save(EmployeeViewDTO jardinero, Date date, String horaInicio, String horaFin,
			int id_instalacion) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String fechaStr = sdf.format(date);

		return dataBase.executeInsertAndReturnId(INSERT_LABOR_JARDINERIA, jardinero.id_empleado, fechaStr,
				horaInicio, horaFin,
				id_instalacion).intValue();
	}


}
