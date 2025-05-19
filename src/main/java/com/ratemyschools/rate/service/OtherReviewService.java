package com.ratemyschools.rate.service;

import com.ratemyschools.rate.dto.OtherReviews.AddOthersReviewDto;
import com.ratemyschools.rate.dto.OtherReviews.GetOthersReviewDto;
import com.ratemyschools.rate.dto.OtherReviews.SchoolOthersAverageDto;
import com.ratemyschools.rate.model.OtherReview;
import com.ratemyschools.rate.repository.OtherReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OtherReviewService {
    private final OtherReviewRepository otherReviewRepository;

    public OtherReviewService(OtherReviewRepository otherReviewRepository) {this.otherReviewRepository = otherReviewRepository;}

    public List<GetOthersReviewDto> getOthersReviewInfo(Long otherId) {
        List<OtherReview> otherReviewList = otherReviewRepository.findByOtherId(otherId);

        return otherReviewList.stream().map(otherReview -> {
            GetOthersReviewDto dto = new GetOthersReviewDto();
            dto.setId(otherReview.getId());
            dto.setRating(otherReview.getRating());
            dto.setReview(otherReview.getReview());
            dto.setCreatedAt(otherReview.getCreatedAt());
            return dto;
        }).collect(Collectors.toList());
    }

    public Double getAverageRating(Long otherId) {return otherReviewRepository.findAverageRatingByOthersId(otherId);}

    public Long getTotalReviews(Long otherId) {return  otherReviewRepository.countByOthersId(otherId);}

    public OtherReview addReview(AddOthersReviewDto input) {
        OtherReview otherReview = new OtherReview();
        otherReview.setOther(input.getOther());
        otherReview.setRating(input.getRating());
        otherReview.setReview(input.getReview());
        otherReview.setCreatedAt(input.getCreatedAt());
        return otherReviewRepository.save(otherReview);
    }
    public boolean deleteOtherReviewById(Long id) {
        if (otherReviewRepository.existsById(id)) {
            otherReviewRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<SchoolOthersAverageDto> getAverageRatingsForAllSchools() {
        return otherReviewRepository.findAverageOthersRatingForAllSchools();
    }

}
