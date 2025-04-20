package com.ratemyschools.rate.repository;

import com.ratemyschools.rate.model.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends CrudRepository<Profile, Long> {
    Optional<Profile> findByUserId(Long userId);
}
