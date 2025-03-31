package com.ratemyschools.rate.repository;


import com.ratemyschools.rate.model.Housing;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HousingRepository extends CrudRepository<Housing,Long> {
    Optional<Housing> findByType (String type);
    List<Housing> findBySchoolId(Long schoolId);
}
