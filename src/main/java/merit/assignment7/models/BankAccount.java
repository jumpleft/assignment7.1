package merit.assignment7.models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.meritamerica.assignment5.Transactions.*;



@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="bank_type", 
discriminatorType = DiscriminatorType.STRING)
public abstract class BankAccount {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "account")
	protected AccountHolder accountholder;
	
	@Column(name = "account_id")
	protected long account_id;
	
	@Column(name = "bal")
	private double bal;
	@Column(name = "IR")
	private double IR;
	
	
	
	public long getUser_id() {
		return account_id;
	}

	public void setUser_id(long user_id) {
		this.account_id = user_id;
	}
	
	@JsonIgnore
	private static BankAccount bank;
	
	@Column(name = "date_opened")
	private Date openedDate;
	
	/*
	@JsonIgnore
	private WithdrawTransaction withdraw;
	@JsonIgnore
	private DepositTransaction deposit;
	@JsonIgnore
	private TransferTransaction transfer;
	*/
	

	
	//@JsonIgnore
	//private ArrayList<Transaction> transactionList = new ArrayList<Transaction>();
	
	public BankAccount() {}
	
	public BankAccount(double bal){
		this.bal = bal;
	}	
	public BankAccount(CDOffering offering, double balance){
		bal = balance;
		IR = offering.getInterestRate();
		//term = offering.getTerm();
	}		
	public BankAccount(double bal, double IR){	
		this.bal = bal;
		this.IR = IR;
	}
	public BankAccount(double bal, double IR, Date acOpened){	
		this.bal = bal;
		this.IR = IR;
		openedDate = acOpened;
	}
	public BankAccount(long acNum, double bal, double IR, Date acOpened){		
		this.id = acNum;
		this.bal = bal;
		this.IR = IR;
		openedDate = acOpened;
	}
	public void setAccountNumber(long acNum) {
		this.id = acNum;
	}
	public long getAccountNumber(){
		return id;
	}
	public void setBalance(double amount) {
		bal = amount;
	}
	public double getBalance(){
		return bal;
	}

	public double getInterestRate(){
		return IR;
	}
	public void setOpenedOn(Date openedDate) {
		this.openedDate = openedDate;
	}
	public Date getOpenedOn(){	
		return openedDate;
	}/*	
	public boolean withdraw(double amount)throws ExceedsAvailableBalanceException, ExceedsFraudSuspicionLimitException, NegativeAmountException, Exception{
		withdraw = new WithdrawTransaction(MeritBank.getBankAccount(acNum),amount);
		withdraw.setTransactionDate(new Date());
		boolean transaction = MeritBank.processTransaction(withdraw);
		addTransaction(withdraw);
		
		return transaction;
		
	}
	public boolean deposit(double amount) throws ExceedsFraudSuspicionLimitException, NegativeAmountException, ExceedsAvailableBalanceException, Exception{
		deposit = new DepositTransaction(MeritBank.getBankAccount(acNum), amount);
		deposit.setTransactionDate(new Date());
		boolean transaction = MeritBank.processTransaction(deposit);
		addTransaction(deposit);
		
		return transaction;
		
	}	
	public boolean transfer(BankAccount targetAccount, double amount)throws ExceedsFraudSuspicionLimitException, NegativeAmountException, ExceedsAvailableBalanceException, Exception{
		transfer = new TransferTransaction(MeritBank.getBankAccount(acNum), targetAccount, amount);
		transfer.setTransactionDate(new Date());
		boolean transaction = MeritBank.processTransaction(transfer);
		addTransaction(transfer);
		
		
		return transaction;
	}*/
		
	public double futureValue(int years){
		double future = MeritBank.recursiveFutureValue(bal, years, IR);
		return future;			
	}
	/*public int getTerm() {
		return term;
	}*/
	/*public static BankAccount readFromStrings(String accountData) throws ParseException, IOException{
	//Should throw a java.lang.NumberFormatException if string cannot be correctly parsed	
		String[] array = accountData.split(",");
		int accNum = Integer.parseInt(array[0]);
		double bal = Double.parseDouble(array[1]);
		double IR = Double.parseDouble(array[2]);
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse(array[3]);
		return bank;
		
	}
	public String writeToString() throws IOException {
			FileWriter write = new FileWriter("BankAccount.txt");
			BufferedWriter buffer = new BufferedWriter(write);
			buffer.write(toString());
			buffer.close();	
			return toString();		
	}
	/*
	public void addTransaction(Transaction transaction) {		
		transactionList.add(transaction);
	}
	@JsonIgnore
	public ArrayList<Transaction> getTransactions() {
		return 	transactionList;	
	}*/

	public void setIR(double IR) {
		this.IR = IR;
	}
	
	@Override
	@JsonIgnore
	public String toString() {
		return id +  "," + bal + "," /*+ term */+ "," + openedDate +"\n"; 
	}
}
	













