<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.List,model.*,storage.LocalStorage"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">

<!-- Bootstrap spinner vises i bootstrap version 4.2 -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">

<!-- Bootstrap glyphicons (icons) -->
<link
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet">

<!-- our CSS -->
<link rel="stylesheet" type="text/css" href="../css/ourStyles.css">
<link href="css/test2.css" rel="stylesheet" />

<!-- our JavaScript -->
<script src="../js/ourScripts.js" defer></script>

<title>Jumper</title>
</head>
<body>

	<div class="container">

		<!-- navigations-menu -->
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark"> <a
			class="navbar-brand" href="#">Jumperr</a> <!-- ændrer udseendet på navigations-menuen på en mobil-device -->
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav ml-auto">

				<!--  Home -->
				<li class="nav-item"><a class="nav-link" href="/">Home</a></li>

				<!--  Trips -->
				<li class="nav-item"><a class="nav-link" href="/Trips">Trips</a>
				</li>

				<!--  Register user -->
				<li class="nav-item"><a class="nav-link" href="/CreateUser">Register</a>
				</li>

				<!--  Jumper - (skal finde et lift)  -->
				<li class="nav-item"><a class="nav-link active" href="/Jumper">Jumper</a>
				</li>

				<!--  Driver - (skal tilbyde et lift) -->
				<li class="nav-item"><a class="nav-link" href="/Driver">Driver</a>
				</li>

				<!--  About - den har en dropdown-menu -->
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
					data-toggle="dropdown"> About </a>
					<div class="dropdown-menu">
						<a class="dropdown-item" href="/AboutUs">About us</a> <a
							class="dropdown-item" href="/FAQ">FAQ</a>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="/ContactUs">Contact us</a>
					</div></li>

				<!--  Profile -->
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navbarDropdown2"
					data-toggle="dropdown"> <span class="fa fa-user"></span>
						Profile
				</a>

					<div class="dropdown-menu">
						<a class="dropdown-item" href="/MyProfile">My profile</a> <a
							class="dropdown-item" href="/ProfileSettings">Settings</a>

						<div class="dropdown-divider"></div>
						<!--  Logout -->
						<a class="dropdown-item" href="/Logout">Logout</a>
					</div></li>
			</ul>
		</div>
		</nav>

		<div id="spinner" class="spinner-border spinner-border-lg d-none"></div>

		<!-- Jumbotron - det er en form for header eller en udvidet header (hero-section) -->
		<div class="jumbotron">

			
			<form>
				<h3>Search for a trip:</h3>
				<div class="row">
					<div class="col">
						<input type="text" class="form-control"
							placeholder="Departure Address">
					</div>
					<div class="col">
						<input type="text" class="form-control"
							placeholder="Time of departure">
					</div>
					<div class="col">
						<input type="text" class="form-control" placeholder="Post code">
					</div>

					<button class="btn btn-primary" onclick="searchForTrips_jumper()">Search</button>					
				</div>
			</form>
			

			
			<br> <br>
			<h4>Matches:</h4>


			<%
				for (Trip t : LocalStorage.getTrips()) {
			%>

			<div class="card bg-light mb-3">
				<div class="card-header bg-dark text-success ont-weight-bold""><%=t.getDriver().getName()%></div>
				<div class="card-body">

					<div class="row">
						<!-- Two columns -->
						<div class="col">
							<div class="card" style="width: 14rem;">
								<ul class="list-group list-group-flush">
									<li class="list-group-item">Date: <%=t.getDate()%></li>
									<li class="list-group-item">From: <%=t.getDepartureAddress()%></li>
									<li class="list-group-item">To: <%=t.getArrivalAddress()%></li>
									<li class="list-group-item">Departure: <%=t.getTimeOfDeparture()%></li>
									<li class="list-group-item">Arrival: <%=t.getTimeOfArrival()%></li>
								</ul>
							</div>
						</div>

						<div class="col-6">2 of 3 (wider)</div>


						<div class="col align-self-center">
							<div class="card bg-light mb-3 ">
								<div class="card-header text-center "><%=t.getDriver().getName()%>
								</div>
								<div class="card-body">


									<div class="col">
										<img class="rounded-circle mx-auto d-block"
											src="${pageContext.request.contextPath}/Images/blankUser3.png"
											style="width: 100px; height: 100px">
									</div>




								</div>
							</div>
						</div>
					</div>
					<!-- Row closes here -->
				</div>
				<!-- Card body closes here -->

				<div class="card-footer  "">Book a seat</div>
			</div>
			<%
				};
			%>



		</div>
		<!-- Jumbotron closes here -->

	</div>
	</div>

	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
		integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
		integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous"></script>
</body>
</html>