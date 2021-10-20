$(document).ready(function(){
	$( "#inputUsuario" ).change(function() {
		var user = $("#inputUsuario").val();  
		var $selectSanatorios = $("#inputSanatorio");

        $.get('/SMG_PARKING/servlets/GetSanatoriosUsuario', {
            usuario : user
	    }, function(data) {

	    	$selectSanatorios.empty();
			var c = 0;
	        $.each(data, function(index, item) {
	            c++;
	        });
	    	if (c > 1){
	            $selectSanatorios.append($("<option disabled selected/>").val("").text("Seleccionar Sanatorio"));
	    	}
	    	
	        $.each(data, function(index, item) {
	            $selectSanatorios.append($("<option />").val(item.id).text(item.nombre));
	        });
	    });
	});
})