package de.stuchlyf.hungergames.backend.controller.vote;

import de.stuchlyf.hungergames.backend.to.VoteTo;
import org.springframework.http.ResponseEntity;

import java.util.Set;

public interface VoteController {
	ResponseEntity<Set<VoteTo>> getAllVotes();

	ResponseEntity<VoteTo> getVote(String id);

	ResponseEntity<VoteTo> postVote(VoteTo voteTo);

	ResponseEntity<VoteTo> putVote(String id, VoteTo voteTo);

	ResponseEntity<Void> deleteVote(String id);
}
