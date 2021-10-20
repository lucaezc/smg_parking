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
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<title>Resultados de búsqueda</title>

<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/menu.css" rel="stylesheet">
<link href="css/datepicker.css" rel="stylesheet">
<link href="css/clockpicker.css" rel="stylesheet">
<script src="js/jquery.min.js"></script>
<script src="js/jquery-3.3.1.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/bootstrap-datepicker.js"></script>
<script src="js/clockpicker.js"></script>
<script src="js/buscar_tickets.js"></script>
<script src="js/jquery.tablesorter.min.js"></script>
<script src="js/busqueda.js"></script>

<style>
    .datepicker {
      z-index: 1600 !important; 
    }
</style>
</head>

<body>
    <jsp:include page="/servlets/Busqueda"/>
	<div class="container">
	<c:if test="${fn:length(tickets) gt 0}">
		<div class="table-wrapper">
			<div class="table-title">		
				<div class="row">
					<div class="col-sm-6">
						<h2><b>Resultados de búsqueda</b></h2>
						<input id="stringBusqueda" type="hidden" value="<c:out value="${stringBusqueda}"/>"> 
						<input id="tipoBusqueda" type="hidden" value="<c:out value="${tipoBusqueda}"/>"> 
					</div>
				</div>
			</div>
			<br>
			<table class="table table-striped table-hover" id="tablaTickets">
				<thead>
					<tr>
						<th>COD. TICKET</th>
						<th>FECHA INGRESO</th>
						<th>HORA INGRESO</th>
						<th>MATRÍCULA</th>
						<th>VEHICULO</th>
						<th>TIPO</th>
						<th>NOMBRE</th>
						<th>DNI</th>
						<th>COCHERA</th>
						<th>ESTADO</th>
						<th>CONTROLES</th>
					</tr>
				</thead>
				<tbody>
					
				
	                <c:forEach items="${tickets}" var="ticket">
	                <tr>
	                    <td>${ticket.id}</td>
	                    <td>${ticket.fechaIngreso}</td>
	                    <td>${ticket.horaIngreso}</td>	                    
	                    <td>${ticket.patente}</td>
	                   	<td>${ticket.tipoVehiculo}</td>
	                   	<td>${ticket.tipoTicket}</td>
	                    <td>${ticket.nombreCompleto}</td>
	                    <td>${ticket.dni}</td>	                    
	                    <td>${ticket.nombreEstacionamiento}</td>
	                   	<td class="ticketEstado">${ticket.estado}</td>
	                   	<td style="display:none;">${ticket.idMedico}</td> 	
	                   	<td style="display:none;">${ticket.nroEncuentroPaciente}</td>
	                   	<td style="display:none;">${ticket.suitePaciente}</td> 	
	                   	<td style="display:none;">${ticket.nhcPaciente}</td> 	
	                   	<td style="display:none;">${ticket.marcaVehiculo}</td> 
	                   	<td style="display:none;">${ticket.idEstacionamiento}</td>	
						<td class="botonera">
							<a href="#verModal" class="view" data-toggle="modal"><img src="img/ver.png" style="width: 20px; height: 20px;" title="Visualizar ticket"></a> 
							<a href="#editarModal" class="edit" data-toggle="modal"><img src="img/editar.png" style="width: 20px; height: 20px;" title="Editar ticket"></a> 
							<a href="#eliminarModal" class="delete" data-toggle="modal"><img src="img/eliminar.png" style="width: 20px; height: 20px;" title="Eliminar ticket"></a>
							<a href="#cerrarModal" class="close" data-toggle="modal"><img src="img/cerrar.png" style="width: 20px; height: 20px;" title="Cerrar ticket"></a>
						</td>
						<td class="botoneraSinCerrar" style="display:none;">
							<a href="#verModal" class="view" data-toggle="modal"><img src="img/ver.png" style="width: 20px; height: 20px;" title="Visualizar ticket"></a> 
							<a href="#editarModal" class="edit" data-toggle="modal"><img src="img/editar.png" style="width: 20px; height: 20px;" title="Editar ticket"></a> 
							<a href="#eliminarModal" class="delete" data-toggle="modal"><img src="img/eliminar.png" style="width: 20px; height: 20px;" title="Eliminar ticket"></a>
						</td>
						<td class="botoneraValet" style="display:none;">
							<a href="#verModal" class="view" data-toggle="modal"><img src="img/ver.png" style="width: 20px; height: 20px;" title="Visualizar ticket"></a> 
							<a href="#eliminarModal" class="delete" data-toggle="modal"><img src="img/eliminar.png" style="width: 20px; height: 20px;" title="Eliminar ticket"></a>
							<a href="#cerrarModal" class="close" data-toggle="modal"><img src="img/cerrar.png" style="width: 20px; height: 20px;" title="Cerrar ticket"></a>
						</td>
						<td class="botoneraValetSinCerrar" style="display:none;">
							<a href="#verModal" class="view" data-toggle="modal"><img src="img/ver.png" style="width: 20px; height: 20px;" title="Visualizar ticket"></a> 
							<a href="#eliminarModal" class="delete" data-toggle="modal"><img src="img/eliminar.png" style="width: 20px; height: 20px;" title="Eliminar ticket"></a>
						</td>
						<td class="botoneraSoloVer" style="display:none;">
							<a href="#verModal" class="view" data-toggle="modal"><img src="img/ver.png" style="width: 20px; height: 20px;" title="Visualizar ticket"></a> 
						</td>
	                </tr>
	                </c:forEach>  
				</tbody>
			</table>
		</div>
		</c:if>
		<c:if test="${fn:length(tickets) eq 0}">
			<h1 style="text-align: center;"><b>No se encontraron tickets para esa búsqueda.</b></h1>
		</c:if>
	</div>

	<!-- Ver -->
	<div id="verModal" class="modal fade">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<form>
					<div class="modal-header">
						<h4 class="modal-title">Ver ticket</h4>	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<div class="row">
								<div class="col-md-6">
									<label>Código de ticket</label> <input type="text" class="form-control" name="idTicketV" id="idTicketV" disabled>
								</div>
								<div class="col-md-6">
									<label>Fecha y hora de ingreso</label> <input type="text" class="form-control" name="fechaHoraIngresoV" id="fechaHoraIngresoV" disabled>
								</div>
							</div>
							<div class="row">
								<div class="col-md-6">
									<label>Tipo de ticket</label> <input type="text" class="form-control" name="tipoTicketV" id="tipoTicketV" disabled>
								</div>
								<div class="col-md-6">
									<label>Estacionamiento</label> <input type="text" class="form-control" name="nombreEstacionamientoV" id="nombreEstacionamientoV" disabled>
								</div> 
							</div>
							<div class="row">
								<div class="col-md-6">
									<label>Tipo de vehículo</label> <input type="text" class="form-control" name="tipoVehiculoV" id="tipoVehiculoV" disabled>
								</div>
								<div class="col-md-6">
									<label>Patente</label> <input type="text" class="form-control" name="patenteV" id="patenteV" disabled>
								</div>
							</div>
							<div class="row">
								<div class="col-md-6">
									<label>Nombre</label> <input type="text" class="form-control" name="nombreCompletoV" id="nombreCompletoV" disabled>
								</div>
								<div class="col-md-6">
									<label>DNI</label> <input type="text" class="form-control" name="dniV" id="dniV" disabled>
								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<input type="button" class="btn btn-default" data-dismiss="modal" value="Volver"> 
					</div>
				</form>
			</div>
		</div>
	</div>
	
	<!-- Editar -->
	<div id="editarModal" class="modal fade">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<form action="/SMG_PARKING/servlets/EditarTicket" autocomplete="off">
					<div class="modal-header">
						<h4 class="modal-title">Editar ticket</h4>
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<div class="row">
								<div class="col-md-5">
									<label>Código de ticket</label> <input type="text" class="form-control" name="idTicketE" id="idTicketE">
								</div>
								<div class="col-md-4">
									<label>Fecha Ingreso</label> <input type="text" class="form-control" name="fechaIngresoE" id="fechaIngresoE" required>
								</div>
								<div class="col-md-3">
									<label>Hora Ingreso</label> <input type="text" class="form-control" name="horaIngresoE" id="horaIngresoE" required>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<label>Tipo de ticket</label>
								    <select class="form-control" id="selectTiposTicketE" name="selectTiposTicketE" required>
								    	<option value="" disabled selected>Seleccionar Tipo de ticket</option>
										<c:forEach items="${tiposTickets}" var="tt">
									        <option value="${tt.id}">${tt.nombre}</option>
									    </c:forEach> 
								    </select>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<label>Estacionamiento</label>
								    <select class="form-control" id="selectEstacionamientoE" name="selectEstacionamientoE" required>
								    	<option value="" disabled selected>Seleccionar Estacionamiento</option>
										<c:forEach items="${estacionamientos}" var="est">
									        <option value="${est.id}">${est.nombre}</option>
									    </c:forEach> 
								    </select>
								</div>
							</div>
							<div class="row">
			 					<div class="col-sm-12">
			 						<label>Tipo de vehículo</label>
								    <select class="form-control" id="selectVehiculoE" name="selectVehiculoE" required>
								    	<option value="" disabled selected>Seleccionar Tipo de vehículo</option>
										<c:forEach items="${tiposVehiculo}" var="tipoV">
									        <option value="${tipoV.id}">${tipoV.nombre}</option>
									    </c:forEach> 
								    </select>
			 					</div>
							</div>
							<div class="row">
								<div class="col-md-6">
									<label>Patente</label> <input type="text" class="form-control" name="patenteE" id="patenteE" required>
								</div>		
								<div class="col-md-6">
									<label>DNI</label> <input type="text" class="form-control" name="dniE" id="dniE" required>
								</div>	
							</div>
							<div class="row">
								<div class="col-md-6">
									<label>Nombre</label> <input type="text" class="form-control" name="nombreE" id="nombreE" required>
								</div>
								<div class="col-md-6">
									<label>Apellido</label> <input type="text" class="form-control" name="apellidoE" id="apellidoE" required>
								</div>
							</div>
							<div class="form-group" id="formPaciente">
								<div class="row">
									<div class="col-md-6">
										<label>Nro. encuentro</label> <input type="text" class="form-control" name="nroEncE" id="nroEncE">
									</div>
									<div class="col-md-6">
										<label>Suite</label> <input type="text" class="form-control" name="suiteE" id="suiteE">
									</div>
								</div>
								<div class="row">
									<div class="col-md-6">
										<label>Nro. Historia Clínica (NHC)</label> <input type="text" class="form-control" name="nhcE" id="nhcE">
									</div>
									<div class="col-md-6">
										<label>Marca vehículo</label> <input type="text" class="form-control" name="marcaVehiculoE" id="marcaVehiculoE">
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<input type="button" class="btn btn-default" data-dismiss="modal" value="Volver"> 
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
				<form action="/SMG_PARKING/servlets/EliminarTicket" autocomplete="off">
					<div class="modal-header">
						<h4 class="modal-title">Eliminar ticket</h4>
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">
						<p class="text-danger">¿Está seguro de querer eliminar el ticket?</p>
						<input type="hidden" class="form-control" name="idTicketE" id="idTicketE" value="">
					</div>
					<div class="modal-footer">
						<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancelar"> 
						<input type="submit" class="btn btn-danger" value="Eliminar">
					</div>
				</form>
			</div>
		</div>
	</div>
	
	<!-- Cerrar -->
	<div id="cerrarModal" class="modal fade">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<form action="/SMG_PARKING/servlets/CerrarTicket" autocomplete="off">
					<div class="modal-header">
						<h4 class="modal-title">Cerrar ticket</h4>
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-sm-6">
								<input type="hidden" class="form-control" name="idTicketCerrar" id="idTicketCerrar" value="">
								<input type="hidden" class="form-control" name="idTipoVehiculoCerrar" id="idTipoVehiculoCerrar" value="">
								<input type="hidden" class="form-control" name="idEstacionamientoCerrar" id="idEstacionamientoCerrar" value="">
								<label class="control-label" for="diaSalida">Dia de salida</label>
								<input class="form-control" id="diaSalida" name="date" placeholder="DD/MM/YYYY" type="text" required />
							</div>
							<div class="col-sm-6">
								<label class="control-label" for="horaSalida">Hora de salida</label>
								<input class="form-control clockpicker" id="horaSalida" name="hour" placeholder="hh:mm" type="text" required />
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12">
								<span id="spanError" style="color:red; font-size:10; font-weight:bold;"></span>
							</div>
						</div>
					</div>
					<br>
					<div class="modal-footer">
						<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancelar"> 
						<input type="submit" id="cerrar" class="btn btn-danger" value="Cerrar">
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
