package de.stuchlyf.hungergames.backend.service.vote.impl;

import de.stuchlyf.hungergames.backend.common.bo.VoteBo;
import de.stuchlyf.hungergames.backend.common.mapper.VoteMapper;
import de.stuchlyf.hungergames.backend.exception.EntityNotFoundException;
import de.stuchlyf.hungergames.backend.persistence.entity.VoteEty;
import de.stuchlyf.hungergames.backend.persistence.repository.VoteEtyRepository;
import de.stuchlyf.hungergames.backend.service.vote.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class VoteServiceImpl implements VoteService {

	private final VoteEtyRepository voteEtyRepo;

	private final VoteMapper voteMapper;

	@Autowired
	public VoteServiceImpl(VoteEtyRepository voteEtyRepo, VoteMapper voteMapper) {
		this.voteEtyRepo = voteEtyRepo;
		this.voteMapper = voteMapper;
	}


	@Override
	public Set<VoteBo> readAllVotes() {

		var etys = voteEtyRepo.findAll();

		return new HashSet<>(voteMapper.voteEtysToVoteBos(etys));
	}

	@Override
	public VoteBo readVoteWithId(UUID id) throws EntityNotFoundException {
		final var ety = voteEtyRepo
			.findById(id)
			.orElseThrow(() -> new EntityNotFoundException(VoteEty.class.getSimpleName(), id.toString()));

		return voteMapper.voteEtyToVoteBo(ety);
	}

	@Override
	public VoteBo createVote(VoteBo voteBo) {
		// TODO: Add Validation
		final var ety = voteEtyRepo.save(voteMapper.voteBoToVoteEty(voteBo));

		return voteMapper.voteEtyToVoteBo(ety);
	}

	@Override
	public VoteBo updateVote(UUID id, VoteBo voteBo) {
		if (!voteEtyRepo.existsById(id)) throw new EntityNotFoundException(VoteEty.class.getSimpleName(), id.toString());
		// TODO: Add Validation
		final var ety = voteEtyRepo.save(voteMapper.voteBoToVoteEty(voteBo));

		return voteMapper.voteEtyToVoteBo(ety);
	}

	@Override
	public void deleteVote(UUID id) {
		voteEtyRepo.deleteById(id);
	}
}
