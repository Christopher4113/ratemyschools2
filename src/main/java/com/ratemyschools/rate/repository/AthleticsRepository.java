package com.ratemyschools.rate.repository;

import com.ratemyschools.rate.model.Athletics;
import com.ratemyschools.rate.model.School;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AthleticsRepository extends CrudRepository<Athletics,Long> {
    Optional<Athletics> findByCategory(String category);
    List<Athletics> findBySchoolId(Long schoolId);
}
