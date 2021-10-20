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

<title>Reportes</title>

<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/menu.css" rel="stylesheet">
<link href="css/datepicker.css" rel="stylesheet">
<script src="js/jquery.min.js"></script>
<script src="js/jquery-3.3.1.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/bootstrap-datepicker.js"></script>
<script src="js/reportes.js"></script>
<script src="js/jspdf.min.js"></script>
<script src="js/jquery.tablesorter.min.js"></script>

</head>

<body>
    <jsp:include page="/servlets/GetTicketsReportes"/>
	<div class="container-fluid">
		<div class="table-title">
			<div class="row">
				<div class="col-sm-2">
					<h2><b>Reportes</b></h2>
				</div>	
				<div class="col-sm-8">
					<div class="container text-center">
						<a href="Javascript: filtros();" class="btn btn-warning" title="Ver/Ocultar filtros"><img src="img/filtro.png" style="width: 20px; height: 20px;"></a>
						<a href="Javascript: limpiarFiltros();" class="btn btn-danger" title="Limpiar filtros"><img src="img/restart.png" style="width: 20px; height: 20px;"></a>
						<a href="Javascript: buscar();" class="btn btn-info"><img src="img/buscar.png" style="width: 20px; height: 20px;"><span> Buscar</span></a>
						<a href="" class="btn btn-primary"><img src="img/imprimir.png" style="width: 20px; height: 20px;"><span> Imprimir</span></a>
						<a href="Javascript: fnExcelReport();" class="btn btn-success"><img src="img/excel.png" style="width: 20px; height: 20px;"><span> Exportar Excel</span></a>
						<a href="#" id="link" style="display:none"></a>
					</div>
				</div>
			</div>
		</div>
		<hr>
		<div class="row" id="filtros">
			<aside class="col-sm-3">
				<div class="card">
					<article class="card-group-item">
						<header class="card-header"> <h6 class="title">Filtrar Vehículo </h6> </header>
						<div class="filter-content">
							<div class="card-body">
								<form>
									<label class="form-check"> <input class="form-check-input" type="checkbox" value="tv-Auto" name="filtroVehiculo" id="filtro_tvAuto"> <span class="form-check-label">Auto</span> </label>
									<label class="form-check"> <input class="form-check-input" type="checkbox" value="tv-Camioneta" name="filtroVehiculo" id="filtro_tvCamioneta"> <span class="form-check-label">Camioneta</span> </label>
									<label class="form-check"> <input class="form-check-input" type="checkbox" value="tv-CamionetaXL" name="filtroVehiculo" id="filtro_tvCamionetaXL"> <span class="form-check-label">Camioneta XL</span> </label>
									<label class="form-check"> <input class="form-check-input" type="checkbox" value="tv-Moto" name="filtroVehiculo" id="filtro_tvMoto"> <span class="form-check-label">Moto</span> </label>
									<label class="form-check"> <input class="form-check-input" type="checkbox" value="tv-Bicicleta" name="filtroVehiculo" id="filtro_tvBicicleta"> <span class="form-check-label">Bicicleta</span> </label>
								</form>
							</div>
						</div>
					</article>
				</div>
			</aside>
			<aside class="col-sm-3">
				<div class="card">
					<article class="card-group-item">
						<header class="card-header"> <h6 class="title">Filtrar Estado </h6> </header>
						<div class="filter-content">
							<div class="card-body">
								<form>
									<label class="form-check"> <input class="form-check-input" type="checkbox" value="et-Abierto" name="filtroTipoTicket" id="filtro_ttAbierto"> <span class="form-check-label">Abierto</span> </label>
									<label class="form-check"> <input class="form-check-input" type="checkbox" value="et-Autorizado" name="filtroTipoTicket" id="filtro_ttAutorizado"> <span class="form-check-label">Autorizado</span> </label>
									<label class="form-check"> <input class="form-check-input" type="checkbox" value="et-PendienteAut" name="filtroTipoTicket" id="filtro_ttPendienteAut"> <span class="form-check-label">Pendiente de autorización</span> </label>
									<label class="form-check"> <input class="form-check-input" type="checkbox" value="et-AbiertoRechazado" name="filtroTipoTicket" id="filtro_ttAbiertoRechazado"> <span class="form-check-label">Abierto/Rechazado</span> </label>
									<label class="form-check"> <input class="form-check-input" type="checkbox" value="et-AutCerrado" name="filtroTipoTicket" id="filtro_ttAutCerrado"> <span class="form-check-label">Autorizado/Cerrado</span> </label>
									<label class="form-check"> <input class="form-check-input" type="checkbox" value="et-Cerrado" name="filtroTipoTicket" id="filtro_ttCerrado"> <span class="form-check-label">Cerrado</span> </label>
									<label class="form-check"> <input class="form-check-input" type="checkbox" value="et-Eliminado" name="filtroTipoTicket" id="filtro_ttEliminado"> <span class="form-check-label">Eliminado</span> </label>
								</form>
							</div>
						</div>
					</article>
				</div>
			</aside>
			<aside class="col-sm-3">
				<div class="card">
					<article class="card-group-item">
						<header class="card-header"> <h6 class="title">Filtrar Fechas </h6> </header>
						<div class="filter-content">
							<div class="card-body">
								<form>
									<label class="control-label" for="fechaDesde">Fecha desde</label> <input class="form-control" id="fechaDesde" name="fechaDesde" placeholder="DD/MM/YYYY" type="text"/><br>
									<label class="control-label" for="fechaHasta">Fecha hasta</label> <input class="form-control" id="fechaHasta" name="fechaHasta" placeholder="DD/MM/YYYY" type="text"/>
								</form>
							</div>
						</div>
					</article>
				</div>
			</aside>
			<aside class="col-sm-3">
				<div class="card">
					<article class="card-group-item">
						<header class="card-header"> <h6 class="title">Filtrar Ingreso </h6> </header>
						<div class="filter-content">
							<div class="card-body">
								<form>
									<label class="form-check"> <input class="form-check-input" type="checkbox" value="tt-Medico" name="filtroTipoIngreso" id="filtro_tiMedico"> <span class="form-check-label">Médico</span> </label>
									<label class="form-check"> <input class="form-check-input" type="checkbox" value="tt-Paciente" name="filtroTipoIngreso" id="filtro_tiPaciente"> <span class="form-check-label">Paciente</span> </label>
									<label class="form-check"> <input class="form-check-input" type="checkbox" value="tt-Autorizado" name="filtroTipoIngreso" id="filtro_tiAutorizado"> <span class="form-check-label">Autorizado</span> </label>
								</form>
							</div>
						</div>
					</article>
				</div>
			</aside>
		</div>
		<br>
	</div>
	<br>
	<div class="container-fluid">
		<div class="table-wrapper" id="tabla">
			<table id="tablaReporte" class="table table-striped table-hover">
				<thead>
					<tr>
						<th style="cursor: pointer;">COD. TICKET</th>
						<th style="cursor: pointer;">FECHA INGRESO</th>
						<th style="cursor: pointer;">HORA INGRESO</th>
						<th style="cursor: pointer;">USUARIO INGRESO</th>
						<th style="cursor: pointer;">MATRICULA</th>
						<th style="cursor: pointer;">VEHICULO</th>
						<th style="cursor: pointer;">TIPO</th>
						<th style="cursor: pointer;">FECHA SALIDA</th>
						<th style="cursor: pointer;">HORA SALIDA</th>
						<th style="cursor: pointer;">ESTADO</th>
						<th style="cursor: pointer;">NOMBRE Y APELLIDO</th>
						<th style="cursor: pointer;">DNI</th>
						<th style="cursor: pointer;">ESTACIONAMIENTO</th>
						<th style="cursor: pointer;">USUARIO CIERRE</th>
						<th style="cursor: pointer;">FECHA CIERRE</th>
						<th style="cursor: pointer;">HORA CIERRE</th>
						<th style="cursor: pointer;">FACTURADO EXCEDIDO</th>
						<th style="cursor: pointer;">EXCEDIDO - FECHA INGRESO</th>
						<th style="cursor: pointer;">EXCEDIDO - HORA INGRESO</th>
						<th style="cursor: pointer;">EXCEDIDO - FECHA SALIDA</th>
						<th style="cursor: pointer;">EXCEDIDO - HORA SALIDA</th>
					</tr>
				</thead>
				<tbody>
					<tr>
	                <c:forEach items="${tickets}" var="ticket">
	                <tr>
	                    <td>${ticket.id}</td>
	                    <td>${ticket.fechaIngreso}</td>
	                    <td>${ticket.horaIngreso}</td>	 
	                   	<td>${ticket.usuarioRegistro}</td> 	                   
	                    <td>${ticket.patente}</td>
	                   	<td>${ticket.tipoVehiculo}</td>
	                   	<td>${ticket.tipoTicket}</td>
	                   	<td>${ticket.fechaEgreso}</td>
	                   	<td>${ticket.horaEgreso}</td>
	                   	<td>${ticket.estado}</td>
	                    <td>${ticket.nombreCompleto}</td>
	                    <td>${ticket.dni}</td>	                    
	                    <td>${ticket.nombreEstacionamiento}</td>	
	                    <td>${ticket.usuarioCierre}</td> 	
	                   	<td>${ticket.fechaCierre}</td> 
	                   	<td>${ticket.horaCierre}</td> 		
	                   	<td>${ticket.facturadoExcedido}</td>	
	                   	<td>${ticket.fechaIngresoExcedido}</td> 	
	                   	<td>${ticket.horaIngresoExcedido}</td> 		                   	
	                   	<td>${ticket.fechaEgresoExcedido}</td> 
	                   	<td>${ticket.horaEgresoExcedido}</td> 	                   	
	                </tr>
	                </c:forEach>  
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	
</body>
</html>
