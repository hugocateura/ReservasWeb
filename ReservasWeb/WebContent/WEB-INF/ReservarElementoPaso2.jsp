<%@page import="entidades.Persona"%>
<%@page import="entidades.TipoElemento"%>
<%@page import="entidades.Elemento"%>
<%@page import="logica.ControladorDeElemento"%>
<%@page import="java.util.*"%>

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
    <title>Reservar Elemento</title>
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
                      <a class="breadcrumb-item" href="#">Reserva</a>
                      <a class="breadcrumb-item" href="#">Reservar Elemento</a>
                      <span class="breadcrumb-item active">Paso 2</span>
                  </div>
                  <div class="caja derecha">
                      <p><i class="icono derecha fa fa-user" aria-hidden="true"></i> Logueado como <%=(((Persona)session.getAttribute("user")).getUsuario()+" con perfil "+((Persona)session.getAttribute("user")).getCategoria())%></p>
                  </div>
                  <form class="form-inline my-2 my-lg-0">
                      <a class="caja derecha salir" href="Login"><i class="icono izquierda fa fa-times-circle" aria-hidden="true"></i>SALIR</a>
                  </form>
              </nav>
           </div>
       </div>
         <% if (((Persona)session.getAttribute("user")).getCategoria().equals("Online")){%>
	<jsp:include page="MenuUsuario.jsp" />
	<%} else if(((Persona)session.getAttribute("user")).getCategoria().equals("Encargado")){%>
	<jsp:include page="MenuEncargado.jsp" />
	<%} else if(((Persona)session.getAttribute("user")).getCategoria().equals("Administrador")){%>
	<jsp:include page="MenuAdmin.jsp" />
	<%} %>

            <div class="col-10 contenedor"> 
				<form class="formulario" action="ConfirmarReserva" method="post"> 
	                <div class="tituloFormularioRes">
	            		<h3>PASO 2: Seleccionar Elemento </h3>
	            	</div>
                   <div class="form-group row">          
					    <label class="col-2 col-form-label">Mostrando elementos del tipo <%=((TipoElemento)session.getAttribute("tipoElemento")).getNombre()%></label>
					    <div class="col-10">
						    <select multiple class="form-control" name="itemElemento" aria-describedby="tipoHelp" required>
									     <% ControladorDeElemento ctrlElemento = new ControladorDeElemento();
							      	     	ArrayList<Elemento> elementosDisponibles = ctrlElemento.getElementosDisponibles(((TipoElemento)session.getAttribute("tipoElemento")), ((String)session.getAttribute("fechaHoraDesde")), ((String)session.getAttribute("fechaHoraHasta")));
							      		 	for(Elemento ele : elementosDisponibles){ %>							      	 
							      	<option value="<%=(ele.getId())%>"><%=ele.getNombre()%></option><%} %>
								<% if(elementosDisponibles.isEmpty()){%><option disabled = "disabled">No existen elementos para el rango de fechas ingresado.</option><%};%>
							    </select>
						    <small id="eleHelp" class="form-text text-muted">Seleccione el elemento a reservar.</small>
						</div>
					</div>
					<div class="form-group row">
					    <label class="col-2 col-form-label">Observacion</label>
					    <div class="col-10">
					    <textarea class="form-control" name="observacion" id="exampleTextarea" rows="3" aria-describedby="obseHelp"></textarea>
 						<small id="obseHelp" class="form-text text-muted">Ingrese comentario a ser considerado.</small>
						</div>
					</div>
					<div class="botones">
						<input type="submit" name="reservarElemento" value="Reservar" class="btn btn-primary">
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