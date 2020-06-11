package merit.assignment7.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import merit.assignment7.models.BankAccount;

public interface BankAccountsRepository extends JpaRepository<BankAccount, Integer> {

	public BankAccount findById(long id);
}
