package merit.assignment7.models;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
//@Table(name = "checking_account")
@DiscriminatorValue("checkingAccount")
public class CheckingAccount extends BankAccount{
	
	private static CheckingAccount cAccount;
	
	
	public CheckingAccount(double bal) {
		super(bal);		
	}
	
	public CheckingAccount() {}
	
	public CheckingAccount(long acNum, double bal, double IR, Date acOpened) {		
		super(acNum, bal, IR, acOpened);		
	}
	// Get Balance 
	@Override
	public double getBalance(){
		return super.getBalance(); 		
	}	
	@Override
	public void setBalance(double amount) {
		super.setBalance(amount);
	}	
	// Get Interest Rate
	@Override
	public double getInterestRate() {		
		return super.getInterestRate();
	}
	/*
	//Withdraw Balance
	public boolean withdraw(double amount) throws NegativeAmountException, ExceedsAvailableBalanceException, ExceedsFraudSuspicionLimitException, Exception {	
		return super.withdraw(amount);		 
	}
	public boolean deposit (double amount) throws ExceedsFraudSuspicionLimitException, NegativeAmountException, ExceedsAvailableBalanceException, Exception {	
		return super.deposit(amount);		
	}
	public boolean transfer(BankAccount targetAccount, double amount) throws ExceedsFraudSuspicionLimitException, NegativeAmountException, ExceedsAvailableBalanceException, Exception {
		return super.transfer(targetAccount,amount);
	}*/
	@Override
	public double futureValue(int years) {	
		return super.futureValue(years);			 
	}
	@Override
	public void setOpenedOn(Date openedDate) {
		super.setOpenedOn(openedDate);
	}
	public Date getStartDate() {
		 return super.getOpenedOn();
	 }
	@Override
	public void setAccountNumber(long acNum) {
		super.setAccountNumber(acNum);
	}
	@Override
	public long getAccountNumber(){		
		return super.getAccountNumber();
	}
	/*
	public static CheckingAccount readFromString(String accountData)throws ParseException{		
		
		String[] array = accountData.split(",");		
		int accNum = Integer.parseInt(array[0]);
		double bal = Double.parseDouble(array[1]);
		double IR = Double.parseDouble(array[2]);
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse(array[3]);
		cAccount = new CheckingAccount(accNum, bal, IR, date);	
		return cAccount;		
	}	
	@Override
	public String writeToString() throws IOException {
		FileWriter write = new FileWriter("AccountDetails.txt",true);
		BufferedWriter buffer = new BufferedWriter(write);
		buffer.write(toString());
		buffer.close();		
		return(toString());
	}
	@Override
	@JsonIgnore
	public String toString() {
		return super.toString(); 
	 }*/
}
	
