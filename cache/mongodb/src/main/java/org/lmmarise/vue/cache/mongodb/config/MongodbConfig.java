package org.lmmarise.vue.cache.mongodb.config;

import org.lmmarise.vue.cache.mongodb.annotation.AutoIncKey;
import org.lmmarise.vue.cache.mongodb.entity.SeqInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/10/13 12:56 下午
 */
@Configuration
public class MongodbConfig {

    /**
     * 提供对自增主键的支持
     */
    @Component
    public static class SaveEventListener extends AbstractMongoEventListener<Object> {
        private static final Logger logger = LoggerFactory.getLogger(SaveEventListener.class);

        @Resource
        private MongoTemplate mongoTemplate;

        @Override
        public void onBeforeConvert(BeforeConvertEvent<Object> event) {
            Object source = event.getSource();
            Class<?> clazz = source.getClass();
            ReflectionUtils.doWithFields(clazz, field -> {
                ReflectionUtils.makeAccessible(field);
                if (field.isAnnotationPresent(AutoIncKey.class)) {
                    if (Integer.class.isAssignableFrom(field.getType()) || int.class.isAssignableFrom(field.getType())) {
                        field.set(source, getNextIntId(source.getClass().getSimpleName()));
                    } else if (Long.class.isAssignableFrom(field.getType()) || long.class.isAssignableFrom(field.getType())) {
                        field.set(source, getNextLongId(source.getClass().getSimpleName()));
                    } else {
                        throw new RuntimeException("not support " + field.getType() + "type!");
                    }
                    logger.debug(source.toString());
                }
            });
        }

        private Integer getNextIntId(String collName) {
            Query query = new Query(Criteria.where("collName").is(collName));
            Update update = new Update();
            update.inc("seqIdInteger", 1);
            FindAndModifyOptions options = new FindAndModifyOptions();
            options.upsert(true);
            options.returnNew(true);
            SeqInfo inc = mongoTemplate.findAndModify(query, update, options, SeqInfo.class);
            Integer id = Objects.requireNonNull(inc).getSeqIdInteger();
            logger.debug("generate id {}", id);
            return id;
        }

        private Long getNextLongId(String collName) {
            Query query = new Query(Criteria.where("collName").is(collName));
            Update update = new Update();
            update.inc("seqIdLong", 1);
            FindAndModifyOptions options = new FindAndModifyOptions();
            options.upsert(true);
            options.returnNew(true);
            SeqInfo inc = mongoTemplate.findAndModify(query, update, options, SeqInfo.class);
            Long id = Objects.requireNonNull(inc).getSeqIdLong();
            logger.debug("generate id {}", id);
            return id;
        }
    }
}
