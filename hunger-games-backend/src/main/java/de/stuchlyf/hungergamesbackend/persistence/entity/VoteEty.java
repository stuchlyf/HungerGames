package de.stuchlyf.hungergamesbackend.persistence.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity(name = "Vote")
@Table(name = "vote")
@Getter
@Setter
@NoArgsConstructor
@With
@AllArgsConstructor
@ToString
public class VoteEty {

	@Setter(AccessLevel.NONE)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, unique = true, updatable = false)
	private UUID id;

	@ManyToOne(optional = false)
	@JoinColumn(nullable = false)
	private UserEty voter;
	
	@ManyToOne(optional = false)
	@JoinColumn(nullable = false)
	private RestaurantEty voted;

	@ManyToOne(optional = false)
	private BallotEty ballot;
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		VoteEty voteEty = (VoteEty) o;
		return id != null && Objects.equals(id, voteEty.id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
