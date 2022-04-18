package de.stuchlyf.hungergamesbackend.service.vote;

import de.stuchlyf.hungergamesbackend.common.bo.VoteBo;
import de.stuchlyf.hungergamesbackend.exception.EntityNotFoundException;

import java.util.Set;
import java.util.UUID;

public interface VoteService {
	Set<VoteBo> readAllVotes();

	VoteBo readVoteWithId(UUID id) throws EntityNotFoundException;

	VoteBo createVote(VoteBo voteTo);

	VoteBo updateVote(UUID id, VoteBo voteBo);

	void deleteVote(UUID id);
}
