package com.icia.project.controller;

import com.icia.project.dto.PartyUserDTO;
import com.icia.project.dto.ReviewDTO;
import com.icia.project.service.PartyUserService;
import com.icia.project.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {
    private final PartyUserService partyUserService;
    private final ReviewService reviewService;

    @GetMapping("/save/{groupId}/{memberId}")
    public String saveForm(@PathVariable Long groupId, @PathVariable Long memberId, Model model) {
        System.out.println("groupId = " + groupId + "memberId = " + memberId);
        PartyUserDTO partyUserDTO = partyUserService.findByGroupIdAndMemberId(groupId, memberId);
        model.addAttribute("partyUser", partyUserDTO);
        return "/studyGroupPages/studyGroupReview";
    }

    @PostMapping("/save")
    public String saveReview(@ModelAttribute ReviewDTO reviewDTO) {
        System.out.println("reviewDTO = " + reviewDTO);
        reviewService.reviewSave(reviewDTO);
        return "redirect:/review/list/" + reviewDTO.getGroupId();
    }

    @GetMapping("/list/{id}")
    public String reviewList(@PathVariable Long id) {
        System.out.println("리스트 id = " + id);
        return "/studyGroupPages/studyGroupReviewList";
    }
}
