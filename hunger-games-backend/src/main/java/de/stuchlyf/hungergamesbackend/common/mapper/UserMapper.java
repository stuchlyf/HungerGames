package de.stuchlyf.hungergamesbackend.common.mapper;

import de.stuchlyf.hungergamesbackend.common.bo.UserBo;
import de.stuchlyf.hungergamesbackend.common.to.UserTo;
import de.stuchlyf.hungergamesbackend.persistence.entity.UserEty;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Collection;

@Mapper(componentModel = "spring", config = BaseConfig.class)
public interface UserMapper {

	UserMapper MAPPER = Mappers.getMapper(UserMapper.class);

	/**
	 * <b>({@link UserEty} => {@link UserBo})</b><br/>
	 * Mapper for mapping a UserEty to UserBo. 
	 * @param source The source Object.
	 * @return The mapped Object.
	 */
	@Mapping(source = "id", target = "id")
	@Mapping(source = "externalId", target = "externalId")
	UserBo userEtyToUserBo(UserEty source);

	/**
	 * <b>({@link UserEty} => {@link UserBo})</b><br/>
	 * Mapper for mapping a UserEty to UserBo. 
	 * @param source The source Object.
	 * @return The mapped Object.
	 */
	@Mapping(source = "id", target = "id", qualifiedByName = "uuidToString")
	@Mapping(source = "externalId", target = "externalId")
	UserTo userBoToUserTo(UserBo source);

	/**
	 * <b>({@link UserEty} => {@link UserBo})</b><br/>
	 * Mapper for mapping a UserEty to UserBo. 
	 * @param source The source Object.
	 * @return The mapped Object.
	 */
	@InheritInverseConfiguration
	@Mapping(source = "id", target = "id", qualifiedByName = "stringToUuid")
	UserBo userToToUserBo(UserTo source);

	/**
	 * <b>({@link UserEty} => {@link UserBo})</b><br/>
	 * Mapper for mapping a UserEty to UserBo. 
	 * @param source The source Object.
	 * @return The mapped Object.
	 */
	@InheritInverseConfiguration
	UserEty userBoToUserEty(UserBo source);

	/**
	 * <b>(Collection<{@link UserEty}> => Collection<{@link UserBo}>)</b><br/>
	 * Mapper for mapping a Collection of UserEty to a Collection of UserBo. 
	 * @param source The source Object.
	 * @return The mapped Object.
	 */
	Collection<UserBo> userEtysToUserBos(Collection<UserEty> source);

	/**
	 * <b>(Collection<{@link UserBo}> => Collection<{@link UserTo}>)</b><br/>
	 * Mapper for mapping a Collection of UserBo to a Collection of UserTo. 
	 * @param source The source Object.
	 * @return The mapped Object.
	 */
	Collection<UserTo> userBosToUserTos(Collection<UserBo> source);

	/**
	 * <b>(Collection<{@link UserTo}> => Collection<{@link UserBo}>)</b><br/>
	 * Mapper for mapping a Collection of UserTo to a Collection of UserBo. 
	 * @param source The source Object.
	 * @return The mapped Object.
	 */
	@InheritInverseConfiguration
	Collection<UserBo> userTosToUserBos(Collection<UserTo> source);

	/**
	 * <b>(Collection<{@link UserBo}> => Collection<{@link UserEty}>)</b><br/>
	 * Mapper for mapping a Collection of UserBo to a Collection of UserEty. 
	 * @param source The source Object.
	 * @return The mapped Object.
	 */
	@InheritInverseConfiguration
	Collection<UserEty> userBosToUserEtys(Collection<UserBo> source);
}
