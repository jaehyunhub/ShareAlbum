package shareAlbum.shareAlbum.domain.group.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class GroupInvitationDto {

    private Long groupId;
    private String inviterId;
    private String receiverId;


}
