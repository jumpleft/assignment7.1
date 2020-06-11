package merit.assignment7.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import merit.assignment7.models.AccountHolderContactDetails;

public interface AccountContactRepository extends JpaRepository<AccountHolderContactDetails, Long> {

}
