package shareAlbum.shareAlbum.domain.group.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Invitation {

    @Id
    @GeneratedValue
    @Column(name = "invitation_id")
    private Long id;

    private String invitation_receiver;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "group_id")
    private MyGroup myGroup;

    @Enumerated(EnumType.STRING)
    private InvitationStatus invitation_status;








}
