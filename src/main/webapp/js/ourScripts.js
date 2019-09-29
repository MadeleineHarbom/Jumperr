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
	    if(document.querySelector("#error").innerHTML.trim().length > 71) {
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

// kaldes når man klikker på en pick-up-point i modal-vinduet under trips
function showPickUpPointInfo(pickUpPoint_ID, element) {
	if(document.querySelector(".pickUpPoint_textarea") != null) {
		let pickUpPoint_textarea = document.querySelector(".pickUpPoint_textarea");
		if(pickUpPoint_textarea.classList.contains("d-none")) {
			pickUpPoint_textarea.classList.remove("d-none");
			if(pickUpPoint_textarea.innerHTML != "") {
				pickUpPoint_textarea.innerHTML = "";				
			}
		}
		
		let pickUpPointInfo = document.querySelector("#pickUpPoint" + pickUpPoint_ID + "_info");
		let pickUpPointTextareas = document.querySelectorAll(".pickUpPoint_textarea");		
		
		for (let i = 0; i < pickUpPointTextareas.length; i++) {
			pickUpPointTextareas[i].innerHTML = pickUpPointInfo.innerHTML;
		}
		
		let pickUpPointLinks = document.querySelectorAll('.showPickUpPointLinks');
		for (let i = 0; i < pickUpPointLinks.length; i++) {
			if(pickUpPointLinks[i].classList.contains("active")) {
				pickUpPointLinks[i].classList.remove("active");
			}
		}
		
		element.classList.add("active");
		
		let removePickUpPoints = document.querySelectorAll("#removePickUpPoint");
		for (let i = 0; i < removePickUpPoints.length; i++) {
			removePickUpPoints[i].href = "/Delete?pickuppointId="+pickUpPoint_ID;
		}
		
	}
}

// når man åbner Modal for at se pick-up-points for den enkelte trip fra Trips-tabellen 
// skal der klikkes på den første pick-up-point hvis der er nogle pick-up-points (for at vise dens detaljer)
function clickOnFirstPickUpPointWhenOpeningModalForTrip() {
	if(document.querySelectorAll('*[id^="modalForTrip"]') != null) {
		let pickUpPointModals = document.querySelectorAll('*[id^="modalForTrip"]');
		
		for (let i = 0; i < pickUpPointModals.length; i++) {
			$(pickUpPointModals[i]).on('shown.bs.modal', function(e) {
				let firstLink = "#" + e.target.id + ' a.showPickUpPointLinks:first';
				$(firstLink).trigger('click');
			})
		}
	}
}

// viser en spinner når man som Jumper søger efter trips - derefter vises resultaterne
function searchForTrips_jumper() {
	
    // viser en div i 0,5 sekund derefter fader den ud
    let spinner = document.querySelector("#spinner");

    spinner.classList.remove("d-none");
    spinner.style.opacity = 1;

    setTimeout(function(){
        let op = 1;  // initial opacity
        let timer = setInterval(function () {
            if (op <= 0.1){
                clearInterval(timer);
                spinner.style.display = 'none';
            }
            spinner.style.opacity = op;
            spinner.style.filter = 'alpha(opacity=' + op * 100 + ")";
            op -= op * 0.1;
        }, 50);
    }, 2000);  
}

setMinimumDate();
setMinimumTime1();
setMinimumTime2();
stripCharactersExceptNumbers();
showError();
enableTooltips();
searchForUser();
searchForTrip();
clickOnFirstPickUpPointWhenOpeningModalForTrip();