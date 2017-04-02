var serviceURL = "http://localhost:8080/Dashboard/rest/";

getAndmed();




function getAndmed() {
	
	$.ajax({
		type: 'GET',
		url: serviceURL + 'andmed',
		dataType: "text", 
		success: function( response ) {
		    console.log(response)
		    $('#andmed').append(response)
		},
	});
	
	
}