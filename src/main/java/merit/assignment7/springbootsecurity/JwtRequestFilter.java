package merit.assignment7.springbootsecurity;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import merit.assignment7.services.JwtUtil;
import merit.assignment7.services.MyUserDetailsService;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
	
	private Logger log = LoggerFactory.getLogger(this.getClass() );
	
	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
		throws ServletException, IOException {
		
		final String authorizationHeader = request.getHeader("Authorization");
		
		String username = null;
		String jwt = null;
		
		//authorizationHeader contains (Bearer then space and jwt)
		//verification
		log.info("logging information here");
		if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			jwt = authorizationHeader.substring(7);
			username = jwtUtil.extractUsername(jwt);
			
			log.info(jwt + "logging information here 2");
			//if the user name isnt null,next condition
			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
				if(jwtUtil.validateToken(jwt,  userDetails)) {
					
				
					UsernamePasswordAuthenticationToken usernamePasswordAuthentiationToken = new UsernamePasswordAuthenticationToken (
							userDetails, null, userDetails.getAuthorities());
					usernamePasswordAuthentiationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthentiationToken);
				}
			}
			chain.doFilter(request, response);
		}
	}

}
