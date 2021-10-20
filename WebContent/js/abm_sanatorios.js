$(document).ready(function(){
    $(document).on("click", ".edit", function () {
        var currentRow = $(this).closest("tr"); 
        var idSanatorio = currentRow.find("td:eq(0)").text();
        var nomSanatorio = currentRow.find("td:eq(1)").text();
        $(".modal-body #idSanatorio").val(idSanatorio);
        $(".modal-body #nomSanatorio").val(nomSanatorio);
    });

    $(document).on("click", ".delete", function () {
        var currentRow = $(this).closest("tr"); 
        var idSanatorio = currentRow.find("td:eq(0)").text();
        $(".modal-body #idSanatorioE").val(idSanatorio);
    });
});