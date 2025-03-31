package com.springboot.djit.data.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;
import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;

import com.djit.entity.application.Application;
import com.querydsl.core.types.Path;


/**
 * QApplication is a Querydsl query type for Application
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QApplication extends EntityPathBase<Application> {

    private static final long serialVersionUID = 516856654L;

    public static final QApplication application = new QApplication("application");

    public final StringPath address = createString("address");

    public final StringPath birth = createString("birth");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final StringPath educationGoal = createString("educationGoal");

    public final StringPath educationLevel = createString("educationLevel");

    public final StringPath email = createString("email");

    public final StringPath employmentInsurance = createString("employmentInsurance");

    public final StringPath married = createString("married");

    public final StringPath motivation = createString("motivation");

    public final StringPath name = createString("name");

    public final NumberPath<Long> number = createNumber("number", Long.class);

    public final StringPath phoneNumber = createString("phoneNumber");

    public final StringPath sex = createString("sex");

    public final StringPath subjectName = createString("subjectName");

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public QApplication(String variable) {
        super(Application.class, forVariable(variable));
    }

    public QApplication(Path<? extends Application> path) {
        super(path.getType(), path.getMetadata());
    }

    public QApplication(PathMetadata metadata) {
        super(Application.class, metadata);
    }

}

