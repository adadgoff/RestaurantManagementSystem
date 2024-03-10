<!DOCTYPE html>
<#-- TODO: пользователь test@test.test и admin@admin.admin уже есть -->
<html>
<head>
    <title>Restaurant Management System (RMS)</title>
</head>

<body>
<h1>Restaurant Management System (RMS)</h1>
<hr>

<h4>Регистрация</h4>
<hr>

<form action="/registration" method="post">
    Эл. почта: <input type="text" name="email"/><br>
    Имя пользователя: <input type="text" name="name"/><br>
    Пароль: <input type="password" name="password"><br>

    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Зарегистрироваться"/>
</form>

</body>
</html>