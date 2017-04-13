$(document).ready(function() {
        $("#button1").click(function(event){
             var name = $('#userName').val();
        $.get('GetUser', {
                userName : name
        }, function(responseText) {
        	 var obj = jQuery.parseJSON(responseText);
        	 if (obj) {
                 //  var dd = JSON.parse(result);
                 alert(obj.completness)
             }
                $('#ajaxGetUserResponse').text(obj.completness);
        });
        });
        	
});
