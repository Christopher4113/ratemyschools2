package com.ratemyschools.rate.repository;

import com.ratemyschools.rate.model.Other;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OtherRepository extends CrudRepository<Other,Long> {
    Optional<Other> findByCategory(String category);
}
