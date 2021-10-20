$(document).ready(function(){
    $(document).on("click", ".edit", function () {
        var currentRow = $(this).closest("tr"); 
        var idEstado = currentRow.find("td:eq(0)").text();
        var descEstado = currentRow.find("td:eq(1)").text();
        $(".modal-body #idEstado").val(idEstado);
        $(".modal-body #descEstadoEdit").val(descEstado);
    });

    $(document).on("click", ".delete", function () {
        var currentRow = $(this).closest("tr"); 
        var idEstado = currentRow.find("td:eq(0)").text();
        $(".modal-body #idEstadoE").val(idEstado);
    });
    
    $("#descEstado").keypress(function (event) {
        var inputValue = event.which;
        if (!(inputValue >= 65 && inputValue <= 123) && (inputValue != 32 && inputValue != 0) && (inputValue != 47 && inputValue != 8) && (inputValue != 9)){ event.preventDefault(); }
    });
    
    $("#descEstadoEdit").keypress(function (event) {
        var inputValue = event.which;
        if (!(inputValue >= 65 && inputValue <= 123) && (inputValue != 32 && inputValue != 0) && (inputValue != 47 && inputValue != 8) && (inputValue != 9)){ event.preventDefault(); }
    });
});