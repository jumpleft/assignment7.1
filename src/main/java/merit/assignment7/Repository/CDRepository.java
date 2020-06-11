package merit.assignment7.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import merit.assignment7.models.*;

public interface CDRepository extends JpaRepository<CDAccount, Long> {

	public CDAccount findById(long id);
}
