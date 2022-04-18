package de.stuchlyf.hungergamesbackend.common.to;

import lombok.*;


@Getter
@Setter
@With
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserTo {
	
	private String id;
	
	private String externalId;
}
