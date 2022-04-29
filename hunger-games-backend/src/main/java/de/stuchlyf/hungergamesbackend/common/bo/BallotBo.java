package de.stuchlyf.hungergamesbackend.common.bo;

import lombok.*;

import java.time.OffsetDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@With
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class BallotBo {
	
	private Long id;
	
	private Set<RestaurantBo> restaurants = new LinkedHashSet<>();
	
	private Set<VoteBo> votes = new LinkedHashSet<>();
	
	private OffsetDateTime startDate;
	
	private OffsetDateTime endDate;
}
