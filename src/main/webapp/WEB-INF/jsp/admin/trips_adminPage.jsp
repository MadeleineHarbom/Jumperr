<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List,model.*,storage.Storage"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

  <!-- Bootstrap CSS -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

  <!-- our CSS -->
  <link rel="stylesheet" type="text/css" href="../css/ourStyles.css"> 
    
  <title>Trips</title>
</head>
<body>

<div class="container">

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
                        Profile
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
            <h1>Trips</h1>
            
            <!-- Hi <brugernavn> -->
            <p>Hi ${username}!</p>
            
            <br/>
            <br/>
            
            <!-- tabel med brugere i systemet -->
            <table class="table table-striped table-responsive">
                <thead>
                  <tr>
                        <th>Day</th>
                        <th>Departure</th>
                        <th>Arrival</th>
                        <th>From</th>
                        <th>To</th>
                        <th>Driver</th>
                             
                  </tr>
                </thead>
                <tbody>
                  <% for(Trip t : Storage.getTrips()) { %>
                        
                         <tr>
                           <td><%= t.getDate()    %></td>
                           <td><%= t.getTimeOfDeparture()   %></td>
                           <td><%= t.getTimeOfArrival()   %></td>
                           <td><%= t.getDepartureAddress()   %></td>
                           <td><%= t.getArrivalAddress()   %></td>
                           <td><%= t.getDriver().getName()   %></td>
                           
                           <td><a href="/Update?userId=<%= t.getId() %>">Edit</a></td>
                           <td><a href="/Delete?userId=<%= t.getId() %>">Delete</a></td>
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