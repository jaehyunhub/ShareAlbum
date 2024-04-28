package shareAlbum.shareAlbum.domain.group.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class GroupList {

    @Id
    @GeneratedValue
    @Column(name = "groupList_id")
    private Long id;
    private String groupCreator;
    private String groupTitle;

    @Enumerated(EnumType.STRING)
    private GroupCategory groupCategory;

    public GroupList(String groupCreator, String groupTitle, GroupCategory groupCategory) {
        this.groupCreator = groupCreator;
        this.groupTitle = groupTitle;
        this.groupCategory = groupCategory;
    }
}
