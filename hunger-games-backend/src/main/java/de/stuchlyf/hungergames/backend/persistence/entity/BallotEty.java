package de.stuchlyf.hungergames.backend.persistence.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@With
@Entity(name = "Ballot")
@Table(name = "ballot")
public class BallotEty {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ballot_id_seq")
	@SequenceGenerator(name = "ballot_id_seq", sequenceName = "ballot_id_seq", allocationSize = 1)
	@Column(name = "id", nullable = false, unique = true, updatable = false)
	private Long id;

	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinTable(name = "ballot_restaurant",
		joinColumns = @JoinColumn(name = "ballot_id"),
		inverseJoinColumns = @JoinColumn(name = "restaurant_id"))
	@ToString.Exclude
	private Set<RestaurantEty> restaurants = new LinkedHashSet<>();
	
	@OneToMany(mappedBy = "ballot", cascade = CascadeType.MERGE, orphanRemoval = true, fetch = FetchType.EAGER)
	@ToString.Exclude
	private Set<VoteEty> votes = new LinkedHashSet<>();
	
	@Column(nullable = false)
	private OffsetDateTime startDate;
	
	@Column(nullable = false)
	private OffsetDateTime endDate;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		BallotEty ballotEty = (BallotEty) o;
		return id != null && Objects.equals(id, ballotEty.id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
