// sætter dato-feltet's min-attribut til dagens dato
function setMinimumDate() {
	let today = new Date();
	let dd = today.getDate();
	let mm = today.getMonth()+1; //January is 0!
	let yyyy = today.getFullYear();
	 if(dd<10){
	        dd='0'+dd
	    } 
	    if(mm<10){
	        mm='0'+mm
	    } 

	today = yyyy+'-'+mm+'-'+dd;
	
	if(document.querySelector("#datefield") != null) {
		document.querySelector("#datefield").setAttribute("min", today);
	}
}

// sætter afgangstidspunkt-feltet's min-attribut til lokaltid
function setMinimumTime1() {
	let d = new Date();
	let hh = d.getHours();
	let mm = d.getMinutes();

	let localTime = hh+':'+mm;
	if(document.querySelector("#timefield1") != null) {
		document.querySelector("#timefield1").setAttribute("min", localTime);
	}
}

//sætter ankomsttidspunkt-feltet's min-attribut til afgangstidspunkt
function setMinimumTime2() {
	let d = new Date();
	let hh = d.getHours();
	let mm = d.getMinutes();

	let localTime = hh+':'+mm;
	// hvis afgangstidspunkt er sat, sættes den mindste tilgængelig værdi for ankomsttidspunkt til
	// afgangstidspunktet ellers sættes den til det nuværende tidspunkt
	if(document.querySelector("#timefield1") != null) {
		document.querySelector("#timefield1").addEventListener("keyup", function() {
			if(document.querySelector("#timefield1").value != "") {
				if(document.querySelector("#timefield2") != null) {
					document.querySelector("#timefield2").setAttribute("min", document.querySelector("#timefield1").value);
				}
			} else {
				document.querySelector("#timefield2").setAttribute("min", localTime);
			}
		});
	}
}

// fjerner alle karakterer fra telefonnummer-feltet undtagen numre
function stripCharactersExceptNumbers() {
	if(document.querySelector("#phone") != null) {
		document.querySelector("#phone").addEventListener("keyup", function() {
			let phoneInputField = document.querySelector("#phone");
		    let newString = phoneInputField.value.replace(/[^0-9]/, "");
	
		    phoneInputField.value = newString;
		    
		});
	}
}

// hvis der er en fejlmeddelelse på loginsiden vises den med bootstrap ellers er den skjult
function showError() {
	
	if(document.querySelector("#error") != null) {
	    if(document.querySelector("#error").innerHTML.trim() != ""){
	        document.querySelector("#error").style.display = "block";
	    } 
	}
}

// tænder for tooltips vha. jQuery
function enableTooltips() {
	$(document).ready(function(){
	    $('[data-toggle="tooltip"]').tooltip();
	});
}

// søger på brugere lige så snart man begynder at indtaste nogle bogstaver
function searchForUser() {
	if(document.querySelector("#searchUser") != null) {
		document.querySelector("#searchUser").addEventListener("keyup", function() {
			let input, filter, table, tr, td, i, txtValue;
			input = document.getElementById("searchUser");
			filter = input.value.toLowerCase();
			table = document.getElementById("usersTable");
			tr = table.getElementsByTagName("tr");
			
			for (i = 1; i < tr.length; i++) {
			  td = tr[i].getElementsByTagName("td")[5]; // username er på index 5
			  if(td) {
				  txtValue = td.textContent || td.innerText;
				  if (txtValue.toLowerCase().indexOf(filter) > -1) {
				    tr[i].style.display = "";
				  } else {
				    tr[i].style.display = "none";
				  }
			  }
			}
		});
	}
}

// søger på trips lige så snart man begynder at indtaste nogle bogstaver
function searchForTrip() {
	if(document.querySelector("#searchTrip") != null) {
		document.querySelector("#searchTrip").addEventListener("keyup", function() {
			let input, filter, table, tr, td, i, txtValue;
			input = document.getElementById("searchTrip");
			filter = input.value.toLowerCase();
			table = document.getElementById("tripsTable");
			tr = table.getElementsByTagName("tr");
			
			for (i = 1; i < tr.length; i++) {
			  td = tr[i].getElementsByTagName("td")[6]; // driver er på index 6
			  if(td) {
				  txtValue = td.textContent || td.innerText;
				  if (txtValue.toLowerCase().indexOf(filter) > -1) {
				    tr[i].style.display = "";
				  } else {
				    tr[i].style.display = "none";
				  }
			  }
			}
		});
	}
}

setMinimumDate();
setMinimumTime1();
setMinimumTime2();
stripCharactersExceptNumbers();
showError();
enableTooltips();
searchForUser();
searchForTrip();