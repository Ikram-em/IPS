package giis.demo.tkrun.employee.createUser;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import giis.demo.util.Database;

public class UserModel {
	private Database database = new Database();
	
	private static final String SQL_CREATE_USER = "INSERT INTO User (username, password, id_rol, id_empleado) VALUES (?, ?, ?, ?)";
	
	private static final String SQL_USER_WITH_USERNAME = "SELECT * from User WHERE username = ?";
	
	private static final String SQL_GET_USER_WITH_CREDENTIALS = "SELECT u.id_user, u.id_rol, r.nombre AS nombre_rol, u.password, u.id_empleado FROM User u " +
			"JOIN Rol r ON u.id_rol = r.id_rol " +
			"WHERE u.username = ?";
	
	public Integer createUser(String username, String password, int id_rol, int id_empleado) {
		String hashedPsswd = BCrypt.hashpw(password, BCrypt.gensalt(12));
		System.out.println(hashedPsswd);
		return database.executeInsertAndReturnId(SQL_CREATE_USER, username, hashedPsswd, id_rol, id_empleado)
				.intValue();
	}
	
	public boolean getUsernameInUse(String username) {
		List<UserDTO> userList = database.executeQueryPojo(UserDTO.class, SQL_USER_WITH_USERNAME, username);
		
		return userList.size() > 0;
	}
	
	public UserDTO getUserWithCredentials(String username, String password) {
		List<UserDTO> userWithUsername = database.executeQueryPojo(UserDTO.class, SQL_GET_USER_WITH_CREDENTIALS, username);
		if (userWithUsername.size() == 0) {
			return null;
		}
		boolean isCorrectPassword = BCrypt.checkpw(password, userWithUsername.get(0).getPassword());
		if (isCorrectPassword) {
			return userWithUsername.get(0);
		}
		return null;
	}
}
