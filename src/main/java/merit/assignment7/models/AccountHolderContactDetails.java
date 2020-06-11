package merit.assignment7.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "account_contact")
public class AccountHolderContactDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "contact_id")
	private long id;
	
	private int phone;
	private String email;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "contact_id", referencedColumnName = "account_id")
	AccountHolder account;


public long getId() {
	return id;
}

public void setId(long id) {
	this.id = id;
}

public int getPhone() {
	return phone;
}

public void setPhone(int phone) {
	this.phone = phone;
}

public AccountHolder getAccount() {
	return account;
}

public void setAccount(AccountHolder account) {
	this.account = account;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

}