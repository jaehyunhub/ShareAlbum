package shareAlbum.shareAlbum.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shareAlbum.shareAlbum.domain.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member,Long>,MemberReposiotryCustom {

    //회원가입시 휴대폰 번호 또는 이메일 주소로 회원가입

    Member findByEmail(String email);
    Member findByPhoneNum(String phonenum);
    Member findByNickname(String nickname);

}
