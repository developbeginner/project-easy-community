package developbeginner.projecteasycommunity.controller;

import developbeginner.projecteasycommunity.dto.MemberDTO;
import developbeginner.projecteasycommunity.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/member")
public class MemberController {
    private MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("member", new MemberDTO.MemberRequestDTO());
        return "/member/register";
    }

    @PostMapping("/register")
    public String callRegisterService(Model model, @ModelAttribute MemberDTO.MemberRequestDTO member, RedirectAttributes redirectAttributes) {
        // check member email, password, nickname is blank
        if (member.getEmail().isBlank()) {
            model.addAttribute("message", "이메일이 비어있습니다.");
            return "/member/register";
        }
        if (member.getPassword().isBlank()) {
            model.addAttribute("message", "비밀번호가 비어있습니다.");
            return "/member/register";
        }
        if (member.getNickname().isBlank()) {
            model.addAttribute("message", "닉네임이 비어있습니다.");
            return "/member/register";
        }
        // call actual member service
        try {
            memberService.register(member);
            redirectAttributes.addFlashAttribute("message", "회원가입에 성공했습니다.");
            return "redirect:/member/login";
        } catch (DataIntegrityViolationException error) {
            model.addAttribute("message", "중복된 회원입니다.");
            return "/member/register";
        }
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        model.addAttribute("member", new MemberDTO());
        return "/member/login";
    }
}
