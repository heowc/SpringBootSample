package com.tistory.heowc.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSchool is a Querydsl query type for School
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSchool extends EntityPathBase<Grade> {

    private static final long serialVersionUID = 1945973043L;

    public static final QSchool school = new QSchool("school");

    public final NumberPath<Integer> grade = createNumber("grade", Integer.class);

    public final StringPath name = createString("name");

    public QSchool(String variable) {
        super(Grade.class, forVariable(variable));
    }

    public QSchool(Path<? extends Grade> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSchool(PathMetadata metadata) {
        super(Grade.class, metadata);
    }

}

