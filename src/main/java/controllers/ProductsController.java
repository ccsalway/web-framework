package controllers;

import domain.entity.Product;
import domain.repository.ProductRepository;
import domain.repository.paging.PageRequest;
import domain.repository.result.Page;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import static core.Helpers.firstNotEmpty;

@WebServlet(name = "Servlet", urlPatterns = {"/products/*"}, loadOnStartup = 1)
public class ProductsController extends BaseController {

    private ProductRepository productRepository = new ProductRepository();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            long pageNo = Long.parseLong(firstNotEmpty(request.getParameter("page"), "1"));
            long pageSize = Long.parseLong(firstNotEmpty(request.getParameter("size"), "5"));
            Page<Product> result = productRepository.findAll(new PageRequest(pageNo, pageSize));
            request.setAttribute("result", result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher(config.get("web.view.prefix") + "products.jsp").forward(request, response);
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
