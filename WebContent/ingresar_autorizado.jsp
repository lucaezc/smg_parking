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
	<title>Ingresar Autorizado</title>
	
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
	<script src="js/ingresar_autorizado.js"></script>
	<script src="js/jquery.mask.js"></script>
</head>

<body>
	<jsp:include page="/servlets/GetTiposVehiculo"/>
	<jsp:include page="/servlets/GetEstacionamientosSanatorio"/>
	
	<div class="container">
		<div class="row">
			<div class="col-sm-6">
				<h2><b>Ingreso de ticket para autorizado</b></h2><br>
			</div>
		</div>
		<form id="formularioAutorizado" action="/SMG_PARKING/servlets/AltaTicketAutorizado" autocomplete="off">
	  		<div class="form-group">
				<div class="row">
 					<div class="col-sm-3">
				        <label class="control-label" for="apellidoAutorizado">Apellido</label> <input class="form-control" id="apellidoAutorizado" name="apellidoAutorizado" type="text" required onkeyup="this.value = this.value.toUpperCase();"/>
 					</div>
 					<div class="col-sm-3">
				        <label class="control-label" for="nombresAutorizado">Nombres</label> <input class="form-control" id="nombresAutorizado" name="nombresAutorizado" type="text" required onkeyup="this.value = this.value.toUpperCase();"/>
 					</div>
 					<div class="col-sm-2">
				        <label class="control-label" for="dni">DNI</label> <input class="form-control" id="dni" name="dni" type="text" required/>
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
</body>
</html>
