package merit.assignment7.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import merit.assignment7.Repository.UserAccountRepository;
import merit.assignment7.models.UserAccount;

//import meritbank.assignment7.repos.UserAccountRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired 
	UserAccountRepository userAccountRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
				return new User("admin","admin", new ArrayList<>());
			}
		
	
	

}
