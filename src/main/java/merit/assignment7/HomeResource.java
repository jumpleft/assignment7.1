/*package merit.assignment7;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import merit.assignment7.services.JwtUtil;
import merit.assignment7.services.MyUserDetailsService;
import merit.assignment7.springbootsecurity.AuthenticationRequest;
import merit.assignment7.springbootsecurity.AuthenticationResponse;


//Rest API
@Controller
@RestController
public class HomeResource {
	
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtUtil jwtTokenUtil;
	
	@RequestMapping({"/hello"})
	public String hello() {
		return "Hello World";
}
	//authenticate endpoint here. Takes in username and password and returns jwt.
	//Request method as a POST.  User will send username and password in POST body
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
 public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		try {
		authenticationManager.authenticate(
		new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
				);
		
	}catch (BadCredentialsException e) {
		throw new Exception("Incorrect username or password", e);
	}
		
	final UserDetails userDetails = userDetailsService
			.loadUserByUsername(authenticationRequest.getUsername());
	
	final String jwt = jwtTokenUtil.generateToken(userDetails);
	
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
}
	
}

	
	


accessible to everyone
@GetMapping("/")
public String home() {
return ("<h1>Welcome</h1>");
}

//accessible to accountholders
@GetMapping("/accountholder")
public String accountholder() {
 return ("<h1>Welcome Account Holder</h1>");
}

//accessible to admin
@GetMapping("/administrator")
public String admin() {
 return ("<h1>Welcome Administrator</h1>");
}*/

