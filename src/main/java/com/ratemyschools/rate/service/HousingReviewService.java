package com.ratemyschools.rate.service;


import com.ratemyschools.rate.dto.HousingReviews.AddHousingReviewDto;
import com.ratemyschools.rate.dto.HousingReviews.GetHousingReviewDto;
import com.ratemyschools.rate.model.HousingReview;
import com.ratemyschools.rate.repository.HousingReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HousingReviewService {
    private final HousingReviewRepository housingReviewRepository;

    public HousingReviewService(HousingReviewRepository housingReviewRepository) {this.housingReviewRepository = housingReviewRepository;}

    public List<GetHousingReviewDto> getHousingReviewInfo(Long housingId) {
        List<HousingReview> housingReviewList = housingReviewRepository.findByHousingId(housingId);

        return housingReviewList.stream().map(housingReview -> {
            GetHousingReviewDto dto = new GetHousingReviewDto();
            dto.setId(housingReview.getId());
            dto.setRating(housingReview.getRating());
            dto.setReview(housingReview.getReview());
            dto.setCreatedAt(housingReview.getCreatedAt());
            return dto;
        }).collect(Collectors.toList());
    }

    public Integer getAverageRating(Long housingId) {return housingReviewRepository.findAverageRatingByHousingId(housingId);}

    public Long getTotalReviews(Long housingId) {return housingReviewRepository.countByHousingId(housingId);}

    public HousingReview addReview(AddHousingReviewDto input) {
        HousingReview housingReview = new HousingReview();
        housingReview.setHousing(input.getHousing());
        housingReview.setRating(input.getRating());
        housingReview.setReview(input.getReview());
        housingReview.setCreatedAt(input.getCreatedAt());
        return housingReviewRepository.save(housingReview);
    }
}
