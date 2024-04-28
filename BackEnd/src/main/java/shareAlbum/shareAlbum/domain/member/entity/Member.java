package shareAlbum.shareAlbum.domain.member.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shareAlbum.shareAlbum.domain.chat.entity.ChatMessage;
import shareAlbum.shareAlbum.domain.group.entity.Group;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id; //PK값
    private String name; //이름
    private String nickname; //사용ID
    private String email; // E-mail
    private String password; //비밀번호
    private String birthday; //생년월일
    private String phoneNum; //핸드폰번호


    @OneToMany(mappedBy = "member")
    private List<Group> groupMember = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<ChatMessage> chatMessages = new ArrayList<>();

    public Member(String name, String nickname, String email, String password, String birthday, String phoneNum) {
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
        this.phoneNum = phoneNum;
    }
}
