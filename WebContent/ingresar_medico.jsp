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
	<title>Ingresar Médico</title>
	
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/menu.css" rel="stylesheet">
	<link href="css/datepicker.css" rel="stylesheet">
	<link href="css/clockpicker.css" rel="stylesheet">
	<script src="js/jquery.min.js"></script>
	<script src="js/jquery-3.3.1.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/bootstrap-datepicker.js"></script>
	<script src="js/jquery.validate.js"></script>
	<script src="js/clockpicker.js"></script>
	<script src="js/jquery.mask.js"></script>
	<script src="js/ingresar_medico.js"></script>
</head>

<body>
	<jsp:include page="/servlets/GetTiposVehiculo"/>
	<jsp:include page="/servlets/GetEstacionamientosSanatorio"/>
	<jsp:include page="/servlets/GetMedicos"/>
	
	<div class="container">
		<div class="row">
			<div class="col-sm-6">
				<h2><b>Ingreso de ticket para médico</b></h2><br>				
				<input id="permisos" type="hidden" value="<c:out value="${permisos}"/>"> 
			</div>
		</div>
		<form id="formularioMedico" action="/SMG_PARKING/servlets/AltaTicketMedico" autocomplete="off">
			<div class="form-group">
				<div class="row">
					<div class="col-sm-8 autocomplete">
	    				<input id="medico" class="form-control" type="text" name="medico" placeholder="Buscar médico..." required>
	    				<select class="form-control" id="selectMedico" style="display:none;">
							<c:forEach items="${medicos}" var="med">
						        <option value="${med.id}">${med.apellidoNombre}</option>
						    </c:forEach> 
						</select>
  					</div>
					<div class="col-sm-4">
						<a href="#agregarModal" class="btn btn-success" data-toggle="modal" id="agregarMedico"><img src="img/agregar.png" style="width: 20px; height: 20px;"><span></span></a>
					</div>
				</div>
			</div>
			<div class="form-group">
				<div class="row">
					<div class="col-sm-8">
						<select class="form-control" id="selectVehiculo" name="selectVehiculo" required>
							<option value="" disabled selected>Seleccionar Tipo de vehículo</option>
							<c:forEach items="${tiposVehiculo}" var="tipoV">
						        <option value="${tipoV.id}">${tipoV.nombre}</option>
						    </c:forEach> 
						</select>
					</div>
				</div>
			</div>
			<div class="form-group">
				<div class="row">
					<div class="col-sm-4">
						<input type="checkbox" class="form-check-input" id="chPatenteNacional" checked> 
						<label class="form-check-label" for="chPatenteNacional">Ingresar patente nacional</label> 
						<input type="text" class="form-control" id="patenteNacional" name="patenteNacional" placeholder="ABC123 (Sólo dígitos)">
					</div>
					<div class="col-sm-4">
						<input type="checkbox" class="form-check-input" id="chPatenteMercosur">
						<label class="form-check-label" for="chPatenteMercosur">Ingresar patente Mercosur</label> 
						<input type="text" class="form-control" id="patenteMercosur" name="patenteMercosur" disabled placeholder="AB123CD (Sólo dígitos)">
					</div>
				</div>
			</div>
			<div class="form-group">
				<div class="row">
					<div class="col-sm-4">
						<label class="control-label" for="diaEntrada">Dia de entrada</label>
						<input class="form-control" id="diaEntrada" name="date" placeholder="DD/MM/YYYY" type="text" required />
					</div>
					<div class="col-sm-4">
						<label class="control-label" for="horaEntrada">Hora de entrada</label>
						<input class="form-control clockpicker" id="horaEntrada" name="hour" placeholder="hh:mm" type="text" required />
					</div>
				</div>
			</div>
			<div class="form-group">
				<div class="row">
					<div class="col-sm-8">
					    <select class="form-control" id="selectEstacionamiento" name="selectEstacionamiento" required>
					    	<option value="" disabled selected>Seleccionar Estacionamiento</option>
							<c:forEach items="${estacionamientos}" var="est">
						        <option value="${est.id}">${est.nombre}</option>
						    </c:forEach> 
					    </select>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-8">
						<span id="spanError" style="color:red; font-size:20; font-weight:bold"></span>
						<input type="hidden" id="excedido" name="excedido"/>
					</div>
				</div>
			</div>
			<div class="form-group" align="right">
				<button type="button" class="btn btn-secondary" id="btnLimpiar">Limpiar</button>
				<button type="submit" class="btn btn-success">Guardar</button>
			</div>
		</form>
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
							<input type="text" class="form-control" id="apellidoMedico" name="apellidoMedico" required>
						</div>
						<div class="form-group">
							<label>Nombres</label>
							<input type="text" class="form-control" id="nombresMedico" name="nombresMedico" required>
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
</body>
</html>
