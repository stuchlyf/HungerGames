package de.stuchlyf.hungergamesbackend.common.to;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class BallotTo {

	private Long id;

	@JsonProperty(required = true)
	private Set<RestaurantTo> restaurants = new LinkedHashSet<>();

	@JsonProperty(required = true)
	private Set<VoteTo> votes = new LinkedHashSet<>();

	private LocalDateTime startDate;

	private LocalDateTime endDate;
	
	@JsonProperty(required = true)
	private Boolean allRestaurants = true;
	
}
