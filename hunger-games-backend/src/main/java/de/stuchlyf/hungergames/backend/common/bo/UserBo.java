package de.stuchlyf.hungergames.backend.common.bo;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@With
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserBo {

	private UUID id;

	private String externalId;
	
}
