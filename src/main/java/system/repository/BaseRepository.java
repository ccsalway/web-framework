package system.repository;

import system.core.Loaders;
import system.annotations.Entity;
import system.repository.result.Result;
import system.database.DataConnection;
import system.database.DataSource;
import system.database.DataMapper;
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
    protected DataMapper<T> dataMapper;
    protected DataSource dataSource1;

    //------------------------------

    protected BaseRepository(Class clazz) {
        try {
            entity = (Entity) clazz.getAnnotation(Entity.class);
            dataMapper = new DataMapper<>(clazz);
            dataSource1 = new DataSource(Loaders.properties("datasource1.properties"));
        } catch (IOException | SQLException e) {
            logger.error(e);
        }
    }

    //------------------------------

    protected Result<T> getRows(DataSource dataSource, String sql, List<Object> vals) throws SQLException {
        Result<T> result = new Result<>();
        try (DataConnection conn = dataSource.getConnection()) {
            List<Map<String, Object>> rows = conn.nativeSelect(sql, vals);
            result.setRows(dataMapper.map(rows));
            result.setTotalRows((long) rows.size());
        }
        return result;
    }

}
