<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>
    <div class="container-fluid" style="margin-top: 200px; margin-left: 150px">
        <form action="/j_spring_security_check" method="post" role="form" class="form-horizontal">
            <div class="form-group">
                <c:if test="${requestScope.get('error') ne null}">
                    <label class="control-label col-sm-6">${requestScope.get('error')}</label>
                </c:if>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-4">Логин:</label>
                <div class="col-xs-3">
                    <input type="text" name="username">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-4">Пароль:</label>
                <div class="col-xs-3">
                    <input type="password" name="password">
                </div>
            </div>
            <div class="form-group" style="margin-left: 225px">
                <div class="col-sm-offset-2 col-sm-12">
                    <a href="/registration" class="btn btn-link">Регистрация</a>
                    <button type="submit" class="btn btn-primary">Войти</button>
                </div>
            </div>
        </form>
    </div>
</body>
</html>
