$(document).ready(function(){
    $(document).on("click", ".autorize", function () {
        var currentRow = $(this).closest("tr"); 
        var idAutorizacion = currentRow.find("td:eq(5)").text();
        var idTicket = currentRow.find("td:eq(6)").text();
        $(".modal-body #idAut").val(idAutorizacion);
        $(".modal-body #idTicket").val(idTicket);
    });
});