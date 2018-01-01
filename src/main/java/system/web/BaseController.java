package system.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.xml.internal.ws.api.config.management.policy.ManagementAssertion;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import system.Settings;
import system.common.Loaders;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

public abstract class BaseController extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass().getName());

    protected Settings settings = Settings.getInstance();

    //------------------------------

    protected void dispatchView(HttpServletRequest request, HttpServletResponse response, String view) throws ServletException, IOException {
        request.getRequestDispatcher(settings.get("web.view.prefix") + view).forward(request, response);
    }

    protected void dispatchJson(HttpServletRequest request, HttpServletResponse response, Object forJson) throws IOException {
        String json = new ObjectMapper().writeValueAsString(forJson);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

}
