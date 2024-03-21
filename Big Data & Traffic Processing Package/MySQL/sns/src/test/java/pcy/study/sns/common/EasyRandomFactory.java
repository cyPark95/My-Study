package pcy.study.sns.common;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import pcy.study.sns.domain.common.entity.BaseEntity;

import java.lang.reflect.Field;
import java.util.function.Predicate;

import static org.jeasy.random.FieldPredicates.*;

/**
 * EasyRandom
 * 자바 빈을 만들어주는 라이브러리
 */
public abstract class EasyRandomFactory {

    private static final Predicate<Field> ID_PREDICATE = named("id")
            .and(ofType(Long.class))
            .and(inClass(BaseEntity.class));

    protected static final EasyRandomParameters EASY_RANDOM_BASE_PARAMETERS = new EasyRandomParameters()
            .excludeField(ID_PREDICATE);

    protected static EasyRandom get() {
        return get(EASY_RANDOM_BASE_PARAMETERS);
    }

    protected static EasyRandom get(EasyRandomParameters params) {
        return new EasyRandom(params);
    }
}
