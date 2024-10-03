package shareAlbum.shareAlbum.domain.group.dto;

import lombok.*;
import shareAlbum.shareAlbum.domain.group.entity.InvitationStatus;

@NoArgsConstructor
@Getter
@ToString
public class GroupInvitationDto {

    private Long invitationId;
    private Long groupId;
    private String groupTitle;
    private String inviterId;
    private String receiverId;
    private InvitationStatus invitationStatus;

    @Builder
    public GroupInvitationDto(Long invitationId, Long groupId, String groupTitle, String inviterId, String receiverId, InvitationStatus invitationStatus) {
        this.invitationId = invitationId;
        this.groupId = groupId;
        this.groupTitle = groupTitle;
        this.inviterId = inviterId;
        this.receiverId = receiverId;
        this.invitationStatus = invitationStatus;
    }
}
