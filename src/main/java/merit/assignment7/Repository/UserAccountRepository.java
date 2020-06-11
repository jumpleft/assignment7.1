package merit.assignment7.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



import merit.assignment7.models.UserAccount;


@Repository
public interface UserAccountRepository extends JpaRepository <UserAccount, Long> {
	
	UserAccount findByUsername(String username);

}