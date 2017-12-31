package domain.repository.core;

import domain.repository.paging.PageRequest;
import domain.repository.result.Page;
import library.database.DataConnection;
import library.database.DataSource;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class PagingRepository<T> extends BaseRepository<T> {


    public PagingRepository(Class clazz) {
        super(clazz);
    }

    //------------------------------

    protected Page<T> getRows(DataSource dataSource, String sql, List<Object> vals, PageRequest pageRequest) throws SQLException {
        Page<T> result = new Page<>();
        try (DataConnection conn = dataSource.getConnection()) {
            sql += String.format(" LIMIT %d, %d", pageRequest.startRow(), pageRequest.pageSize());
            List<Map<String, Object>> rows = conn.nativeSelect(sql, vals);
            result.setRows(objectMapper.map(rows));
            result.setTotalRows((long)conn.scalar("SELECT FOUND_ROWS()"));
            result.setCurrentPage(pageRequest.currentPage());
            result.setPageSize(pageRequest.pageSize());
        }
        return result;
    }


}
