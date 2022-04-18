package de.stuchlyf.hungergamesbackend.service.restaurant.impl;

import de.stuchlyf.hungergamesbackend.common.bo.RestaurantBo;
import de.stuchlyf.hungergamesbackend.common.mapper.RestaurantMapper;
import de.stuchlyf.hungergamesbackend.persistence.entity.RestaurantEty;
import de.stuchlyf.hungergamesbackend.persistence.repository.RestaurantEtyRepository;
import de.stuchlyf.hungergamesbackend.service.restaurant.RestaurantService;
import de.stuchlyf.hungergamesbackend.exception.EntityNotFoundException;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class RestaurantServiceImpl implements RestaurantService {
	
	private final RestaurantEtyRepository restaurantEtyRepo;
	
	private final RestaurantMapper restaurantMapper = Mappers.getMapper(RestaurantMapper.class);
	
	@Autowired
	public RestaurantServiceImpl(RestaurantEtyRepository restaurantEtyRepo) {
		this.restaurantEtyRepo = restaurantEtyRepo;
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
