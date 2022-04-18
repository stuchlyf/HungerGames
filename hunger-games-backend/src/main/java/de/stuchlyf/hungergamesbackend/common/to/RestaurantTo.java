package de.stuchlyf.hungergamesbackend.common.to;

import lombok.*;

import java.io.Serializable;


@Getter
@Setter
@With
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class RestaurantTo implements Serializable {
	private String id;
	private String name;
	private String googleId;
}
