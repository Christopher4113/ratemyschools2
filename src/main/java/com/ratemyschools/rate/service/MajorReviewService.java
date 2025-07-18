package com.ratemyschools.rate.service;

import com.ratemyschools.rate.dto.MajorReviews.AddMajorsReviewDto;
import com.ratemyschools.rate.dto.MajorReviews.GetMajorsReviewDto;
import com.ratemyschools.rate.dto.MajorReviews.SchoolMajorsAverageDto;
import com.ratemyschools.rate.model.MajorReview;
import com.ratemyschools.rate.repository.MajorReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MajorReviewService {
    private final MajorReviewRepository majorReviewRepository;

    public MajorReviewService(MajorReviewRepository majorReviewRepository) {this.majorReviewRepository = majorReviewRepository;}

    public List<GetMajorsReviewDto> getMajorsReviewInfo(Long majorId) {
        List<MajorReview> majorsReviewList = majorReviewRepository.findByMajorId(majorId);

        return majorsReviewList.stream().map(majorsReview -> {
            GetMajorsReviewDto dto = new GetMajorsReviewDto();
            dto.setId(majorsReview.getId());
            dto.setRating(majorsReview.getRating());
            dto.setReview(majorsReview.getReview());
            dto.setCreatedAt(majorsReview.getCreatedAt());
            dto.setUsername(majorsReview.getUsername());
            return dto;
        }).collect(Collectors.toList());
    }

    public Double getAverageRating(Long majorId) {return majorReviewRepository.findAverageRatingByMajorsId(majorId);}

    public Long getTotalReviews(Long majorId) {return majorReviewRepository.countByMajorsId(majorId);}

    public MajorReview addReview(AddMajorsReviewDto input) {
        MajorReview majorsReview = new MajorReview();
        majorsReview.setMajor(input.getMajor());
        majorsReview.setRating(input.getRating());
        majorsReview.setReview(input.getReview());
        majorsReview.setCreatedAt(input.getCreatedAt());
        majorsReview.setUsername(input.getUsername());
        return majorReviewRepository.save(majorsReview);
    }

    public boolean deleteMajorReviewById(Long id) {
        if (majorReviewRepository.existsById(id)) {
            majorReviewRepository.deleteById(id);
            return true;
        }
        return false;
    }
    public List<SchoolMajorsAverageDto> getAverageRatingsForAllSchools() {
        return majorReviewRepository.findAverageMajorsRatingForAllSchools();
    }

}
