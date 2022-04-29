package de.stuchlyf.hungergamesbackend.controller.user.impl;

import de.stuchlyf.hungergamesbackend.common.mapper.UserMapper;
import de.stuchlyf.hungergamesbackend.common.mapper.UuidMapper;
import de.stuchlyf.hungergamesbackend.controller.user.UserController;
import de.stuchlyf.hungergamesbackend.service.user.UserService;
import de.stuchlyf.hungergamesbackend.to.UserTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("user-service")
public class UserControllerImpl implements UserController {

	private final UserService userSvc;
	private final UserMapper userMapper;

	@Autowired
	public UserControllerImpl(UserService userSvc, UserMapper userMapper) {
		this.userSvc = userSvc;
		this.userMapper = userMapper;
	}

	@Override
	@GetMapping("users")
	public ResponseEntity<Set<UserTo>> getAllUsers() {
		final var bos = userSvc.readAllUsers();
		final var tos = new HashSet<>(userMapper.userBosToUserTos(bos));

		return ResponseEntity.ok(tos);
	}

	@Override
	@GetMapping("user/{id}")
	public ResponseEntity<UserTo> getUser(@PathVariable String id) {
		final var bo = userSvc.readUserWithId(UuidMapper.stringToUuid(id));
		final var to = userMapper.userBoToUserTo(bo);

		return ResponseEntity.ok(to);
	}

	@Override
	@PostMapping("user")
	public ResponseEntity<UserTo> postUser(@RequestBody UserTo userTo) {
		final var bo = userSvc.createUser(userMapper.userToToUserBo(userTo));
		final var to = userMapper.userBoToUserTo(bo);

		return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(to.getId()).toUri()).build();
	}

	@Override
	@PutMapping("user/{id}")
	public ResponseEntity<UserTo> putUser(@PathVariable String id, @RequestBody UserTo userTo) {
		userSvc.updateUser(UuidMapper.stringToUuid(id), userMapper.userToToUserBo(userTo));

		return ResponseEntity.noContent().location(ServletUriComponentsBuilder.fromCurrentRequest().build().toUri()).build();
	}

	@Override
	@DeleteMapping("user/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable String id) {
		userSvc.deleteUser(UuidMapper.stringToUuid(id));

		return ResponseEntity.noContent().build();
	}
}
