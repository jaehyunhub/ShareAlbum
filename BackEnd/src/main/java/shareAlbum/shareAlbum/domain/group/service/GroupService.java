package shareAlbum.shareAlbum.domain.group.service;

import shareAlbum.shareAlbum.domain.group.dto.GroupCreateDto;
import shareAlbum.shareAlbum.domain.group.entity.GroupList;

public interface GroupService {

    //그룹 만들기
    public void createGroup(GroupList groupList);
    //그룹 초대 보내기
    //그룹 멤버 추방?
    //그룹 삭제

}
