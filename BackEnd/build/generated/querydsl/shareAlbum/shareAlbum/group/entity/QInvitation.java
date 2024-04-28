package shareAlbum.shareAlbum.group.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;
import shareAlbum.shareAlbum.domain.group.entity.Invitation;
import shareAlbum.shareAlbum.domain.group.entity.InvitationStatus;


/**
 * QInvitation is a Querydsl query type for Invitation
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QInvitation extends EntityPathBase<Invitation> {

    private static final long serialVersionUID = 1363920821L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QInvitation invitation = new QInvitation("invitation");

    public final QGroup group;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath invitation_receiver = createString("invitation_receiver");

    public final EnumPath<InvitationStatus> invitation_status = createEnum("invitation_status", InvitationStatus.class);

    public QInvitation(String variable) {
        this(Invitation.class, forVariable(variable), INITS);
    }

    public QInvitation(Path<? extends Invitation> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QInvitation(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QInvitation(PathMetadata metadata, PathInits inits) {
        this(Invitation.class, metadata, inits);
    }

    public QInvitation(Class<? extends Invitation> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.group = inits.isInitialized("group") ? new QGroup(forProperty("group"), inits.get("group")) : null;
    }

}

