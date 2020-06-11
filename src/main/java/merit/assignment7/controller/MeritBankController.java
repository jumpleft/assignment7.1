package merit.assignment7.controller;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

//import com.meritamerica.assignment6.*;
import merit.assignment7.ErrorHandling.BadRequestError;
import merit.assignment7.ErrorHandling.ExceedsCombinedBalanceLimitException;
import merit.assignment7.ErrorHandling.ExceedsFraudSuspicionLimitException;
import merit.assignment7.ErrorHandling.FileNotFoundError;
import merit.assignment7.models.AccountHolder;
import merit.assignment7.models.CDAccount;
import merit.assignment7.models.CDOffering;
import merit.assignment7.models.CheckingAccount;
import merit.assignment7.models.MeritBank;
import merit.assignment7.models.SavingsAccount;

@RestController
public class MeritBankController {
	
	
	private List<AccountHolder> accounts = MeritBank.getAccountHolders();
	private Map<Long,AccountHolder> list= new HashMap<Long, AccountHolder>();
	private AccountHolder account;
	//private int index;
	
	
	@PostMapping(value= "/AccountHolders", consumes = "application/json" )
	public @ResponseBody @ResponseStatus(HttpStatus.CREATED) AccountHolder addAccount(@RequestBody @Valid AccountHolder account) {
		MeritBank.addAccountHolder(account);
		addToList(account);
		return account;			
	}
	
	@GetMapping(value ="/AccountHolders")
	public @ResponseBody List<AccountHolder> getAccounts() {
		return accounts;
	}

	@GetMapping(value ="/AccountHolders/{id}")
	public @ResponseBody @Valid AccountHolder getAccountID(@PathVariable long id) throws FileNotFoundError{
	//	index = id -1;
		//account = MeritBank.getAccounts(index);
		account = getAccount(id);
		if(account == null) {
			throw new FileNotFoundError();
		}
		else 
			return account;	
	}
	
	@PostMapping(value="/AccountHolders/{id}/CheckingAccounts")
	public @ResponseBody @ResponseStatus(HttpStatus.CREATED) CheckingAccount addCheckingAccount(@RequestBody CheckingAccount cAccount, @PathVariable long id ) 
			throws ExceedsCombinedBalanceLimitException, ExceedsFraudSuspicionLimitException, Exception, FileNotFoundError,BadRequestError {
		//index = id -1;
		//account = MeritBank.getAccounts(index);	
		account = getAccount(id);
	    
		if(account == null) {
			throw new FileNotFoundError();
		}
		else if ( cAccount.getBalance() < 0) {
			throw new BadRequestError();
		}
		else if (account.getCombinedBalance() + cAccount.getBalance() > 250000) {
			throw new BadRequestError();
		}
		else {
			account.addCheckingAccount(cAccount);
			return cAccount;
		}	
	}	
	@GetMapping(value = "/AccountHolders/{id}/CheckingAccounts")
	public @ResponseBody List<CheckingAccount> getCheckingAccounts(@PathVariable long id) throws FileNotFoundError {
		//index = id -1;
		//account = MeritBank.getAccounts(index);
		account = getAccount(id);
	    if(account == null) {
			throw new FileNotFoundError();
		}
	    else 
	    	return account.getCheckingAccounts();
	    
	}	
	@PostMapping(value = "/AccountHolders/{id}/SavingsAccounts")
	public @ResponseBody @ResponseStatus(HttpStatus.CREATED)SavingsAccount addSavingsAccount(@RequestBody SavingsAccount sAccount, @PathVariable long id) 
			throws ExceedsCombinedBalanceLimitException, ExceedsFraudSuspicionLimitException, Exception,FileNotFoundError,BadRequestError {
		//index = id -1;
		
		//account = MeritBank.getAccounts(index);
		
		account = getAccount(id);
	    
		if(account == null) {
			throw new FileNotFoundError();
		}
		else if ( sAccount.getBalance() < 0) {
			throw new BadRequestError();
		}
		else if (account.getCombinedBalance() + sAccount.getBalance() > 250000) {
			throw new BadRequestError();
		}
		else {
			account.addSavingsAccount(sAccount);
			return sAccount;
		}	
	}
	@GetMapping(value = "/AccountHolders/{id}/SavingsAccounts")
	public @ResponseBody List<SavingsAccount> getSavingsAccount(@PathVariable long id) {
		//index = id -1;
		
		//account = MeritBank.getAccounts(index);
		account = getAccount(id);
	    
		if(account == null) {
			throw new FileNotFoundError();
		}
		else 
			return account.getSavingsAccounts();
		
	}	
	@PostMapping(value = "/AccountHolders/{id}/CDAccounts")
	public @ResponseBody @ResponseStatus(HttpStatus.CREATED) CDAccount addCDAccount(@RequestBody CDAccount cdAccount, @PathVariable long id) 
			throws ExceedsFraudSuspicionLimitException,FileNotFoundError,BadRequestError {
		//index = id -1;
		
		//account = MeritBank.getAccounts(index);
	    
		account = getAccount(id);
		if(account == null) {
			throw new FileNotFoundError();
		}
		else if ( cdAccount.getBalance() < 0) {
			throw new BadRequestError();
		}
		else if (account.getCombinedBalance() + cdAccount.getBalance() > 250000) {
			throw new BadRequestError();
		}
		else {
			account.addCDAccount(cdAccount);
			return cdAccount;
		}	
	}
	
	@GetMapping(value = "/AccountHolders/{id}/CDAccounts")
	public @ResponseBody List<CDAccount> getCDAccount(@PathVariable long id) {
		//index = id -1;
		
		//account = MeritBank.getAccounts(index);
		account = getAccount(id);
	    
		if(account == null) {
			throw new FileNotFoundError();
		}
		else 
		return account.getCDAccounts();
		
	}
	
	@PostMapping(value ="CDOfferings")
	public @ResponseBody @ResponseStatus(HttpStatus.CREATED) CDOffering addCDOffering(@RequestBody CDOffering cdoffering) {
		MeritBank.setCDOfferings(cdoffering);
		return cdoffering;
	}
	@GetMapping(value = "CDOfferings")
	public @ResponseBody List<CDOffering> getCDOffering() {
		return MeritBank.getCDOfferings();
	}
	
	public void addToList(AccountHolder account) {
		list.put(account.getID(),account);
	}
	
	public AccountHolder getAccount(long id) {
		return list.get(id);
	}
}
