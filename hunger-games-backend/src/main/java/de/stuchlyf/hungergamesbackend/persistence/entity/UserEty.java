package de.stuchlyf.hungergamesbackend.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@With
@Entity(name = "User")
@Table(name = "_user")
public class UserEty {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, unique = true)
	private UUID id;
	
	@Column(nullable = false, unique = true)
	private String externalId;
}
