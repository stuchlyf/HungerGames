package de.stuchlyf.hungergames.backend.persistence.repository;

import de.stuchlyf.hungergames.backend.persistence.entity.RestaurantEty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RestaurantEtyRepository extends JpaRepository<RestaurantEty, UUID> {
}
