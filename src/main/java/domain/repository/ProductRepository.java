package domain.repository;

import domain.entity.Product;
import domain.repository.core.PagingRepository;
import domain.repository.paging.PageRequest;
import domain.repository.result.Page;
import domain.repository.result.Result;

import java.sql.SQLException;


public class ProductRepository extends PagingRepository<Product> {

    public ProductRepository() {
        super(Product.class);
    }

    public Page<Product> findAll(PageRequest pageRequest) throws SQLException {
        String sql = String.format("SELECT SQL_CALC_FOUND_ROWS * FROM %s ORDER BY id", entity.table());
        return getRows(dataSource1, sql, null, pageRequest);
    }

    public Result<Product> findAll() throws SQLException {
        String sql = String.format("SELECT * FROM %s ORDER BY name", entity.table());
        return getRows(dataSource1, sql, null);
    }

}
