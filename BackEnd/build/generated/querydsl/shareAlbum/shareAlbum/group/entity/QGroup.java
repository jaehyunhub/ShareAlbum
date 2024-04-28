package shareAlbum.shareAlbum.group.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;
import shareAlbum.shareAlbum.domain.group.entity.Group;
import shareAlbum.shareAlbum.domain.group.entity.Invitation;


/**
 * QGroup is a Querydsl query type for Group
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QGroup extends EntityPathBase<Group> {

    private static final long serialVersionUID = 1290929379L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGroup group = new QGroup("group1");

    public final QGroupList groupList;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<Invitation, QInvitation> invitation = this.<Invitation, QInvitation>createList("invitation", Invitation.class, QInvitation.class, PathInits.DIRECT2);

    public final shareAlbum.shareAlbum.member.entity.QMember member;

    public QGroup(String variable) {
        this(Group.class, forVariable(variable), INITS);
    }

    public QGroup(Path<? extends Group> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QGroup(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QGroup(PathMetadata metadata, PathInits inits) {
        this(Group.class, metadata, inits);
    }

    public QGroup(Class<? extends Group> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.groupList = inits.isInitialized("groupList") ? new QGroupList(forProperty("groupList")) : null;
        this.member = inits.isInitialized("member") ? new shareAlbum.shareAlbum.member.entity.QMember(forProperty("member")) : null;
    }

}

