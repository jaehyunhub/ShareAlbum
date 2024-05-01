package shareAlbum.shareAlbum.domain.member.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.validation.BindingResult;
import shareAlbum.shareAlbum.domain.member.dto.MemberDto;
import shareAlbum.shareAlbum.domain.member.entity.Member;
import shareAlbum.shareAlbum.domain.member.entity.MemberStatus;
import shareAlbum.shareAlbum.domain.member.repository.MemberRepository;

import javax.naming.Binding;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
@Rollback(value = false)
public class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @BeforeEach
    public void before(){
        //핸드폰번호
        Member member1 = Member.builder()
                .loginId("01011111111")
                .name("김나비")
                .nickname("kim1234")
                .email("abc@abc.com")
                .password("123456")
                .phoneNum("01011111111")
                .memberStatus(MemberStatus.ACTIVE)
                .build();

        Member member2 = Member.builder()
                .loginId("abcd@abcd.com")
                .name("김호랑")
                .nickname("kimfe123")
                .email("abcd@abcd.com")
                .password("123456")
                .phoneNum("01066778888")
                .memberStatus(MemberStatus.ACTIVE)
                .build();

        Member member3 = Member.builder()
                .loginId("cd@acd.com")
                .name("김말숙")
                .nickname("abc1234")
                .email("cd@acd.com")
                .password("123456")
                .phoneNum("01024886444")
                .memberStatus(MemberStatus.ACTIVE)
                .build();
        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);

    }
    @Test
    public void 회원가입중복체크() throws Exception{
        //given
        MemberDto phoneCheck = MemberDto.builder()
                .loginId("01011111111")
                .name("김사자")
                .nickname("ki123")
                .phoneNum("01011111111")
                .password("123456")
                .email("bbbb@bbb.com")
                .build();

        MemberDto emailCheck = MemberDto.builder()
                .loginId("abcd@abcd.com")
                .name("김판다")
                .nickname("ki123")
                .email("abcd@abcd.com")
                .phoneNum("01069343123")
                .password("123456")
                .build();
        MemberDto nicknameCheck = MemberDto.builder()
                .loginId("zzz@abd.com")
                .name("김꿩")
                .nickname("abc1234")
                .password("123456")
                .phoneNum("01055662044")
                .email("zzz@abd.com")
                .build();

        //WHEN
        HashMap<String, String> result1 = memberService.validatePhoneNumAndNickName(phoneCheck.getPhoneNum(), phoneCheck.getNickname());
        HashMap<String, String> result2 = memberService.validateEmailAndNickName(emailCheck.getEmail(), emailCheck.getNickname());
        HashMap<String, String> result3 = memberService.validateEmailAndNickName(nicknameCheck.getEmail(), nicknameCheck.getNickname());

        //THEN
        Assertions.assertThat(result1.get("error")).isEqualTo("핸드폰 번호가 중복되었습니다.");
        Assertions.assertThat(result2.get("error")).isEqualTo("이메일이 중복되었습니다.");
        Assertions.assertThat(result3.get("error")).isEqualTo("닉네임이 중복되었습니다");
    }

    @Test
    public void 회원등록테스트() throws Exception{
        //given
        MemberDto member1 = MemberDto.builder()
                .loginId("01012356788")
                .name("김사자")
                .nickname("ki123")
                .phoneNum("01012356788")
                .password("kim3009!")
                .email("bbbb@bbb.com")
                .build();

        MemberDto member2 = MemberDto.builder()
                .loginId("zzdd@abcd.com")
                .name("김판다")
                .nickname("ki123")
                .email("zzdd@abcd.com")
                .phoneNum("01069343123")
                .password("123456")
                .build();
        MemberDto member3 = MemberDto.builder()
                .loginId("zzz@abd.com")
                .name("김꿩")
                .nickname("kkkk1234")
                .password("123456")
                .phoneNum("01055662044")
                .email("zzz@abd.com")
                .build();

        //WHEN
        memberService.signUp(member1);
        memberService.signUp(member2);
        memberService.signUp(member3);
        List<Member> result = memberRepository.findAll();
        //THEN
        Assertions.assertThat(result.size()).isEqualTo(6);
    }






}