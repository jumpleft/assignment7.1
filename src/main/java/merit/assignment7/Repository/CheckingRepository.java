package merit.assignment7.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import merit.assignment7.models.*;

public interface CheckingRepository extends JpaRepository<CheckingAccount, Long> {

	public CheckingAccount findById(long id);
	
}
