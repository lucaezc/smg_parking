$(document).ready(function(){
    $(document).on("click", ".edit", function () {
        var currentRow = $(this).closest("tr"); 
        var idPerfil = currentRow.find("td:eq(0)").text();
        var nomPerfil = currentRow.find("td:eq(1)").text();
        var permisos = currentRow.find("td:eq(2)").text();
        var admin = currentRow.find("td:eq(3)").text();
        var coordinador = currentRow.find("td:eq(4)").text();
        var valet = currentRow.find("td:eq(5)").text();

        $(".modal-body #idPerfil").val(idPerfil);
        $(".modal-body #nomPerfil").val(nomPerfil);
        if (admin == 'S'){
            $("#per_Admin").prop("checked", true);
        }else{
        	if (coordinador == 'S'){
                $("#per_Coordinador").prop("checked", true);
            }else{
            	if (valet == 'S'){
                    $("#per_Valet").prop("checked", true);
                }
            }
        }
    });

    $(document).on("click", ".delete", function () {
        var currentRow = $(this).closest("tr"); 
        var idPerfil = currentRow.find("td:eq(0)").text();
        $(".modal-body #idPerfilE").val(idPerfil);
    });
});