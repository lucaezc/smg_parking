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
	
	<title>SMG PARKING</title>
	
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/login.css" rel="stylesheet">
	<script src="js/jquery.min.js"></script>
	<script src="js/jquery-3.3.1.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/index.js"></script>
</head>

<body class="text-center" style="background-image: url('img/fondoLogin.png'); background-repeat: no-repeat; background-size: cover;">
    <jsp:include page="/servlets/GetSanatorios"/>
	<form class="form-signin" action="/SMG_PARKING/servlets/Login" autocomplete="off">
		<div class="div mb-3">
			<img src="img/logo.png" style="width: 430px; height: 106px; display: block; margin-left: auto; margin-right: auto;">
		</div>
		<div class="div mb-3">
			<label for="inputUsuario" class="sr-only">Usuario</label> 
			<input type="text" id="inputUsuario" name="usuario" class="form-control" placeholder="Usuario" required autofocus style="background-color: #ededed;">
		</div>
		<div class="div mb-3">
			<label for="inputPassword" class="sr-only">Password</label> 
			<input type="password" id="inputPassword" name="password" class="form-control" placeholder="Contraseña" required style="background-color: #ededed;">
		</div>
		<div class="div mb-3">
			<select name="sanatorio" id="inputSanatorio" class="form-control" required style="background-color: #ededed;">
				<option value="" disabled selected>Seleccionar Sanatorio</option>    
				<c:forEach items="${sanatorios}" var="sanatorio">
			        <option value="${sanatorio.id}">${sanatorio.nombre}</option>
			    </c:forEach> 
			</select>
		</div>
		<button class="btn btn-lg btn-danger btn-block" type="submit">Ingresar</button>
		<br><span id="spanError" style="color:red; font-size:20; font-weight:bold">${mensajeError}</span> 
	</form>
</body>
</html>