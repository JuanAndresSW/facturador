
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado</title>
    </head>
    <body>
        <h1>Lista de Personas:</h1>
        <table>
            <thead>
                <td>#</td>
                <td>Nombre</td>
                <td>Apellido</td>
                <td>Email</td>
            </thead>
            <tbody>
                <c:forEach items="${personas}" var="persona">
                    <tr>
                        <td>${persona.idPersona}</td>
                        <td>${persona.nombre}</td>
                        <td>${persona.apellido}</td>
                        <td>${persona.email}</td>
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
