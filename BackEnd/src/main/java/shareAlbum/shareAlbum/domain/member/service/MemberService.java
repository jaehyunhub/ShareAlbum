package shareAlbum.shareAlbum.domain.member.service;

import org.springframework.validation.BindingResult;
import shareAlbum.shareAlbum.domain.member.dto.MemberDto;
import shareAlbum.shareAlbum.domain.member.dto.MemberLoginDto;
import shareAlbum.shareAlbum.domain.member.query.mainPage.MemberInfoDto;

import java.util.HashMap;

public interface MemberService {

    //회원가입체크
    HashMap<String,String> vaildateSignUp(BindingResult result, MemberDto memberDto);
    HashMap<String, String> validateEmailAndNickName(String email,String nickname);
    HashMap<String, String> validatePhoneNumAndNickName(String phoneNum,String nickname);
    //회원가입
   void signUp(MemberDto memberDto) throws Exception;

    //로그인 체크
    MemberInfoDto logIn(MemberLoginDto memberLoginDto);

}
