package de.stuchlyf.hungergamesbackend.service.ballot;

import de.stuchlyf.hungergamesbackend.common.bo.BallotBo;
import de.stuchlyf.hungergamesbackend.exception.EntityNotFoundException;

import java.util.Set;

public interface BallotService {
	Set<BallotBo> readAllBallots();

	BallotBo readBallotWithId(Long id) throws EntityNotFoundException;

	BallotBo createBallot(BallotBo ballotTo);

	BallotBo updateBallot(Long id, BallotBo ballotBo);

	void deleteBallot(Long id);
}
