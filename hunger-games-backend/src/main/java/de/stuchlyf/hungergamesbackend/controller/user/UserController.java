package de.stuchlyf.hungergamesbackend.controller.user;

import de.stuchlyf.hungergamesbackend.to.UserTo;
import org.springframework.http.ResponseEntity;

import java.util.Set;

public interface UserController {
	ResponseEntity<Set<UserTo>> getAllUsers();

	ResponseEntity<UserTo> getUser(String id);

	ResponseEntity<UserTo> postUser(UserTo userTo);

	ResponseEntity<UserTo> putUser(String id, UserTo userTo);

	ResponseEntity<Void> deleteUser(String id);
}
