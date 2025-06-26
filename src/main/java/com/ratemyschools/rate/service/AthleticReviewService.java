package com.ratemyschools.rate.service;


import com.ratemyschools.rate.dto.AthleticReviews.AddAthleticsReviewDto;
import com.ratemyschools.rate.dto.AthleticReviews.GetAthleticsReviewDto;
import com.ratemyschools.rate.dto.AthleticReviews.SchoolAthleticsAverageDto;
import com.ratemyschools.rate.model.AthleticsReview;
import com.ratemyschools.rate.repository.AthleticsReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AthleticReviewService {
    private final AthleticsReviewRepository athleticsReviewRepository;

    public AthleticReviewService(AthleticsReviewRepository athleticsReviewRepository) {this.athleticsReviewRepository = athleticsReviewRepository;}

    public List<GetAthleticsReviewDto> getAthleticsReviewInfo(Long athleteId) {
        List<AthleticsReview> athleticsReviewList = athleticsReviewRepository.findByAthleticsId(athleteId);

        return athleticsReviewList.stream().map(athleticsReview -> {
            GetAthleticsReviewDto dto = new GetAthleticsReviewDto();
            dto.setId(athleticsReview.getId());
            dto.setRating(athleticsReview.getRating());
            dto.setReview(athleticsReview.getReview());
            dto.setCreatedAt(athleticsReview.getCreatedAt());
            dto.setUsername(athleticsReview.getUsername());
            return dto;
        }).collect(Collectors.toList());
    }
    public Double getAverageRating(Long athleteId) {
        return athleticsReviewRepository.findAverageRatingByAthleticsId(athleteId);
    }
    public Long getTotalReviews(Long athleteId) {
        return athleticsReviewRepository.countByAthleticsId(athleteId);
    }

    public AthleticsReview addReview(AddAthleticsReviewDto input) {
        AthleticsReview athleticsReview = new AthleticsReview();
        athleticsReview.setAthletics(input.getAthletics());
        athleticsReview.setRating(input.getRating());
        athleticsReview.setReview(input.getReview());
        athleticsReview.setCreatedAt(input.getCreatedAt());
        athleticsReview.setUsername(input.getUsername());

        return athleticsReviewRepository.save(athleticsReview);
    }

    public boolean deleteAthleticReviewsById(Long id) {
        if (athleticsReviewRepository.existsById(id)) {
            athleticsReviewRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<SchoolAthleticsAverageDto> getAverageRatingsForAllSchools() {
        return athleticsReviewRepository.findAverageAthleticsRatingForAllSchools();
    }

}
