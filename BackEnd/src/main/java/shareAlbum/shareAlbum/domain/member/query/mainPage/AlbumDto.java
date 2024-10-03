package shareAlbum.shareAlbum.domain.member.query.mainPage;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@ToString
public class AlbumDto {
    private Long id;
    private String imagePath;
    private Long groupListId;
    private String content;
    private Long commentCount;
    private Long myLikeCount;

    @Builder
    public AlbumDto(Long id, String imagePath, Long groupListId, String content, Long commentCount, Long myLikeCount) {
        this.id = id;
        this.imagePath = imagePath;
        this.groupListId = groupListId;
        this.content = content;
        this.commentCount = commentCount;
        this.myLikeCount = myLikeCount;
    }
}
