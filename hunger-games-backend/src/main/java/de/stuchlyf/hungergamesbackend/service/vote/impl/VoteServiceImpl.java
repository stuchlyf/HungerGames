package de.stuchlyf.hungergamesbackend.service.vote.impl;

import de.stuchlyf.hungergamesbackend.common.bo.VoteBo;
import de.stuchlyf.hungergamesbackend.common.mapper.VoteMapper;
import de.stuchlyf.hungergamesbackend.persistence.entity.VoteEty;
import de.stuchlyf.hungergamesbackend.persistence.repository.VoteEtyRepository;
import de.stuchlyf.hungergamesbackend.exception.EntityNotFoundException;
import de.stuchlyf.hungergamesbackend.service.vote.VoteService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class VoteServiceImpl implements VoteService {

	private final VoteEtyRepository voteEtyRepo;

	private final VoteMapper voteMapper = Mappers.getMapper(VoteMapper.class);

	@Autowired
	public VoteServiceImpl(VoteEtyRepository voteEtyRepo) {
		this.voteEtyRepo = voteEtyRepo;
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
