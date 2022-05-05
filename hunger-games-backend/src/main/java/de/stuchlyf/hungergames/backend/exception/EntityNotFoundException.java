package de.stuchlyf.hungergames.backend.exception;

import lombok.Getter;

public class EntityNotFoundException extends IllegalArgumentException {
	
	@Getter
	private final String entityName;
	
	@Getter
	private final String id;

	public EntityNotFoundException(String entityName, String id) {
		super("Entity %s with id %s could not be found.".formatted(entityName, id));
		
		this.entityName = entityName;
		this.id = id;
	}
}
