<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List,model.*,storage.LocalStorage"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

  <!-- Bootstrap CSS -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

  <!-- Bootstrap glyphicons (icons) -->
  <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    
  <!-- our CSS -->
  <link rel="stylesheet" type="text/css" href="../css/ourStyles.css"> 
  
  <!-- our JavaScript -->
  <script src="../js/ourScripts.js" defer></script>  
    
  <title>Trips</title>
</head>
<body>

<div id="trips_admin" class="container">

    <!-- navigations-menu -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <a class="navbar-brand" href="#">Jumperr</a>
        
        <!-- ændrer udseendet på navigations-menuen på en mobil-device -->
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent">
            <span class="navbar-toggler-icon"></span>
        </button>           
        
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ml-auto">
            
                <!--  Home -->
                <li class="nav-item">
                    <a class="nav-link" href="/">Home</a>
                </li>
                
                <!--  Trips -->
                <li class="nav-item">
                    <a class="nav-link active" href="/Trips">Trips</a>
                </li>                   
                
                <!--  Register user -->
                <li class="nav-item">
                    <a class="nav-link" href="/CreateUser">Register</a>
                </li>
                                   
                <!--  Jumper - (skal finde et lift)  -->
                <li class="nav-item">
                    <a class="nav-link" href="/Jumper">Jumper</a>
                </li>
                
                <!--  Driver - (skal tilbyde et lift) -->
                <li class="nav-item">
                    <a class="nav-link" href="/Driver">Driver</a>
                </li>                 
                
                <!--  About - den har en dropdown-menu -->
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" data-toggle="dropdown">
                        About
                    </a>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="/AboutUs">About us</a>
                        <a class="dropdown-item" href="/FAQ">FAQ</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="/ContactUs">Contact us</a>
                    </div>
                </li>
                
                <!--  Profile -->
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown2" data-toggle="dropdown">                            
                        <span class="fa fa-user"></span> Profile
                    </a>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="/MyProfile">My profile</a>
                        <a class="dropdown-item" href="/ProfileSettings">Settings</a>
                        
                        <div class="dropdown-divider"></div>
                        
                        <!--  Logout -->
                        <a class="dropdown-item" href="/Logout">Logout</a>
                    </div>
                </li>
                                  
            </ul>
        </div>
    </nav>     
    
        <!-- Jumbotron - det er en form for header eller en udvidet header (hero-section) -->
        <div class="jumbotron">

            <!-- add trip - åbner i et Modal -->
            <button class="btn btn-primary" data-toggle="modal" data-target="#createTrip">
                <span class="fa fa-plus"></span> Add trip
            </button>
            
            <!-- Modal -->
            <div class="modal fade" id="createTrip" role="dialog">
              
              <div class="modal-dialog modal-lg">
              
                <!-- Modal indhold -->
                <div class="modal-content">
                
                  <!-- Modal header -->
                  <div class="modal-header">
                    <h4 class="modal-title">Add trip</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>                   
                  </div>
                  
                  <!-- Modal body -->
                  <div class="modal-body">            
            
		            <!-- trip-formular -->               
		            <main class="my-form">
		                <div class="container col-md-10">
		                    <div class="row justify-content-center">
		                        <div class="col-md-12">
		                            <!-- fejl meddelelse -->
		                            <div id="error" class="alert alert-danger" role="alert">${error}</div>  
		                                <div class="card">
		                                    <div class="card-header">Trip</div>
		                                    <div class="card-body">
		                                        <form name="my-form" action="Driver" method="post">
		                                        
		                                            <div class="form-group row">
		                                                <label for="full_name" class="col-md-4 col-form-label text-md-right">Date</label>
		                                                <div class="col-md-6">
		                                                    <input type="date" id="datefield" class="form-control" name="date" required="true">
		                                                    <small id="errorDate" class="text-muted form-errors">The date should not be before today</small>
		                                                </div>
		                                            </div>
		            
		                                            <div class="form-group row">
		                                                <label for="email_address" class="col-md-4 col-form-label text-md-right">Time of departure</label>
		                                                <div class="col-md-6">
		                                                    <input type="time" id="timefield1" class="form-control" name="timeOfDeparture" required="true">
		                                                    <small id="errorTime1" class="text-muted form-errors">The time should not be before local time</small>
		                                                </div>
		                                            </div>
		            
		                                            <div class="form-group row">
		                                                <label for="address" class="col-md-4 col-form-label text-md-right">Time of arrival</label>
		                                                <div class="col-md-6">
		                                                    <input type="time" id="timefield2" class="form-control" name="timeOfArrival" required="true">
		                                                    <small id="errorTime2" class="text-muted form-errors">The time should not be before time of departure</small>
		                                                </div>
		                                            </div>
		            
		                                            <div class="form-group row">
		                                                <label for="phoneNumber" class="col-md-4 col-form-label text-md-right">Departure address</label>
		                                                <div class="col-md-6">
		                                                    <input type="text" id="phoneNumber" class="form-control" name="departureAddress" required="true">
		                                                </div>
		                                            </div>
		            
		                                            <div class="form-group row">
		                                                <label for="username" class="col-md-4 col-form-label text-md-right">Arrival address</label>
		                                                <div class="col-md-6">
		                                                    <input type="text" id="username" class="form-control" name="arrivalAddress" required="true">
		                                                </div>
		                                            </div>  
		                                            
		                                            <div class="form-group row">
		                                                <label for="username" class="col-md-4 col-form-label text-md-right">Available seats</label>
		                                                <div class="col-md-6">
		                                                    <input type="text" id="availableSeats" class="form-control" name="availableSeats" required="true">
		                                                </div>
		                                            </div>		                                                     
		            
                                                    <!-- Modal footer -->
                                                    <div class="modal-footer">                                                  
                                                        <div class="col-md-6 offset-md-4">                                                  
                                                            <button type="button" class="btn btn-default" data-dismiss="modal">
                                                            Close
                                                            </button>
                                                            
                                                            <button type="submit" class="btn btn-primary">
                                                            Add trip
                                                            </button>
                                                        </div>           
                                                   </div>   
		                                      </form>
		                                    </div> <!-- card-body -->
		                                </div> <!-- card -->
		                            </div> <!-- yderste column -->
		                        </div> <!-- yderste row -->
		                    </div>  <!-- container i login formularen -->         
		               </main> <!-- trip formularen -->      
                  </div> <!-- modal body slutter her -->
                  
                </div> <!-- modal indhold slutter her -->
                
              </div> <!-- modal dialog slutter her -->
            </div> <!-- modal vinduet slutter her -->		                     
            
            <!-- Search form -->
            <form class="form-inline mt-3 mb-5 inner-addon left-addon">
              <input class="form-control mr-3 w-20" type="text" placeholder="Search by driver"
                aria-label="Search" id="searchTrip">
              <span class="fa fa-search"></span>
            </form>            
            
            <!-- tabel med trips i systemet -->
            <table id="tripsTable" class="table table-striped table-responsive">
                <thead>
                  <tr class="crud-table text-white">
                        <th>ID</th>
                        <th>Day</th>
                        <th>Departure</th>
                        <th>Arrival</th>
                        <th>From</th>
                        <th>To</th>
                        <th>Driver</th>
                        <th>Available seats</th>
                        <th>PickUpPoints</th> 
                        <th>Actions</th>                            
                  </tr>
                </thead>
                <tbody>
                  <%
                      for(Trip t : LocalStorage.getTrips()) {
                  %>
                        
                        <tr>
                           <td><%= t.getId()   %></td>
                           <td><%= t.getDate()    %></td>
                           <td><%= t.getTimeOfDeparture()   %></td>
                           <td><%= t.getTimeOfArrival()   %></td>
                           <td><%= t.getDepartureAddress()   %></td>
                           <td><%= t.getArrivalAddress()   %></td>
                           <td><%= t.getDriver().getName()   %></td>
                           <td><%= t.getAvailableSeats()  %></td>
                           <td>
                                <select class="custom-select custom-select-sm">
                                    <option selected>PickUpPoints</option>
	                            <% if(t.getPickUpPoints().size() != 0) { %>																  
									    <option value="1">Jumper: <%= t.getPickUpPoints().get(0).getJumper().getName() %></option>
									    <option value="2">Departure address: <%= t.getPickUpPoints().get(0).getDepartureAddress() %></option>
									    <option value="3">Arrival address: <%= t.getPickUpPoints().get(0).getArrivalAddress() %></option>
									    <option value="4">Price: <%= t.getPickUpPoints().get(0).getPrice() %></option>
									    <option value="5">Km: <%= t.getPickUpPoints().get(0).getKm() %></option>								
	                            <% } %>
                                </select>
                           </td>
                           <td class="actionsColumn">
                               <a href="/Update?tripId=<%= t.getId() %>" data-toggle="tooltip" data-placement="top" title="Edit" class="btn btn-success">
                                   <span class="fa fa-edit"></span> 
                               </a>
                               <a href="/Delete?tripId=<%= t.getId() %>" data-toggle="tooltip" data-placement="top" title="Delete" class="btn btn-danger">
                                   <span class="fa fa-trash"></span> 
                               </a>
                           </td>                                                     
                        </tr>        
                     <% } %>
                </tbody>
            </table>              
        </div>     

    </div>


    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>    

</body>
</html>