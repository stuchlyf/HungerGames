package de.stuchlyf.hungergames.backend.service.restaurant;

import de.stuchlyf.hungergames.backend.common.bo.RestaurantBo;
import de.stuchlyf.hungergames.backend.exception.EntityNotFoundException;

import java.util.Set;
import java.util.UUID;

public interface RestaurantService {
	Set<RestaurantBo> readAllRestaurants();

	RestaurantBo readRestaurantWithId(UUID id) throws EntityNotFoundException;

	RestaurantBo createRestaurant(RestaurantBo restaurantTo);

	RestaurantBo updateRestaurant(UUID id, RestaurantBo restaurantBo);

	void deleteRestaurant(UUID id);
}
