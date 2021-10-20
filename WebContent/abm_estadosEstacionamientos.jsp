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
	
	<title>ABM Estados de Estacionamientos</title>
	
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/menu.css" rel="stylesheet">
	<script src="js/jquery.min.js"></script>
	<script src="js/jquery-3.3.1.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/abm_estadosEstacionamientos.js"></script>

</head>

<body>
    <jsp:include page="/servlets/GetEstadosEstacionamientos"/>
	<div class="container">
		<div class="table-wrapper">
			<div class="table-title">
				<div class="row">
					<div class="col-sm-6">
						<h2>ABM <b>Estados de Estacionamientos</b></h2>
					</div>
					<div class="col-sm-6" align="right">
						<a href="#agregarModal" class="btn btn-success"	data-toggle="modal"><img src="img/agregar.png" style="width: 20px; height: 20px;"><span>Agregar</span></a>
					</div>
				</div>
			</div>
			<table class="table table-striped table-hover">
				<thead>
					<tr>
						<th>Id</th>
						<th>Descripción</th>
						<th>Acciones</th>
					</tr>
				</thead>
				<tbody>
	                <c:forEach items="${estados}" var="estado">
	                <tr>
	                    <td>${estado.id}</td>
	                    <td>${estado.nombre}</td>
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
				<form action="/SMG_PARKING/servlets/AltaEstadoEstacionamiento" autocomplete="off">
					<div class="modal-header">
						<h4 class="modal-title">Agregar estado de estacionamiento</h4>
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<label>Descripción</label> 
							<input type="text" id="descEstado" name="descEstado" class="form-control" required>
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
				<form action="/SMG_PARKING/servlets/EditarEstadoEstacionamiento" autocomplete="off">
					<div class="modal-header">
						<h4 class="modal-title">Editar estado de estacionamiento</h4>
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
	</div>

	<!-- Eliminar -->
	<div id="eliminarModal" class="modal fade">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<form action="/SMG_PARKING/servlets/EliminarEstadoEstacionamiento" autocomplete="off">
					<div class="modal-header">
						<h4 class="modal-title">Eliminar estado de estacionamiento</h4>
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">
						<p class="text-danger">¿Está seguro de querer eliminar el estado de estacionamiento?</p>
						<input type="hidden" class="form-control" name="idEstadoE" id="idEstadoE" value="">
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
