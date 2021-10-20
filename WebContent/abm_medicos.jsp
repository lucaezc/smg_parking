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

<title>ABM Médicos</title>

<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/menu.css" rel="stylesheet">
<script src="js/jquery.min.js"></script>
<script src="js/jquery-3.3.1.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/jquery.tablesorter.min.js"></script>
<script src="js/abm_medicos.js"></script>
<style type="text/css">
	tr.selected td {
	    background-color: #333;
	    color: #fff;    
	}
</style>

</head>

<body>
    <jsp:include page="/servlets/GetMedicosParcial"/>
	<div class="container">
		<div class="table-wrapper">
			<div class="table-title">
				<div class="row">
					<div class="col-sm-6">
						<h2>ABM <b>Médicos</b></h2>
					</div>
				</div>
			</div>
			<div class="form-group">
				<div class="row">
					<div class="col-sm-6">
	    				<input id="medico" class="form-control" type="text" name="medico" placeholder="Buscar médico...">
  					</div>
					<div class="col-sm-6" align="right">
						<a href="Javascript: buscar();" class="btn btn-info"><img src="img/buscar.png" style="width: 20px; height: 20px;"><span> Buscar</span></a>
						<a href="#agregarModal" class="btn btn-success"	data-toggle="modal"><img src="img/agregar.png" style="width: 20px; height: 20px;"><span>Agregar</span></a>
						<a href="Javascript: editar();" class="btn btn-dark"><img src="img/editar.png" style="width: 20px; height: 20px;">Editar</a> 
						<a href="Javascript: eliminar();" class="btn btn-dark"><img src="img/eliminar.png" style="width: 20px; height: 20px;">Eliminar</a>
					</div>
				</div>
			</div>
			
			<div class="table-wrapper" id="tabla">
				<table id="tablaMedicos" class="table table-striped table-hover">
					<thead>
						<tr>
							<th>ID</th>
							<th>Apellido</th>
							<th>Nombre</th>
							<th>DNI</th>
							<th>Matrícula nacional</th>
						</tr>
					</thead>
					<tbody>
		                <c:forEach items="${medicos}" var="medico">
		                <tr class="tablaData" style="cursor:pointer;">
		                    <td>${medico.id}</td>
		                    <td>${medico.apellido}</td>
		                    <td>${medico.nombre}</td>
	       	                <td>${medico.dni}</td>
	       	                <td>${medico.matriculaNacional}</td>
		                </tr>
		                </c:forEach>  
					</tbody>
				</table>
			</div>
			
		</div>
	</div>

	<!--Agregar médico -->
	<div id="agregarModal" class="modal fade">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<form action="/SMG_PARKING/servlets/AltaMedico" autocomplete="off">
					<div class="modal-header">
						<h4 class="modal-title">Agregar médico</h4>
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<label>DNI</label> 
							<input type="text" class="form-control" id="dniMedico" name="dniMedico" required>
						</div>
						<div class="form-group">
							<label>Nro. Matrícula Nacional</label>
							<input type="text" class="form-control" id="matNacMedico" name="matNacMedico" required>
						</div>
						<div class="form-group">
							<label>Apellido</label>
							<input type="text" class="form-control" id="apellidoMedico" name="apellidoMedico" required onkeyup="this.value = this.value.toUpperCase();">
						</div>
						<div class="form-group">
							<label>Nombres</label>
							<input type="text" class="form-control" id="nombresMedico" name="nombresMedico" required onkeyup="this.value = this.value.toUpperCase();">
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
				<form action="/SMG_PARKING/servlets/EditarMedico" autocomplete="off">
					<div class="modal-header">
						<h4 class="modal-title">Editar médico</h4>
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<input type="hidden" class="form-control" name="idMedicoE" id="idMedicoE" value="">
							<label>Nombre</label> <input type="text" class="form-control" name="nombreE" id="nombreE" value="" required onkeyup="this.value = this.value.toUpperCase();">
							<label>Apellido</label> <input type="text" class="form-control" name="apellidoE" id="apellidoE" value="" required onkeyup="this.value = this.value.toUpperCase();">
							<label>DNI</label> <input type="text" class="form-control" name="dniE" id="dniE" value="" required>
							<label>Matrícula Nacional</label> <input type="text" class="form-control" name="matNacE" id="matNacE" value="" required>							
						</div>
					</div>
					<div class="modal-footer">
						<input type="button" class="btn btn-default" id="cancelarEditar" value="Cancelar"> 
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
				<form action="/SMG_PARKING/servlets/EliminarMedico" autocomplete="off">
					<div class="modal-header">
						<h4 class="modal-title">Eliminar médico</h4>
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">
						<p class="text-danger">¿Está seguro de querer eliminar el médico?</p>
						<input type="hidden" class="form-control" name="idMedicoD" id="idMedicoD" value="">
					</div>
					<div class="modal-footer">
						<input type="button" class="btn btn-default" id="cancelarEliminar" value="Cancelar"> 
						<input type="submit" class="btn btn-danger" value="Eliminar">
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
