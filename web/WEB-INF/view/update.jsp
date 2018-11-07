<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Update Student</title>
</head>
<body>
<div>Имя: <c:out value="${requestScope.student.name}"/></div>
<div>Возраст: <c:out value="${requestScope.student.age}"/></div>
<div>Курс: <c:out value="${requestScope.student.course}"/></div>
<br/>
<form method="post" action="<c:url value='/update'/>">
    <label><input type="text" name="name"/></label>Изменить имя<br>
    <label><input type="text" name="age"/></label>Изменить возраст<br>
    <label><input type="text" name="course"/></label>Изменить курс<br>
    <label><input type="number" hidden name="id" value="${requestScope.student.id}"/></label>
    <input type="submit" value="change" name="Ok"><br>
</form>
</body>
</html>