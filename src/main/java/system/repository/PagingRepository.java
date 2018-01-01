package system.repository;

import system.database.DataConnection;
import system.database.DataSource;
import system.repository.paging.PageRequest;
import system.repository.result.Page;

import java.sql.SQLException;
import java.util.List;

public class PagingRepository<T> extends BaseRepository<T> {


    public PagingRepository(Class clazz) {
        super(clazz);
    }

    //------------------------------

    protected Page<T> findAll(DataSource dataSource, PageRequest pageRequest) throws SQLException {
        try (DataConnection conn = dataSource.getConnection()) {
            String sql = String.format(
                    "SELECT SQL_CALC_FOUND_ROWS * FROM %s ORDER BY %s LIMIT %d, %d",
                    entity.table(),
                    pageRequest.getSortBy(),
                    pageRequest.getStartRow(),
                    pageRequest.getPageSize()
            );
            List<T> mappedObject = dataMapper.map(conn.nativeSelect(sql));
            long totalRows = (long)conn.scalar("SELECT FOUND_ROWS()");

            return new Page<>(mappedObject, totalRows, pageRequest.getPageNumber(), pageRequest.getPageSize());
        }
    }

}
