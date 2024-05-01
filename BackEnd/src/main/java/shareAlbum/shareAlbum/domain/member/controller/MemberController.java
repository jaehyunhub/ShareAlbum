package shareAlbum.shareAlbum.domain.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import shareAlbum.shareAlbum.domain.member.dto.MemberDto;
import shareAlbum.shareAlbum.domain.member.repository.MemberRepository;
import shareAlbum.shareAlbum.domain.member.service.MemberService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;

    //회원가입
    @PostMapping("/signup")
    public Map<String,String> signUp(@Valid @RequestBody MemberDto memberDto, BindingResult result) {
        try {
            HashMap<String, String> check = memberService.vaildateSignUp(result,memberDto);
            System.out.println("여기까지는 로직이 도는거임?");
            System.out.println("check = " + check);
            if(!check.containsKey("success")) {
                System.out.println("here11111");
            }else{
                memberService.signUp(memberDto);
            }
            return check;

        } catch (Exception e) {
            System.out.println("here5555");
            HashMap<String, String> error = new HashMap<>();
            error.put("error", "로그인 실패");
            return error;
        }
    }
}
