package shareAlbum.shareAlbum.domain.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import shareAlbum.shareAlbum.domain.member.dto.MemberDto;
import shareAlbum.shareAlbum.domain.member.dto.MemberLoginDto;
import shareAlbum.shareAlbum.domain.member.query.mainPage.MemberInfoDto;
import shareAlbum.shareAlbum.domain.member.repository.MemberRepository;
import shareAlbum.shareAlbum.domain.member.service.MemberService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;

    //회원가입
    @PostMapping("/signup")
    public ResponseEntity<Map<String,String>> signUp(@Valid @RequestBody MemberDto memberDto, BindingResult result) {
        try {
            HashMap<String, String> check = memberService.vaildateSignUp(result, memberDto);
            if (check.containsKey("success")) {
                memberService.signUp(memberDto);
                return ResponseEntity.ok().build();
            }
            //실패한 경우
            return ResponseEntity.badRequest().body(check);

        } catch (Exception e) {
            HashMap<String, String> error = new HashMap<>();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/login")
    public ResponseEntity<MemberInfoDto> login(@RequestBody @Valid MemberLoginDto memberLoginDto,BindingResult result) {
        //아이디 정보 체크
        try {
            if (result.hasErrors()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            System.out.println("memberLoginDto.toString() = " + memberLoginDto.toString());
            MemberInfoDto memberInfoDto = memberService.logIn(memberLoginDto);
            //회원정보가 있는지 한번 더 체크
            if (memberInfoDto == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.ok().body(memberInfoDto);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }







    }
}
