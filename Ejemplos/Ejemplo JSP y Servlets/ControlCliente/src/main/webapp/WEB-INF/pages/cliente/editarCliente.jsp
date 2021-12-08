<!DOCTYPE html>
<html>
    <!-- Head -->
    <jsp:include page="/WEB-INF/pages/commons/head.jsp"/>
    <body style="background-color: #dddddd;">

        <!-- jsp:include es una etiqueta que permite combinar archivos JSP y HTML -->
        <!-- La inclusion puede ser dinamica o estatica -->
        <!-- solo necesitas escribir la etiqueta y especificar la ruta -->
        <!-- el atributo de page hace que la inclusion sea "dinamica" -->

        <!-- Cabezero -->
        <jsp:include page="/WEB-INF/pages/commons/cabezero.jsp"/>

        <form action="${pageContext.request.contextPath}/ServletController?accion=update&idCliente=${cliente.idCliente}" 
              method="POST" class="was-validated" autocomplete="false">
            <!-- Botones navegacion -->
            <jsp:include page="/WEB-INF/pages/commons/botonesNavegacionEdicion.jsp"/>
            <!-- Formulario -->
            <section id="details">
                <div class="conteiner">
                    <div class="row justify-content-md-center">
                        <div class="col-md-11">
                            <div class="card text-white bg-dark mb-3">
                                <div class="card-header bg-secondary border-dark">
                                    <h4 class="card-title">Editar Cliente</h4>
                                </div>
                                <div class="card-body">
                                    <div class="form-group">
                                        <label for="nombre" class="form-label">Nombre</label>
                                        <input type="text" class="form-control bg-secondary text-white" value="${cliente.nombre}" name="nombre" required> 
                                        <!-- Div en caso que el dato sea invalido -->
                                        <div class="invalid-feedback">
                                            Ingresa tu nombre
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="apellido" class="form-label">Apellido</label>
                                        <input type="text" class="form-control bg-secondary text-white" value="${cliente.apellido}" name="apellido" required> 
                                        <div class="invalid-feedback">
                                            Ingresa tu apellido
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="email" class="form-label">Email</label>
                                        <input type="email" class="form-control bg-secondary text-white" value="${cliente.email}" name="email" required> 
                                        <div class="invalid-feedback">
                                            Ingresa tu email
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="telefono" class="form-label">Telefono</label>
                                        <input type="tell" class="form-control bg-secondary text-white" value="${cliente.telefono}" name="telefono" required>
                                        <div class="invalid-feedback">
                                            Ingresa tu telefono
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="saldo" class="form-label">Saldo</label>
                                        <input type="number" class="form-control bg-secondary text-white" value="${cliente.saldo}" id="saldo" name="saldo" required> 
                                        <div class="invalid-feedback">
                                            Ingresa tu saldo
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </form>
        <!-- Cabezero o Pie de Pagina -->
        <jsp:include page="/WEB-INF/pages/commons/pieDePagina.jsp"/>

        <!-- JavaScript de Bootstrap -->
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
    </body>
</html>

