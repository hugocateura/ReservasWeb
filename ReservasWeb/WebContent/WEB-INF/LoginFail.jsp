<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
   <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/bootstrap.css" type="text/css">
    <link rel="stylesheet" href="css/estilo.css" type="text/css">
    <link rel="stylesheet" href="font-awesome/css/font-awesome.min.css">
    <link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">
    <link rel="icon" href="assets/icono.ico">
    <script src="style/ie-emulation-modes-warning.js"></script>
    <title>Ingreso de usuario</title>
  </head>

  <body>
  	<div class="container">
  		
	    <div class="container login">
	      <form class="form-signin" name="signin" action="Start" method="post">
	        <h2 class="form-signin-heading"><img src="assets/icono.PNG" width="30" height="30" class="d-inline-block align-top" alt="">  LOGIN</h2>
	        
	        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
	            <div class="input-group-addon iconoLogin"><i class="fa fa-user" aria-hidden="true"></i></div>
		        <input name="user" id="inputUser" class="form-control" placeholder="Usuario" required="" autofocus="" type="">
	        </div>
		
		    <div class="input-group mb-2 mr-sm-2 mb-sm-0">
			    <div class="input-group-addon iconoLogin"><i class="fa fa-unlock" aria-hidden="true"></i></div>
			    <input name="pass" id="inputPass" class="form-control" placeholder="Contraseña" required="" type="Password">
			</div>
		    <button class="btn btn-lg btn-primary btn-block" type="submit">Ingresar</button>
	      </form>
	    </div>
	    
	    <br/>
	    	
	    <div class="row">
	    	<div class="col-2"></div>
	    	<div class="col-7 alert alert-danger confirmacion"><%=(String)session.getAttribute("mensaje")%></div>
	    	<div class="col-3"></div>
	    </div>
	    
    </div>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="style/ie10-viewport-bug-workaround.js"></script>
    <script src="js/jquery-3.2.1.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.1.1.slim.min.js" integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
    <script src="js/bootstrap.js" crossorigin="anonymous"></script>
    <script src="js/main.js"></script>
</body>
</html>