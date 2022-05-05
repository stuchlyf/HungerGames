package de.stuchlyf.hungergames.backend.persistence.repository;

import de.stuchlyf.hungergames.backend.persistence.entity.UserEty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserEtyRepository extends JpaRepository<UserEty, UUID> {
}
