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
	if(hh < 10) {
		hh = '0'+hh;
	}
	if(mm<10) {
		mm = '0'+mm;
	}
	
	let today = new Date();
	let dd = today.getDate();
	let mmo = today.getMonth()+1; //January is 0!
	let yyyy = today.getFullYear();
	 if(dd<10){
	        dd='0'+dd
	    } 
    if(mmo<10){
        mmo='0'+mmo
    } 
	    
    today = yyyy+'-'+mmo+'-'+dd;

	let localTime = hh+':'+mm;
	
	// hvis datoen er sat til dagens dato må afgangstidspunkt ikke sættes før det nuværende tidspunkt
	if(document.querySelector("#timefield1") != null) {
		if(document.querySelector("#datefield") != null) {
			document.querySelector("#datefield").addEventListener("input", function() {
				if(document.querySelector("#datefield").value == today) {
					document.querySelector("#timefield1").setAttribute("min", localTime);
				} else if(document.querySelector("#datefield").value != today) {
					document.querySelector("#timefield1").removeAttribute("min");
				}
			});			
		}		
	}
}

//sætter ankomsttidspunkt-feltet's min-attribut til afgangstidspunkt
function setMinimumTime2() {
	let d = new Date();
	let hh = d.getHours();
	let mm = d.getMinutes();
	if(hh < 10) {
		hh = '0'+hh;
	}
	if(mm<10) {
		mm = '0'+mm;
	}

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
	
    // viser en spinner i en Modal i 3 sekunder, derefter forsvinder den og formularen sender data afsted   
    $('form').submit( function(event) {
        var formId = this.id,
            form = this;
        $('.modal').modal('show');

        event.preventDefault();

        setTimeout( function () { 
            form.submit();
        }, 3000);
    }); 
}

// autocomplete funktionalitet for søgning på adresser i Jumper-siden
function autocomplete(inp, arr) {
	  /*the autocomplete function takes two arguments,
	  the text field element and an array of possible autocompleted values:*/
	  var currentFocus;
	  /*execute a function when someone writes in the text field:*/
	  inp.addEventListener("input", function(e) {
	      var a, b, i, val = this.value;
	      /*close any already open lists of autocompleted values*/
	      closeAllLists();
	      if (!val) { return false;}
	      currentFocus = -1;
	      /*create a DIV element that will contain the items (values):*/
	      a = document.createElement("DIV");
	      a.setAttribute("id", this.id + "autocomplete-list");
	      a.setAttribute("class", "autocomplete-items");
	      /*append the DIV element as a child of the autocomplete container:*/
	      this.parentNode.appendChild(a);
	      /*for each item in the array...*/
	      for (i = 0; i < arr.length; i++) {
	        /*check if the item starts with the same letters as the text field value:*/
	        if (arr[i].substr(0, val.length).toUpperCase() == val.toUpperCase()) {
	          /*create a DIV element for each matching element:*/
	          b = document.createElement("DIV");
	          /*make the matching letters bold:*/
	          b.innerHTML = "<strong>" + arr[i].substr(0, val.length) + "</strong>";
	          b.innerHTML += arr[i].substr(val.length);
	          /*insert a input field that will hold the current array item's value:*/
	          b.innerHTML += "<input type='hidden' value='" + arr[i] + "'>";
	          /*execute a function when someone clicks on the item value (DIV element):*/
	              b.addEventListener("click", function(e) {
	              /*insert the value for the autocomplete text field:*/
	              inp.value = this.getElementsByTagName("input")[0].value;
	              /*close the list of autocompleted values,
	              (or any other open lists of autocompleted values:*/
	              closeAllLists();
	          });
	          a.appendChild(b);
	        }
	      }
	  });
	  /*execute a function presses a key on the keyboard:*/
	  inp.addEventListener("keydown", function(e) {
	      var x = document.getElementById(this.id + "autocomplete-list");
	      if (x) x = x.getElementsByTagName("div");
	      if (e.keyCode == 40) {
	        /*If the arrow DOWN key is pressed,
	        increase the currentFocus variable:*/
	        currentFocus++;
	        /*and and make the current item more visible:*/
	        addActive(x);
	      } else if (e.keyCode == 38) { //up
	        /*If the arrow UP key is pressed,
	        decrease the currentFocus variable:*/
	        currentFocus--;
	        /*and and make the current item more visible:*/
	        addActive(x);
	      } else if (e.keyCode == 13) {
	        /*If the ENTER key is pressed, prevent the form from being submitted,*/
	        e.preventDefault();
	        if (currentFocus > -1) {
	          /*and simulate a click on the "active" item:*/
	          if (x) x[currentFocus].click();
	        }
	      }
	  });
	  function addActive(x) {
	    /*a function to classify an item as "active":*/
	    if (!x) return false;
	    /*start by removing the "active" class on all items:*/
	    removeActive(x);
	    if (currentFocus >= x.length) currentFocus = 0;
	    if (currentFocus < 0) currentFocus = (x.length - 1);
	    /*add class "autocomplete-active":*/
	    x[currentFocus].classList.add("autocomplete-active");
	  }
	  function removeActive(x) {
	    /*a function to remove the "active" class from all autocomplete items:*/
	    for (var i = 0; i < x.length; i++) {
	      x[i].classList.remove("autocomplete-active");
	    }
	  }
	  function closeAllLists(elmnt) {
	    /*close all autocomplete lists in the document,
	    except the one passed as an argument:*/
	    var x = document.getElementsByClassName("autocomplete-items");
	    for (var i = 0; i < x.length; i++) {
	      if (elmnt != x[i] && elmnt != inp) {
	      x[i].parentNode.removeChild(x[i]);
	    }
	  }
	}
	/*execute a function when someone clicks in the document:*/
	document.addEventListener("click", function (e) {
	    closeAllLists(e.target);
	});
} 

setMinimumDate();
setMinimumTime1();
// setMinimumTime2();
stripCharactersExceptNumbers();
showError();
enableTooltips();
searchForUser();
searchForTrip();
clickOnFirstPickUpPointWhenOpeningModalForTrip();

/* autocomplete-test */
let countries = ["Denmark","Sweden","Norway"];
let input1 = document.querySelector("#fromAddress");
let input2 = document.querySelector("#toAddress");
autocomplete(input1, countries);
autocomplete(input2, countries);