$(document).ready(function(){
    $(document).on("click", ".edit", function () {
        var currentRow = $(this).closest("tr"); 
        var idEst = currentRow.find("td:eq(0)").text();
        var nombreEst = currentRow.find("td:eq(1)").text();
        var capacidadEst = currentRow.find("td:eq(2)").text();
        var idSanatorioEst = currentRow.find("td:eq(3)").text();
        var idEstadoEst = currentRow.find("td:eq(4)").text();

        $(".modal-body #idEstEdit").val(idEst);
        $(".modal-body #nombreEstEdit").val(nombreEst);
        $(".modal-body #capacidadEstEdit").val(capacidadEst);

        $(".modal-body #idSanatorioEst option").filter(function() {
            return this.text == idSanatorioEst; 
        }).attr('selected', true);

        $(".modal-body #idEstadoEst option").filter(function() {
            return this.text == idEstadoEst; 
        }).attr('selected', true);
    });

    $(document).on("click", ".delete", function () {
        var currentRow = $(this).closest("tr"); 
        var idEst = currentRow.find("td:eq(0)").text();
        $(".modal-body #idEstE").val(idEst);
    });
    
    $("#nombreEst").keypress(function (event) {
        var inputValue = event.which;
        if (!(inputValue >= 65 && inputValue <= 123) && (inputValue != 32 && inputValue != 0) && (inputValue != 47 && inputValue != 8) && (inputValue != 9)){ event.preventDefault(); }
    });
    
    $("#nombreEstEdit").keypress(function (event) {
        var inputValue = event.which;
        if (!(inputValue >= 65 && inputValue <= 123) && (inputValue != 32 && inputValue != 0) && (inputValue != 47 && inputValue != 8) && (inputValue != 9)){ event.preventDefault(); }
    });
    
    $("#capacidadEst").inputFilter(function(value) { return /^\d*$/.test(value); });
    $("#capacidadEstEdit").inputFilter(function(value) { return /^\d*$/.test(value); });

});

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
   