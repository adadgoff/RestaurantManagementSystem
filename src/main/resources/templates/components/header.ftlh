<!DOCTYPE html>
<html>
<head>
    <title>RMS</title>
</head>

<body>
    <h1>Restaurant Management System (RMS)</h1>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="Выйти"/>
    </form>
    <hr>
