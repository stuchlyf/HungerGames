package de.stuchlyf.hungergames.backend.service.user.impl;

import de.stuchlyf.hungergames.backend.common.bo.UserBo;
import de.stuchlyf.hungergames.backend.exception.EntityNotFoundException;
import de.stuchlyf.hungergames.backend.common.mapper.UserMapper;
import de.stuchlyf.hungergames.backend.persistence.entity.UserEty;
import de.stuchlyf.hungergames.backend.persistence.repository.UserEtyRepository;
import de.stuchlyf.hungergames.backend.service.user.UserService;
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
