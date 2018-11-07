<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Students</title>
</head>
<body>
<h2>добавить нового студента</h2><br/>
<form method="post" action="<c:url value='/add_student'/>">
    <label><input type="text" name="name"></label>Имя<br>
    <label><input type="number" name="age"></label>Возраст<br>
    <label><input type="number" name="course"></label>Курс<br>
    <input type="submit" value="update" name="Ok"><br>
</form>
<form method="post" action="<c:url value='/'/>">
    <label><input type="number" name="search"></label>Курс<br>
    <input type="submit" value="поиск по курсу" name="Ok"><br>
</form>
<form method="post" action="<c:url value='/'/>">
    <input type="submit" value="все пользователи" name="search"><br>
</form>
<table border="1" style="border: black" cellpadding="5">
    <tr>
        <td>id</td>
        <td>Имя</td>
        <td>Возраст</td>
        <td>Курс</td>
        <td>Кнопки</td>
    </tr>
    <c:forEach var="student" items="${requestScope.studentMap}">
        <tr>
            <td><c:out value="${student.id}"/></td>
            <td><c:out value="${student.name}"/></td>
            <td><c:out value="${student.age}"/></td>
            <td><c:out value="${student.course}"/></td>
            <td>
                <form method="post" action="<c:url value='/delete'/>">
                    <label><input type="number" hidden name="id" value="${student.id}"/></label>
                    <input type="submit" name="delete" value="Удалить"/>
                </form>
                <form method="get" action="<c:url value='/update'/>">
                    <label><input type="number" hidden name="id" value="${student.id}"/></label>
                    <input type="submit" value="Редактированть"/>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>