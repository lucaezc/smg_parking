$(document).ready(function(){
    $(":checkbox").on("click", function(){
        var checkedValues = $(":checkbox:checked").map(function() {
            return this.value;
        }).get();
        
        var filtros = "";
        for (var i = 0; i < checkedValues.length; i++) {
        	filtros = checkedValues[i] + " " + filtros;
        }
        $('#sanatoriosAdd').val(filtros);
    });
    
    $(document).on("click", ".delete", function () {
        var currentRow = $(this).closest("tr"); 
        var idUsuario = currentRow.find("td:eq(0)").text();
        $(".modal-body #idUsuarioE").val(idUsuario);
    });
    
    $("#passwordCheckAdd").change(function() {
    	var pwd = $('#passwordAdd').val();
    	var pwdCheck = $('#passwordCheckAdd').val();
    	if (pwd != pwdCheck){
    		$('#passwordAdd').val("");
    		$('#passwordAdd').focus();
    		$('#passwordCheckAdd').val("");
    		$("#passwordError").text("Las contraseñas ingresadas no coinciden.");
    	}else{
    		$("#passwordError").text("");
    	}
	});
    
    $("#nombreAdd").keypress(function (event) {
        var inputValue = event.which;
        if (!(inputValue >= 65 && inputValue <= 123) && (inputValue != 32 && inputValue != 0) && (inputValue != 47 && inputValue != 8) && (inputValue != 9)){ event.preventDefault(); }
    });
    
    $("#apellidoAdd").keypress(function (event) {
        var inputValue = event.which;
        if (!(inputValue >= 65 && inputValue <= 123) && (inputValue != 32 && inputValue != 0) && (inputValue != 47 && inputValue != 8) && (inputValue != 9)){ event.preventDefault(); }
    });
    
    $('#documentoAdd').mask('00000000');
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