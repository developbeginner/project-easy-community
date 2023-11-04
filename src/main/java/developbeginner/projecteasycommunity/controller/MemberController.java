package developbeginner.projecteasycommunity.controller;

import developbeginner.projecteasycommunity.service.MemberService;
import developbeginner.projecteasycommunity.vo.Member;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
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
    public String getRegisterPage(Model model, @ModelAttribute Member member) {
        model.addAttribute("member", new Member());
        return "member/register";
    }

    @PostMapping("/register")
    public String callRegisterService(Model model, @ModelAttribute Member member, RedirectAttributes redirectAttributes) {
        if (member.getEmail().isBlank()) {
            System.out.println("이메일이 비어있습니다.");
            model.addAttribute("message", "이메일이 비어있습니다.");
            return "/member/register";
        }
        if (member.getPassword().isBlank()) {
            System.out.println("비밀번호가 비어있습니다.");
            model.addAttribute("message", "비밀번호가 비어있습니다.");
            return "/member/register";
        }
        if (member.getNickname().isBlank()) {
            System.out.println("닉네임이 비어있습니다.");
            model.addAttribute("message", "닉네임이 비어있습니다.");
            return "/member/register";
        }
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
        model.addAttribute("member", new Member());
        return "member/login";
    }

    @PostMapping("/login")
    public String callLoginService(Model model, @ModelAttribute Member member, RedirectAttributes redirectAttributes) {
        if (member.getEmail().isBlank()) {
            System.out.println("이메일이 비어있습니다.");
            model.addAttribute("message", "이메일이 비어있습니다.");
            return "/member/login";
        }
        if (member.getPassword().isBlank()) {
            System.out.println("비밀번호가 비어있습니다.");
            model.addAttribute("message", "비밀번호가 비어있습니다.");
            return "/member/login";
        }
        try {
            memberService.login(member);
            redirectAttributes.addFlashAttribute("message", "로그인이 성공하였습니다.");
            return "redirect:/home";
        } catch (IllegalArgumentException error) {
            model.addAttribute("message", error.getMessage());
            return "/member/login";
        }
    }
}
