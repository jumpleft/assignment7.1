package merit.assignment7.models;

import javax.persistence.Entity;
import javax.persistence.Id;
//import javax.persistence.Transient;

//import org.springframework.beans.factory.annotation.Autowired;

//import meritbank.assignment7.controllers.MeritBankService;
//import meritbank.assignment7.exceptions.UsernameAlreadyExistsException;
//import meritbank.assignment7.repos.UserAccountRepository;

@Entity
public class UserAccount {
	
	
	@Id
	private String username;
	private String password;
	private Long accountHolderId;
	private String role;
	
	
	
	public UserAccount() {
		this.role = "USER";
	}
	
	public UserAccount(String username, String password, String role) {
		this.username = username;
		this.password = password;
		this.role = role;
	}
	
	
	public void setUsername(String username) { this.username = username; }
	public String getUsername() { return username; }
	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }
	public Long getAccountHolderId() { return accountHolderId; }
	public void setAccountHolderId(Long accountHolderId) { this.accountHolderId = accountHolderId; }
	public String getRole() { return role; }
	public void setRole(String role) { this.role = role; }
	
	@Override
    public String toString() {
        return "User{" + "username='" + username + '\'' + ", password='" + password + '\'' + ", role='" + role + '\'' + '}';
	
}
}