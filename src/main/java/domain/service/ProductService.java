package domain.service;

import domain.entity.Product;
import domain.repository.ProductRepository;
import system.repository.paging.PageRequest;
import system.repository.result.Page;

import java.sql.SQLException;
import java.util.Map;

public class ProductService {

    private ProductRepository productRepository = new ProductRepository();

    //------------------------------

    public Page<Product> getAllProducts(PageRequest pageRequest) throws SQLException {
        return productRepository.findAll(pageRequest);
    }

    public long saveProduct(Map<String, Object> vals) throws SQLException {
        return productRepository.save(vals);
    }


}
