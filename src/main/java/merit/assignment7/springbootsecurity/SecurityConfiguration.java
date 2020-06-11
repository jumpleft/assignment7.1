/*package merit.assignment7.springbootsecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SuppressWarnings("deprecation")
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//set your configuration on the auth object
		
		auth.inMemoryAuthentication()
		.withUser("accountholder")
		.password("meritbank")
		.roles("AccountHolder")
		.and()
		.withUser("admin")
		.password("meritbank")
		.roles("Administrator");
		
		
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
		
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/admin").hasRole("admin")
		.antMatchers("/accountholder").hasAnyRole("accountholder", "admin")
		.antMatchers("/").permitAll()
		
		.and().formLogin();
	}

}
*/