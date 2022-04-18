package de.stuchlyf.hungergamesbackend.common.bo;


import lombok.*;

import java.util.UUID;

@Getter
@Setter
@With
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class VoteBo {

	private UUID id;

	private UserBo voter;
	
	private RestaurantBo voted;
	
	private BallotBo ballot;
}
