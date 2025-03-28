package com.ratemyschools.rate.service;

import com.ratemyschools.rate.dto.ClubReviews.AddClubsReviewDto;
import com.ratemyschools.rate.dto.ClubReviews.GetClubsReviewDto;
import com.ratemyschools.rate.model.ClubsReview;
import com.ratemyschools.rate.repository.ClubsReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClubReviewService {
    private final ClubsReviewRepository clubsReviewRepository;

    public ClubReviewService(ClubsReviewRepository clubsReviewRepository) {this.clubsReviewRepository = clubsReviewRepository;}

    public List<GetClubsReviewDto> getClubsReviewInfo(Long clubId) {
        List<ClubsReview> clubsReviewList = clubsReviewRepository.findByClubsId(clubId);

        return clubsReviewList.stream().map(clubsReview -> {
            GetClubsReviewDto dto = new GetClubsReviewDto();
            dto.setId(clubsReview.getId());
            dto.setRating(clubsReview.getRating());
            dto.setReview(clubsReview.getReview());
            dto.setCreatedAt(clubsReview.getCreatedAt());
            return dto;
        }).collect(Collectors.toList());
    }

    public Integer getAverageRating(Long clubId) {
        return clubsReviewRepository.findAverageRatingByClubsId(clubId);
    }

    public Long getTotalReviews(Long clubId) {
        return clubsReviewRepository.countByClubsId(clubId);
    }

    public ClubsReview addReview(AddClubsReviewDto input) {
        ClubsReview clubsReview = new ClubsReview();
        clubsReview.setClubs(input.getClubs());
        clubsReview.setRating(input.getRating());
        clubsReview.setReview(input.getReview());
        clubsReview.setCreatedAt(input.getCreatedAt());
        return clubsReviewRepository.save(clubsReview);
    }
}
