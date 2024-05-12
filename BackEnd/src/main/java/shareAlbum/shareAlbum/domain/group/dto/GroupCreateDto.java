package shareAlbum.shareAlbum.domain.group.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@Builder
public class GroupCreateDto {


    private String groupCreater;
    @NotBlank(message = "그룹 명을 입력해주세요")
    private String groupTitle;

    private String groupCategory;





}
