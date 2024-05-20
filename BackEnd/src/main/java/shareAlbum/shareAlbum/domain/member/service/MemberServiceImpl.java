package shareAlbum.shareAlbum.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import shareAlbum.shareAlbum.domain.member.dto.MemberDto;
import shareAlbum.shareAlbum.domain.member.dto.MemberLoginDto;
import shareAlbum.shareAlbum.domain.member.query.mainPage.MemberInfoDto;
import shareAlbum.shareAlbum.domain.member.entity.Member;
import shareAlbum.shareAlbum.domain.member.entity.MemberStatus;
import shareAlbum.shareAlbum.domain.member.repository.MemberRepository;

import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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
            System.out.println("memberDto = " + memberDto);

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
    public HashMap<String, String> validateEmailAndNickName(String email,String nickName) {
        HashMap<String, String> result = new HashMap<>();
        Optional<Member> emailValidateCheck = memberRepository.findByEmail(email);
        Optional<Member> nickNameValidateCheck = memberRepository.findByNickname(nickName);
        if(emailValidateCheck.isPresent()){
            System.out.println("memberRepository = " + memberRepository.findByEmail(email));
            result.put("error", "이메일이 중복되었습니다.");
            return result;
        }else{
            if (nickNameValidateCheck.isPresent()) {
                System.out.println("memberRepository = " + memberRepository.findByNickname(nickName));
                result.put("error","닉네임이 중복되었습니다");
                return result;
            }
        }
        return result;
    }

    @Override
    public HashMap<String, String> validatePhoneNumAndNickName(String phoneNum, String nickName) {
        HashMap<String, String> result = new HashMap<>();
        Optional<Member> phoneValidateCheck = memberRepository.findByPhoneNum(phoneNum);
        Optional<Member> nickNameValidateCheck = memberRepository.findByNickname(nickName);

        if(phoneValidateCheck.isPresent()){
            System.out.println("memberRepository = " + memberRepository.findByPhoneNum(phoneNum));
            result.put("error", "핸드폰 번호가 중복되었습니다.");
            return result;
        }else{
            if (nickNameValidateCheck.isPresent()) {
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
            Member member = Member.builder()
                    .loginId(memberDto.getLoginId())
                    .email(Optional.ofNullable(memberDto.getEmail()))
                    .phoneNum(Optional.ofNullable(memberDto.getPhoneNum()))
                    .name(memberDto.getName())
                    .nickname(memberDto.getNickname())
                    .password(memberDto.getPassword())
                    .memberStatus(MemberStatus.ACTIVE)
                    .build();
            memberRepository.save(member);
        } catch (Exception e) {
            System.out.println("e = " + e);
        }
    }

    @Override
    public MemberInfoDto logIn(MemberLoginDto memberLoginDto) {
        MemberInfoDto memberInfoDto = new MemberInfoDto();
        Member member = memberRepository.findByLoginId(memberLoginDto.getLoginId()).orElse(null);
        //유저 체크
        if (member == null) {
            throw new NoSuchElementException("회원 정보가 없습니다.");
        }
        //입력된 비밀번호랑 db에 저장된 비밀번호 체크
        if(member.getPassword().equals(memberLoginDto.getPassword())) {
            return memberRepository.searchMemberAllInfo(member);
        }else{
            throw new IllegalStateException("비밀번호가 다릅니다.");
        }
    }

}
