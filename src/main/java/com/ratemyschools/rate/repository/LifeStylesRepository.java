package com.ratemyschools.rate.repository;

import com.ratemyschools.rate.model.LifeStyle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LifeStylesRepository extends CrudRepository<LifeStyle,Long> {
    Optional<LifeStyle> findByCategory(String category);
    List<LifeStyle> findBySchoolId(Long schoolId);
}
