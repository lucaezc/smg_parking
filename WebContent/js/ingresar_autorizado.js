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
        $('#apellidoAutorizado').val("");
        $('#nombresAutorizado').val("");
        $('#selectVehiculo').val("");
        $('#patenteNacional').val("");
        $('#patenteMercosur').val("");
        $('#diaEntrada').val("");
        $('#horaEntrada').val("");
        $('#nomApeAutorizante').val("");
        $('#dni').val("");
    }); 
    
    $("#apellidoAutorizado").keypress(function (event) {
        var inputValue = event.which;
        if (!(inputValue >= 65 && inputValue <= 123) && (inputValue != 32 && inputValue != 0) && (inputValue != 47 && inputValue != 8) && (inputValue != 9)){ event.preventDefault(); }
    });
    
    $("#nombresAutorizado").keypress(function (event) {
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
    
})