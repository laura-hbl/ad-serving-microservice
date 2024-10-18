package com.arago.adserver.repository;

import com.arago.adserver.model.Ad;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for accessing and managing Ad entities.
 */
@Repository
public interface AdRepository extends CrudRepository<Ad, String> {
}
