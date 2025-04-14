package com.ratemyschools.rate.service;

import com.ratemyschools.rate.dto.LifeStylesReview.AddLifeStylesReviewDto;
import com.ratemyschools.rate.dto.LifeStylesReview.GetLifeStylesReviewDto;
import com.ratemyschools.rate.model.LifeStyleReview;
import com.ratemyschools.rate.repository.LifeStylesReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LifeStyleReviewService {
    private final LifeStylesReviewRepository lifeStylesReviewRepository;

    public LifeStyleReviewService(LifeStylesReviewRepository lifeStylesReviewRepository) {this.lifeStylesReviewRepository = lifeStylesReviewRepository;}

    public List<GetLifeStylesReviewDto> getLifeStylesReviewInfo(Long lifeStyleId) {
        List<LifeStyleReview> lifeStyleReviewList = lifeStylesReviewRepository.findByLifeStyle_Id(lifeStyleId);

        return lifeStyleReviewList.stream().map(lifeStyleReview -> {
            GetLifeStylesReviewDto dto = new GetLifeStylesReviewDto();
            dto.setId(lifeStyleReview.getId());
            dto.setRating(lifeStyleReview.getRating());
            dto.setReview(lifeStyleReview.getReview());
            dto.setCreatedAt(lifeStyleReview.getCreatedAt());
            return dto;
        }).collect(Collectors.toList());
    }

    public Integer getAverageRating(Long lifeStyleId) {
        return lifeStylesReviewRepository.findAverageRatingByLifeStylesId(lifeStyleId);
    }
    public Long getTotalReviews(Long lifeStyleId) {
        return lifeStylesReviewRepository.countByLifeStylesId(lifeStyleId);
    }

    public LifeStyleReview addReview(AddLifeStylesReviewDto input) {
        LifeStyleReview lifeStyleReview = new LifeStyleReview();
        lifeStyleReview.setLifeStyle(input.getLifeStyle());
        lifeStyleReview.setRating(input.getRating());
        lifeStyleReview.setReview(input.getReview());
        lifeStyleReview.setCreatedAt(input.getCreatedAt());
        return lifeStylesReviewRepository.save(lifeStyleReview);
    }

    public boolean deleteLifeStyleReviewById(Long id) {
        if (lifeStylesReviewRepository.existsById(id)) {
            lifeStylesReviewRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
