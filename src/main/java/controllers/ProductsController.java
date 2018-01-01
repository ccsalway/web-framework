package controllers;

import domain.entity.Product;
import domain.repository.ProductRepository;
import system.repository.paging.PageRequest;
import system.repository.result.Page;
import system.web.BaseController;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static system.core.Helpers.firstNotEmpty;

@WebServlet(name = "Products", urlPatterns = {"/products/*"}, loadOnStartup = 1)
public class ProductsController extends BaseController {

    private ProductRepository productRepository = new ProductRepository();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            long pageNo = Long.parseLong(firstNotEmpty(request.getParameter("page"), "1"));
            long pageSize = Long.parseLong(firstNotEmpty(request.getParameter("size"), "5"));

            //Result<Product> result = productRepository.findAll();
            Page<Product> result = productRepository.findAll(new PageRequest(pageNo, pageSize));

            request.setAttribute("result", result);

            dispatchJson(request, response, result);
            //dispatchView(request, response, "products.jsp");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)  {
    }

    @Override
    public void doPut(HttpServletRequest request, HttpServletResponse response) {
    }

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) {
    }

}
