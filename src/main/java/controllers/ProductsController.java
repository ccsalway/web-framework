package controllers;

import domain.repository.ProductRepository;
import domain.entity.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "Servlet", urlPatterns = {"/products"}, loadOnStartup = 1)
public class ProductsController extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass().getName());

    private ProductRepository productRepository = new ProductRepository();


    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();

        try {
            List<Product> products = productRepository.findAll();

            for (Product product : products) {
                out.println(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            out.write(e.getMessage());
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
    }

    @Override
    public void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
    }


    @Override
    public void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
    }

}
