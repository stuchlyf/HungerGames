package de.stuchlyf.hungergamesbackend.service.ballot.impl;

import de.stuchlyf.hungergamesbackend.common.bo.BallotBo;
import de.stuchlyf.hungergamesbackend.common.mapper.BallotMapper;
import de.stuchlyf.hungergamesbackend.persistence.entity.BallotEty;
import de.stuchlyf.hungergamesbackend.persistence.repository.BallotEtyRepository;
import de.stuchlyf.hungergamesbackend.service.ballot.BallotService;
import de.stuchlyf.hungergamesbackend.exception.EntityNotFoundException;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class BallotServiceImpl implements BallotService {

	private final BallotEtyRepository ballotEtyRepo;

	private final BallotMapper ballotMapper = Mappers.getMapper(BallotMapper.class);

	@Autowired
	public BallotServiceImpl(BallotEtyRepository ballotEtyRepo) {
		this.ballotEtyRepo = ballotEtyRepo;
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
