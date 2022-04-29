package de.stuchlyf.hungergamesbackend.common.mapper;


import org.mapstruct.InjectionStrategy;

@org.mapstruct.MapperConfig(uses = { UuidMapper.class }, injectionStrategy = InjectionStrategy.CONSTRUCTOR, componentModel = "spring")
public interface BaseConfig {
}
