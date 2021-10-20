$(document).ready(function(){
	
	$("#cancelarEliminar").on("click", function() {
		$('#eliminarModal').hide();
	});
	
	$("#cancelarEditar").on("click", function() {
		$('#editarModal').hide();
	});
	
	$(".tablaData td").on("click", function() {
	    var tr = $(this).parent();
	    if(tr.hasClass("selected")) {
	        tr.removeClass("selected");
	    } else {
	        tr.addClass("selected");
	    }
	});
	    
});

function buscar(){
	var medico = $('#medico').val();
    
    $.get('/SMG_PARKING/servlets/GetMedicosFiltrados', {
    	medico : medico
    }, function(data) {
    	$('#tablaMedicos tr').not(':first').remove();
    	var html = '';
    	for(var i = 0; i < data.length; i++){ 		
    		html += '<tr class="tablaData" style="cursor:pointer;"><td>' + data[i].id + '</td><td>' + data[i].apellido + '</td><td>' + data[i].nombre + '</td><td>' + data[i].dni + '</td><td>' + data[i].matriculaNacional + '</td></tr>';
    	}
    	$('#tablaMedicos tr').first().after(html);
    	
    	$(".tablaData td").on("click", function() {
    	    var tr = $(this).parent();
    	    if(tr.hasClass("selected")) {
    	        tr.removeClass("selected");
    	    } else {
    	        tr.addClass("selected");
    	    }
    	});
    }); 
}

function editar(){
	var cantSeleccionados = 0;
	var currentRow = null;
	$('#tablaMedicos tr.selected').each(function() {
		cantSeleccionados = cantSeleccionados + 1;
		currentRow = $(this);
	});
	
	if (cantSeleccionados > 1){
		alert("Debe seleccionar sólo un médico para editar.")
	}else{
	   var idMedico = currentRow.find("td:eq(0)").text();
		var dni = currentRow.find("td:eq(3)").text();
		var matriculaNacional = currentRow.find("td:eq(4)").text();
		var nombre = currentRow.find("td:eq(2)").text();
		var apellido = currentRow.find("td:eq(1)").text();
		  
	    $(".modal-body #idMedicoE").val(idMedico);
		$(".modal-body #dniE").val(dni);
		$(".modal-body #matNacE").val(matriculaNacional);
		$(".modal-body #nombreE").val(nombre);
		$(".modal-body #apellidoE").val(apellido);
		$('#editarModal').show();
	}
}

function eliminar(){
	var cantSeleccionados = 0;
	var currentRow = null;
	$('#tablaMedicos tr.selected').each(function() {
		cantSeleccionados = cantSeleccionados + 1;
		currentRow = $(this);
	});
	
	if (cantSeleccionados > 1){
		alert("Debe seleccionar sólo un médico para eliminar.")
	}else{
	    var idMedico = currentRow.find("td:eq(0)").text();
	    $(".modal-body #idMedicoD").val(idMedico);
		$('#eliminarModal').show();
	}
}