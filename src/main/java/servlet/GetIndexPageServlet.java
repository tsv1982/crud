package servlet;

import model.Student;
import util.StudentController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GetIndexPageServlet extends HttpServlet {
    private Map<Integer, Student> studentMap;

    @Override
    @SuppressWarnings("unchecked")
    public void init() {
        final Object studentMap1 = getServletContext().getAttribute("studentMap");
        if (!(studentMap1 instanceof ConcurrentHashMap)) {
            throw new IllegalStateException();
        } else {
            studentMap = (ConcurrentHashMap<Integer, Student>) studentMap1;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            req.setAttribute("studentMap", studentMap.values());
            req.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            int search = Integer.parseInt(req.getParameter("search"));
            if (search > 0) {
                studentMap = StudentController.search(search);
            }
        } catch (NumberFormatException e) {
            studentMap = StudentController.getAll();
        }
        try {
            resp.sendRedirect(req.getContextPath() + "/");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}