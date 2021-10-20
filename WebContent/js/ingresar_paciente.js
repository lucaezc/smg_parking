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
   
   // VALIDACIÓN HORA DE SALIDA
   $("#horaEntrada").change(function() { 
	   var fechaEntrada = date_input.val();
	   var horaEntrada = $(this).val() + ":00";
	   var entrada = fechaEntrada + " " + horaEntrada;
	   if(futureDateTime(toJSDate(entrada)) || pastDateTime(toJSDate(entrada))){
		   $("#guardar").hide();
		   $("#spanError").text("La fecha de entrada tiene una diferencia mayor a 30 min de la hora actual.");
	   }else{
		   $("#guardar").show();
		   $("#spanError").text("");
	   }
   	});
   
    $('#chPatenteNacional').change(function() { 
        if(this.checked) {
            $('#chPatenteMercosur').prop('checked', false);
            $("#patenteMercosur").prop('disabled', true);
            $("#patenteNacional").prop('disabled', false);
            $('#patenteMercosur').val("");
            $("#patenteMercosur").prop('required', false);
            $("#patenteNacional").prop('required', true);
        }else{
            $('#chPatenteMercosur').prop('checked', true);
            $("#patenteMercosur").prop('disabled', false);
            $("#patenteNacional").prop('disabled', true);
            $('#patenteNacional').val("");
            $("#patenteMercosur").prop('required', true);
            $("#patenteNacional").prop('required', false);
        }
    });
    
    $('#chPatenteMercosur').change(function() { 
        if(this.checked) {
            $('#chPatenteNacional').prop('checked', false);
            $("#patenteNacional").prop('disabled', true);
            $("#patenteMercosur").prop('disabled', false);
            $('#patenteNacional').val("");
            $("#patenteMercosur").prop('required', true);
            $("#patenteNacional").prop('required', false);
        }else{
            $('#chPatenteNacional').prop('checked', true);
            $("#patenteNacional").prop('disabled', false);
            $("#patenteMercosur").prop('disabled', true);
            $('#patenteMercosur').val("");
            $("#patenteMercosur").prop('required', false);
            $("#patenteNacional").prop('required', true);
        }
    });
    
    $('.clockpicker').clockpicker();
    
    // SI EL SANATORIO TIENE UN SÓLO ESTACIONAMIENTO ASOCIADO MUESTRA SÓLO UNO 
    var $selectEstacionamiento = $("#selectEstacionamiento");
    $.get('/SMG_PARKING/servlets/GetEstacionamientosSanatorioCombo', {
    }, function(data) {
    	$selectEstacionamiento.empty();
		var c = 0;
        $.each(data, function(index, item) {
            c++;
        });
    	if (c > 1){
    		$selectEstacionamiento.append($("<option disabled selected/>").val("").text("Seleccionar Estacionamiento"));
    	}
        $.each(data, function(index, item) {
        	$selectEstacionamiento.append($("<option />").val(item.id).text(item.nombre));
        });
        
        if (c == 1){
        	$("#selectEstacionamiento").change();
        }
    });
    
    $("#selectEstacionamiento").change(function() {
    	var idEst = $("#selectEstacionamiento").val();
        $.get('/SMG_PARKING/servlets/CheckEstacionamiento', {
        	idEstacionamiento : idEst
        }, function(data) {
        	if (data.lugaresLibres == 0){
        		if ($("#permisos").val() != 'Valet Parking'){
                	$("#spanError").text("El estacionamiento seleccionado está lleno. Se facturará como 'Exceso'.");
        	    }
        		$("#excedido").val("1");
        	}else{
        		$("#spanError").text("");
            	$("#excedido").val("0");
        	}
        });
	});
    
    $("#btnLimpiar").click(function(){
        $('#apellidoPaciente').val("");
        $('#nombresPaciente').val("");
        $('#nhc').val("");
        $('#encuentro').val("");
        $('#suite').val("");
        $('#marca').val("");
        $('#selectVehiculo').val("");
        $('#patenteNacional').val("");
        $('#patenteMercosur').val("");
        $('#diaEntrada').val("");
        $('#horaEntrada').val("");
        $('#dni').val("");
        $("#spanError").text("");
    }); 
    
    $("#apellidoPaciente").keypress(function (event) {
        var inputValue = event.which;
        if (!(inputValue >= 65 && inputValue <= 123) && (inputValue != 32 && inputValue != 0) && (inputValue != 47 && inputValue != 8) && (inputValue != 9)){ event.preventDefault(); }
    });
    
    $("#nombresPaciente").keypress(function (event) {
        var inputValue = event.which;
        if (!(inputValue >= 65 && inputValue <= 123) && (inputValue != 32 && inputValue != 0) && (inputValue != 47 && inputValue != 8) && (inputValue != 9)){ event.preventDefault(); }
    });
    
    $("#marca").keypress(function (event) {
        var inputValue = event.which;
        if (!(inputValue >= 65 && inputValue <= 123) && (inputValue != 32 && inputValue != 0) && (inputValue != 47 && inputValue != 8) && (inputValue != 9)){ event.preventDefault(); }
    });
    
    $('#patenteNacional').keyup(function(){
        this.value = this.value.toUpperCase();
    });
    
    $('#patenteMercosur').keyup(function(){
        this.value = this.value.toUpperCase();
    });
    
    $('#dni').mask('00000000');
    $('#patenteNacional').mask('000');
    $('#patenteMercosur').mask('000');
    $("#nhc").inputFilter(function(value) { return /^\d*$/.test(value); });
    $("#encuentro").inputFilter(function(value) { return /^\d*$/.test(value); });
    $("#suite").inputFilter(function(value) { return /^\d*$/.test(value); });
})

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