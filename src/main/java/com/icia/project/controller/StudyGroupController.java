package com.icia.project.controller;

import com.icia.project.dto.ApplyDTO;
import com.icia.project.dto.MemberDTO;
import com.icia.project.dto.PartyUserDTO;
import com.icia.project.dto.StudygroupDTO;
import com.icia.project.service.ApplyService;
import com.icia.project.service.MemberService;
import com.icia.project.service.PartyUserService;
import com.icia.project.service.StudygroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/studygroup")
public class StudyGroupController {
    private final StudygroupService studygroupService;
    private final MemberService memberService;
    private final ApplyService applyService;
    private final PartyUserService partyUserService;

    @Transactional
    @GetMapping("/list")
    public String list(Model model, HttpSession session) {
        List<StudygroupDTO> studygroupDTOList = studygroupService.findAll();
        String loginUser = (String) session.getAttribute("loginId");
        System.out.println("studygroupDTOList = " + studygroupDTOList);
        if (loginUser == null) {
            model.addAttribute("loginUserId", 0);
            System.out.println("0");
        } else {
            MemberDTO memberDTO = memberService.findByMemberId(loginUser);
            System.out.println("memberDTO = " + memberDTO);
            model.addAttribute("loginUserId", memberDTO.getId());
        }
        model.addAttribute("groupList", studygroupDTOList);
        return "/studyGroupPages/studyGroupList";
    }

    @GetMapping("/save")
    public String save() {
        return "/studyGroupPages/studyGroupSave";
    }

    //  모임 등록
    @PostMapping("/save")
    public String saveGroup(@ModelAttribute StudygroupDTO studygroupDTO, HttpSession session) throws IOException {
        String loginId = (String) session.getAttribute("loginId");
        MemberDTO memberDTO = memberService.findByMemberId(loginId);
        studygroupService.save(studygroupDTO, loginId);
        return "redirect:list";
    }

    //  모임 수정
    @PostMapping("/update")
    public String updateGroup(@ModelAttribute StudygroupDTO studygroupDTO) throws IOException {
        System.out.println("컨트롤러에 있는 업데이트 studygroupDTO = " + studygroupDTO);
        studygroupService.updateUser(studygroupDTO);
        return "redirect:list";
    }

    //  모임 시간 저장
    @PostMapping("/axiosSave")
    public ResponseEntity axiosSave(@RequestBody StudygroupDTO studygroupDTO) {
        return new ResponseEntity<>(studygroupDTO.getPartyTimes(), HttpStatus.OK);
    }

    @GetMapping("/axiosUpdate/{id}")
    public ResponseEntity axiosUpdate(@PathVariable Long id, HttpSession session) {
        StudygroupDTO studygroupDTO = studygroupService.findById(id);
        System.out.println("studygroupDTO = " + studygroupDTO);
        String loginUserId = (String) session.getAttribute("loginId");
        MemberDTO memberDTO = memberService.findByMemberId(loginUserId);
        if (studygroupDTO.getHostId() == memberDTO.getId()) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    // 업데이트 화면 출력
    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model, HttpSession session) {
        String loginId = (String) session.getAttribute("loginId");
        MemberDTO memberDTO = memberService.findByMemberId(loginId);
        System.out.println("id = " + id);
        StudygroupDTO studygroupDTO = studygroupService.findById(id);
        model.addAttribute("memberDTO", memberDTO);
        model.addAttribute("studygroupDTO", studygroupDTO);
        return "/studyGroupPages/studygroupUpdate";
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteGroup(@PathVariable Long id) {
        studygroupService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Transactional
    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model, HttpSession session) {
        StudygroupDTO studygroupDTO = studygroupService.findById(id);
        String loginUser = (String) session.getAttribute("loginId");
        if (loginUser == null) {
            model.addAttribute("loginUser", "");
            model.addAttribute("loginUserId", 0);
        } else {
            MemberDTO loginUserDTO = memberService.findByMemberId(loginUser);
            System.out.println("컨트롤러에 있는 loginUserDTO = " + loginUserDTO);
            model.addAttribute("loginUser", loginUserDTO.getMemberName());
            model.addAttribute("loginUserId", loginUserDTO.getId());
            ApplyDTO applyDTO = applyService.findApplyBtn(loginUserDTO.getId(), studygroupDTO.getId());
            PartyUserDTO partyUserDTO = partyUserService.findByGroupIdAndMemberId(studygroupDTO.getId(), loginUserDTO.getId());
            System.out.println("컨트롤러에 있는 applyDTO = " + applyDTO);
            System.out.println("스터디그룹컨트롤러에 있는 partyUserDTO = " + partyUserDTO);
            model.addAttribute("partyUserDTO", partyUserDTO);
            if (applyDTO != null) {
                model.addAttribute("applyDTO", applyDTO);
            } else {
                model.addAttribute("applyDTO", null);
            }
            if (partyUserDTO != null) {
                model.addAttribute("partyUserDTO", partyUserDTO);
            } else {
                model.addAttribute("partyUserDTO", null);
            }
        }
        model.addAttribute("group", studygroupDTO);
        return "/studyGroupPages/studyGroupDetail";
    }

    //  스터디룸 입장 보안
    @PostMapping("/room/{groupId}/{memberId}")
    public ResponseEntity roomAxios(@PathVariable Long groupId, @PathVariable Long memberId, HttpSession session) {
        System.out.println("groupId = " + groupId + "memberId" + memberId);
        PartyUserDTO partyUserDTO = partyUserService.findByGroupIdAndMemberId(groupId, memberId);
        System.out.println("스터디그룹 컨트롤러에 있는 partyUserDTO = " + partyUserDTO);
        String loginUser = (String) session.getAttribute("loginId");
        MemberDTO loginMember = memberService.findByMemberId(loginUser);
        System.out.println("loginMember = " + loginMember);
        if (partyUserDTO.getPartyId() == groupId && partyUserDTO.getUserName().equals(loginMember.getMemberName()) && partyUserDTO.getMemberId() == memberId) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}
