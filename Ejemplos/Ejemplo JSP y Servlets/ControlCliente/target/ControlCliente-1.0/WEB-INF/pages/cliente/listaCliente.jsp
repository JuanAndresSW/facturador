<!-- Directiva taglig para utilizar librerias de JSTL(Java ServerPage standard tag library) -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="es_AR" />
<!-- especifica zona local Argentina -->
<section id="cliente">
    <div class="container">
        <div class="row">
            <div class="col-md-9">
                <div class="card text-white bg-dark mb-3">
                    <div class="card-header ">
                        <h4>Listado Cliente</h4>
                    </div>
                    <table class="table table-dark table-striped">
                        <thead class="table-dark">
                            <tr>
                                <th>#</th>
                                <th>Nombre Completo</th>
                                <th>Saldo</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <!-- forEch con JSTL-->
                           <!-- Expresion Language para simplificar llamadas a JavaBean -->
                            <c:forEach var="cliente" items="${clientes}" varStatus="status">
                                <tr>
                                    <td>${status.count}</td>
                                    <td>${cliente.nombre} ${cliente.apellido}</td>
                                    <td><fmt:formatNumber value="${cliente.saldo}" type="currency"/></td>
                                    <td><a href="${pageContext.request.contextPath}/ServletController?accion=updateCliente&idCliente=${cliente.idCliente}" class="btn btn-secondary">
                                            <i class="fas fa-caret-right"></i> Editar  
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <!-- Inicio para las totales -->
            <div class="col col-md-3">
                <div class="card text-center bg-warning mb-4" style="width: 23rem;">
                    <div class="card-body">
                        <h3>Saldo Total</h3>
                        <h4 class="display-4">
                            <fmt:formatNumber value="${saldoTotal}" type="currency"/>
                        </h4>
                    </div>
                </div>
                <div class="card text-center bg-success text-white mb-3" style="width: 23rem;">
                    <div class="card-body">
                        <h3>Total Clientes</h3>
                        <h4 class="display-4">
                            <i class="fas fa-users"></i> ${totalClientes}
                        </h4>
                    </div>

                </div>
            </div>
            <!-- FIn paa las totales -->
        </div>
    </div>
</section>

<!-- Agregar Cliente de forma Modal -->
<jsp:include page="../cliente/agregarCliente.jsp" />