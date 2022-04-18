package de.stuchlyf.hungergamesbackend.controller.restaurant.impl;

import de.stuchlyf.hungergamesbackend.common.mapper.RestaurantMapper;
import de.stuchlyf.hungergamesbackend.common.mapper.UuidMapper;
import de.stuchlyf.hungergamesbackend.common.to.RestaurantTo;
import de.stuchlyf.hungergamesbackend.controller.restaurant.RestaurantController;
import de.stuchlyf.hungergamesbackend.service.restaurant.RestaurantService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("restaurant-service")
public class RestaurantControllerImpl implements RestaurantController {

	private final RestaurantService restaurantSvc;
	private final RestaurantMapper restaurantMapper = Mappers.getMapper(RestaurantMapper.class);

	@Autowired
	public RestaurantControllerImpl(RestaurantService restaurantSvc) {
		this.restaurantSvc = restaurantSvc;
	}

	@Override
	@GetMapping("restaurants")
	public ResponseEntity<Set<RestaurantTo>> getAllRestaurants() {
		final var bos = restaurantSvc.readAllRestaurants();
		final var tos = new HashSet<>(restaurantMapper.restaurantBosToRestaurantTos(bos));

		return ResponseEntity.ok(tos);
	}

	@Override
	@GetMapping("restaurant/{id}")
	public ResponseEntity<RestaurantTo> getRestaurant(@PathVariable String id) {
		final var bo = restaurantSvc.readRestaurantWithId(UuidMapper.stringToUuid(id));
		final var to = restaurantMapper.restaurantBoToRestaurantTo(bo);

		return ResponseEntity.ok(to);
	}

	@Override
	@PostMapping("restaurant")
	public ResponseEntity<RestaurantTo> postRestaurant(@RequestBody RestaurantTo restaurantTo) {
		final var bo = restaurantSvc.createRestaurant(restaurantMapper.restaurantToToRestaurantBo(restaurantTo));
		final var to = restaurantMapper.restaurantBoToRestaurantTo(bo);

		return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(to.getId()).toUri()).build();
	}

	@Override
	@PutMapping("restaurant/{id}")
	public ResponseEntity<RestaurantTo> putRestaurant(@PathVariable String id, @RequestBody RestaurantTo restaurantTo) {
		restaurantSvc.updateRestaurant(UuidMapper.stringToUuid(id), restaurantMapper.restaurantToToRestaurantBo(restaurantTo));
		
		return ResponseEntity.noContent().location(ServletUriComponentsBuilder.fromCurrentRequest().build().toUri()).build();
	}

	@Override
	@DeleteMapping("restaurant/{id}")
	public ResponseEntity<Void> deleteRestaurant(@PathVariable String id) {
		restaurantSvc.deleteRestaurant(UuidMapper.stringToUuid(id));
		
		return ResponseEntity.noContent().build();
	}
}
