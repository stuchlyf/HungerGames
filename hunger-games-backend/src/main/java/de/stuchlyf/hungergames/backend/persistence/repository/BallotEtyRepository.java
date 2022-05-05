package de.stuchlyf.hungergames.backend.persistence.repository;

import de.stuchlyf.hungergames.backend.persistence.entity.BallotEty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface BallotEtyRepository extends JpaRepository<BallotEty, Long> {
	
	List<BallotEty> findALlByStartDateAfterOrderByStartDate(LocalDateTime startDate);
	
}
