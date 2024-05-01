package shareAlbum.shareAlbum.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import shareAlbum.shareAlbum.domain.member.dto.MemberDto;
import shareAlbum.shareAlbum.domain.member.entity.Member;
import shareAlbum.shareAlbum.domain.member.entity.MemberStatus;
import shareAlbum.shareAlbum.domain.member.repository.MemberRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public HashMap<String,String> vaildateSignUp(BindingResult result,MemberDto memberDto) {
        HashMap<String, String> check = new HashMap<>();

        //입력 값이 잘못되었으면
        if(result.hasErrors()){
            List<FieldError> errors = result.getFieldErrors();
            for (FieldError error : errors) {
                String fieldName = error.getField();
                String errorMessage = error.getDefaultMessage();
                check.put(fieldName, errorMessage);
            }
            return check;
        //입력 값은 제대로 들어왔으면
        }else{
            //로그인 아이디가 이메일인지 핸드폰번호인지 체크
            memberDto.checkEmailOrPhone(memberDto.getLoginId());
            String phoneNum = memberDto.getPhoneNum();
            String nickname = memberDto.getNickname();

            if (phoneNum != null) {
                HashMap<String, String> phoneAndNicknameCheck = validatePhoneNumAndNickName(phoneNum, nickname);
                if (phoneAndNicknameCheck.containsKey("error")) {
                    return phoneAndNicknameCheck;
                }
            }else{
                String email = memberDto.getEmail();
                if (email != null) {
                    HashMap<String, String> emailAndNicknameCheck = validateEmailAndNickName(email, nickname);
                    if (emailAndNicknameCheck.containsKey("error")) {
                        return emailAndNicknameCheck;
                    }
                }
            }
            HashMap<String, String> success = new HashMap<>();
            success.put("success", "success");
            return success;
        }
    }

    @Override
    public HashMap<String, String> validateEmailAndNickName(String email,String nickname) {
        HashMap<String, String> result = new HashMap<>();
        if(memberRepository.findByEmail(email)!=null){
            result.put("error", "이메일이 중복되었습니다.");
            return result;
        }else{
            if (memberRepository.findByNickname(nickname)!=null) {
                result.put("error","닉네임이 중복되었습니다");
                return result;
            }
        }
        return result;
    }

    @Override
    public HashMap<String, String> validatePhoneNumAndNickName(String phoneNum, String nickName) {
        HashMap<String, String> result = new HashMap<>();
        if(memberRepository.findByPhoneNum(phoneNum) !=null){
            result.put("error", "핸드폰 번호가 중복되었습니다.");
            return result;
        }else{
            if (memberRepository.findByNickname(nickName)!=null) {
                result.put("error","닉네임이 중복되었습니다");
                return result;

            }
        }
        return result;
    }

    @Override
    @Transactional
    public void signUp(MemberDto memberDto) throws Exception {
        try {
            System.out.println("memberDto = " + memberDto);
            Member member = Member.builder()
                    .loginId(memberDto.getLoginId())
                    .email(memberDto.getEmail())
                    .phoneNum(memberDto.getPhoneNum())
                    .name(memberDto.getName())
                    .nickname(memberDto.getNickname())
                    .password(memberDto.getPassword())
                    .memberStatus(MemberStatus.ACTIVE)
                    .build();
            System.out.println("member = " + member);
            memberRepository.save(member);
        } catch (Exception e) {
            System.out.println("e = " + e);
        }
    }
}
