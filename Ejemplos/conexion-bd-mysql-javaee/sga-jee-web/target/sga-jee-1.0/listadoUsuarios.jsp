<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado</title>
    </head>
    <body>
        <h1>Lista Usuario</h1>
        <table>
            <thead>
                <td>#</td>
                <td>#Persona</td>
                <td>Username</td>
                <td>Password</td>
            </thead>
            <tbody>
                <c:forEach items="${usuarios}" var="usuario">
                    <tr>
                        <td>${usuario.idUsuario}</td>
                        <td>${usuario.persona.idPersona}</td>
                        <td>${usuario.username}</td>
                        <td>${usuario.password}</td>
                    </tr>
                </c:forEach>
            </tbody>
            <tfoot>
               <hr />
               <td><a href="index.html">Volver al inicio</a></td>
            </tfoot>
        </table>
    </body>
</html>
