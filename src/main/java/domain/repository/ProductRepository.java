package domain.repository;

import domain.entity.Product;
import system.repository.PagingRepository;
import system.repository.paging.PageRequest;
import system.repository.result.Page;

import java.sql.SQLException;
import java.util.Map;

public class ProductRepository extends PagingRepository<Product> {


    public ProductRepository() {
        super(Product.class);
    }

    //------------------------------

    public Page<Product> findAll(PageRequest pageRequest) throws SQLException {
        return findAll(dataSource1, pageRequest);
    }

    public long save(Map<String, Object> vals) throws SQLException {
        return save(dataSource1, vals);
    }

}
