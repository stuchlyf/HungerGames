package de.stuchlyf.hungergamesbackend.common.to;

import lombok.*;


@Getter
@Setter
@With
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class VoteTo {
	
	private String id;
	
	private UserTo voter;
	
	private RestaurantTo voted;
	
	private BallotTo ballot;
}
