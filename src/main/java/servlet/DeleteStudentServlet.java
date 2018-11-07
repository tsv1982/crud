package servlet;

import model.Student;
import util.StudentController;
import util.Utils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DeleteStudentServlet extends HttpServlet {
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            req.setCharacterEncoding("UTF-8");
            if (Utils.idIsNumber(req)) {
                studentMap.remove(Integer.valueOf(req.getParameter("id")));
                StudentController.delete(Integer.valueOf(req.getParameter("id")));
            }
            resp.sendRedirect(req.getContextPath() + "/");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}