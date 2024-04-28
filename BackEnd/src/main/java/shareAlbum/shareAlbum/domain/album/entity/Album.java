package shareAlbum.shareAlbum.domain.album.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shareAlbum.shareAlbum.domain.group.entity.Group;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Album {

    @Id
    @GeneratedValue
    @Column(name = "album_id")
    private Long id;

    private byte[] pictures;
    private String content;

    @Enumerated(EnumType.STRING)
    private AlbumStatus albumStatus;

    @OneToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @OneToMany(mappedBy = "album")
    private List<Comment> comment = new ArrayList<>();

    @OneToMany(mappedBy = "album")
    private List<Like> like = new ArrayList<>();
}
