package giis.demo.tkrun.login;

public class Session {
	private static Session instance;
	private Integer userId;
	private Integer roleId;
	private String roleName;
	private Integer employeeId;
	
	private Session() {}
	
	public static Session get() {
		if (instance == null) {
            instance = new Session();
        }
        return instance;
	}
	
	public void login(int userId, int roleId, String roleName, int employeeId) {
		this.userId = userId;
		this.roleId = roleId;
		this.roleName = roleName;
		this.employeeId = employeeId;
	}
	
	public void logout() {
		this.instance = null;
		this.userId = null;
		this.roleId = null;
		this.roleName = null;
	}
	
	public int getUserId() {
		return this.userId;
	}
	
	public int getRoleId() {
		return this.roleId;
	}
	
	public String getRoleName() {
		return this.roleName;
	}
	
	public int getEmployeeID() {
		return this.employeeId;
	}
}
