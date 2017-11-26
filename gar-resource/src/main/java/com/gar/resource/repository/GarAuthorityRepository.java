package com.gar.resource.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gar.resource.domain.GarAuthority;

/**
 * Spring Data JPA repository for the Authority entity.
 */
@Repository
public interface GarAuthorityRepository extends JpaRepository<GarAuthority, String> {
    
}
