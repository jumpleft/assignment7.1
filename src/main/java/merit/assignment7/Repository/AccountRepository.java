package merit.assignment7.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import merit.assignment7.models.AccountHolder;

public interface AccountRepository extends JpaRepository<AccountHolder, Long>{

	public AccountHolder findById(long id);
}
