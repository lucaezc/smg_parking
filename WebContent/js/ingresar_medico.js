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
    
    $('.clockpicker').clockpicker();

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
    
    $("#btnLimpiar").click(function(){
        $('#selectMedicos').val("");
        $('#selectVehiculo').val("");
        $('#patenteNacional').val("");
        $('#patenteMercosur').val("");
        $('#selectEstacionamiento').val("");
        $('#diaEntrada').val("");
        $('#horaEntrada').val("");
        $('#medico').val("");
    }); 
    
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
    
	if (($("#permisos").val() == 'Coordinador') || ($("#permisos").val() == 'Valet Parking')){
    	$("#agregarMedico").hide();
    }
	
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
	
    $("#medico").keypress(function (event) {
        var inputValue = event.which;
        if (!(inputValue >= 65 && inputValue <= 123) && (inputValue != 32 && inputValue != 0) && (inputValue != 47 && inputValue != 8) && (inputValue != 9)){ event.preventDefault(); }
    });
    
    $("#apellidoMedico").keypress(function (event) {
        var inputValue = event.which;
        if (!(inputValue >= 65 && inputValue <= 123) && (inputValue != 32 && inputValue != 0) && (inputValue != 47 && inputValue != 8) && (inputValue != 9)){ event.preventDefault(); }
    });
    
    $("#nombresMedico").keypress(function (event) {
        var inputValue = event.which;
        if (!(inputValue >= 65 && inputValue <= 123) && (inputValue != 32 && inputValue != 0) && (inputValue != 47 && inputValue != 8) && (inputValue != 9)){ event.preventDefault(); }
    });
    
    $('#patenteNacional').keyup(function(){
        this.value = this.value.toUpperCase();
    });
    
    $('#patenteMercosur').keyup(function(){
        this.value = this.value.toUpperCase();
    });
    
    $('#dniMedico').mask('00000000');
    $('#patenteNacional').mask('000');
    $('#patenteMercosur').mask('000');
    $("#matNacMedico").inputFilter(function(value) { return /^\d*$/.test(value); });
    
    var medicos = [];
    $("#selectMedico > option").each(function() {
        medicos.push(this.text);
    });

	autocomplete(document.getElementById("medico"), medicos);
	
    // VALIDACIÓN ESTACIONAMIENTO LLENO - EXCESO
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
})

function autocomplete(inp, arr) {
	var currentFocus;
	  
  	inp.addEventListener("input", function(e) {
	      var a, b, i, val = this.value;
	      closeAllLists();
	      if (!val) { return false;}
	      currentFocus = -1;
	      a = document.createElement("DIV");
	      a.setAttribute("id", this.id + "autocomplete-list");
	      a.setAttribute("class", "autocomplete-items");
	      this.parentNode.appendChild(a);

	      for (i = 0; i < arr.length; i++) {
	        if (arr[i].substr(0, val.length).toUpperCase() == val.toUpperCase()) {
	          b = document.createElement("DIV");
	          b.innerHTML = "<strong>" + arr[i].substr(0, val.length) + "</strong>";
	          b.innerHTML += arr[i].substr(val.length);
	          b.innerHTML += "<input type='hidden' value='" + arr[i] + "'>";
	          b.addEventListener("click", function(e) {
	          	inp.value = this.getElementsByTagName("input")[0].value;
	            closeAllLists();
	          });
	          a.appendChild(b);
	        }
	      }
	});

  	inp.addEventListener("keydown", function(e) {
		  var x = document.getElementById(this.id + "autocomplete-list");
	      if (x) x = x.getElementsByTagName("div");
	      if (e.keyCode == 40) {
	      	currentFocus++;
	        addActive(x);
	      } else if (e.keyCode == 38) {
	        currentFocus--;
	        addActive(x);
	      } else if (e.keyCode == 13) {
	        e.preventDefault();
	        if (currentFocus > -1) {
	          if (x) x[currentFocus].click();
	        }
	      }
	});
	
  	function addActive(x) {
	    if (!x) return false;
	    removeActive(x);
	    if (currentFocus >= x.length) currentFocus = 0;
	    if (currentFocus < 0) currentFocus = (x.length - 1);
	    x[currentFocus].classList.add("autocomplete-active");
	}
  	
	function removeActive(x) {
	    for (var i = 0; i < x.length; i++) {
	      x[i].classList.remove("autocomplete-active");
	    }
	}
	
	function closeAllLists(elmnt) {
	    var x = document.getElementsByClassName("autocomplete-items");
	    for (var i = 0; i < x.length; i++) {
	      if (elmnt != x[i] && elmnt != inp) {
	      x[i].parentNode.removeChild(x[i]);
	    }
	  }
	}
	
	document.addEventListener("click", function (e) {
	    closeAllLists(e.target);
	});
} // autocomplete(inp, arr)