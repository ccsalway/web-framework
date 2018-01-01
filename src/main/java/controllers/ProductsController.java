package controllers;

import domain.entity.Product;
import domain.service.ProductService;
import javafx.util.Pair;
import system.repository.paging.PageRequest;
import system.repository.result.Page;
import system.web.BaseController;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import static system.common.Helpers.firstNotEmpty;

@WebServlet(name = "Products", urlPatterns = {"/products/*"}, loadOnStartup = 1)
public class ProductsController extends BaseController {

    private ProductService productService = new ProductService();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String pageNumber = request.getParameter("page");
            String pageSize = firstNotEmpty(request.getParameter("size"), settings.get("web.data.pagesize"));
            Page<Product> result = productService.getAllProducts(new PageRequest(pageNumber, pageSize, "name asc"));
            request.setAttribute("result", result);
            dispatchJson(request, response, result);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(500, "Database Exception. Check Server logs.");
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            HashMap<String, Object> vals = new HashMap<>();
            vals.put("name", request.getParameter("name"));
            //TODO: check name is unique

            long id = productService.saveProduct(vals);
            dispatchJson(request, response, new Pair<>("id", id));
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(500, "Database Exception. Check Server logs.");
        }
    }

    @Override
    public void doPut(HttpServletRequest request, HttpServletResponse response) {
    }

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) {
    }

}
