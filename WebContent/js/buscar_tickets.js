$(document).ready(function(){
    var date_input=$('input[name="date"]');
    var container=$('.bootstrap-iso form').length>0 ? $('.bootstrap-iso form').parent() : "body";
    var options={
        format: 'dd/mm/yyyy',
        container: container,
        todayHighlight: true,
        autoclose: true,
    };
    date_input.datepicker(options);
   
    date_input.on('changeDate', function(ev){
        $(this).datepicker('hide');
    });
    
    // VALIDACIÓN HORA DE SALIDA
     $("#horaSalida").change(function() { 
     	var fechaSalida = date_input.val();
    	var horaSalida = $(this).val() + ":00";
    	var salida = fechaSalida + " " + horaSalida;
        var tipoTicket = $("#tipoTicketCerrar").val();
        if (tipoTicket != 'Autorizado'){
        	if(futureDateTime(toJSDate(salida)) || pastDateTime(toJSDate(salida))){
        		$("#cerrar").hide();
        		$("#spanError").text("La fecha de cierre tiene una diferencia mayor a 30 min de la hora actual.");
        	}else{
        		$("#cerrar").show();
        		$("#spanError").text("");
        	}
        }
    });
    
    $('.clockpicker').clockpicker();
	
    $("#tablaTickets").tablesorter(); 
    
    // COLORES PARA LOS ESTADOS
    $('#tablaTickets td.ticketEstado').each(function(){
        if ($(this).text() == 'Abierto') {
            $(this).css('color','#ff0000');
        }
        
        if ($(this).text() == 'Cerrado') {
            $(this).css('color','#0000FF');
        }
        
        if ($(this).text() == 'Autorizado') {
            $(this).css('color','#82017d');
        }
        
        if ($(this).text() == 'Abierto/Rechazado') {
            $(this).css('color','#ff7b00');
        }
        
        if ($(this).text() == 'Autorizado/Cerrado') {
            $(this).css('color','#01c601');
        }
        
        if ($(this).text() == 'Pendiente de autorización') {
            $(this).css('color','#848181');
        }
    });
    
    // VISUALIZACIÓN DEL BOTÓN CERRAR
    $('#tablaTickets tr').each(function(){
        var estado = $(this).find("td:eq(9)").text(); 
        var botones = $(this).find("td:eq(16)"); 
        var botonesSinCerrar = $(this).find("td:eq(17)");
        var botonesValet = $(this).find("td:eq(18)"); 
        var botonesValetSinCerrar = $(this).find("td:eq(19)"); 
        var botonesSoloVer = $(this).find("td:eq(20)"); 

		if ($("#permisos").val() != 'Valet Parking'){
	        if (estado == 'Cerrado' || estado == 'Autorizado/Cerrado' || estado == 'Abierto/Rechazado' || estado == 'Pendiente de autorización'){
	        	botones.hide();
	        	botonesSinCerrar.show();
	        }else{
	        	if (estado == 'Eliminado'){
		        	botones.hide();
	        		botonesSoloVer.show();
	        	}else{
		        	botones.show();
	        	}
	        }
		}else{
	        if (estado == 'Cerrado' || estado == 'Autorizado/Cerrado' || estado == 'Abierto/Rechazado' || estado == 'Pendiente de autorización'){
	        	botones.hide();
	        	botonesValetSinCerrar.show();
	        }else{
	        	if (estado == 'Eliminado'){
		        	botones.hide();
	        		botonesSoloVer.show();
	        	}else{
		        	botones.hide();
		        	botonesValet.show();
		        }
	        }
		}
    });

    $(document).on("click", ".delete", function () {
        var currentRow = $(this).closest("tr"); 
        var idTicketE = currentRow.find("td:eq(0)").text();
        $(".modal-body #idTicketE").val(idTicketE);
    });

    $(document).on("click", ".view", function () {
        var currentRow = $(this).closest("tr"); 
        var idTicket = currentRow.find("td:eq(0)").text();
        var fechaIngreso = currentRow.find("td:eq(1)").text();
        var horaIngreso = currentRow.find("td:eq(2)").text();
        var patente = currentRow.find("td:eq(3)").text();
        var tipoVehiculo = currentRow.find("td:eq(4)").text();
        var tipoTicket = currentRow.find("td:eq(5)").text();
        var nombreCompleto = currentRow.find("td:eq(6)").text();
        var dni = currentRow.find("td:eq(7)").text();
        var nombreEstacionamiento = currentRow.find("td:eq(8)").text();
        var fechaHoraIngreso = fechaIngreso + " " + horaIngreso;

        $(".modal-body #idTicketV").val(idTicket);
        $(".modal-body #fechaHoraIngresoV").val(fechaHoraIngreso);
        $(".modal-body #tipoTicketV").val(tipoTicket);
        $(".modal-body #nombreEstacionamientoV").val(nombreEstacionamiento);
        $(".modal-body #tipoVehiculoV").val(tipoVehiculo);
        $(".modal-body #patenteV").val(patente);
        $(".modal-body #nombreCompletoV").val(nombreCompleto);
        $(".modal-body #dniV").val(dni);
    });
    
    $(document).on("click", ".edit", function () {
    	var currentRow = $(this).closest("tr"); 
        var idTicket = currentRow.find("td:eq(0)").text();
        var fechaIngreso = currentRow.find("td:eq(1)").text();
        var horaIngreso = currentRow.find("td:eq(2)").text();
        var patente = currentRow.find("td:eq(3)").text();
        var tipoVehiculo = currentRow.find("td:eq(4)").text();
        var tipoTicket = currentRow.find("td:eq(5)").text();
        var nombreCompleto = currentRow.find("td:eq(6)").text();
        var dni = currentRow.find("td:eq(7)").text();
        var nombreEstacionamiento = currentRow.find("td:eq(8)").text();
        var idMedico = currentRow.find("td:eq(10)").text();
        var nroEncuentroPaciente = currentRow.find("td:eq(11)").text();
        var suitePaciente = currentRow.find("td:eq(12)").text();
        var nhcPaciente = currentRow.find("td:eq(13)").text();
        var marcaVehiculo = currentRow.find("td:eq(14)").text();
        
        var s = nombreCompleto.split(",");
        var apellido = $.trim(s[0]);
        var nombres = $.trim(s[1]);
        
        $("#formPaciente").hide();
        $("nombreE").prop('disabled', true);
        $("apellidoE").prop('disabled', true);

        if (idMedico == 0){ 
        	$("#formPaciente").show();
            $("nombreE").prop('disabled', false);
            $("apellidoE").prop('disabled', false);
        }
        $(".modal-body #idTicketE").val(idTicket);
        $(".modal-body #fechaIngresoE").val(fechaIngreso);
        $(".modal-body #horaIngresoE").val(horaIngreso);
        $(".modal-body #tipoTicketE").val(tipoTicket);
        $(".modal-body #nombreEstacionamientoE").val(nombreEstacionamiento);
        $(".modal-body #tipoVehiculoE").val(tipoVehiculo);
        $(".modal-body #patenteE").val(patente);
        $(".modal-body #nombreCompletoE").val(nombreCompleto);
        $(".modal-body #dniE").val(dni);
        $(".modal-body #nroEncE").val(nroEncuentroPaciente);
        $(".modal-body #suiteE").val(suitePaciente);
        $(".modal-body #nhcE").val(nhcPaciente);
        $(".modal-body #marcaVehiculoE").val(marcaVehiculo);
                
        $(".modal-body #selectTiposTicketE option").filter(function() {
        	return this.text == tipoTicket; 
        }).attr('selected', true);

        $(".modal-body #selectVehiculoE option").filter(function() {
            return this.text == tipoVehiculo; 
        }).attr('selected', true);
        
        $(".modal-body #selectEstacionamientoE option").filter(function() {
            return this.text == nombreEstacionamiento; 
        }).attr('selected', true);
        
        $(".modal-body #nombreE").val(nombres);
        $(".modal-body #apellidoE").val(apellido);
    });
    
    $(document).on("click", ".close", function () {
		$("#spanError").text("");
		$("#cerrar").show();
        $(".modal-body #idTicketCerrar").val();
        $(".modal-body #idTipoVehiculoCerrar").val();
        $(".modal-body #idEstacionamientoCerrar").val();
        $(".modal-body #tipoTicketCerrar").val();
        $(".modal-body #diaSalida").val(null);
        $(".modal-body #horaSalida").val(null);
    	
        var currentRow = $(this).closest("tr"); 
        var idTicket = currentRow.find("td:eq(0)").text();
        var idTipoVehiculo = currentRow.find("td:eq(4)").text();
        var idEstacionamiento = currentRow.find("td:eq(15)").text();
        var tipoTicket = currentRow.find("td:eq(5)").text();
        $(".modal-body #idTicketCerrar").val(idTicket);
        $(".modal-body #idTipoVehiculoCerrar").val(idTipoVehiculo);
        $(".modal-body #idEstacionamientoCerrar").val(idEstacionamiento);
        $(".modal-body #tipoTicketCerrar").val(tipoTicket);
    });
        
    $("#selectTiposTicketE").change(function(){
        var op = $("#selectTiposTicketE option:selected").val();
        if (op == 1){
        	$("#formPaciente").show();
            $("nombreE").prop('disabled', false);
            $("apellidoE").prop('disabled', false);
        }else{
            $("#formPaciente").hide();
            $("nombreE").prop('disabled', true);
            $("apellidoE").prop('disabled', true);
        }
    });
    
	$("#cerrar").on("click", function() {
     	var diaSalida = $("#diaSalida").val();
    	var horaSalida = $("#horaSalida").val();
		var idTicket = $("#idTicketCerrar").val();
    	var idTipoVehiculoCerrar = $("#idTipoVehiculoCerrar").val();
		var idEstacionamientoCerrar = $("#idEstacionamientoCerrar").val();
		    	
	    $.get('/SMG_PARKING/servlets/CerrarTicket', {
	    	idTicketCerrar : idTicket,
	    	date : diaSalida,
	    	hour : horaSalida,
	    	idTipoVehiculoCerrar : idTipoVehiculoCerrar,
	    	idEstacionamientoCerrar : idEstacionamientoCerrar
	    }); 

	    $.get('/SMG_PARKING/servlets/GetDatosImpresion', {
	    	idTicket : idTicket
	    }, function(data) {
	    	
	        var WinPrint = window.open('', '', 'left=100,top=100,width=600,height=600');
	        WinPrint.document.write("<html><head> </head><body>");
	        WinPrint.document.write('<div align="center" style="font-weight: bold;">' + data.nombreSanatorio + '</div>');
	        WinPrint.document.write('<div align="center" style="font-weight: bold;">ESTACIONAMIENTO</div>');
	        WinPrint.document.write("<br>");	     
	        WinPrint.document.write('<div align="right">TICKET NRO: ' + data.idTicket + '</div>');
	        WinPrint.document.write("<br>");	     
	        WinPrint.document.write("<div>ENTRADA: " + data.fechaHoraIngreso + "</div>");
	        WinPrint.document.write("<div>VEHICULO: " + data.tipoVehiculo + "</div>");
	        WinPrint.document.write("<div>PATENTE: " + data.patente + "</div>");
	        
			if (data.tipoTicket == 1) { 
		        WinPrint.document.write("<div>PACIENTE: " + data.apellidoPaciente + "," + data.nombrePaciente + "</div>");
		        WinPrint.document.write("<div>DNI: " + data.dniPaciente + "</div>");
		        WinPrint.document.write("<div>NHC: " + data.nhcPaciente + "</div>");
		        WinPrint.document.write("<div>NRO ENCUENTRO: " + data.nroEncPaciente + "</div>");
			}else {
				if (data.tipoTicket == 2) {
			        WinPrint.document.write("<div>PROFESIONAL: " + data.apellidoMedico + "," + data.nombreMedico + "</div>");
			        WinPrint.document.write("<div>DNI: " + data.dniMedico + "</div>");
			        WinPrint.document.write("<div>MATRICULA: " + data.matriculaMedico + "</div>");
				}else {
			        WinPrint.document.write("<div>AUTORIZADO: " + data.apellidoPaciente + "," + data.nombrePaciente + "</div>");
			        WinPrint.document.write("<div>DNI: " + data.dniPaciente + "</div>");
				}
			}
	        WinPrint.document.write("<div>SALIDA: " + data.fechaHoraEgreso + "</div>");	   
	        WinPrint.document.write("<br>");	     
			if (data.tipoTicket == 1) { 
		        WinPrint.document.write("<div>Firma Paciente: ______________________________</div>");	     
			}else {
				if (data.tipoTicket == 2) {
			        WinPrint.document.write("<div>Firma Médico: ______________________________</div>");	     
				}else {
			        WinPrint.document.write("<div>Firma Autorizado: ______________________________</div>");	     
				}
			}
	        WinPrint.document.write("<br>");	     
	        WinPrint.document.write('<div align="justify">La empresa no se responsabiliza por perdidas, sustracciones o deterioros que pudiera sufrir su vehiculo o cualquier articulo dentro del mismo.</div>');	        
	        WinPrint.document.write("</body></html>");
	        
	        WinPrint.document.close();
	        WinPrint.focus();
	        WinPrint.print();
	        WinPrint.close();
	        window.location.href = "menu.jsp";
	    }); 
		

    });
});

function toJSDate( dateTime ) {
	var dateTime = dateTime.split(" ");
	var date = dateTime[0].split("/");
	var time = dateTime[1].split(":");
	return new Date(date[2], (date[1]-1), date[0], time[0], time[1], 0, 0); //(year, month, day, hours, minutes, seconds, milliseconds), subtract 1 from month because Jan is 0 and Dec is 11
}

function futureDateTime( dateTime ) { //Check to see if the DateTime is in the future, param: dateTime must be a JS Date Object, return True if DateTime is after Now, return False if DateTime is before Now
	var now = new Date();
	now.setMinutes(now.getMinutes() + 30);
	var future = false;
	if(Date.parse(now) < Date.parse(dateTime)) {
		future = true;
	}
	return future;
}

function pastDateTime( dateTime ) {
	var now = new Date();
	now.setMinutes(now.getMinutes() - 30);
	var past = false;
	if(Date.parse(now) > Date.parse(dateTime)) {
		past = true;
	}
	return past;
}