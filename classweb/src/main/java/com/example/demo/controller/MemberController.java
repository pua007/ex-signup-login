package com.example.demo.controller;


import com.example.demo.dto.MemberDto;
import com.example.demo.entity.MemberEntity;
import com.example.demo.service.MemberService;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class MemberController {
    //생성자 주입방식
    private final MemberService memberService;

    //회원가입 페이지 출력요청
    @GetMapping("/member/save")
    public String saveForm(){
        return "save";
    }

    //@ModelAttribute 매칭해준다.
    @PostMapping("/member/save")
    public String save(@ModelAttribute MemberDto memberDto){
        //스트링 변수로 값을 받아서 service로 넘겨주고 service에서는 repository로 넘겨주고 -> repository에서는 DB로 넘겨준다
        System.out.println("MemberController.save");
        memberService.save(memberDto);
        return "login";
    }

    @GetMapping("/member/login")
    public String loginForm(){
        return "login";
    }

    @PostMapping("/member/login")
    public String login(@ModelAttribute MemberDto memberDto, HttpSession session){
        MemberDto loginResult = memberService.login(memberDto);
        if(loginResult != null){
            //로그인 성공
            session.setAttribute("loginEmail", loginResult.getMemberEmail());
            return "main";
        }else{
            //로그인 실패
            return "login";
        }

    }

    @GetMapping("/member/")
    public String findAll(Model model){
        List<MemberDto> memberDtoList = memberService.findAll();
        // 어떠한 html로 가져갈 데이터가 있디면 model사용
        model.addAttribute("memberList", memberDtoList);
        return "list";
    }

    @GetMapping("/member/{id}")
    public String findById(@PathVariable("id") Long id, Model model){
        MemberDto memberDto = memberService.findById(id);
        model.addAttribute("member", memberDto);
        return "detail";
    }

    @GetMapping("/member/update")
    public String updateForm(HttpSession session, Model model){
        String myEmail = (String)session.getAttribute("loginEmail");
        MemberDto memberDto = memberService.updateForm(myEmail);
        model.addAttribute("updateMember", memberDto);
        return "update";
    }

    @PostMapping("/member/update")
    public String update(@ModelAttribute MemberDto memberDto){
        memberService.update(memberDto);
        return "redirect:/member/"+ memberDto.getId();
    }

    @GetMapping("/member/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        memberService.deleteById(id);
        return "redirect:/member/";
    }

    @GetMapping("/member/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return"index";
    }

    @PostMapping("/member/email-check")//@RequestParam("name")
    public @ResponseBody String emailCheck(@RequestParam("memberEmail") String memberEmail){
        //ajax를 쓸때는 리턴 타입앞에 @ResponseBody를 써줘야 한다.
        System.out.println("memberEmail = " + memberEmail);
        String checkResult = memberService.emailCheck(memberEmail);
        if(checkResult != null){
            return "ok";
        }else{
            return "no";
        }

    }
}
