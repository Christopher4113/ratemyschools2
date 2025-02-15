package com.ratemyschools.rate.repository;

import com.ratemyschools.rate.model.School;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SchoolRepository extends CrudRepository<School,Long> {
    Optional<School> findBySchoolName(String schoolName);


}
