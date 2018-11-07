package servlet;

import model.Student;
import util.StudentController;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Map;

@WebListener
public class ContextListener implements ServletContextListener {
    private Map<Integer, Student> studentMap;

    @Override
    @SuppressWarnings("unchecked")
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        final ServletContext servletContext =
                servletContextEvent.getServletContext();
        studentMap = StudentController.getAll();
        servletContext.setAttribute("studentMap", studentMap);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        studentMap = null;
    }
}
