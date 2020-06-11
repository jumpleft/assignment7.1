package merit.assignment7.springbootsecurity;

public class AuthenticationRequest {
	
	//This authentication request defines input argument to the authenticate method
	//The user will send in the POST request
	private String username;
	private String password;
	
	//get and set for username and password
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	//constructor for username and password
	public AuthenticationRequest(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	//default constructor
	public AuthenticationRequest() {

	}
	
	

}
