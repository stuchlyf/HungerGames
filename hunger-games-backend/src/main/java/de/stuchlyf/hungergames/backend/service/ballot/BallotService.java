package de.stuchlyf.hungergames.backend.service.ballot;

import de.stuchlyf.hungergames.backend.exception.EntityNotFoundException;
import de.stuchlyf.hungergames.backend.common.bo.BallotBo;

import java.util.Set;

public interface BallotService {
	Set<BallotBo> readAllBallots();

	BallotBo readBallotWithId(Long id) throws EntityNotFoundException;

	BallotBo createBallot(BallotBo ballotTo);

	BallotBo updateBallot(Long id, BallotBo ballotBo);

	void deleteBallot(Long id);
}
