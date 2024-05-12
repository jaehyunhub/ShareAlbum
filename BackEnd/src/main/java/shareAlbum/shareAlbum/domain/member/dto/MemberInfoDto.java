package shareAlbum.shareAlbum.domain.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import shareAlbum.shareAlbum.domain.album.entity.Album;
import shareAlbum.shareAlbum.domain.group.entity.MyGroup;

import java.util.List;

@Getter
@NoArgsConstructor
public class MemberInfoDto {

    private String name;
    private String loginId;
    private String nickname;
    private List<MyGroup> myGroupList;
    private List<Album> myAlbum;

    public void checkPhoneFormat(String loginId) {
        if (loginId.contains("-")) {
            this.loginId = loginId.replace("-","");
        }
    }
}
