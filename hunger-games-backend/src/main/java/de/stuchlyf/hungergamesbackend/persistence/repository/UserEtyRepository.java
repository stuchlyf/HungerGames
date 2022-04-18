package de.stuchlyf.hungergamesbackend.persistence.repository;

import de.stuchlyf.hungergamesbackend.persistence.entity.UserEty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserEtyRepository extends JpaRepository<UserEty, UUID> {
}
