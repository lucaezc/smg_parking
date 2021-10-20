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

<title>Autorizaciones pendientes</title>

<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/menu.css" rel="stylesheet">
<script src="js/jquery.min.js"></script>
<script src="js/jquery-3.3.1.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/autorizaciones.js"></script>

</head>

<body>
    <jsp:include page="/servlets/GetAutorizaciones"/>
	<div class="container">
		<div class="table-wrapper">
			<div class="table-title">
				<div class="row">
					<div class="col-sm-6">
						<h2><b>Autorizaciones</b></h2>
					</div>
				</div>
			</div>
			<table class="table table-striped table-hover">
				<thead>
					<tr>
						<th>Usuario solicitante</th>
						<th>Autorizado</th>
						<th>Fecha del pedido</th>
						<th>Hora del pedido</th>
						<th>Estacionamiento</th>
						<th>Acciones</th>
					</tr>
				</thead>
				<tbody>
	                <c:forEach items="${autorizaciones}" var="aut">
	                <tr>
	                    <td>${aut.usuarioSolicitante}</td>
	                    <td>${aut.nomApeAutorizado}</td>
	                    <td>${aut.fechaPedido}</td>
	                    <td>${aut.horaPedido}</td>
	                    <td>${aut.estacionamiento}</td>	                    
	                   	<td style="display:none;">${aut.id}</td>
	                   	<td style="display:none;">${aut.ticketId}</td>
           				<td>
							<a href="#autorizarModal" class="autorize" data-toggle="modal"><img src="img/autorizar.png" style="width: 20px; height: 20px;"></a> 
						</td>
	                </tr>
	                </c:forEach>  
				</tbody>
			</table>
		</div>
	</div>

	<!-- Autorizar -->
	<div id="autorizarModal" class="modal fade">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<form action="/SMG_PARKING/servlets/Autorizar" autocomplete="off">
					<div class="modal-header">
						<h4 class="modal-title">Autorizar</h4>
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">
						<p class="text-danger">¿Desea autorizar este pedido?</p>
						<input type="hidden" class="form-control" name="idAut" id="idAut" value="">
						<input type="hidden" class="form-control" name="idTicket" id="idTicket" value="">
					</div>
					<div class="modal-footer">
						<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancelar" style="margin-right: 15px">
						<input type="submit" class="btn btn-danger" name="accion" value="Rechazar" style="margin-right: 15px">
						<input type="submit" class="btn btn-success" name="accion" value="Autorizar">
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
