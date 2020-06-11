package merit.assignment7.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import merit.assignment7.models.SavingsAccount;

public interface SavingsRepository extends JpaRepository<SavingsAccount, Long> {

	public SavingsAccount findById(long id);
}
