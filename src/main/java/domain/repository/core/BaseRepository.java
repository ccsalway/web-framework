package domain.repository.core;

import core.Loaders;
import core.annotations.Entity;
import domain.repository.result.Result;
import library.database.DataConnection;
import library.database.DataSource;
import library.database.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public abstract class BaseRepository<T> {

    private final Logger logger = LogManager.getLogger(this.getClass().getName());

    //------------------------------

    protected Entity entity;
    protected DataSource dataSource1;
    ObjectMapper<T> objectMapper;

    //------------------------------

    BaseRepository(Class clazz) {
        try {
            entity = (Entity) clazz.getAnnotation(Entity.class);
            dataSource1 = new DataSource(Loaders.properties("application.properties"));
            objectMapper = new ObjectMapper<>(clazz);
        } catch (IOException e) {
            logger.error(e);
        }
    }

    //------------------------------

    protected Result<T> getRows(DataSource dataSource, String sql, List<Object> vals) throws SQLException {
        Result<T> result = new Result<>();
        try (DataConnection conn = dataSource.getConnection()) {
            List<Map<String, Object>> rows = conn.nativeSelect(sql, vals);
            result.setRows(objectMapper.map(rows));
            result.setTotalRows((long)rows.size());
        }
        return result;
    }

}
