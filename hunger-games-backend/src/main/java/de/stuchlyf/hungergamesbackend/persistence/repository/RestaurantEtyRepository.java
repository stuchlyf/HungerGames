package de.stuchlyf.hungergamesbackend.persistence.repository;

import de.stuchlyf.hungergamesbackend.persistence.entity.RestaurantEty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RestaurantEtyRepository extends JpaRepository<RestaurantEty, UUID> {
}
