package com.raccoon.blog.Board.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QPDSfile is a Querydsl query type for PDSfile
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPDSfile extends EntityPathBase<PDSfile> {

    private static final long serialVersionUID = -777815L;

    public static final QPDSfile pDSfile = new QPDSfile("pDSfile");

    public final NumberPath<Long> fno = createNumber("fno", Long.class);

    public final StringPath pdsfile = createString("pdsfile");

    public QPDSfile(String variable) {
        super(PDSfile.class, forVariable(variable));
    }

    public QPDSfile(Path<? extends PDSfile> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPDSfile(PathMetadata metadata) {
        super(PDSfile.class, metadata);
    }

}

