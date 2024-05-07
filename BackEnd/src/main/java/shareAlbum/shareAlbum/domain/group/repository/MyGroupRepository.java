package shareAlbum.shareAlbum.domain.group.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shareAlbum.shareAlbum.domain.group.entity.MyGroup;

public interface MyGroupRepository extends JpaRepository<MyGroup,Long> {
}
