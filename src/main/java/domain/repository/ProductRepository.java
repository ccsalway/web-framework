package domain.repository;

import domain.entity.Product;
import library.database.DataConnection;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class ProductRepository extends BaseRepository<Product> {

    public ProductRepository() {
        super(Product.class);
    }

    public List<Product> findAll() throws SQLException {
        try (DataConnection conn = dataSource.getConnection()) {
            List<Map<String, Object>> rows = conn.nativeSelect(String.format("SELECT * FROM %s ORDER BY name", entity.table()));
            return objectMapper.map(rows);
        }
    }

}
