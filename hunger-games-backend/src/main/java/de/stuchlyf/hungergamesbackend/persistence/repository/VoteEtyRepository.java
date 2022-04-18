package de.stuchlyf.hungergamesbackend.persistence.repository;

import de.stuchlyf.hungergamesbackend.persistence.entity.VoteEty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface VoteEtyRepository extends JpaRepository<VoteEty, UUID> {
	
	@Query("SELECT v.voted, COUNT(DISTINCT v) FROM Vote v WHERE v.ballot.id = ?1 GROUP BY v.voted")
	List<Long> findAllByBallotIdAndGroupByVoted(Long ballotId);
}
