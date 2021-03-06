<%@page import="entidades.Persona"%>
<%@page import="logica.ControladorDePersona"%>
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
    <title>Agregar Persona</title>
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
	                      <a class="breadcrumb-item" href="#">Persona</a>
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
            <form class="formulario" action="ConfirmarAltaPersona" method="post">
            	<div class="tituloFormulario">
            		<h3>Alta de Persona</h3>
            	</div>
            	<div class="form-group row">
				    <label class="col-2 col-form-label">DNI</label>
				    <div class="col-10">
				    	<input type="text" class="form-control" name="personaDni" aria-describedby="dniHelp" placeholder="Ingrese DNI" required>
				    	<small id="dnilHelp" class="form-text text-muted">Ingrese su número de documento.</small>
					</div>
				</div>
				<div class="form-group row">
				    <label class="col-2 col-form-label">Nombre</label>
				    <div class="col-10">
				    	<input type="text" class="form-control" name="personaNombre" aria-describedby="nombreHelp" placeholder="Ingrese Nombre" required>
				    	<small id="nombreHelp" class="form-text text-muted">Ingrese su nombre completo.</small>
					</div>
				</div>
				<div class="form-group row">
				    <label class="col-2 col-form-label">Apellido</label>
				    <div class="col-10">
				    	<input type="text" class="form-control" name="personaApellido" aria-describedby="apellidoHelp" placeholder="Ingrese Apellido" required>
				    	<small id="apellidoHelp" class="form-text text-muted">Ingrese su apellido.</small>
					</div>
				</div>
				<div class="form-group row">
				    <label class="col-2 col-form-label">Usuario</label>
				    <div class="col-10">
					    <input type="text" class="form-control" name="personaUsuario" aria-describedby="usuarioHelp" placeholder="Ingrese usuario" required>
					    <small id="usuarioHelp" class="form-text text-muted">Ingrese el usuario que luego utilizará para ingresar al sistema.</small>
					</div>
				</div>
				<div class="form-group row">
				  <label class="col-2 col-form-label">Contraseña</label>
				  <div class="col-10">
				    <input class="form-control" type="password" name="personaPass" required>
				    <small id="usuarioHelp" class="form-text text-muted">Ingrese una contraseña que luego utilizará para ingresar al sistema.</small>
				  </div>
				</div>
				<div class="form-group row">
				    <label class="col-2 col-form-label">Categoria</label>
				    <div class="col-10">
					    <select multiple class="form-control" name="personaCategoria" aria-describedby="categoriaHelp" required>
					      <option>Administrador</option>
					      <option>Encargado</option>
					      <option>Online</option>
					    </select>
					    <small id="categoriaHelp" class="form-text text-muted">Seleccione la categoria de la persona.</small>
				    </div>
				  </div>
				  <div class="form-group row">
				    <label class="col-2 col-form-label">Habilitado</label>
				    <div class="col-10">
				      <input type="checkbox" class="form-check-input" value="1" name="personaHabilitado" aria-describedby="habilitadoHelp" checked>
				      <small id="habilitadoHelp" class="form-text text-muted">Tilde esta opción si la persona esta habilitada.</small>
				    </div>
				  </div>
				
				<div class="botones">
					<input type="submit" name="guardarPersona" value="Guardar" class="btn btn-primary">
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
     </div>
    <!-- jQuery first, then Tether, then Bootstrap JS. -->
    <script src="js/jquery-3.2.1.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.1.1.slim.min.js" integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
    <script src="js/bootstrap.js" crossorigin="anonymous"></script>
    <script src="js/main.js"></script>
</body>
</html>