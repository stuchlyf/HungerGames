package de.stuchlyf.hungergames.backend.controller.vote.impl;

import de.stuchlyf.hungergames.backend.controller.vote.VoteController;
import de.stuchlyf.hungergames.backend.service.vote.VoteService;
import de.stuchlyf.hungergames.backend.to.VoteTo;
import de.stuchlyf.hungergames.backend.common.mapper.UuidMapper;
import de.stuchlyf.hungergames.backend.common.mapper.VoteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("vote-service")
public class VoteControllerImpl implements VoteController {

	private final VoteService voteSvc;
	private final VoteMapper voteMapper;

	@Autowired
	public VoteControllerImpl(VoteService voteSvc, VoteMapper voteMapper) {
		this.voteSvc = voteSvc;
		this.voteMapper = voteMapper;
	}

	@Override
	@GetMapping("votes")
	public ResponseEntity<Set<VoteTo>> getAllVotes() {
		final var bos = voteSvc.readAllVotes();
		final var tos = new HashSet<>(voteMapper.voteBosToVoteTos(bos));

		return ResponseEntity.ok(tos);
	}

	@Override
	@GetMapping("vote/{id}")
	public ResponseEntity<VoteTo> getVote(@PathVariable String id) {
		final var bo = voteSvc.readVoteWithId(UuidMapper.stringToUuid(id));
		final var to = voteMapper.voteBoToVoteTo(bo);

		return ResponseEntity.ok(to);
	}

	@Override
	@PostMapping("vote")
	public ResponseEntity<VoteTo> postVote(@RequestBody VoteTo voteTo) {
		final var bo = voteSvc.createVote(voteMapper.voteToToVoteBo(voteTo));
		final var to = voteMapper.voteBoToVoteTo(bo);

		return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(to.getId()).toUri()).build();
	}

	@Override
	@PutMapping("vote/{id}")
	public ResponseEntity<VoteTo> putVote(@PathVariable String id, @RequestBody VoteTo voteTo) {
		voteSvc.updateVote(UuidMapper.stringToUuid(id), voteMapper.voteToToVoteBo(voteTo));

		return ResponseEntity.noContent().location(ServletUriComponentsBuilder.fromCurrentRequest().build().toUri()).build();
	}

	@Override
	@DeleteMapping("vote/{id}")
	public ResponseEntity<Void> deleteVote(@PathVariable String id) {
		voteSvc.deleteVote(UuidMapper.stringToUuid(id));

		return ResponseEntity.noContent().build();
	}
}
