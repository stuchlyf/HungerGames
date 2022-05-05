package de.stuchlyf.hungergamesbackend.controller.ballot.impl;

import de.stuchlyf.hungergamesbackend.common.mapper.BallotMapper;
import de.stuchlyf.hungergamesbackend.controller.ballot.BallotController;
import de.stuchlyf.hungergamesbackend.service.ballot.BallotService;
import de.stuchlyf.hungergamesbackend.to.BallotTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("ballot-service")
public class BallotControllerImpl implements BallotController {
	
	private final BallotService ballotSvc;
	private final BallotMapper ballotMapper;

	@Autowired
	public BallotControllerImpl(BallotService ballotSvc, BallotMapper ballotMapper) {
		this.ballotSvc = ballotSvc;
		this.ballotMapper = ballotMapper;
	}

	@Override
	@GetMapping("ballots")
	public ResponseEntity<Set<BallotTo>> getAllBallots() {
		final var bos = ballotSvc.readAllBallots();
		final var tos = new HashSet<>(ballotMapper.ballotBosToBallotTos(bos));
		
		return ResponseEntity.ok(tos);
	}

	@Override
	@GetMapping("ballot/{id}")
	public ResponseEntity<BallotTo> getBallot(@PathVariable Long id) {
		final var bo = ballotSvc.readBallotWithId(id);
		final var to = ballotMapper.ballotBoToBallotTo(bo);
		
		return ResponseEntity.ok(to);
	}

	@Override
	@PostMapping("ballot")
	public ResponseEntity<BallotTo> postBallot(@RequestBody BallotTo ballotTo) {
		final var bo = ballotSvc.createBallot(ballotMapper.ballotToToBallotBo(ballotTo));
		final var to = ballotMapper.ballotBoToBallotTo(bo);
		
		return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(to.getId()).toUri()).build();
	}

	@Override
	@PutMapping("ballot/{id}")
	public ResponseEntity<BallotTo> putBallot(@PathVariable Long id, @RequestBody BallotTo ballotTo) {
		ballotSvc.updateBallot(id, ballotMapper.ballotToToBallotBo(ballotTo));
		
		return ResponseEntity.noContent().location(ServletUriComponentsBuilder.fromCurrentRequest().build().toUri()).build();
	}

	@Override
	@DeleteMapping("ballot/{id}")
	public ResponseEntity<Void> deleteBallot(@PathVariable Long id) {
		ballotSvc.deleteBallot(id);
		
		return ResponseEntity.noContent().build();
	}
}
