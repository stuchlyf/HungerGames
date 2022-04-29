package de.stuchlyf.hungergamesbackend.common.mapper;

import de.stuchlyf.hungergamesbackend.common.bo.VoteBo;
import de.stuchlyf.hungergamesbackend.to.VoteTo;
import de.stuchlyf.hungergamesbackend.persistence.entity.VoteEty;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR, componentModel = "spring", config = BaseConfig.class, uses = {RestaurantMapper.class, UserMapper.class})
public interface VoteMapper {

	/**
	 * <b>({@link VoteEty} => {@link VoteBo})</b><br/>
	 * Mapper for mapping a VoteEty to VoteBo. 
	 * @param source The source Object.
	 * @return The mapped Object.
	 */
	@Mapping(source = "id", target = "id")
	@Mapping(source = "voter", target = "voter")
	@Mapping(source = "voted", target = "voted")
	@Mapping(source = "ballot", target = "ballot")
	VoteBo voteEtyToVoteBo(VoteEty source);

	/**
	 * <b>({@link VoteBo} => {@link VoteTo})</b><br/>
	 * Mapper for mapping a VoteBo to VoteTo. 
	 * @param source The source Object.
	 * @return The mapped Object.
	 */
	@Mapping(source = "id", target = "id", qualifiedByName = "uuidToString")
	@Mapping(source = "voter", target = "voter")
	@Mapping(source = "voted", target = "voted")
	@Mapping(source = "ballot", target = "ballot")
	VoteTo voteBoToVoteTo(VoteBo source);

	/**
	 * <b>({@link VoteTo} => {@link VoteBo})</b><br/>
	 * Mapper for mapping a VoteTo to VoteBo.
	 * @param source The source Object.
	 * @return The mapped Object.
	 */
	@InheritInverseConfiguration
	@Mapping(source = "id", target = "id", qualifiedByName = "stringToUuid")
	VoteBo voteToToVoteBo(VoteTo source);

	/**
	 * <b>({@link VoteBo} => {@link VoteEty})</b><br/>
	 * Mapper for mapping a VoteBo to VoteEty. 
	 * @param source The source Object.
	 * @return The mapped Object.
	 */
	@InheritInverseConfiguration
	VoteEty voteBoToVoteEty(VoteBo source);

	/**
	 * <b>(Collection<{@link VoteEty}> => Collection<{@link VoteBo}>)</b><br/>
	 * Mapper for mapping a Collection of VoteEty to a Collection of VoteBo. 
	 * @param source The source Object.
	 * @return The mapped Object.
	 */
	Collection<VoteBo> voteEtysToVoteBos(Collection<VoteEty> source);

	/**
	 * <b>(Collection<{@link VoteBo}> => Collection<{@link VoteTo}>)</b><br/>
	 * Mapper for mapping a Collection of VoteBo to a Collection of VoteTo. 
	 * @param source The source Object.
	 * @return The mapped Object.
	 */
	Collection<VoteTo> voteBosToVoteTos(Collection<VoteBo> source);

	/**
	 * <b>(Collection<{@link VoteTo}> => Collection<{@link VoteBo}>)</b><br/>
	 * Mapper for mapping a Collection of VoteTo to a Collection of VoteBo. 
	 * @param source The source Object.
	 * @return The mapped Object.
	 */
	@InheritInverseConfiguration
	Collection<VoteBo> voteTosToVoteBos(Collection<VoteTo> source);

	/**
	 * <b>(Collection<{@link VoteBo}> => Collection<{@link VoteEty}>)</b><br/>
	 * Mapper for mapping a Collection of VoteBo to a Collection of VoteEty. 
	 * @param source The source Object.
	 * @return The mapped Object.
	 */
	@InheritInverseConfiguration
	Collection<VoteEty> voteBosToVoteEtys(Collection<VoteBo> source);
}
