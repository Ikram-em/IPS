package giis.demo.tkrun.login;

public class ShareholderSession {
	private static ShareholderSession instance;
	private Integer shareholderId;
	
	private ShareholderSession() {}
	
	public static ShareholderSession get() {
		if (instance == null) {
            instance = new ShareholderSession();
        }
        return instance;
	}
	
	public void login(int shareholderId) {
		this.shareholderId = shareholderId;
	}
	
	public void logout() {
		this.instance = null;
		this.shareholderId = null;
	}
	
	public Integer getId() {
		return this.shareholderId;
	}
}
