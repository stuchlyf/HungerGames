package de.stuchlyf.hungergamesbackend.common.mapper;

import de.stuchlyf.hungergamesbackend.common.bo.BallotBo;
import de.stuchlyf.hungergamesbackend.to.BallotTo;
import de.stuchlyf.hungergamesbackend.persistence.entity.BallotEty;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR, componentModel = "spring", config = BaseConfig.class, uses = {VoteMapper.class, RestaurantMapper.class})
public interface BallotMapper {
	
	/**
	 * <b>({@link BallotEty} => {@link BallotBo})</b><br/>
	 * Mapper for mapping a BallotEty to BallotBo. 
	 * @param source The source Object.
	 * @return The mapped Object.
	 */
	@Mapping(source = "id", target = "id")
	@Mapping(source = "restaurants", target = "restaurants")
	@Mapping(source = "votes", target = "votes")
	@Mapping(source = "startDate", target = "startDate")
	@Mapping(source = "endDate", target = "endDate")
	BallotBo ballotEtyToBallotBo(BallotEty source);

	/**
	 * <b>({@link BallotEty} => {@link BallotBo})</b><br/>
	 * Mapper for mapping a BallotEty to BallotBo. 
	 * @param source The source Object.
	 * @return The mapped Object.
	 */
	@Mapping(source = "id", target = "id")
	@Mapping(source = "restaurants", target = "restaurants")
	@Mapping(source = "votes", target = "votes")
	@Mapping(source = "startDate", target = "startDate")
	@Mapping(source = "endDate", target = "endDate")
	BallotTo ballotBoToBallotTo(BallotBo source);

	/**
	 * <b>({@link BallotEty} => {@link BallotBo})</b><br/>
	 * Mapper for mapping a BallotEty to BallotBo. 
	 * @todo: map allRestaurants Property correctly
	 * @param source The source Object.
	 * @return The mapped Object.
	 */
	@InheritInverseConfiguration
	BallotBo ballotToToBallotBo(BallotTo source);

	/**
	 * <b>({@link BallotEty} => {@link BallotBo})</b><br/>
	 * Mapper for mapping a BallotEty to BallotBo. 
	 * @param source The source Object.
	 * @return The mapped Object.
	 */
	@InheritInverseConfiguration
	BallotEty ballotBoToBallotEty(BallotBo source);

	/**
	 * <b>(Collection<{@link BallotEty}> => Collection<{@link BallotBo}>)</b><br/>
	 * Mapper for mapping a Collection of BallotEty to a Collection of BallotBo. 
	 * @param source The source Object.
	 * @return The mapped Object.
	 */
	Collection<BallotBo> ballotEtysToBallotBos(Collection<BallotEty> source);

	/**
	 * <b>(Collection<{@link BallotBo}> => Collection<{@link BallotTo}>)</b><br/>
	 * Mapper for mapping a Collection of BallotBo to a Collection of BallotTo. 
	 * @param source The source Object.
	 * @return The mapped Object.
	 */
	Collection<BallotTo> ballotBosToBallotTos(Collection<BallotBo> source);

	/**
	 * <b>(Collection<{@link BallotTo}> => Collection<{@link BallotBo}>)</b><br/>
	 * Mapper for mapping a Collection of BallotTo to a Collection of BallotBo. 
	 * @param source The source Object.
	 * @return The mapped Object.
	 */
	@InheritInverseConfiguration
	Collection<BallotBo> ballotTosToBallotBos(Collection<BallotTo> source);

	/**
	 * <b>(Collection<{@link BallotBo}> => Collection<{@link BallotEty}>)</b><br/>
	 * Mapper for mapping a Collection of BallotBo to a Collection of BallotEty. 
	 * @param source The source Object.
	 * @return The mapped Object.
	 */
	@InheritInverseConfiguration
	Collection<BallotEty> ballotBosToBallotEtys(Collection<BallotBo> source);
}
