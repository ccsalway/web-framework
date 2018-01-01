package system.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import system.Settings;
import system.annotations.Entity;
import system.common.Loaders;
import system.database.DataConnection;
import system.database.DataMapper;
import system.database.DataSource;
import system.repository.result.Result;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public abstract class BaseRepository<T> {

    private final Logger logger = LogManager.getLogger(this.getClass().getName());

    protected Settings settings = Settings.getInstance();

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

    protected Result<T> findAll(DataSource dataSource, String sortBy) throws SQLException {
        try (DataConnection conn = dataSource.getConnection()) {
            String sql = String.format(
                    "SELECT * FROM %s ORDER BY %s",
                    entity.table(),
                    sortBy
            );
            List<Map<String, Object>> rows = conn.nativeSelect(sql);
            List<T> mappedObject = dataMapper.map(rows);
            long totalRows = (long)rows.size();

            return new Result<>(mappedObject, totalRows);
        }
    }

    protected long save(DataSource dataSource, Map<String, Object> vals) throws SQLException {
        try (DataConnection conn = dataSource.getConnection()) {
            return conn.insertRow(entity.table(), vals);
        }
    }

}
