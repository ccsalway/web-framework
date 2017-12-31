package controllers;

import core.Loaders;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.util.Properties;

public abstract class BaseController extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass().getName());

    //------------------------------

    Properties config;

    //------------------------------

    BaseController() {
        try {
            config = Loaders.properties("application.properties");
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e);
        }
    }

}
