package merit.assignment7.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import merit.assignment7.springbootsecurity.AuthenticationRequest;
import merit.assignment7.springbootsecurity.AuthenticationResponse;
import merit.assignment7.models.UserAccount;
import merit.assignment7.services.JwtUtil;
import merit.assignment7.services.MyUserDetailsService;
import merit.assignment7.Repository.UserAccountRepository;



@RestController
//@RequestMapping("/authenticate")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private UserAccountRepository userAccountRepository;

	@RequestMapping(value= "/authenticate", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		try {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
				);
		}catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}
	
	final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
	
	final String jwt = jwtTokenUtil.generateToken(userDetails);
			//generateToken(userDetails);
	
	return ResponseEntity.ok(new AuthenticationResponse(jwt));
	
	
	}
	
	@PostMapping("/authenticate/createUser")
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody UserAccount CreateUser(@RequestBody @Valid UserAccount u) {
		userAccountRepository.save(u);
			return u;	
	}

	@PostMapping(value = "/authenticate/CreateUser")
	public ResponseEntity<?> createUser(@RequestBody UserAccount users){
		userAccountRepository.save(users);
		return ResponseEntity.ok(users);
	}









}