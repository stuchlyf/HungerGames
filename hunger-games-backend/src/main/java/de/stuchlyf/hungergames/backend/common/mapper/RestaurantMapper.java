package de.stuchlyf.hungergames.backend.common.mapper;

import de.stuchlyf.hungergames.backend.common.bo.RestaurantBo;
import de.stuchlyf.hungergames.backend.persistence.entity.RestaurantEty;
import de.stuchlyf.hungergames.backend.to.RestaurantTo;
import org.mapstruct.*;

import java.util.Collection;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR, componentModel = "spring", config = BaseConfig.class)
public interface RestaurantMapper {

	/**
	 * <b>({@link RestaurantEty} => {@link RestaurantBo})</b><br/>
	 * Mapper for mapping a RestaurantEty to RestaurantBo. 
	 * @param source The source Object.
	 * @return The mapped Object.
	 */
	@Mapping(source = "id", target = "id")
	@Mapping(source = "name", target = "name")
	@Mapping(source = "googleId", target = "googleId")
	RestaurantBo restaurantEtyToRestaurantBo(RestaurantEty source);

	/**
	 * <b>({@link RestaurantEty} => {@link RestaurantBo})</b><br/>
	 * Mapper for mapping a RestaurantEty to RestaurantBo. 
	 * @param source The source Object.
	 * @return The mapped Object.
	 */
	@Mapping(source = "id", target = "id", qualifiedByName = "uuidToString")
	@Mapping(source = "name", target = "name")
	@Mapping(source = "googleId", target = "googleId")
    RestaurantTo restaurantBoToRestaurantTo(RestaurantBo source);

	/**
	 * <b>({@link RestaurantEty} => {@link RestaurantBo})</b><br/>
	 * Mapper for mapping a RestaurantEty to RestaurantBo. 
	 * @param source The source Object.
	 * @return The mapped Object.
	 */
	@InheritInverseConfiguration
	@Mapping(source = "id", target = "id", qualifiedByName = "stringToUuid")
	RestaurantBo restaurantToToRestaurantBo(RestaurantTo source);

	/**
	 * <b>({@link RestaurantEty} => {@link RestaurantBo})</b><br/>
	 * Mapper for mapping a RestaurantEty to RestaurantBo. 
	 * @param source The source Object.
	 * @return The mapped Object.
	 */
	@InheritInverseConfiguration
	RestaurantEty restaurantBoToRestaurantEty(RestaurantBo source);

	/**
	 * <b>(Collection<{@link RestaurantEty}> => Collection<{@link RestaurantBo}>)</b><br/>
	 * Mapper for mapping a Collection of RestaurantEty to a Collection of RestaurantBo. 
	 * @param source The source Object.
	 * @return The mapped Object.
	 */
	Collection<RestaurantBo> restaurantEtysToRestaurantBos(Collection<RestaurantEty> source);

	/**
	 * <b>(Collection<{@link RestaurantBo}> => Collection<{@link RestaurantTo}>)</b><br/>
	 * Mapper for mapping a Collection of RestaurantBo to a Collection of RestaurantTo. 
	 * @param source The source Object.
	 * @return The mapped Object.
	 */
	Collection<RestaurantTo> restaurantBosToRestaurantTos(Collection<RestaurantBo> source);

	/**
	 * <b>(Collection<{@link RestaurantTo}> => Collection<{@link RestaurantBo}>)</b><br/>
	 * Mapper for mapping a Collection of RestaurantTo to a Collection of RestaurantBo. 
	 * @param source The source Object.
	 * @return The mapped Object.
	 */
	@InheritInverseConfiguration
	Collection<RestaurantBo> restaurantTosToRestaurantBos(Collection<RestaurantTo> source);

	/**
	 * <b>(Collection<{@link RestaurantBo}> => Collection<{@link RestaurantEty}>)</b><br/>
	 * Mapper for mapping a Collection of RestaurantBo to a Collection of RestaurantEty. 
	 * @param source The source Object.
	 * @return The mapped Object.
	 */
	@InheritInverseConfiguration
	Collection<RestaurantEty> restaurantBosToRestaurantEtys(Collection<RestaurantBo> source);
	
}
