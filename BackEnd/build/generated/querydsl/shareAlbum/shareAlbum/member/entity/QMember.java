package shareAlbum.shareAlbum.member.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;
import shareAlbum.shareAlbum.domain.chat.entity.ChatMessage;
import shareAlbum.shareAlbum.domain.group.entity.Group;
import shareAlbum.shareAlbum.domain.member.entity.Member;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = 1788832785L;

    public static final QMember member = new QMember("member1");

    public final StringPath birthday = createString("birthday");

    public final ListPath<ChatMessage, shareAlbum.shareAlbum.chat.entity.QChatMessage> chatMessages = this.<ChatMessage, shareAlbum.shareAlbum.chat.entity.QChatMessage>createList("chatMessages", ChatMessage.class, shareAlbum.shareAlbum.chat.entity.QChatMessage.class, PathInits.DIRECT2);

    public final StringPath email = createString("email");

    public final ListPath<Group, shareAlbum.shareAlbum.group.entity.QGroup> groupMember = this.<Group, shareAlbum.shareAlbum.group.entity.QGroup>createList("groupMember", Group.class, shareAlbum.shareAlbum.group.entity.QGroup.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final StringPath nickname = createString("nickname");

    public final StringPath password = createString("password");

    public final StringPath phoneNum = createString("phoneNum");

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

