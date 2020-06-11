package merit.assignment7.models;
import java.util.ArrayList;
import java.util.Collections;

//import com.meritamerica.assignment5.Transactions.*;


import java.util.List;

import merit.assignment7.models.AccountHolder;
import merit.assignment7.models.BankAccount;
import merit.assignment7.models.CDOffering;

public class MeritBank {


	private static List<AccountHolder> ah = new ArrayList<AccountHolder>();
	private static List<CDOffering> cdOfferings = new ArrayList<CDOffering>();
	private static int numOfCDOffering;
	private static long accountNum;
	private static int numOfAccountHolder;
	private static BankAccount account;
	private static long accountNext;

	public static void addAccountHolder(AccountHolder account){
		numOfAccountHolder++;
		setAccountNumber();
		account.setID(accountNum);
		ah.add(account);
	}
	public static List<AccountHolder> getAccountHolders(){
		return ah;		
	}	
	
	public static List<CDOffering> getCDOfferings(){
		return cdOfferings;		
	}
	
	public static CDOffering getBestCDOffering(double depositAmount){
		CDOffering bestCDOffering = null;
		double bestValue = 0;				
		for(CDOffering cd : cdOfferings) {
			double futureValue = recursiveFutureValue(depositAmount, cd.getTerm(), cd.getInterestRate());		
			if (futureValue > bestValue) {
				bestValue =futureValue;
				bestCDOffering = cd;
			}			
		}			
		return bestCDOffering;		
	}
	public static CDOffering getSecondBestCDOffering(double depositAmount){
		CDOffering bestCDOffering = null;
		CDOffering secondCDOffering = null;
		double bestvalue = 0;
		double secondBest =0;
		for(CDOffering cd: cdOfferings) {
			double futureValue = recursiveFutureValue(depositAmount, cd.getTerm(), cd.getInterestRate());
			if (futureValue > bestvalue && secondBest <= bestvalue || secondBest >= futureValue) {
				bestCDOffering = cd;
				bestvalue =futureValue;
				secondBest = futureValue;
			}
			
		}		
		return secondCDOffering;					
	}
	public static void clearCDOfferings(){
		cdOfferings = null;
	}
	public static void setCDOfferings(CDOffering offerings){
	
		cdOfferings.add(offerings);	
		numOfCDOffering++;
	}
	public static void setAccountNumber() {
		accountNum++;
	}
	public static long getAccountNumber() {
		return accountNum;
	}
	
	public static void setNextAccountNumber(long nextAccountNumber){
		accountNext = nextAccountNumber;
	}
	
	public static long getNextAccountNumber(){
		
		return ++accountNext;
	}
	public static double totalBalances(){
		double totalBalance =0;
		
		for(AccountHolder a : ah) {
			totalBalance += a.getCombinedBalance();
		}
		return totalBalance;
	} 
	/*
	public static boolean readFromFile(String fileName) throws ExceedsCombinedBalanceLimitException, ExceedsFraudSuspicionLimitException, Exception   {
	try {
		FileReader read = new FileReader(fileName);
		BufferedReader buffer = new BufferedReader(read);
		String line;
		
		//Set AccountNumber:
		line = buffer.readLine();
		String[] array = line.split(",");
		long accNum = Long.parseLong(array[0]);
		setNextAccountNumber(accNum);
		array = null;
			
		//Set Number of CD Offering:	
		line = buffer.readLine();
		array = line.split(",");
		int numCDOffering = Integer.parseInt(array[0]);
		array = null;
			
		//Set CD Offerings:	
		for(int i = 0; i < numCDOffering; i++) {
			line = buffer.readLine();
			String[] cdArray = line.split(",");
			int term = Integer.parseInt(cdArray[0]);
			double offer = Double.parseDouble(cdArray[1]);
			cdOfferings.add(new CDOffering(term, offer));
		}
			
		//Set Number of Account Holders:
		line = buffer.readLine();
		array = line.split(",");
		int numAccountHolder = Integer.parseInt(array[0]);
		array = null;
			
		//Account creation:
		for(int i =0; i<numAccountHolder; i++) {
			line = buffer.readLine();
			String[] arrayAccountCreation = line.split(",");
			String lastName = arrayAccountCreation[0];
			String middleName = arrayAccountCreation[1];
			String firstName = arrayAccountCreation[2];
			String ssn = arrayAccountCreation[3];
			ah.add(new AccountHolder(firstName, middleName, lastName, ssn));
			//Check number of Checking Accounts:
			line = buffer.readLine();
			int numChkAccount = Integer.parseInt(line);
			//Create checking account if present:
			if(numChkAccount !=0) {
				for(int c = 0; c < numChkAccount; c++) {
					line = buffer.readLine();	
					ah.get(i).addCheckingAccount(CheckingAccount.readFromString(line));					
					//Check number of Transaction in Checking:
					line = buffer.readLine();
					int chkTransaction = Integer.parseInt(line);		
					//Create Transaction:
					if(chkTransaction != 0) {
						for(int ct = 0; ct < chkTransaction; ct++) {						
							line = buffer.readLine();
							Transaction.readFromString(line);	
						}								
					}
				}
			//Checking Loops Ends:	
			}	
			//Check for Number of Savings Account:
			line = buffer.readLine();
			int numSavAccount = Integer.parseInt(line);
				
			//Create Savings Account if not 0:
			if(numSavAccount != 0) {
				for(int s = 0; s < numSavAccount; s++) {
					line = buffer.readLine();
					ah.get(i).addSavingsAccount(SavingsAccount.readFromString(line));
					//Check number of Transaction in Savings:
					line = buffer.readLine();
					int savTransaction = Integer.parseInt(line);		
					//Create Transaction:
					if(savTransaction != 0) {
						for(int st = 0; st < savTransaction; st++) {
							line = buffer.readLine();
							Transaction.readFromString(line);
						}												
					}
				}
			//Savings Loop Ends:	
			}	
			//Check Number of CD Accounts:
			line = buffer.readLine();
			int numCDAccounts = Integer.parseInt(line);		
			//Create CD Accounts if not 0:
			if(numCDAccounts !=0) {
				for(int cd = 0; cd < numCDAccounts; cd++) {
					line = buffer.readLine();
					ah.get(i).addCDAccount(CDAccount.readFromString(line));
					line = buffer.readLine();
					int cdTransaction = Integer.parseInt(line);				
					//Create Transaction:
					if(cdTransaction != 0) {
						for(int st = 0; st < cdTransaction; st++) {
							line = buffer.readLine();
							Transaction.readFromString(line);
						}							
					}
				}		
			//CDAccounts Loop Ends:
			}			
		//AccountHolder Loop Ends:
		}
		line = buffer.readLine();
		//Number of Fraud Queue:
		int numOfFraud = Integer.parseInt(line);
		//Create FraudQueue:
		if(numOfFraud !=0) {
		for(int i = 0; i < numOfFraud; i++) {
				line = buffer.readLine();
				String fraud[] = line.split(",");
				int transID = Integer.parseInt(fraud[0]);
				int frauNum = Integer.parseInt(fraud[1]);
				double amount = Double.parseDouble(fraud[2]);
				Date date = new SimpleDateFormat("dd/MM/yyyy").parse(fraud[3]);
				if(transID < 0 && amount >1000) {
					WithdrawTransaction withdraw = new WithdrawTransaction(MeritBank.getBankAccount(frauNum), amount);
					withdraw.setTransactionDate(date);
					FraudQueue.enqueue(withdraw);
				}
				else if(transID>0 && amount >1000) {
					TransferTransaction transfer = new TransferTransaction(MeritBank.getBankAccount(transID), MeritBank.getBankAccount(frauNum), amount);
					transfer.setTransactionDate(date);
					FraudQueue.enqueue(transfer);
				}
			}
		}
		buffer.close();
	}	
	catch(IOException e) {
		e.printStackTrace();
		return false;
		}
		
	catch(ParseException e) {
		e.printStackTrace();
		return false;
		}
		
	catch(NumberFormatException e) {
		e.printStackTrace();
		return false;
		}
	return true;
	}

	public static boolean writeToFile(String fileName) {
	try {
		FileWriter write = new FileWriter(fileName, true);
		BufferedWriter buffer = new BufferedWriter(write);
				
		buffer.write(Long.toString(accountNext) + "\n");
		buffer.write(Integer.toString(numOfCDOffering) + "\n");
		for(CDOffering cd : cdOfferings){
			buffer.write(cd.toString());
			}
		
		buffer.write(Integer.toString(numOfAccountHolder)+  "\n");
		for(AccountHolder a : ah) {
			if(a != null) {
				buffer.write(account.toString());
				buffer.write(a.getNumberOfCheckingAccounts() + "\n");
				if(a.getNumberOfCheckingAccounts() != 0) {
					for(int ck =0; ck < a.getNumberOfCheckingAccounts(); ck++) {
						if(a.selectCheckingAccount(ck) != null) { 
							buffer.write(a.selectCheckingAccount(ck).toString());
							System.out.println(a.selectCheckingAccount(ck).toString());
							buffer.write(a.selectCheckingAccount(ck).getTransactions().toString());
							System.out.println(a.selectCheckingAccount(ck).getTransactions().toString());
							
							}
						}
					}
				
				if(a.getNumberOfSavingsAccounts() != 0) {
					for(int sav =0; sav<a.getNumberOfSavingsAccounts(); sav++) {
						if(a.selectSavingsAccount(sav) != null) {
							buffer.write(a.selectSavingsAccount(sav).toString());
							buffer.write(a.selectSavingsAccount(sav).getTransactions().toString());
							}
						}
				
					}
				
				if(a.getNumberOfCDAccounts() !=0) {
					for(int cd =0; cd <a.getNumberOfCDAccounts(); cd++) {
						if(a.selectCDAccount(cd) != null) {
							buffer.write(a.selectCDAccount(cd).toString());
							buffer.write(a.selectCDAccount(cd).getTransactions().toString());						
							
							}
						}
					}	
		
			}
			
		}
		buffer.close();
		
	}
		
		catch(IOException e) {
			e.printStackTrace();
		}
		
		return true;
	}*/
	
	public static List<AccountHolder> sortAccountHolders() {
		Collections.sort(ah);
		return ah;
		
	}
	
		public	static int numberAccountHolders() {	
		return numOfAccountHolder;
	}/*
	public static boolean processTransaction(Transaction transaction) throws NegativeAmountException, ExceedsAvailableBalanceException, 
	ExceedsFraudSuspicionLimitException, Exception{
	
		if(transaction.withdrawTrue()) {
			return ProcessTransactions.processWithdraw(transaction);
		}
		
		else if(transaction.transferTrue()) {
			return ProcessTransactions.processTransfer(transaction);
		}
		else
			return ProcessTransactions.processDeposit(transaction);
		
	}
	
	public static  FraudQueue getFraudQueue() {
		FraudQueue fraud = new FraudQueue();
		return fraud;
	}*/
		
		public static double recursiveFutureValue(double amount, int years, double interestRate) {
			double future = 1;	
			if (years < 1)
					return future *amount;
				else 
					return (1+interestRate) * recursiveFutureValue(amount,years-1,interestRate);
		}
	public static BankAccount getBankAccount(long accountId) {
	
		for(AccountHolder a : ah) {
				for(int c= 0; c < a.getCheckingAccounts().size(); c++) {
					if(a.selectCheckingAccount(c) !=null) {
						if(a.selectCheckingAccount(c).getAccountNumber() == accountId) {
							account = a.selectCheckingAccount(c);
							return account;
						}
					}
				}	
				for(int s= 0; s <a.getSavingsAccounts().size(); s++) {
					if(a.selectSavingsAccount(s) !=null) {	
						if(a.selectSavingsAccount(s).getAccountNumber() == accountId) {
							account = a.selectSavingsAccount(s);
							return account;
						}
					}
				}	
				for(int cd = 0; cd <a.getCDAccounts().size(); cd++) {
					if(a.selectCDAccount(cd) !=null) {
						if(a.selectCDAccount(cd).getAccountNumber() == accountId) {
							account = a.selectCDAccount(cd);
							return account;
						}
					}
				}					
		}					
		return account;
	}
}


