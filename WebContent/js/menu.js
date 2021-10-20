$(document).ready(function() {
	
	//VALIDACIÓN DEL CAMPO BÚSQUEDA (SÓLO NÚMEROS Y NO PERMITE ENTER)
	(function($) {
	  $.fn.inputFilter = function(inputFilter) {
	    return this.on("input keydown keyup mousedown mouseup select contextmenu drop", function() {
	      if (inputFilter(this.value)) {
	        this.oldValue = this.value;
	        this.oldSelectionStart = this.selectionStart;
	        this.oldSelectionEnd = this.selectionEnd;
	      } else if (this.hasOwnProperty("oldValue")) {
	        this.value = this.oldValue;
	        this.setSelectionRange(this.oldSelectionStart, this.oldSelectionEnd);
	      }
	    });
	  };
	}(jQuery));

	// VALIDACIÓN PARA QUE NO SE ACCEDA DESDE UNA PÁGINA INVÁLIDA Y SIN LOGIN DE POR MEDIO
	if ($("#nombreUsuario").val() === "" || $("#nombreSanatorio").val() === ""){
		salir();
	}
	
	// BLOQUEO POR PERFIL
	if ($("#permisos").val() == 'Coordinador'){
    	$("#administrador").hide();
    	$("#administradorSubMenu").hide();
    }
	if ($("#permisos").val() == 'Valet Parking'){
    	$("#administrador").hide();
    	$("#administradorSubMenu").hide();
    	$("#autorizaciones").hide();
    	$("#reportes").hide();
    }
	
	// BUSQUEDA - CAMBIA A BORDE NORMAL AL HACER FOCUS SOBRE CAMPO DE BÚSQUEDA
	$("#stringBusqueda").focusin(function() {
		$("#stringBusqueda").css("border", "1px solid white");
	});
	
	// BUSQUEDA - CAMBIA A BORDE NORMAL AL HACER FOCUS SOBRE EL COMBOBOX DE TIPO DE BÚSQUEDA
	$("#tipoBusqueda").focusin(function() {
		$("#tipoBusqueda").css("border", "1px solid white");
	});
	
	// BUSQUEDA DE TICKET EN BARRA SUPERIOR
	$("#botonBusqueda").click( function(){
		var stringBusqueda = $("#stringBusqueda").val();
		var tipoBusqueda = $("#tipoBusqueda").val();

		if (tipoBusqueda === null){
			$("#tipoBusqueda").css("border", "1px solid red");
		}else{
			if (stringBusqueda === null || stringBusqueda === ''){
				$("#stringBusqueda").css("border", "1px solid red");
			}else{
				var parametros = "?stringBusqueda="+stringBusqueda+"&tipoBusqueda="+tipoBusqueda;
				$('#paginaprincipal').load("resultados_busqueda.jsp" + parametros); 
			}
		}
	});
	
	// IMPIDE QUE EN CAMPO DE BÚSQUEDA SE PRESIONE LA TECLA ENTER O SPACE
	$("#stringBusqueda").keypress(function(event){ if (event.keyCode == 10 || event.keyCode == 13 || event.keyCode == 32) event.preventDefault(); });  
});

// ABRE BARRA LATERAL
function openNav() {
  document.getElementById("sidebar").style.width = "250px";
}

// CIERRA BARRA LATERAL
function closeNav() {
  document.getElementById("sidebar").style.width = "0";
}

// MUESTRA MODAL INGRESAR TICKET
function modal(){
    $('#ingresarModal').modal("show");
}

// CARGAR PÁGINA
function cargar(destino){
    $('#paginaprincipal').load(destino);
    $('#sidebar').width(0);
}

//CARGAR PÁGINA COMO MODAL
function cargarModal(destino){
    $('#paginaprincipal').load(destino); 
    $('#ingresarModal').modal("hide");
    $('#sidebar').width(0);
}

function sidebar(){
    if ($('#sidebar').width() == 0){
        $('#sidebar').width(250);
    }else{
        $('#sidebar').width(0);
    }
}

function salir(){
	window.location.href = "index.jsp";
	sessionStorage.clear();
}

function nuevaBusqueda(){
	$("#stringBusqueda").val('');
	$("#tipoBusqueda").val('');
}