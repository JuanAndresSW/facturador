<!DOCTYPE html>
<html>
    <!-- Head -->
    <jsp:include page="../WEB-INF/pages/commons/head.jsp"/>
    <body style="background-color: #dddddd;">
        
        <!-- jsp:include es una etiqueta que permite combinar archivos JSP y HTML -->
        <!-- La inclusion puede ser dinamica o estatica -->
        <!-- solo necesitas escribir la etiqueta y especificar la ruta -->
        <!-- el atributo de page hace que la inclusion sea "dinamica" -->
        
        <!-- Cabezero -->
        <jsp:include page="../WEB-INF/pages/commons/cabezero.jsp"/>

        <!-- Opcion agregar / Navegacion -->
        <jsp:include page="../WEB-INF/pages/commons/botonesNavegacion.jsp"/>

        <!-- Formulario -->
        <jsp:include page="../WEB-INF/pages/cliente/listaCliente.jsp" />

        <!-- Cabezero o Pie de Pagina -->
        <jsp:include page="../WEB-INF/pages/commons/pieDePagina.jsp"/>

        <!-- JavaScript de Bootstrap -->
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
    </body>
</html>
