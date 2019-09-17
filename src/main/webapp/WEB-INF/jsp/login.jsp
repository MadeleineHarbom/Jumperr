<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

  <!-- Bootstrap CSS -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

  <!-- our CSS -->
  <link rel="stylesheet" type="text/css" href="../css/ourStyles.css"> 
  
  <title>Login page</title>
</head>
<body>

    <!-- container/grid der indeholder alle elementerne i body-tagget -->           
    <div class="container">
    
        <!-- navigations-menu -->
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <a class="navbar-brand" href="#">Jumperr</a>
            
            <!-- �ndrer udseendet p� navigations-menuen p� en mobil-device -->
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent">
                <span class="navbar-toggler-icon"></span>
            </button>           
            
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav ml-auto">
                
                    <!--  Home -->
                    <li class="nav-item">
                        <a class="nav-link active" href="/">Home</a>
                    </li>
                    
	                <!--  Register user -->
	                <li class="nav-item">
	                    <a class="nav-link" href="/CreateUser">Register</a>
	                </li>                      
                    
                    <!--  About - den har en dropdown-menu -->
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" data-toggle="dropdown">
                            About
                        </a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="#">About us</a>
                            <a class="dropdown-item" href="#">FAQ</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="#">Contact us</a>
                        </div>
                    </li>
                    
                </ul>
            </div>
        </nav>         
    
        <!-- Jumbotron - det er en form for header eller en udvidet header (hero-section) -->
        <div class="jumbotron"> 
        
            <h1>Jumperr, a modern way of traveling!</h1>
            
            <p class="lead">
                Jumperr utilizes the capacity of cars through a simple matchmaking platform -
                A hybrid solution between GoMore and TravellingCard, which is 40% faster transport at competitive prices
            </p>
            
            <hr class="my-5">                      
 
            <!-- login-formular -->		   		  
	        <main class="my-form">
	            <div class="container col-md-8">
	                <div class="row justify-content-center">
	                    <div class="col-md-10">
                            <!-- fejl meddelelse -->
                            <div id="error" class="alert alert-danger" role="alert">${error}</div>  
	                            <div class="card">
	                                <div class="card-header">Login</div>
	                                <div class="card-body">
	                                    <form name="my-form" action="/" method="post">
	        
	                                        <div class="form-group row">
	                                            <label for="username" class="col-md-4 col-form-label text-md-right">Username</label>
	                                            <div class="col-md-6">
	                                                <input type="text" id="username" class="form-control" name="username" required="true">
	                                            </div>
	                                        </div>
	        
	                                        <div class="form-group row">
	                                            <label for="password" class="col-md-4 col-form-label text-md-right">Password</label>
	                                            <div class="col-md-6">
	                                                <input type="text" id="password" class="form-control" name="password" required="true">
	                                            </div>
	                                        </div>                                   
	        
                                            <div class="col-md-6 offset-md-4">
                                                <button type="submit" class="btn btn-primary">
                                                Login
                                                </button>
                                            </div>	                                        
                                       </form>
	                                </div> <!-- card-body -->
                                </div> <!-- card -->
                            </div> <!-- yderste column -->
	                    </div> <!-- yderste row -->
	                </div>	<!-- container i login formularen -->         
	           </main> <!-- login formularen -->
           </div> <!-- jumbotron -->
     </div> <!-- yderste container -->
     
     <!-- hvis der er en fejlmeddelelse vises den med bootstrap ellers er den skjult -->
     <script> 
     if(document.querySelector("#error").innerHTML.trim() != ""){
         document.querySelector("#error").style.display = "block";
     } 
     </script>   

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

</body>
</html>