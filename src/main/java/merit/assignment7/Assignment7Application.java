//testing testicals

package merit.assignment7;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import merit.assignment7.ErrorHandling.ExceedsAvailableBalanceException;
import merit.assignment7.ErrorHandling.ExceedsFraudSuspicionLimitException;
import merit.assignment7.ErrorHandling.NegativeAmountException;

@SpringBootApplication
public class Assignment7Application {

	public static void main(String[] args) throws NegativeAmountException, ExceedsAvailableBalanceException, ExceedsFraudSuspicionLimitException, Exception  {
		SpringApplication.run(Assignment7Application.class, args);
	}

}
