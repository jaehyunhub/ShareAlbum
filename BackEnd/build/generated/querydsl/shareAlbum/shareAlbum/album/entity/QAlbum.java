package shareAlbum.shareAlbum.album.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;
import shareAlbum.shareAlbum.domain.album.entity.Album;
import shareAlbum.shareAlbum.domain.album.entity.AlbumStatus;
import shareAlbum.shareAlbum.domain.album.entity.Comment;
import shareAlbum.shareAlbum.domain.album.entity.Like;


/**
 * QAlbum is a Querydsl query type for Album
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAlbum extends EntityPathBase<Album> {

    private static final long serialVersionUID = -1288541469L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAlbum album = new QAlbum("album");

    public final EnumPath<AlbumStatus> albumStatus = createEnum("albumStatus", AlbumStatus.class);

    public final ListPath<Comment, QComment> comment = this.<Comment, QComment>createList("comment", Comment.class, QComment.class, PathInits.DIRECT2);

    public final StringPath content = createString("content");

    public final shareAlbum.shareAlbum.group.entity.QGroup group;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<Like, QLike> like = this.<Like, QLike>createList("like", Like.class, QLike.class, PathInits.DIRECT2);

    public final ArrayPath<byte[], Byte> pictures = createArray("pictures", byte[].class);

    public QAlbum(String variable) {
        this(Album.class, forVariable(variable), INITS);
    }

    public QAlbum(Path<? extends Album> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAlbum(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAlbum(PathMetadata metadata, PathInits inits) {
        this(Album.class, metadata, inits);
    }

    public QAlbum(Class<? extends Album> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.group = inits.isInitialized("group") ? new shareAlbum.shareAlbum.group.entity.QGroup(forProperty("group"), inits.get("group")) : null;
    }

}

