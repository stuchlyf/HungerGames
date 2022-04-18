package de.stuchlyf.hungergamesbackend.common.bo;

import lombok.*;

import java.time.LocalDateTime;
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
	
	private LocalDateTime startDate;
	
	private LocalDateTime endDate;
}
