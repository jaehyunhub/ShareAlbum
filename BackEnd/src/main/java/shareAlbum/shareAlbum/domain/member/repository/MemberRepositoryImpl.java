package shareAlbum.shareAlbum.domain.member.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import shareAlbum.shareAlbum.domain.member.dto.MemberDto;

import javax.persistence.EntityManager;

public class MemberRepositoryImpl implements MemberReposiotryCustom{

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public MemberRepositoryImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }
    @Override
    public void savePhoneNumOrEmail(MemberDto memberDto) {
    }
}
