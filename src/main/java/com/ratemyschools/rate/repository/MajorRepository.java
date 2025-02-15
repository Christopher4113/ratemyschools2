package com.ratemyschools.rate.repository;

import com.ratemyschools.rate.model.Major;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MajorRepository extends CrudRepository<Major,Long> {
    Optional<Major> findByMajorName (String majorName);
}
