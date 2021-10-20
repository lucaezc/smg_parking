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

<title>Historial</title>

<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/menu.css" rel="stylesheet">
<script src="js/jquery.min.js"></script>
<script src="js/jquery-3.3.1.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/logs.js"></script>

</head>

<body>
    <jsp:include page="/servlets/GetLogs"/>
	<div class="container">
		<div class="table-wrapper">
			<div class="table-title">
				<div class="row">
					<div class="col-sm-2">
						<h2><b>Historial</b></h2>
					</div>
					<div class="col-sm-8">
						<div class="container text-right">
							<a href="Javascript: fnExcelReport();" class="btn btn-success"><img src="img/excel.png" style="width: 20px; height: 20px;"><span> Exportar Excel</span></a>
							<a href="#" id="link" style="display:none"></a>
						</div>
					</div>
				</div>
			</div>
			<table id="tablaLogs" class="table table-striped table-hover">
				<thead>
					<tr>
						<th>Id</th>
						<th>Fecha</th>
						<th>Hora</th>
						<th>Usuario</th>
						<th>Sanatorio</th>
						<th>Modulo</th>
						<th>Descripción</th>
					</tr>
				</thead>
				<tbody>
	                <c:forEach items="${logs}" var="log">
	                <tr>
	                    <td>${log.id}</td>
	                    <td>${log.fecha}</td>
	                  	<td>${log.hora}</td>
	                    <td>${log.usuario}</td>
	                    <td>${log.sanatorio}</td>
	                  	<td>${log.modulo}</td>
	                    <td>${log.descripcion}</td>
	                </tr>
	                </c:forEach>  
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>
