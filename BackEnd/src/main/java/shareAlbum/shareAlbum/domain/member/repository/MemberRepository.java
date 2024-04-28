package shareAlbum.shareAlbum.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shareAlbum.shareAlbum.domain.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member,Long> {

}
