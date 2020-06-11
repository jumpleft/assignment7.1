package merit.assignment7.models;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonSetter;

import merit.assignment7.ErrorHandling.BadRequestError;
import merit.assignment7.ErrorHandling.ExceedsCombinedBalanceLimitException;
import merit.assignment7.ErrorHandling.ExceedsFraudSuspicionLimitException;



@Entity
@Table(name ="account")
public class AccountHolder implements Comparable<AccountHolder> {
	
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
	private long id;
    @NotNull
    @NotBlank
	private String firstName;
    
	private String middleName;
	
	@NotNull
    @NotBlank
	private String lastName;
	@NotNull
    @NotBlank
	private String ssn;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "account_id" , referencedColumnName = "contact_id")
	private AccountHolderContactDetails contact;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<CheckingAccount> cAccount = cAccount = new ArrayList<CheckingAccount>();
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<SavingsAccount> sAccount =sAccount = new ArrayList<SavingsAccount>();
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<CDAccount> cdAccount =cdAccount = new ArrayList<CDAccount>();	
	
	private double checkingTotalBalance=0;
	private double savingsTotalBalance=0;
	private int numberOfSavingsAccount = 0;
	private int numberOfCheckingAccounts=0;
	private int numberOfCDAccounts = 0;
	private double cdTotalBalance=0;
	
	//Default Constructor
	
	public AccountHolder() {}
	
	//AccountHolder Constructor
	public AccountHolder (String firstName, String middleName, String lastName, String ssn){	
		
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName; 
		this.ssn = ssn;
		/*
		cAccount = new ArrayList<CheckingAccount>();
		sAccount = new ArrayList<SavingsAccount>();
		cdAccount = new ArrayList<CDAccount>();		
		*/
	}
	@JsonSetter
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public long getID() {
		return id;
	}
	
	public void setID(long id) {
		this.id = 0;
		this.id = id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	@JsonSetter
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}	
	public String getMiddleName() {
		return middleName;
	}
	@JsonSetter
	public void setLastName(String lastName) {
		this.lastName = lastName;		
	}	
	public String getLastName() {
		return lastName;
	}
	@JsonSetter
	public void setSSN(String ssn) {
		this.ssn = ssn;
	}
	public String getSSN(){
		return ssn;
	}	
	public CheckingAccount addCheckingAccount(double openingBalance) throws ExceedsCombinedBalanceLimitException, ExceedsFraudSuspicionLimitException, Exception{
		/*
		if(openingBalance>1000) {
			Date date = new Date();
			OpeningTransaction transaction = new OpeningTransaction(openingBalance, date);
			FraudQueue.enqueue(transaction);
			throw new ExceedsFraudSuspicionLimitException();
		}*/
		
		 if(openingBalance + checkingTotalBalance + getsavingsTotalBalance() > 250000) 
			throw new ExceedsCombinedBalanceLimitException();
		
		else {
			Date date = new Date();
			CheckingAccount cAccount =new CheckingAccount(MeritBank.getNextAccountNumber(),openingBalance,.0001, date);
			this.cAccount.add(cAccount);
			this.checkingTotalBalance = checkingTotalBalance + openingBalance;
			this.numberOfCheckingAccounts++;
			return cAccount;
		}				
			
	}
	public CheckingAccount addCheckingAccount(CheckingAccount cAccount) throws ExceedsCombinedBalanceLimitException, Exception, ExceedsFraudSuspicionLimitException{
	/*
		if(cAccount.getBalance()>1000) {
			OpeningTransaction transaction = new OpeningTransaction(cAccount);
			transaction.setTransactionDate(cAccount.getOpenedOn());
			FraudQueue.enqueue(transaction);
			throw new ExceedsFraudSuspicionLimitException();
		}*/
		 if(cAccount.getBalance() +checkingTotalBalance + getsavingsTotalBalance() >250000) 
			throw new ExceedsCombinedBalanceLimitException();
		else {
			Date date = new Date();
			cAccount.setOpenedOn(date);
			cAccount.setAccountNumber(MeritBank.getNextAccountNumber());
			cAccount.setIR(.0001);
			this.cAccount.add(cAccount);
			this.checkingTotalBalance = checkingTotalBalance + cAccount.getBalance();
			this.numberOfCheckingAccounts++;
			return cAccount;
			}	
	}				
	public List<CheckingAccount> getCheckingAccounts(){
		return cAccount;
	}
	
	public int getNumberOfCheckingAccounts(){
		return numberOfCheckingAccounts;
	}

	
	public SavingsAccount addSavingsAccount(double openingBalance) throws ExceedsCombinedBalanceLimitException, Exception, ExceedsFraudSuspicionLimitException{
	/*
		if(openingBalance > 1000){
			Date date3 = new Date();
			OpeningTransaction transaction = new OpeningTransaction(openingBalance,date3);
			FraudQueue.enqueue(transaction);
			throw new ExceedsFraudSuspicionLimitException();
		}*/
		 if (openingBalance + savingsTotalBalance + getcheckingTotalBalance() > 250000)
			throw new ExceedsCombinedBalanceLimitException();
		else {
			Date date = new Date();
			SavingsAccount sAccount = new SavingsAccount(MeritBank.getNextAccountNumber(),openingBalance,.01, date);
			this.sAccount.add(sAccount);
			this.savingsTotalBalance = savingsTotalBalance + openingBalance;
			this.numberOfSavingsAccount++;
			return sAccount;
		}
		
		
	}

	public SavingsAccount addSavingsAccount(SavingsAccount sAccount) throws ExceedsCombinedBalanceLimitException, ExceedsFraudSuspicionLimitException, Exception, BadRequestError{
		/*
		if(sAccount.getBalance()>1000) {
			OpeningTransaction transaction = new OpeningTransaction(sAccount);
			transaction.setTransactionDate(sAccount.getOpenedOn());
			FraudQueue.enqueue(transaction);
			throw new ExceedsFraudSuspicionLimitException();
		}*/
		 if(sAccount.getBalance() +savingsTotalBalance + getcheckingTotalBalance() >250000) 
			throw new ExceedsCombinedBalanceLimitException();
		else if(sAccount.getBalance() + savingsTotalBalance + getcheckingTotalBalance() < 250000){
			Date date = new Date();
			sAccount.setOpenedOn(date);
			sAccount.setAccountNumber(MeritBank.getNextAccountNumber());
			sAccount.setIR(.01);
			this.sAccount.add(sAccount);
			this.numberOfSavingsAccount = numberOfSavingsAccount++;
			this.savingsTotalBalance = savingsTotalBalance + sAccount.getBalance(); 
		}		
		return sAccount;
	}
	public List<SavingsAccount> getSavingsAccounts() {
		return sAccount;
		
	}
	public int getNumberOfSavingsAccounts(){
		return numberOfSavingsAccount;
	}
	public double getcheckingTotalBalance(){
		for(CheckingAccount c : cAccount) {
			checkingTotalBalance = c.getBalance();
		}
		return checkingTotalBalance;
	}	
	public double getsavingsTotalBalance(){
		for(SavingsAccount s : sAccount) {
			savingsTotalBalance += s.getBalance();
		}
		return savingsTotalBalance;
	}
	
	public CDAccount addCDAccount(CDOffering offering, double openingBalance) throws ExceedsFraudSuspicionLimitException {
	
		if(openingBalance > 1000) 
			throw new ExceedsFraudSuspicionLimitException();
		else{
			CDAccount cdAccount = new CDAccount(offering, openingBalance);
			this.cdAccount.add(cdAccount);		
			this.cdTotalBalance = cdTotalBalance + openingBalance;
			numberOfCDAccounts++;
			return cdAccount;
		}
	}
	public CDAccount addCDAccount(CDAccount cdAccount) throws ExceedsFraudSuspicionLimitException{
	
		if(cdAccount.getBalance() > 1000)
			throw new ExceedsFraudSuspicionLimitException();
		else{
			Date date = new Date();
			cdAccount.setOpenedOn(date);
			cdAccount.setAccountNumber(MeritBank.getNextAccountNumber());
			this.cdAccount.add(cdAccount);
			this.cdTotalBalance = cdTotalBalance + cdAccount.getBalance();
			numberOfCDAccounts++;
		}
		return cdAccount;
	}
	public List<CDAccount> getCDAccounts(){
		return cdAccount;		
	}
	public int getNumberOfCDAccounts(){
		return numberOfCDAccounts;
	}
	public double getCDBalance(){
		for(CDAccount cd : cdAccount) {
			cdTotalBalance += cd.getBalance();
		}
		return cdTotalBalance;	
	}
	public double getCombinedBalance(){		
		return savingsTotalBalance + checkingTotalBalance + cdTotalBalance;
	}
	public CheckingAccount selectCheckingAccount(int i ) {
		return cAccount.get(i);
	}	
	public SavingsAccount selectSavingsAccount(int i) {
		return sAccount.get(i);		
	}	
	public CDAccount selectCDAccount(int i) {
		return cdAccount.get(i);
	}
	@Override
	public String toString() {
		return 	 lastName + "," + middleName + "," + firstName + "," + ssn +"\n"; 
	}
	
	@Override
	public int compareTo(AccountHolder o) {
		if (this.getCombinedBalance() > o.getCombinedBalance()) 
			return 1;
		else if (this.getCombinedBalance() == o.getCombinedBalance())
			return 0;	
		else
			return -1;
	}	
}	
	
	
	
	
	

