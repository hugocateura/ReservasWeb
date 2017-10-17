<%@page import="entidades.Persona"%>
<%@page import="entidades.Reserva"%>
<%@page import="entidades.Elemento"%>
<%@page import="entidades.TipoElemento"%>

<%@page import="java.util.ArrayList"%>
<%@page import="logica.ControladorDeReserva"%>
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
    <title>Mis Reservas</title>
</head>
 <body>
    <div class="container-fluid">
       <div class="row">
           <div class="col-12">
               <nav class="navbar navbar-expand-lg navbar-light bg-light">
                  <div class="collapse navbar-collapse" id="navbarText">
                      <div class="caja izquierda">
                          <a class="navbar-brand" href="Start"><img src="assets/icono.PNG" width="30" height="30" class="d-inline-block align-top" alt="">   SYSRES</a>
                      </div>
                      <a class="breadcrumb-item" href="Start"><i class="iconoInicio fa fa-home" aria-hidden="true"></i></a>
                      <a class="breadcrumb-item" href="#">Reserva</a>
                      <span class="breadcrumb-item active">Mis Reservas</span>
                  </div>
                  <div class="caja derecha">
                      <p><i class="icono izquierda fa fa-user" aria-hidden="true"></i><%=((Persona)session.getAttribute("user")).getUsuario()%></p>
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

            <div class="col-10 contenido">
            	<form action="CancelarReserva" class="formulario" method="post">
	                  <div class="tituloFormularioRes">
		            		<h3>Mis Reservas</h3>
		              </div>
		              <div class="form-group row">
						    <label class="col-2 col-form-label">Reservas activas</label>
						    <div class="col-10">
							    <select multiple class="form-control" name="itemReserva" aria-describedby="reservaHelp" required>
									     <% ControladorDeReserva ctrlReserva = new ControladorDeReserva();
							      	 Persona pers = ((Persona) request.getSession().getAttribute("user"));
							      	 ArrayList<Reserva> todasLasReservas = ctrlReserva.reservasPendientesPersona(pers);
							      	 for(Reserva res : todasLasReservas){ %>
							      	 
							      	<option value="<%=res.getId()%>"> ID:<%=res.getId()%> | Elemento:<%=res.getElemento().getId()%> | Tipo de Elemento:<%=res.getTipo().getId()%> | Desde:<%=res.getFechaHoraDesde()%> | Hasta:<%=res.getFechaHoraHasta()%> | Usuario:<%=(res.getPersona()).getId()%> | Obs:<%=res.getObservacion()%></option>
							      								    
							      <%} %>
							    </select>
							    <small id="tipoHelp" class="form-text text-muted">Seleccione la reserva a a cancelar.</small>
							</div>
					</div>
	                  
						 <div class="botones">
							<input type="submit" name="siguiente" value="Eliminar" class="btn btn-primary btnEliminar">
						 </div>
                    </form>
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