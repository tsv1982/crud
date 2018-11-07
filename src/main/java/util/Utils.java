package util;

import model.Student;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public final class Utils {
    private static String regex = "[+]?\\d+";

    private Utils() {
    }

    public static boolean idIsNumber(HttpServletRequest request) {
        final String id = request.getParameter("id");
        return id != null && (id.length() > 0) && id.matches(regex);
    }

    public static boolean requestIsValid(HttpServletRequest request) {
        final String name = request.getParameter("name");
        final String age = request.getParameter("age");
        return name != null && name.length() > 0 &&
                age != null && age.length() > 0 &&
                age.matches(regex);
    }

    public static boolean idIsInvalid(final String id, Map<Integer, Student> repo) {
        return !(id != null &&
                id.matches(regex) &&
                repo.get(Integer.parseInt(id)) != null);
    }
}