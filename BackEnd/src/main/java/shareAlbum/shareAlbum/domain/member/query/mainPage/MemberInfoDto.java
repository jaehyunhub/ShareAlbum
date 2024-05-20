package shareAlbum.shareAlbum.domain.member.query.mainPage;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Getter
@NoArgsConstructor
public class MemberInfoDto {

    private String name;
    private String loginId;
    private String nickname;
    private List<MyGroupDto> myGroupList;
    private Map<Long,List<AlbumDto>> myAlbum;

    @Builder
    public MemberInfoDto(String name, String loginId, String nickname, List<MyGroupDto> myGroupList, Map<Long,List<AlbumDto>> myAlbum) {
        this.name = name;
        this.loginId = loginId;
        this.nickname = nickname;
        this.myGroupList = myGroupList;
        this.myAlbum = myAlbum;
    }

}
