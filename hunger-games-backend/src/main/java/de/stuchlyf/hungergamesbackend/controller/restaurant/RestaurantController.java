package de.stuchlyf.hungergamesbackend.controller.restaurant;

import de.stuchlyf.hungergamesbackend.to.RestaurantTo;
import org.springframework.http.ResponseEntity;

import java.util.Set;

public interface RestaurantController {

	ResponseEntity<Set<RestaurantTo>> getAllRestaurants();
	
	ResponseEntity<RestaurantTo> getRestaurant(String id);
	
	ResponseEntity<RestaurantTo> postRestaurant(RestaurantTo restaurantTo);
	
	ResponseEntity<RestaurantTo> putRestaurant(String id, RestaurantTo restaurantTo);
	
	ResponseEntity<Void> deleteRestaurant(String id);
}
