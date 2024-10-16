package com.arago.adserver.repository;

import com.arago.adserver.model.Ad;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdRepository extends CrudRepository<Ad, String> {
}
