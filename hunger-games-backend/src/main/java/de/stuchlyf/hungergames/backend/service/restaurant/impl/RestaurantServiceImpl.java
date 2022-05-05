package de.stuchlyf.hungergames.backend.service.restaurant.impl;

import de.stuchlyf.hungergames.backend.common.bo.RestaurantBo;
import de.stuchlyf.hungergames.backend.exception.EntityNotFoundException;
import de.stuchlyf.hungergames.backend.common.mapper.RestaurantMapper;
import de.stuchlyf.hungergames.backend.persistence.entity.RestaurantEty;
import de.stuchlyf.hungergames.backend.persistence.repository.RestaurantEtyRepository;
import de.stuchlyf.hungergames.backend.service.restaurant.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class RestaurantServiceImpl implements RestaurantService {
	
	private final RestaurantEtyRepository restaurantEtyRepo;
	
	private final RestaurantMapper restaurantMapper;
	
	@Autowired
	public RestaurantServiceImpl(RestaurantEtyRepository restaurantEtyRepo, RestaurantMapper restaurantMapper) {
		this.restaurantEtyRepo = restaurantEtyRepo;
		this.restaurantMapper = restaurantMapper;
	}


	@Override
	public Set<RestaurantBo> readAllRestaurants() {
		
		var etys = restaurantEtyRepo.findAll();

		return new HashSet<>(restaurantMapper.restaurantEtysToRestaurantBos(etys));
	}

	@Override
	public RestaurantBo readRestaurantWithId(UUID id) throws EntityNotFoundException {
		final var ety = restaurantEtyRepo
			.findById(id)
			.orElseThrow(() -> new EntityNotFoundException(RestaurantEty.class.getSimpleName(), id.toString()));
		
		return restaurantMapper.restaurantEtyToRestaurantBo(ety);
	}

	@Override
	public RestaurantBo createRestaurant(RestaurantBo restaurantBo) {
		// TODO: Add Validation
		final var ety = restaurantEtyRepo.save(restaurantMapper.restaurantBoToRestaurantEty(restaurantBo));
		
		return restaurantMapper.restaurantEtyToRestaurantBo(ety);
	}

	@Override
	public RestaurantBo updateRestaurant(UUID id, RestaurantBo restaurantBo) {
		if (!restaurantEtyRepo.existsById(id)) throw new EntityNotFoundException(RestaurantEty.class.getSimpleName(), id.toString());
		// TODO: Add Validation
		final var ety = restaurantEtyRepo.save(restaurantMapper.restaurantBoToRestaurantEty(restaurantBo));
		
		return restaurantMapper.restaurantEtyToRestaurantBo(ety);
	}

	@Override
	public void deleteRestaurant(UUID id) {
		restaurantEtyRepo.deleteById(id);
	}
}
