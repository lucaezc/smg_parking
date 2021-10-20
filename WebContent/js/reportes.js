$(document).ready(function(){
    var date_input=$('input[name="fechaDesde"]');
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
    
    var date_input1=$('input[name="fechaHasta"]');
    var container1=$('.bootstrap-iso form').length>0 ? $('.bootstrap-iso form').parent() : "body";
    var options1={
        format: 'dd/mm/yyyy',
        container: container1,
        todayHighlight: true,
        autoclose: true,
    };
    date_input1.datepicker(options1);
    
    date_input1.on('changeDate', function(ev){
        $(this).datepicker('hide');
    });
    
})

function filtros(){
    $('#filtros').toggle();
}

function fnExcelReport()
{
    var tab_text="<table border='2px'><tr bgcolor='#87AFC6'>";
    var textRange; var j=0;
    tab = document.getElementById('tablaReporte'); // id of table

    for(j = 0 ; j < tab.rows.length ; j++) 
    {     
        tab_text=tab_text+tab.rows[j].innerHTML+"</tr>";
    }

    tab_text=tab_text+"</table>";
    tab_text= tab_text.replace(/<A[^>]*>|<\/A>/g, "");//remove if u want links in your table
    tab_text= tab_text.replace(/<img[^>]*>/gi,""); // remove if u want images in your table
    tab_text= tab_text.replace(/<input[^>]*>|<\/input>/gi, ""); // removes input params

    var link = document.getElementById('link');

    link.href = 'data:application/vnd.ms-excel;base64,' + window.btoa(tab_text);
    
    var nombre = nombreArchivo();

    link.download = nombre;
    link.click();    
}

function buscar(){
	var fechaDesde = $('#fechaDesde').val();
	var fechaHasta = $('#fechaHasta').val();

    var checkedValues = $(":checkbox:checked").map(function() {
        return this.value;
    }).get();
    
    var filtros = "";
    for (var i = 0; i < checkedValues.length; i++) {
    	filtros = checkedValues[i] + " " + filtros;
    }
    $.get('/SMG_PARKING/servlets/GetTicketsFiltrados', {
    	fechaDesde : fechaDesde,
    	fechaHasta : fechaHasta,
    	filtros : filtros
    }, function(data) {
    	$('#tablaReporte tr').not(':first').remove();
    	var html = '';
    	for(var i = 0; i < data.length; i++){
    		if (typeof data[i].fechaModificacion === "undefined") { data[i].fechaModificacion = ""; }
    		if (typeof data[i].horaModificacion === "undefined") { data[i].horaModificacion = ""; }
    		if (typeof data[i].fechaCierre === "undefined") { data[i].fechaCierre = ""; }
    		if (typeof data[i].horaCierre === "undefined") { data[i].horaModificacion = ""; }
    		if (typeof data[i].fechaIngresoExcedido === "undefined") { data[i].fechaIngresoExcedido = ""; }
    		if (typeof data[i].horaIngresoExcedido === "undefined") { data[i].horaIngresoExcedido = "";	}
    		if (typeof data[i].fechaEgresoExcedido === "undefined") { data[i].fechaEgresoExcedido = "";	}
    		if (typeof data[i].horaEgresoExcedido === "undefined") { data[i].horaEgresoExcedido = ""; }  		
    		html += '<tr><td>' + data[i].id + '</td><td>' + data[i].fechaIngreso + '</td><td>' + data[i].horaIngreso + '</td><td>' + data[i].usuarioRegistro + '</td><td>' + data[i].patente + '</td><td>' + data[i].tipoVehiculo 
		       	 + '</td><td>' + data[i].tipoTicket + '</td><td>' + data[i].fechaEgreso + '</td><td>' + data[i].horaEgreso + '</td><td>' + data[i].estado + '</td><td>' + data[i].nombreCompleto
		       	 + '</td><td>' + data[i].dni + '</td><td>' + data[i].nombreEstacionamiento + '</td><td>' + data[i].usuarioCierre + '</td><td>' + data[i].fechaCierre + '</td><td>' + data[i].horaCierre
		       	 + '</td><td>' + data[i].facturadoExcedido + '</td><td>' + data[i].fechaIngresoExcedido + '</td><td>' + data[i].horaIngresoExcedido + '</td><td>' + data[i].fechaEgresoExcedido 
		       	 + '</td><td>' + data[i].horaEgresoExcedido + '</td></tr>';
    	}
    	$('#tablaReporte tr').first().after(html);
    }); 
}

function limpiarFiltros(){
	$('#filtro_tvAuto').prop('checked', false);
	$('#filtro_tvCamioneta').prop('checked', false);
	$('#filtro_tvCamionetaXL').prop('checked', false);
	$('#filtro_tvMoto').prop('checked', false);
	$('#filtro_tvBicicleta').prop('checked', false);
	
	$('#filtro_ttAbierto').prop('checked', false);
	$('#filtro_ttAutorizado').prop('checked', false);
	$('#filtro_ttPendienteAut').prop('checked', false);
	$('#filtro_ttAbiertoRechazado').prop('checked', false);
	$('#filtro_ttAutCerrado').prop('checked', false);
	$('#filtro_ttCerrado').prop('checked', false);
	$('#filtro_ttEliminado').prop('checked', false);

	$('#filtro_tiMedico').prop('checked', false);
	$('#filtro_tiPaciente').prop('checked', false);
	$('#filtro_tiAutorizado').prop('checked', false);
	$('#fechaDesde').val('');
	$('#fechaHasta').val('');
}

//function exportPDF() {
//	
//    var pdf = new jsPDF('l', 'pt', 'A4');
//    source = $('#tabla')[0];
//
//    specialElementHandlers = {
//        '#bypassme': function (element, renderer) {
//            return true
//        }
//    };
//    margins = {
//        top: 80,
//        bottom: 60,
//        left: 10,
//        width: 700
//    };
//
//    pdf.fromHTML(
//    source, // HTML string or DOM elem ref.
//    margins.left, // x coord
//    margins.top, { // y coord
//        'width': margins.width, // max width of content on PDF
//        'elementHandlers': specialElementHandlers
//    },
//
//    function (dispose) {
//    	var nombre = nombreArchivo() + ".pdf";
//        pdf.save(nombre);
//    }, margins);
//}

function nombreArchivo() {
    var today = new Date();
    var dia = today.getDate();
    var mes = today.getMonth() + 1;
    var anio = today.getFullYear();
    var hs = today.getHours();
    var min = today.getMinutes();
    var seg = today.getSeconds();
    if (hs < 10) { hs = '0' + hs; }
    if (dia < 10) { dia = '0' + dia; }
    if (mes < 10) { mes = '0' + mes; }
    if (min < 10) { min = '0' + min; }
    if (seg < 10) { seg = '0' + seg; }

    var date = anio + "-" + mes + "-" + dia;
    var time = hs + ":" + min + ":" + seg;
    var dateTime = date + " " + time;
	
    return "SMGPARKING_" + dateTime;
}