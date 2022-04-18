package de.stuchlyf.hungergamesbackend.persistence.repository;

import de.stuchlyf.hungergamesbackend.persistence.entity.BallotEty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface BallotEtyRepository extends JpaRepository<BallotEty, Long> {
	
	List<BallotEty> findALlByStartDateAfterOrderByStartDate(LocalDateTime startDate);
	
}
