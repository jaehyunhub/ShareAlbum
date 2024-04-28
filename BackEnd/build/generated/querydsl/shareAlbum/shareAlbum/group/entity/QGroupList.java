package shareAlbum.shareAlbum.group.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import shareAlbum.shareAlbum.domain.group.entity.GroupCategory;
import shareAlbum.shareAlbum.domain.group.entity.GroupList;


/**
 * QGroupList is a Querydsl query type for GroupList
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QGroupList extends EntityPathBase<GroupList> {

    private static final long serialVersionUID = -923598815L;

    public static final QGroupList groupList = new QGroupList("groupList");

    public final EnumPath<GroupCategory> groupCategory = createEnum("groupCategory", GroupCategory.class);

    public final StringPath groupCreator = createString("groupCreator");

    public final StringPath groupTitle = createString("groupTitle");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QGroupList(String variable) {
        super(GroupList.class, forVariable(variable));
    }

    public QGroupList(Path<? extends GroupList> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGroupList(PathMetadata metadata) {
        super(GroupList.class, metadata);
    }

}

