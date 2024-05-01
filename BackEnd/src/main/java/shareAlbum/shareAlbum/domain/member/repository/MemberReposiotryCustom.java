package shareAlbum.shareAlbum.domain.member.repository;

import shareAlbum.shareAlbum.domain.member.dto.MemberDto;

public interface MemberReposiotryCustom {
    //회원아이디를 찾고 없으면 이메일을 찾기
    void savePhoneNumOrEmail(MemberDto memberDto);
}
