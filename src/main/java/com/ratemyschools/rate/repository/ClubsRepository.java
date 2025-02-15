package com.ratemyschools.rate.repository;

import com.ratemyschools.rate.model.Clubs;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClubsRepository extends CrudRepository<Clubs,Long> {
    Optional<Clubs> findByClubName(String clubName);
}
