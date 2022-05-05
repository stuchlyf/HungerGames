package de.stuchlyf.hungergames.backend.service.user;

import de.stuchlyf.hungergames.backend.common.bo.UserBo;
import de.stuchlyf.hungergames.backend.exception.EntityNotFoundException;

import java.util.Set;
import java.util.UUID;

public interface UserService {
	Set<UserBo> readAllUsers();

	UserBo readUserWithId(UUID id) throws EntityNotFoundException;

	UserBo createUser(UserBo userTo);

	UserBo updateUser(UUID id, UserBo userBo);

	void deleteUser(UUID id);
}
