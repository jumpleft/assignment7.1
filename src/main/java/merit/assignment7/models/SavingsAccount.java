package merit.assignment7.models;


import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import merit.assignment7.models.BankAccount;

@Entity
//@Table(name = "savings_account", catalog ="bank")
@DiscriminatorValue("savingsAccount")
public class SavingsAccount extends BankAccount {
	
	private static SavingsAccount sAccount;


	public SavingsAccount() {}
	public SavingsAccount(double bal) {
		super(bal);
	}
	public SavingsAccount(long acNum, double bal, double IR, Date acOpened){
		super(acNum, bal, IR, acOpened);
	}
	@Override
	public void setBalance(double amount) {
		super.setBalance(amount);
	}
	// Get Balance 
	@Override
	public double getBalance(){
		return super.getBalance(); 		
	}
	// Get Interest Rate
	@Override
	public double getInterestRate() {		
		return super.getInterestRate();
	}/*
	public boolean deposit (double amount) throws ExceedsFraudSuspicionLimitException, NegativeAmountException, ExceedsAvailableBalanceException, Exception {		
		 return super.deposit(amount);
	} 
	//Withdraw Balance
	public void withDraw(double amount) throws NegativeAmountException, ExceedsAvailableBalanceException, ExceedsFraudSuspicionLimitException, Exception {
		 super.withdraw(amount);
	}
	public boolean transfer(BankAccount targetAccount, double amount) throws ExceedsFraudSuspicionLimitException, NegativeAmountException, ExceedsAvailableBalanceException, Exception {
		return super.transfer(targetAccount,amount);
	}*/
	@Override
	public double futureValue(int years) {	
		return super.futureValue(years);		
	}	 
	public  Date getStartDate() {
		 return super.getOpenedOn();
	 }
	@Override
	public long getAccountNumber(){
		return super.getAccountNumber();
	}
	/*
	public static SavingsAccount readFromString(String accountData) throws ParseException{
		String[] array = accountData.split(",");
		int accNum = Integer.parseInt(array[0]);
		double bal = Double.parseDouble(array[1]);
		double IR = Double.parseDouble(array[2]);
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse(array[3]);
		sAccount = new SavingsAccount(accNum, bal, IR, date);
		return sAccount;		
	}
	@Override
	public String writeToString() throws IOException {		
		FileWriter write = new FileWriter("AccountDetails.txt", true);
		BufferedWriter buffer = new BufferedWriter(write);
		buffer.write(toString());
		buffer.close();
		return toString();		
		}
	@Override
	@JsonIgnore
	public String toString() {	 
		 return super.toString(); 
	 }*/

}
