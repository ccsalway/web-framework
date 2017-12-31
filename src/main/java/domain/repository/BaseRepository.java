package domain.repository;

import core.Loaders;
import core.annotations.Entity;
import library.database.DataSource;
import library.database.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

abstract class BaseRepository<T> {

    private final Logger logger = LogManager.getLogger(this.getClass().getName());

    //------------------------------

    Entity entity;
    DataSource dataSource;
    ObjectMapper<T> objectMapper;

    //------------------------------

    BaseRepository(Class clazz) {
        try {
            entity = (Entity) clazz.getAnnotation(Entity.class);
            dataSource = new DataSource(Loaders.properties("application.properties"));
            objectMapper = new ObjectMapper<>(clazz);
        } catch (IOException e) {
            logger.error(e);
        }
    }

}
