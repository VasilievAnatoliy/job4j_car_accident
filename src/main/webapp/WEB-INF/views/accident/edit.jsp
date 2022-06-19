<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
          crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
            crossorigin="anonymous"></script>

    <title>Редактировать инцидент</title>
</head>
<body>
<div class="container">
    <div class="row pt -3">
        <div class="card" style="width: 100%">
            <div class="card-header">
                Редактировать инцидент
            </div>
            <div class="card-body">
                <form action="<c:url value='/update'/>" method='POST'>
                    <input type="hidden" name="id" value="${accident.id}">
                    <input type="hidden" name="typeId" value="${accident.type.id}">
                    <div class="form-group">
                        <label>Название:
                            <input type='text' class="form-control" size="30" name='name' value="${accident.name}">
                        </label>
                    </div>
                    <div class="form-group">
                        <label>Описание:</label>
                        <input type='text' class="form-control" name='text' value="${accident.text}">
                    </div>
                    <div class="form-group">
                        <label>Адрес:</label>
                        <input type='text' class="form-control" name='address' value="${accident.address}">
                    </div>
                    <button type="submit" class="btn btn-primary">Сохранить</button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
