package de.stuchlyf.hungergamesbackend.service.user;

import de.stuchlyf.hungergamesbackend.common.bo.UserBo;
import de.stuchlyf.hungergamesbackend.exception.EntityNotFoundException;

import java.util.Set;
import java.util.UUID;

public interface UserService {
	Set<UserBo> readAllUsers();

	UserBo readUserWithId(UUID id) throws EntityNotFoundException;

	UserBo createUser(UserBo userTo);

	UserBo updateUser(UUID id, UserBo userBo);

	void deleteUser(UUID id);
}
