<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container-fluid" style="margin-top:150px; margin-left: 100px">
    <form:form modelAttribute="user" action="/registration" method="post" class="form-horizontal" role="form">
        <div class="form-group">
            <label class="control-label col-sm-12" style="margin-left: 325px"><form:errors cssClass="control-label col-sm-4" path="username"/></label>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-4">Логин:</label>
            <div class="col-xs-3">
                <form:input path="username" class="form-control"/>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-4">Пароль:</label>
            <form:errors cssClass="control-label" path="password"/>
            <div class="col-xs-3">
                <form:input path="password" class="form-control"/>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-4">ФИО:</label>
            <form:errors cssClass="control-label" path="fio"/>
            <div class="col-xs-3">
                <form:input path="fio" class="form-control"/>
            </div>
        </div>

        <div class="form-group" style="margin-left: 306px">
            <div class="col-sm-offset-2 col-sm-12">
                <a href="/" class="btn btn-danger">Отмена</a>
                <button type="submit" class="btn btn-primary">Заегистрироваться</button>
            </div>
        </div>

    </form:form>
</div>
</body>
</html>
