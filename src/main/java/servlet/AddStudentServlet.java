package servlet;

import model.Student;
import util.StudentController;
import util.Utils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class AddStudentServlet extends HttpServlet {
    private Map<Integer, Student> studentMap;
    private AtomicInteger id;

    @Override
    @SuppressWarnings("unchecked")
    public void init() {
        final Object studentMap1 = getServletContext().getAttribute("studentMap");
        if (!(studentMap1 instanceof ConcurrentHashMap)) {
            throw new IllegalStateException();
        } else {
            studentMap = (ConcurrentHashMap<Integer, Student>) studentMap1;
        }
        id = new AtomicInteger(studentMap.size() + 1);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            req.setCharacterEncoding("UTF-8");
            if (Utils.requestIsValid(req)) {
                final String name = req.getParameter("name");
                final int age = Integer.parseInt(req.getParameter("age"));
                final int course = Integer.parseInt(req.getParameter("course"));
                final Student student = new Student();
                final int id = this.id.getAndIncrement();
                student.setId(id);
                student.setAge(age);
                student.setName(name);
                student.setCourse(course);
                studentMap.put(id, student);
                StudentController.addStudent(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            resp.sendRedirect(req.getContextPath() + "/");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}