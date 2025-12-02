package com.springboot.controller;

import com.springboot.domain.Member;
import com.springboot.domain.MemberFormDto;
import com.springboot.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private PasswordEncoder passwordEncoder;

//    @GetMapping("/add")
//    public String memberForm(Model model) {
//        model.addAttribute("memberFormDto", new MemberFormDto());
//        return "addMember";
//    }
    @GetMapping("/add")
    public String memberForm(Model model) {
        model.addAttribute("memberFormDto", new MemberFormDto());
        return "addMember";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/add")
    public String newMember(@Valid @ModelAttribute MemberFormDto memberFormDto,
                            BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "addMember";
        }

        try {
            Member member = Member.createMember(memberFormDto, passwordEncoder);
            memberService.saveMember(member);
        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "addMember";
        }

        return "redirect:/";
    }

    @GetMapping(value = "/update/{memeberId}")
    public String requestUpdateMemberForm(@PathVariable(name = "memberId") String memberId, Model model) {
        Member member = memberService.getMemberByMemberId(memberId);
        model.addAttribute("memberFormDto", member);
        return "member/updateMember";
    }

    @PostMapping(value = "/update")
    public String submitUpdateMember(@Valid @ModelAttribute MemberFormDto memberFormDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "member/updateMember";
        }

        try {
            Member member = Member.createMember(memberFormDto, passwordEncoder);
            memberService.saveMember(member);
        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "member/updateMember";


        }

        return "redirect:/members";
    }

// 회원 삭제

    @GetMapping(value = "/delete/{memberId}")
    public String deleteMember(@PathVariable(name = "memberId") String memberId) {
        memberService.deleteMember(memberId);

        return "redirect:/logout";
    }
}