<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container" style="margin-top: 10px">
    <a href="/logout" class="btn btn-primary col-sm-offset-10 col-sm-1" style="float: right; margin-right: 80px">Выйти</a>
    <form role="form" action="/phonebook" method="post" style="margin-top: 50px">
        <div class="form-group">
            <div class="col-sm-8">
                <input class="form-control" id="ex3" type="text" name="search">
            </div>
            <div class="col-sm-2">
                <select class="form-control col-sm-4" id="sel1" name="field">
                    <option value="surname">Фамилия</option>
                    <option value="name">Имя</option>
                    <option value="mobilePhone">Телефон</option>
                </select>
            </div>
            <div class="col-sm-2">
                <input type="submit" class="btn btn-primary">
            </div>
        </div>
    </form>
    <div style="margin-top: 20px; padding-bottom: 20px; float: right; margin-right: 80px">
        <a href="/" class="btn btn-warning" style="margin-right: 35px">Сбросить поиск</a>
        <a href="/create" class="btn btn-warning">Добавить</a>
    </div>

    <table class="table table-border">
        <thead>
        <tr>
            <th>Фамилия</th>
            <th>Имя</th>
            <th>Отчество</th>
            <th>Мобильный</th>
            <th>Домашний</th>
            <th>Адрес</th>
            <th>Почта</th>
        <tbody>
            <c:forEach var="contact" items="${page.contactsList}">
                <tr>
                    <td>${contact.surname}</td>
                    <td>${contact.name}</td>
                    <td>${contact.patronymic}</td>
                    <td>${contact.mobilePhone}</td>
                    <td>${contact.homePhone}</td>
                    <td>${contact.address}</td>
                    <td>${contact.eMail}</td>
                    <td><a href="/edit?id=${contact.id}" class="btn btn-warning">Редактировать</a></td>
                    <td><a href="/delete?id=${contact.id}" class="btn btn-danger">Удалить</a></td>
                </tr>
            </c:forEach>
        </tbody>
        </tr>
        </thead>
    </table>

    <c:if test="${page.pageCount > 1}">
        <ul class="pagination">
            <c:forEach begin="${(page.currentPage - 2) <= 0 ? 1 : page.currentPage - 2}" end="${(page.currentPage + 2) >= page.pageCount ? page.pageCount : page.currentPage + 2}" step="1" varStatus="loop">
                <li><a href="/phonebook?pageNumber=${loop.index}">${loop.index}</a></li>
            </c:forEach>
        </ul>
    </c:if>

</div>
</body>
</html>
