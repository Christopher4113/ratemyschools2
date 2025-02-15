package com.ratemyschools.rate.repository;

import com.ratemyschools.rate.model.LifeStyle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LifeStyleRepository  extends CrudRepository<LifeStyle,Long> {
    Optional<LifeStyle> findByCategory(String category);
}
