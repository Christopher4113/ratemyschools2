package com.ratemyschools.rate.repository;

import com.ratemyschools.rate.model.Jobs;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JobsRepository  extends CrudRepository<Jobs,Long> {
    Optional<Jobs> findByCategory (String category);
}
