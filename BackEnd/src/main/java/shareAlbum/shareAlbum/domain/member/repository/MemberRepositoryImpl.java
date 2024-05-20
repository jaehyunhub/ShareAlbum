package shareAlbum.shareAlbum.domain.member.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import shareAlbum.shareAlbum.domain.group.repository.MyGroupRepository;
import shareAlbum.shareAlbum.domain.member.query.mainPage.AlbumDto;
import shareAlbum.shareAlbum.domain.member.query.mainPage.MemberInfoDto;
import shareAlbum.shareAlbum.domain.member.entity.Member;
import shareAlbum.shareAlbum.domain.member.query.mainPage.MyGroupDto;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static shareAlbum.shareAlbum.domain.album.entity.QAlbum.album;

@Repository
public class MemberRepositoryImpl implements MemberReposiotryCustom{

    private final MyGroupRepository myGroupRepository;
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;
    @Autowired

    public MemberRepositoryImpl(MyGroupRepository myGroupRepository, EntityManager em) {
        this.myGroupRepository = myGroupRepository;
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    //로그인 후 메인페이지에 보여줄 내용 전체 조회
    //그룹 설정안한 사진들, 앨범에 좋아요 수, 댓글 수, 총 게시물 수, 그룹 명들 ,그룹 멤버 수, 메세지 열람 안한 수
    @Override
    public MemberInfoDto searchMemberAllInfo(Member member){
        List<MyGroupDto> myGroupList = myGroupRepository.findByMemberId(member.getId());
        List<Long> myGroupIdList = myGroupList.stream().map(o->o.getId()).collect(Collectors.toList());
        System.out.println("myGroupIdList.toString() = " + myGroupIdList.toString());

        List<AlbumDto> myAlbumList = queryFactory
                .select(Projections.fields(AlbumDto.class,
                        album.id,
                        album.imagePath,
                        album.content,
                        album.groupList.id.as("groupListId") ))
                .from(album)
                .where(album.groupList.id.in(myGroupIdList))
                .fetch();

        Map<Long, List<AlbumDto>> myAlbumMap = myAlbumList.stream()
                .collect(Collectors.groupingBy(AlbumDto::getGroupListId));

        MemberInfoDto memberInfoDto = MemberInfoDto.builder()
                .name(member.getName())
                .loginId(member.getLoginId())
                .nickname(member.getNickname())
                .myGroupList(myGroupList)
                .myAlbum(myAlbumMap)
                .build();

        return memberInfoDto;
    }

}
