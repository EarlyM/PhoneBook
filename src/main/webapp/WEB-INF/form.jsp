<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>
    <spring:url value="/create" var="add"/>
    <spring:url value="/edit" var="edit"/>

    <div class="container-fluid" style="margin-top:40px">
        <form:form modelAttribute="contact" action="${contact.id == 0 ? add : edit}" method="post" class="form-horizontal" role="form">
            <form:input path="id" type="hidden"/>
            <div class="form-group">
                <label class="control-label col-sm-4">Фамилия:</label>
                <form:errors cssClass="control-label" path="surname"/>
                <div class="col-xs-3">
                    <form:input path="surname" class="form-control" placeholder="Обязательное поле"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-4">Имя:</label>
                <form:errors cssClass="control-label" path="name"/>
                <div class="col-xs-3">
                    <form:input path="name" class="form-control" placeholder="Обязательное поле"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-4">Фамилия:</label>
                <form:errors cssClass="control-label" path="patronymic"/>
                <div class="col-xs-3">
                    <form:input path="patronymic" class="form-control" placeholder="Обязательное поле"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-4">Мобильный:</label>
                <form:errors cssClass="control-label" path="mobilePhone"/>
                <div class="col-xs-3">
                    <form:input path="mobilePhone" class="form-control" placeholder="Обязательное поле"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-4">Домашний:</label>
                <div class="col-xs-3">
                    <form:input path="homePhone" class="form-control"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-4">Адрес:</label>
                <div class="col-xs-3">
                    <form:input path="address" autocomplete="off" class="form-control"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-4">e-mail:</label>
                <form:errors cssClass="control-label" path="eMail"/>
                <div class="col-xs-3">
                    <form:input path="eMail" class="form-control"/>
                </div>
            </div>

            <div class="form-group" style="margin-left: 420px">
                <div class="col-sm-offset-2 col-sm-12">
                    <a href="/phonebook" class="btn btn-danger">Отмена</a>
                    <button type="submit" class="btn btn-primary">Отправить</button>
                </div>
            </div>

        </form:form>
    </div>
</body>
</html>
