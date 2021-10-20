function fnExcelReport()
{
    var tab_text="<table border='2px'><tr bgcolor='#87AFC6'>";
    var textRange; var j=0;
    tab = document.getElementById('tablaLogs'); // id of table

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
	
    return "SMGPARKING_HISTORIAL_" + dateTime;
}