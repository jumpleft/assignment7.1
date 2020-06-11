package merit.assignment7.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import merit.assignment7.models.*;

public interface CDOfferingRepository extends JpaRepository<CDOffering, Long> {

	public CDOffering findById(long id);
}
