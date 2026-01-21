package giis.demo.tkrun.accion;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import giis.demo.util.Database;

public class AccionistaDAO {
	private Database database = new Database();
	
	private static String CREATE_SHAREHOLDER = "INSERT INTO Accionista(nombre, apellido, dni, fecha_nacimiento, username, password) VALUES (?, ?, ?, ?, ?, ?)";
	
	private static final String SQL_SHAREHOLDER_WITH_USERNAME = "SELECT id_accionista from Accionista WHERE username = ?";
	
	private static final String SQL_SHAREHOLDER_WITH_ID = "SELECT id_accionista, nombre, apellido from Accionista WHERE id_accionista = ?";
	
	private static final String SQL_SHAREHOLDER_WITH_CREDENTIALS = "SELECT id_accionista, password from Accionista WHERE username = ?";
		
	public void createShareholder(String nombre, String apellido, String dni, Date fechaNacimiento, String username, String password) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String hashedPw = BCrypt.hashpw(password, BCrypt.gensalt(12));
		
		database.executeUpdate(CREATE_SHAREHOLDER, nombre, apellido, dni, formatter.format(fechaNacimiento), username, hashedPw);
	}
	
	public boolean getUsernameInUse(String username) {
		List<AccionistaSimpleDTO> shareholderList = database.executeQueryPojo(AccionistaSimpleDTO.class, SQL_SHAREHOLDER_WITH_USERNAME, username);
		
		return shareholderList.size() > 0;
	}
	
	public AccionistaSimpleDTO getAccionista(int id) {
		List<AccionistaSimpleDTO> shareholderList = database.executeQueryPojo(AccionistaSimpleDTO.class, SQL_SHAREHOLDER_WITH_ID, id);
		
		if (shareholderList.size() == 0) {
			return null;
		}
		
		return shareholderList.get(0);
	}
	
	public AccionistaUserDTO getShareholderWithCredentials(String username, String password) {
		List<AccionistaUserDTO> accionistaList = database.executeQueryPojo(AccionistaUserDTO.class, SQL_SHAREHOLDER_WITH_CREDENTIALS, username);
		
		if (accionistaList.size() == 0) {
			return null;
		}
		boolean isCorrectPassword = BCrypt.checkpw(password, accionistaList.get(0).getPassword());
		if (isCorrectPassword) {
			return accionistaList.get(0);
		}
		return null;
	}
}
