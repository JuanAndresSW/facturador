<!-- Elemento modal completo  clases, etc. En la documentacion-->
<div class="modal fade" id="agregarClienteModal"tabindex="-1" aria-labelledby="agregarClienteModalLabel" aria-hidden="true">
    <div class="modal-dialog  modal-lg">
        <div class="modal-content">
            <div class="modal-header bg-info text-white">
                <h5 class="modal-title" id="agregarClienteModalLabel">Agregar Cliente</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <!-- Formulario se envia con el atributo de insert al servlet con el metodo de envio POST -->
            <!-- Aclaro &{..} esta sintaxis es Expression Lenguage -->
            <form class="was-validated" action="${pageContext.request.contextPath}/ServletController?accion=insert" method="post">
                <!-- la clase was-validated es la validacion default del navegador -->
                <div class="modal-body">
                    <div class="form-group">
                        <label for="nombre" class="form-label">Nombre</label>
                        <input type="text" class="form-control" id="nombre" name="nombre" aria-describedby="nombreFeedback" required> 
                        <!-- Div en caso que el dato sea invalido -->
                        <div class="invalid-feedback">
                            Ingresa tu nombre
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="apellido" class="form-label">Apellido</label>
                        <input type="text" class="form-control" id="apellido" name="apellido" aria-describedby="apellidoFeedback" required> 
                        <div class="invalid-feedback">
                            Ingresa tu apellido
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email" class="form-label">Email</label>
                        <input type="email" class="form-control" id="email" name="email" aria-describedby="emailFeedback" required> 
                        <div class="invalid-feedback">
                            Ingresa tu email
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="telefono" class="form-label">Telefono</label>
                        <input type="tell" class="form-control" id="telefono" name="telefono" aria-describedby="telefonoFeedback" required>
                        <div class="invalid-feedback">
                            Ingresa tu telefono
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="saldo" class="form-label">Saldo</label>
                        <input type="number" class="form-control" id="saldo" name="saldo" aria-describedby="saldoFeedback" required> 
                        <div class="invalid-feedback">
                            Ingresa tu saldo
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary">Guardar</button>
                    <input class="btn btn-secondary" type="reset" value="Cancel">
                </div>
            </form>
        </div>
    </div>
</div>