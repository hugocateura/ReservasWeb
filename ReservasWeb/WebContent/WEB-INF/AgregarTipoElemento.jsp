<%@page import="entidades.Persona"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/estilo.css" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">
    <link rel="stylesheet" href="font-awesome/css/font-awesome.min.css">
    <link rel="icon" href="assets/icono.ico">
    <title>Agregar Tipo de Elemento</title>
</head>
<body>
    <div class="container-fluid">
       <div class="row">
           <div class="col-12">
               <nav class="navbar navbar-expand-lg navbar-light bg-light cabecera">
                  <div class="collapse navbar-collapse" id="navbarText">
                      <div class="caja izquierda">
                          <a class="navbar-brand" href="Start"><img src="assets/icono.PNG" width="30" height="30" class="d-inline-block align-top" alt="">   SYSRES</a>
                      </div>
                      <a class="breadcrumb-item" href="Start"><i class="fa fa-home" aria-hidden="true"></i></a>
                      <a class="breadcrumb-item" href="#"><%=((Persona) session.getAttribute("user")).getCategoria()%></a>
                      <a class="breadcrumb-item" href="#">Tipo de Elemento</a>
                      <span class="breadcrumb-item active">Agregar</span>
                  </div>
                  <div class="caja derecha">
                      <p><i class="icono derecha fa fa-user" aria-hidden="true"></i> Logueado como <%=(((Persona)session.getAttribute("user")).getUsuario()+" con perfil "+((Persona)session.getAttribute("user")).getCategoria())%></p>
                  </div>
                  <form class="form-inline my-2 my-lg-0">
                      <a class="caja derecha salir" href="Login"><i class="icono izquierda fa fa-times-circle" aria-hidden="true"></i>SALIR</a>
                  </form>
              </nav>
           </div>
       
           <% if (((Persona)session.getAttribute("user")).getCategoria().equals("Online")){%>
	<jsp:include page="MenuUsuario.jsp" />
	<%} else if(((Persona)session.getAttribute("user")).getCategoria().equals("Encargado")){%>
	<jsp:include page="MenuEncargado.jsp" />
	<%} else if(((Persona)session.getAttribute("user")).getCategoria().equals("Administrador")){%>
	<jsp:include page="MenuAdmin.jsp" />
	<%} %>
		
            <div class="col-10 contenedor">
	            <form class="formulario" action="ConfirmarAltaTipoElemento" method="post">
		           	<div class="tituloFormulario" >
		           		<h3>Alta Tipo de Elemento</h3>
		           	</div>	
		           	
		           	<div class="form-group row">
					    <label class="col-2 col-form-label">Nombre</label>
					    <div class="col-10">
					    	<input type="text" class="form-control" name="tipoElementoNombre" aria-describedby="nombreHelp" placeholder="Ingrese Nombre" required>
					    	<small id="nombrelHelp" class="form-text text-muted">Ingrese el nombre del tipo de elemento.</small>
						</div>
					</div>
					
					<div class="form-group row">
					  <label class="col-2 col-form-label">Cant. máxima de reservas</label>
					  <div class="col-10">
					    <input class="form-control" type="number" name="tipoElementoCantMaxRes" id="example-number-input" aria-describedby="cantmaxHelp" placeholder="Ingrese la cantidad" required>
					    <small id="cantmaxlHelp" class="form-text text-muted">Seleccione la cantidad máxima de reservas.</small>
					  </div>
					</div>
					
					<div class="form-group row">
					  <label class="col-2 col-form-label">Cant. máxima de horas</label>
					  <div class="col-10">
					    <input class="form-control" type="number" name="tipoElementoCantMaxHs" id="example-number-input" aria-describedby="cantmaxhsHelp" placeholder="Ingrese la duración" required>
					    <small id="cantmaxhsHelp" class="form-text text-muted">Seleccione la cantidad máxima de horas que puede durar una reserva.</small>
					  </div>
					</div>
					
					<div class="form-group row">
					  <label class="col-2 col-form-label">Anticipación máxima</label>
					  <div class="col-10">
					    <input class="form-control" type="number" name="anticipacionMaxima" id="example-number-input" aria-describedby="anticipacionMaximaHelp" placeholder="Ingrese la cantidad" required>
					    <small id="anticipacionMaximalHelp" class="form-text text-muted">Seleccione máxima anticipación en días que puede tener una reserva.</small>
					  </div>
					</div>
					
				  	<div class="form-group row">
					   	<label class="col-2 col-form-label">Reserva Encargado</label>
				    	<div class="col-10">
				    		<input type="checkbox" class="form-check-input" value="1" name="reservaEncargado" aria-describedby="reservaEncargadoHelp">
				      		<small id="reservaEncargadoHelp" class="form-text text-muted">Tilde esta opción si el tipo de Elemento solo puede ser reservado por un encargado.</small>
				    	</div>
				  	</div>
					
					<div class="botones">
						<input type="submit" name="guardarElemento" value="Guardar" class="btn btn-primary">
					</div>
		          </form>
            </div>
            </div>
        </div>
       <div class="row footer">
           <div class="col-12">
            <nav class="navbar navbar-expand-lg navbar-light bg-light">
             <p class="footerTexto">Derechos reservados | SANKIP &copy;</p>
            </nav>
           </div>
        </div>
    <!-- jQuery first, then Tether, then Bootstrap JS. -->
    <script src="js/jquery-3.2.1.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.1.1.slim.min.js" integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
    <script src="js/bootstrap.js" crossorigin="anonymous"></script>
    <script src="js/main.js"></script>
    </body>
</html>