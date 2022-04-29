package de.stuchlyf.hungergamesbackend.service.user.impl;

import de.stuchlyf.hungergamesbackend.common.bo.UserBo;
import de.stuchlyf.hungergamesbackend.common.mapper.UserMapper;
import de.stuchlyf.hungergamesbackend.persistence.entity.UserEty;
import de.stuchlyf.hungergamesbackend.persistence.repository.UserEtyRepository;
import de.stuchlyf.hungergamesbackend.service.user.UserService;
import de.stuchlyf.hungergamesbackend.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

	private final UserEtyRepository userEtyRepo;

	private final UserMapper userMapper;

	@Autowired
	public UserServiceImpl(UserEtyRepository userEtyRepo, UserMapper userMapper) {
		this.userEtyRepo = userEtyRepo;
		this.userMapper = userMapper;
	}


	@Override
	public Set<UserBo> readAllUsers() {

		var etys = userEtyRepo.findAll();

		return new HashSet<>(userMapper.userEtysToUserBos(etys));
	}

	@Override
	public UserBo readUserWithId(UUID id) throws EntityNotFoundException {
		final var ety = userEtyRepo
			.findById(id)
			.orElseThrow(() -> new EntityNotFoundException(UserEty.class.getSimpleName(), id.toString()));

		return userMapper.userEtyToUserBo(ety);
	}

	@Override
	public UserBo createUser(UserBo userBo) {
		// TODO: Add Validation
		final var ety = userEtyRepo.save(userMapper.userBoToUserEty(userBo));

		return userMapper.userEtyToUserBo(ety);
	}

	@Override
	public UserBo updateUser(UUID id, UserBo userBo) {
		if (!userEtyRepo.existsById(id)) throw new EntityNotFoundException(UserEty.class.getSimpleName(), id.toString());
		// TODO: Add Validation
		final var ety = userEtyRepo.save(userMapper.userBoToUserEty(userBo));

		return userMapper.userEtyToUserBo(ety);
	}

	@Override
	public void deleteUser(UUID id) {
		userEtyRepo.deleteById(id);
	}
}
