package de.stuchlyf.hungergames.backend.service.ballot.impl;

import de.stuchlyf.hungergames.backend.exception.EntityNotFoundException;
import de.stuchlyf.hungergames.backend.common.bo.BallotBo;
import de.stuchlyf.hungergames.backend.common.mapper.BallotMapper;
import de.stuchlyf.hungergames.backend.persistence.entity.BallotEty;
import de.stuchlyf.hungergames.backend.persistence.repository.BallotEtyRepository;
import de.stuchlyf.hungergames.backend.service.ballot.BallotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class BallotServiceImpl implements BallotService {

	private final BallotEtyRepository ballotEtyRepo;

	private final BallotMapper ballotMapper;

	@Autowired
	public BallotServiceImpl(BallotEtyRepository ballotEtyRepo, BallotMapper ballotMapper) {
		this.ballotEtyRepo = ballotEtyRepo;
		this.ballotMapper = ballotMapper;
	}


	@Override
	public Set<BallotBo> readAllBallots() {

		var etys = ballotEtyRepo.findAll();

		return new HashSet<>(ballotMapper.ballotEtysToBallotBos(etys));
	}

	@Override
	public BallotBo readBallotWithId(Long id) throws EntityNotFoundException {
		final var ety = ballotEtyRepo
			.findById(id)
			.orElseThrow(() -> new EntityNotFoundException(BallotEty.class.getSimpleName(), id.toString()));

		return ballotMapper.ballotEtyToBallotBo(ety);
	}

	@Override
	public BallotBo createBallot(BallotBo ballotBo) {
		// TODO: Add Validation
		final var ety = ballotEtyRepo.save(ballotMapper.ballotBoToBallotEty(ballotBo));

		return ballotMapper.ballotEtyToBallotBo(ety);
	}

	@Override
	public BallotBo updateBallot(Long id, BallotBo ballotBo) {
		if (!ballotEtyRepo.existsById(id)) throw new EntityNotFoundException(BallotEty.class.getSimpleName(), id.toString());
		// TODO: Add Validation
		final var ety = ballotEtyRepo.save(ballotMapper.ballotBoToBallotEty(ballotBo));

		return ballotMapper.ballotEtyToBallotBo(ety);
	}

	@Override
	public void deleteBallot(Long id) {
		ballotEtyRepo.deleteById(id);
	}
}
