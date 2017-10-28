<%@page import="entidades.Persona"%>
<%@page import="entidades.TipoElemento"%>
<%@page import="logica.ControladorTipoDeElemento"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.lang.Integer"%>

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
    <script type="text/javascript" src="js/tipoElementoSeleccionado.js"></script>
    <title>Reservar Elemento</title>
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
                      <a class="breadcrumb-item" href="Start"><i class="fa fa-home" aria-hidden="true"></i></a>
                      <a class="breadcrumb-item" href="#">Reserva</a>
                      <a class="breadcrumb-item" href="#">Reservar Elemento</a>
                      <span class="breadcrumb-item active">Paso 1</span>
                  </div>
                  <div class="caja derecha">
                      <p><i class="icono izquierda fa fa-user" aria-hidden="true"></i><%=(((Persona)session.getAttribute("user")).getNombre()+" as "+((Persona)session.getAttribute("user")).getUsuario())%></p>
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
                <form class="formulario" action="ReservarElemento1" method="post">
	                <div class="tituloFormularioRes">
	            		<h3>PASO 1: Seleccionar Tipo de Elemento y Fecha</h3>
	            	</div>
                   <div class="form-group row">
						    <label class="col-2 col-form-label">Tipo Elemento</label>
						    <div class="col-10">
							    <select multiple class="form-control" name="itemTipo" aria-describedby="tipoHelp" required>
									    <% 
							      	 	ArrayList<TipoElemento> todosTiposElemento = (ArrayList<TipoElemento>) request.getAttribute("listaTipos");
							      	 	for(TipoElemento te : todosTiposElemento){ 
							      	 	%>
							      	 
							      	<option value="<%=(te.getId())%>"><%=te.getNombre()%></option>
							      								    
							      <%} %>
							    </select>
							    <small id="tipoHelp" class="form-text text-muted">Seleccione el tipo de elemento a reservar.</small>
							</div>
					</div>
																	
									
					<div class="form-inline dia">
					  <label class="col-2">Desde</label>
					  <input type="date" class=" form-control mb-2 mr-sm-2 mb-sm-0" name="diaDesdeReserva" aria-describedby="desdeHelp" required>
					  <input type="time" value="00:00:00" class="form-control mb-2 mr-sm-2 mb-sm-0" name="horaDesdeReserva" required>
					  <small id="desdeHelp" class="form-text text-muted">Seleccione el dia y la hora desde la cual desea reservar el elemento.</small>
					</div>
					<div class="form-inline dia">
					  <label class="col-2">Hasta</label>
					  <input type="date" class="form-control mb-2 mr-sm-2 mb-sm-0" name="diaHastaReserva" aria-describedby="hastaHelp" required>
					  <input type="time" value="00:00:00" class="form-control mb-2 mr-sm-2 mb-sm-0" name="horaHastaReserva" required>
					  <small id="hastaHelp" class="form-text text-muted">Seleccione el dia y la hora hasta la cual desea reservar el elemento.</small>
					 </div>
					<div class="botones">
						<input type="submit" name="siguiente" value="Siguiente" class="btn btn-primary">
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