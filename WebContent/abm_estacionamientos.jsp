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

<title>ABM Estacionamientos</title>

<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/menu.css" rel="stylesheet">
<script src="js/jquery.min.js"></script>
<script src="js/jquery-3.3.1.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/abm_estacionamientos.js"></script>

</head>

<body>
    <jsp:include page="/servlets/GetEstacionamientos"/>
    <jsp:include page="/servlets/GetEstadosEstacionamientos"/>
    <jsp:include page="/servlets/GetSanatorios"/>
	<div class="container">
		<div class="table-wrapper">
			<div class="table-title">
				<div class="row">
					<div class="col-sm-6">
						<h2>ABM <b>Estacionamientos</b></h2>
					</div>
					<div class="col-sm-6" align="right">
						<a href="#agregarModal" class="btn btn-success" data-toggle="modal"><img src="img/agregar.png" style="width: 20px; height: 20px;"><span>Agregar</span></a>
					</div>
				</div>
			</div>
			<table class="table table-striped table-hover">
				<thead>
					<tr>
						<th>Id</th>
						<th>Estacionamiento</th>
						<th>Capacidad</th>
						<th>Sanatorio/Centro</th>
						<th>Estado</th>
						<th>Acciones</th>
					</tr>
				</thead>
				<tbody>
	                <c:forEach items="${estacionamientos}" var="est">
	                <tr>
	                    <td>${est.id}</td>
	                    <td>${est.nombre}</td>
	                    <td>${est.capacidad}</td>	                    
	                    <td>${est.nombreSanatorio}</td>
	                   	<td>${est.nombreEstado}</td>
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
				<form action="/SMG_PARKING/servlets/AltaEstacionamiento" autocomplete="off">
					<div class="modal-header">
						<h4 class="modal-title">Agregar estacionamiento</h4>
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<label>Nombre</label> <input type="text" id="nombreEst" name="nombreEst" class="form-control" required>
						</div>
						<div class="form-group">
							<label>Capacidad</label> <input type="text" id="capacidadEst" name="capacidadEst" class="form-control" required>
						</div>
						<div class="form-group">
							<label>Sanatorio</label> 
							<select name="idSanatorioEst" class="form-control" required>
								<option value="" disabled selected>Seleccionar Sanatorio</option>    
								<c:forEach items="${sanatorios}" var="sanatorio">
							        <option value="${sanatorio.id}">${sanatorio.nombre}</option>
							    </c:forEach> 
							</select>
						</div>
						<div class="form-group">
							<label>Estado</label> 
							<select name="idEstadoEst" class="form-control" required>
								<option value="" disabled selected>Seleccionar Estado</option>    
								<c:forEach items="${estados}" var="estado">
							        <option value="${estado.id}">${estado.nombre}</option>
							    </c:forEach> 
							</select>
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


	<!-- Editar -->
	<div id="editarModal" class="modal fade">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<form action="/SMG_PARKING/servlets/EditarEstacionamiento" autocomplete="off">
					<div class="modal-header">
						<h4 class="modal-title">Editar estacionamiento</h4>
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<input type="hidden" class="form-control" name="idEstEdit" id="idEstEdit" value="">
							<label>Nombre</label> <input type="text" class="form-control" name="nombreEstEdit" id="nombreEstEdit" required onkeyup="this.value = this.value.toUpperCase();">
						</div>
						<div class="form-group">
							<label>Capacidad</label> <input type="text" class="form-control" name="capacidadEstEdit" id="capacidadEstEdit" required>
						</div>
						<div class="form-group">
							<label>Sanatorio</label> 
							<select class="form-control" name="idSanatorioEst" id="idSanatorioEst" required>
								<option value="" disabled selected>Seleccionar Sanatorio</option>    
								<c:forEach items="${sanatorios}" var="sanatorio">
							        <option value="${sanatorio.id}">${sanatorio.nombre}</option>
							    </c:forEach> 
							</select>
						</div>
						<div class="form-group">
							<label>Estado</label> 
							<select class="form-control" name="idEstadoEst" id="idEstadoEst" required>
								<option value="" disabled selected>Seleccionar Estado</option>    
								<c:forEach items="${estados}" var="estado">
							        <option value="${estado.id}">${estado.nombre}</option>
							    </c:forEach> 
							</select>
						</div>
					</div>
					<div class="modal-footer">
						<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancelar"> 
						<input type="submit" class="btn btn-info" value="Guardar">
					</div>
				</form>
			</div>
		</div>
	</div>

	<!-- Eliminar -->
	<div id="eliminarModal" class="modal fade">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<form action="/SMG_PARKING/servlets/EliminarEstacionamiento" autocomplete="off">
					<div class="modal-header">
						<h4 class="modal-title">Eliminar estacionamiento</h4>
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">
						<p class="text-danger">¿Está seguro de querer eliminar el estacionamiento?</p>
						<input type="hidden" class="form-control" name="idEstE" id="idEstE" value="">
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
