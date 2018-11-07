package servlet;

import model.Student;
import util.StudentController;
import util.Utils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UpdateStudentServlet extends HttpServlet {
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
            int id = Integer.parseInt(req.getParameter("id"));
            final String name = req.getParameter("name");
            final int age = Integer.parseInt(req.getParameter("age"));
            final int course = Integer.parseInt(req.getParameter("course"));
            final Student student = studentMap.get(id);
            student.setName(name);
            student.setAge(age);
            student.setCourse(course);
            StudentController.update(student.getName(), student.getAge(), student.getCourse(), student.getId());
            resp.sendRedirect(req.getContextPath() + "/");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            final String id = req.getParameter("id");
            if (Utils.idIsInvalid(id, studentMap)) {
                resp.sendRedirect(req.getContextPath() + "/");
                return;
            }
            final Student student = studentMap.get(Integer.parseInt(id));
            req.setAttribute("student", student);
            req.getRequestDispatcher("/WEB-INF/view/update.jsp")
                    .forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}