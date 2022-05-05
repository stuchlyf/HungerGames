package de.stuchlyf.hungergames.backend.common.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface UuidMapper {

	@Named(value = "uuidToString")
	static String uuidToString(UUID source) {
		return source.toString();
	}

	@Named(value = "stringToUuid")
	static UUID stringToUuid(String source) {
		return UUID.fromString(source);
	}
}
