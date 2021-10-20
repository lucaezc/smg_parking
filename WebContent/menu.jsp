<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<link rel="icon" href="img/logoparking.png">
	
	<title>SMG PARKING - Menú Principal</title>
	
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/menu.css" rel="stylesheet">
	<script src="js/jquery.min.js"></script>
	<script src="js/jquery-3.3.1.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/menu.js"></script>
</head>

<body style="background-image: url('img/fondoMain.png'); background-repeat: no-repeat; background-size: cover;">
	<nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
		<div>
			<span style="font-size:18px;cursor:pointer; color:white; margin-right: 5px" onclick="salir()" title="Salir">&#10060;</span>
			<span style="font-size:18px;cursor:pointer; color:white; margin-right: 5px" onclick="sidebar()">&#9776;</span>
			<a class="navbar-brand"> <img class="logo" src="img/logoparking.png" style="width: 20px; height: 20px; margin-right: 5px"> 
				<label style="font-size:18px; cursor:none; color:white; margin-right: 5px">SMG PARKING</label> 
			</a>
		</div>

		<div class="collapse navbar-collapse">
			<form class="form-inline" autocomplete="off" style="width:100%;">
				<div class="col-sm-6">
					<select name="tipoBusqueda" id="tipoBusqueda" style="background-color: #353535; color:white; margin-right: 5px" class="form-control" tabindex=1>
						<option value="" selected disabled>Criterio de búsqueda</option>    
						<option value="idTicket">ID Ticket</option>    
						<option value="dni">DNI</option>    
						<option value="patente">Patente</option>    
						<option value="apeNom">Apellido</option>    
					</select> 					
					<input class="form-control" type="text" placeholder="Buscar" aria-label="Search" id="stringBusqueda" style="background-color: #353535; color:white" size="10" tabindex=2 onkeyup="this.value = this.value.toUpperCase();">
					<a class="btn btn-outline-success" id="botonBusqueda" style="margin-right: 5px; cursor:pointer" tabindex=3>Buscar ticket</a>
					<a href="Javascript: nuevaBusqueda();" class="btn btn-outline-danger" title="Nueva búsqueda"><img src="img/restartRojo.png" style="width: 20px; height: 20px;" tabindex=4></a>
				</div>
				<div class="col-sm-6" align="right">
					<span style="color: white; font-weight: bold; font-style: italic; font-size: 9pt">SANATORIO: </span><span style="color: lightblue; font-weight: bold; font-size: 9pt">${nombreSanatorio}</span><br>
					<span style="color: white; font-weight: bold; font-style: italic; font-size: 9pt">USUARIO: </span><span style="color: lightblue; font-weight: bold; font-size: 9pt">${nombreUsuario}</span>
					<input id="permisos" type="hidden" value="<c:out value="${permisos}"/>"> 
					<input id="nombreUsuario" type="hidden" value="<c:out value="${nombreUsuario}"/>"> 
					<input id="nombreSanatorio" type="hidden" value="<c:out value="${nombreSanatorio}"/>">
				</div>
			</form>
		</div>
	</nav>

	<div id="sidebar" class="sidenav">
		<ul class="list-unstyled components">
			<li class="active"><a href="Javascript: modal();" style="padding: 10px">Ingresar Nuevo Ticket</a></li>
			<li><a href="Javascript: cargar('buscar_tickets.jsp');" style="padding: 10px">Buscar tickets</a></li>
			<li><a href="Javascript: cargar('reportes.jsp');" id="reportes" style="padding: 10px">Reportes</a></li>
			<li><a href="Javascript: cargar('autorizaciones.jsp');" id="autorizaciones" style="padding: 10px">Autorizaciones pendientes</a></li>
			<li><a class="dropdown-toggle" id="administrador" aria-expanded="true" style="padding: 10px; cursor:none;">Administrador</a>
				<ul class="collapse" id="administradorSubMenu">
					<li><a href="Javascript: cargar('logs.jsp');" style="padding: 20px">Historial</a></li>
					<li><a href="Javascript: cargar('abm_estacionamientos.jsp');" id="abm_estacionamientos" style="padding: 20px">ABM Estacionamientos</a></li>
					<li><a href="Javascript: cargar('abm_sanatorios.jsp');" id="abm_sanatorios" style="padding: 20px">ABM Sanatorios</a></li>
					<li><a href="Javascript: cargar('abm_estadosEstacionamientos.jsp');" id="abm_estadosEstacionamientos" style="padding: 20px">ABM Estados de Estacionamientos</a></li>
					<li><a href="Javascript: cargar('abm_estadosTickets.jsp');" id="abm_estadosTickets" style="padding: 20px">ABM Estados de Tickets</a></li>
					<li><a href="Javascript: cargar('abm_perfiles.jsp');" id="abm_perfiles" style="padding: 20px">ABM Perfiles</a></li>
					<li><a href="Javascript: cargar('abm_usuarios.jsp');" style="padding: 20px">ABM Usuarios</a></li>
					<li><a href="Javascript: cargar('abm_medicos.jsp');" style="padding: 20px">ABM Médicos</a></li>
				</ul>
			</li>
		</ul>
	</div>

	<div id="paginaprincipal"></div>
	
	<div id="ingresarModal" class="modal fade">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<form>
					<div class="modal-body">
						<img src="img/estacionamiento.png" style="width: 100px; height: 100px; display: block; margin-left: auto; margin-right: auto;">
					</div>
					<div class="modal-footer">
						<input type="image" src="img/doctor.png" formaction="Javascript: cargarModal('ingresar_medico.jsp');" alt="Médico" width="150px" height="150px"> 
						<input type="image" src="img/paciente.png" formaction="Javascript: cargarModal('ingresar_paciente.jsp');" alt="Paciente" width="150px" height="150px"> 
						<input type="image" src="img/autorizado.png" formaction="Javascript: cargarModal('ingresar_autorizado.jsp');" alt="Autorizado" width="150px" height="150px">
					</div>
				</form>
			</div>
		</div>
	</div>
	
	<div id="buscarModal" class="modal fade">
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
</body>
</html> 
