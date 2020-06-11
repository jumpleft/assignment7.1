package merit.assignment7.springbootsecurity;

//returns jwt
public class AuthenticationResponse {

	//variable
	private final String jwt;

   //constructor
	public AuthenticationResponse(String jwt) {
		this.jwt = jwt;
	}
	
	//getter
	public String getJwt() {
		return jwt;
	}

}
