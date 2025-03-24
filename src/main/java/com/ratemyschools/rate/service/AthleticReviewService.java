package com.ratemyschools.rate.service;


import com.ratemyschools.rate.dto.AthleticReviews.GetAthleticsReviewDto;
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
            return dto;
        }).collect(Collectors.toList());
    }
    public Integer getAverageRating(Long athleteId) {
        return athleticsReviewRepository.findAverageRatingByAthleticsId(athleteId);
    }
    public Long getTotalReviews(Long athleteId) {
        return athleticsReviewRepository.countByAthleticsId(athleteId);
    }


}
