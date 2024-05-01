package shareAlbum.shareAlbum.domain.member.service;

import org.springframework.validation.BindingResult;
import shareAlbum.shareAlbum.domain.member.dto.MemberDto;

import java.util.HashMap;
import java.util.Map;

public interface MemberService {

    //이메일,비밀번호 체크
    HashMap<String,String> vaildateSignUp(BindingResult result, MemberDto memberDto);

    HashMap<String, String> validateEmailAndNickName(String email,String nickname);
    HashMap<String, String> validatePhoneNumAndNickName(String phoneNum,String nickname);
    //회원가입
   void signUp(MemberDto memberDto) throws Exception;

}
