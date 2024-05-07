package shareAlbum.shareAlbum.domain.group.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shareAlbum.shareAlbum.domain.group.entity.GroupList;
import shareAlbum.shareAlbum.domain.group.entity.MyGroup;

public interface GroupRepository extends JpaRepository<GroupList,String> {

}
