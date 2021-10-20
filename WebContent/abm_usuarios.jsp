<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="HandheldFriendly" content="true">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="img/logoparking.png">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<title>ABM Usuarios</title>

<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/menu.css" rel="stylesheet">
<script src="js/jquery.min.js"></script>
<script src="js/jquery-3.3.1.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/jquery.mask.js"></script>
<script src="js/abm_usuarios.js"></script>

</head>

<body>
<jsp:include page="/servlets/GetUsuarios"/>
<jsp:include page="/servlets/GetSanatorios"/>
<jsp:include page="/servlets/GetPerfiles"/>
	<div class="container">
		<div class="table-wrapper">
			<div class="table-title">
				<div class="row">
					<div class="col-sm-6">
						<h2>ABM <b>Usuarios</b></h2>
					</div>
					<div class="col-sm-6" align="right">
						<a href="#agregarModal" class="btn btn-success add"	data-toggle="modal"><img src="img/agregar.png" style="width: 20px; height: 20px;"><span>Agregar</span></a>
					</div>
				</div>
			</div>
			<table class="table table-striped table-hover">
				<thead>
					<tr>
						<th>Id</th>
						<th>Nombre de usuario</th>
						<th>Apellido</th>
						<th>Nombre</th>
						<th>DNI</th>
						<th>Sanatorios asociados</th>	
						<th>Acciones</th>											
					</tr>
				</thead>
				<tbody>
	                <c:forEach items="${usuarios}" var="usuario">
	                <tr>
	                    <td>${usuario.id}</td>
	                    <td>${usuario.username}</td>
	                    <td>${usuario.apellido}</td>
	                    <td>${usuario.nombre}</td>
	                    <td>${usuario.dni}</td>
	                    <td>${usuario.sanatorios}</td>
           				<td>
							<a href="#editarModal" class="edit" data-toggle="modal"><img src="img/editar.png" style="width: 20px; height: 20px;"></a> 
							<a href="#eliminarModal" class="delete" data-toggle="modal"><img src="img/eliminar.png" style="width: 20px; height: 20px;"></a>
						</td>
	                </tr>
	                </c:forEach>  
				</tbody>
			</table>
		</div>
	</div>

	<!--Agregar -->
	<div id="agregarModal" class="modal fade">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<form action="/SMG_PARKING/servlets/AltaUsuario" class="formAgregar" autocomplete="off">
					<div class="modal-header">
						<h4 class="modal-title">Agregar usuario</h4>
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<div class="row">
 								<div class="col-sm-6">
									<label>Nombre de usuario</label> <input type="text" id="usernameAdd" name="usernameAdd" class="form-control" required>
 								</div>
 								<div class="col-sm-6">
									<label>Documento</label> <input type="text" id="documentoAdd" name="documentoAdd" class="form-control" required>
 								</div>
 							</div>
						</div>
						<div class="form-group">
							<div class="row">
 								<div class="col-sm-6">
									<label>Nombres</label> <input type="text" id="nombreAdd" name="nombreAdd" class="form-control" required onkeyup="this.value = this.value.toUpperCase();">
 								</div>
 								<div class="col-sm-6">
									<label>Apellido</label> <input type="text" id="apellidoAdd" name="apellidoAdd" class="form-control" required onkeyup="this.value = this.value.toUpperCase();">
 								</div>
 							</div>
						</div>
						<div class="form-group">
							<div class="row">
 								<div class="col-sm-12">
									<label>Mail</label> <input type="text" id="mailAdd" name="mailAdd" class="form-control" required>
 								</div>
 							</div>
						</div>
						<div class="form-group">
							<div class="row">
 								<div class="col-sm-12">
									<span id="passwordError" style="color: red; font-weight: bold; font-size: 10pt"></span>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="row">
 								<div class="col-sm-6">
 									<label>Contraseña</label> <input type="password" id="passwordAdd" name="passwordAdd" class="form-control" required>
 								</div>
  								<div class="col-sm-6">
									<label>Confirmar contraseña</label> <input type="password" id="passwordCheckAdd" name="passwordCheckAdd" class="form-control" required> 
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="row">
								<div class="col-sm-12">
									<label>Perfil asociado</label>
									<select class="form-control" id="selectPerfiles" name="selectPerfiles" required>
										<option value="" disabled selected>Seleccionar Perfil</option>
										<c:forEach items="${perfiles}" var="perfil">
									        <option value="${perfil.id}">${perfil.nombre}</option>
									    </c:forEach> 
									</select>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="list-group checkbox-list-group">
								<label>Sanatorios asociados:</label>
				                <c:forEach items="${sanatorios}" var="sanatorio">
            						<div class="list-group-item">&nbsp;<label><input type="checkbox" value="<c:out value="${sanatorio.id}"/>"><span class="list-group-item-text"> ${sanatorio.nombre}</span></label></div>
				                </c:forEach>  
								<input type="hidden" id="sanatoriosAdd" name="sanatoriosAdd" class="form-control"> 				                
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancelar"> 
						<input type="submit" class="btn btn-success" value="Agregar">
					</div>
				</form>
			</div>
		</div>
	</div>


<!-- 	<!-- Editar -->
 	<div id="editarModal" class="modal fade">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<form action="/SMG_PARKING/servlets/EditarUsuario" autocomplete="off">
					<div class="modal-header">
						<h4 class="modal-title">Editar estado</h4>
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<input type="hidden" class="form-control" name="idEstado" id="idEstado" value="">
							<label>Descripción</label> 
							<input type="text" class="form-control" name="descEstadoEdit" id="descEstadoEdit" value="" required>
						</div>
					</div>
					<div class="modal-footer">
						<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancelar"> 
						<input type="submit" class="btn btn-info" value="Guardar">
					</div>
				</form>
			</div>
		</div>
	</div>  -->

	<!-- Eliminar -->
	<div id="eliminarModal" class="modal fade">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<form action="/SMG_PARKING/servlets/EliminarUsuario" autocomplete="off">
					<div class="modal-header">
						<h4 class="modal-title">Eliminar usuario</h4>
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">
						<p class="text-danger">¿Está seguro de querer eliminar el usuario?</p>
						<input type="hidden" class="form-control" name="idUsuarioE" id="idUsuarioE" value="">
					</div>
					<div class="modal-footer">
						<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancelar"> 
						<input type="submit" class="btn btn-danger" value="Eliminar">
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
