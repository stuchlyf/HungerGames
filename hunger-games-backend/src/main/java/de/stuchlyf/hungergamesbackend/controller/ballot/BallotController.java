package de.stuchlyf.hungergamesbackend.controller.ballot;

import de.stuchlyf.hungergamesbackend.common.to.BallotTo;
import org.springframework.http.ResponseEntity;

import java.util.Set;

public interface BallotController {
	
	ResponseEntity<Set<BallotTo>> getAllBallots();

	ResponseEntity<BallotTo> getBallot(Long id);

	ResponseEntity<BallotTo> postBallot(BallotTo ballotTo);

	ResponseEntity<BallotTo> putBallot(Long id, BallotTo ballotTo);

	ResponseEntity<Void> deleteBallot(Long id);
}
