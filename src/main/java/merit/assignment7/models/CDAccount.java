package merit.assignment7.models;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import merit.assignment7.ErrorHandling.ExceedsFraudSuspicionLimitException;




@Entity
//@Table(name = "cd_account")
@DiscriminatorValue("cdAccount")
public class CDAccount extends BankAccount{

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cd_id")
	private CDOffering offering;
	
	private int term;
	private double IR;
	
	public double getIR() {
		return IR;
	}
	public void setIR(double iR) {
		IR = iR;
	}


	private static CDAccount cdAccount;

	public CDAccount() {}
	public CDAccount(CDOffering offering, double balance) {
		super(offering,balance);
		term = offering.getTerm();
		IR = offering.getInterestRate();
	}	
	

	
	public CDOffering getOffering() {
		return offering;
	}
	public void setOffering(CDOffering offering) {
		this.offering = offering;
	}
	public CDAccount(long acNum, double bal, double IR, Date acOpened , int term){
		super(acNum, bal, IR, acOpened);
		this.term =term;
	}
	@Override
	public double getBalance(){
		return super.getBalance();
	}
	@Override
	public double getInterestRate(){		
		return super.getInterestRate();
	}

	public int getTerm(){
		return term;
	}
	@Override
	public Date getOpenedOn(){
		return super.getOpenedOn();
	}

	public boolean withdraw(CDAccount cdAccountdouble, double amount) throws  ExceedsFraudSuspicionLimitException{
		throw new ExceedsFraudSuspicionLimitException();		
	}
		
	public boolean deposit(CDAccount cdAccountdouble,double amount) throws ExceedsFraudSuspicionLimitException{
		throw new ExceedsFraudSuspicionLimitException();
	}

	@Override
	public long getAccountNumber(){
		return super.getAccountNumber();
	}


	public static CDAccount readFromString(String accountData) throws ParseException{	
		String[] string = accountData.split(",");		
		int accNum = Integer.parseInt(string[0]);
		double bal = Double.parseDouble(string[1]);
		double IR = Double.parseDouble(string[2]);
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse(string[3]);
		int term = Integer.parseInt(string[4]);
		cdAccount = new CDAccount(accNum, bal, IR, date, term);		
		return cdAccount;
	}
/*
	@Override
	public String writeToString() throws IOException{
		
		FileWriter write = new FileWriter("AccountDetails.txt",true);
		BufferedWriter buffer = new BufferedWriter(write);
		buffer.write(toString());
		buffer.close();
		return toString();	
	}
	public double futureValue(){
		super.setIR(IR);
		return super.futureValue(term);
	}
	@Override
	@JsonIgnore
	 public String toString() {
		 return  getAccountNumber() + ", " + getBalance() + ", "+ getInterestRate() + ", " + term + ", " + getOpenedOn();
	 }*/
}
