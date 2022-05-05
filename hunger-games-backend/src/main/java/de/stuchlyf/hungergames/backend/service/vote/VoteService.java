package de.stuchlyf.hungergames.backend.service.vote;

import de.stuchlyf.hungergames.backend.exception.EntityNotFoundException;
import de.stuchlyf.hungergames.backend.common.bo.VoteBo;

import java.util.Set;
import java.util.UUID;

public interface VoteService {
	Set<VoteBo> readAllVotes();

	VoteBo readVoteWithId(UUID id) throws EntityNotFoundException;

	VoteBo createVote(VoteBo voteTo);

	VoteBo updateVote(UUID id, VoteBo voteBo);

	void deleteVote(UUID id);
}
