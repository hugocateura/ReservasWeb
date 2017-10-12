<%@page import="entidades.Persona"%>
<%@page import="entidades.Reserva"%>
<%@page import="java.util.ArrayList"%>
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
          <div class="row todo">
            <div class="col-2 contenedor">
               <ul class="menu">
                  <li class="">
                    <a class="link" href="#"><i class="icono izquierda fa fa-wrench" aria-hidden="true"></i>Administracion<i class="icono derecha fa fa-chevron-down" aria-hidden="true"></i></a>
                       <ul>
                          <li>
                            <a class="link" href="#">Persona<i class="icono derecha fa fa-chevron-down" aria-hidden="true"></i></a>
                               <ul>
                                  <li><a class="link" href="AgregarPersona">Agregar</a></li>
                                  <li><a class="link" href="ListadoPersona">Listado</a></li>
                                </ul> 
                          </li>
                          <li>
                            <a class="link" href="#">Tipo de Elemento<i class="icono derecha fa fa-chevron-down" aria-hidden="true"></i></a>
                                <ul>
                                  <li><a class="link" href="AgregarTipoElemento">Agregar</a></li>
                                  <li><a class="link" href="ListadoTipoElemento">Listado</a></li>
                                </ul>
                          </li>
                          <li>
                            <a class="link" href="#">Elemento<i class="icono derecha fa fa-chevron-down" aria-hidden="true"></i></a>
                                <ul>
                                  <li><a class="link" href="AgregarElemento">Agregar</a></li>
                                  <li><a class="link" href="ListadoElemento">Listado</a></li>
                                </ul>
                          </li>
                          <li>
                            <a class="link" href="#">Reserva<i class="icono derecha fa fa-chevron-down" aria-hidden="true"></i></a>
                                <ul>
                                  <li><a class="link" href="AnularReserva">Anular</a></li>
                                </ul>
                          </li>
                        </ul>
                  </li>
                  <li>
                    <a class="link" href="#"><i class="icono izquierda fa fa-ticket" aria-hidden="true"></i>Reserva<i class="icono derecha fa fa-chevron-down" aria-hidden="true"></i></a>
                        <ul class="">
                              <li><a class="link res" href="ReservarElemento">Reservar</a></li>
                              <li><a class="link res" href="MisReservas">Mis Reservas</a></li>
                        </ul>
                  </li>
                </ul>
            </div>
            <div class="col-10 contenido">
            	<form action="" class="formulario">
	                  <div class="tituloFormularioRes">
		            		<h3>Mis Reservas</h3>
		              </div>
	                  <table class="table table-striped">
						  <thead>
						    <tr>
						      <th>Id</th>
						      <th>Elemento</th>
						      <th>Tipo de Elemento</th>
						      <th>Persona</th>
						      <th>Fecha y Hora Desde</th>
						      <th>Fecha y Hora Hasta</th>
						      <th>Observaciones</th>
						    </tr>
						  </thead>
						  <tbody>
						  <%ArrayList<Reserva> lisMisRes= (ArrayList<Reserva>)request.getAttribute("listadoMisReservas");
						  	for(Reserva res : lisMisRes){%>
						    <tr>
						      <th scope="row"><%=res.getId() %></th>
						      <td><%=res.getElemento().getId() %></td>
						      <td><%=res.getTipo().getId() %></td>
						      <td><%=res.getPersona().getId() %></td>
						      <td><%=res.getFechaHoraDesde() %></td>
						      <td><%=res.getFechaHoraHasta()%></td>
						      <td><%=res.getObservacion() %></td>
						    </tr>
						    <%} %>						   
						  </tbody>
						 </table>
						 <div class="botones">
							<input type="submit" name="siguiente" value="Cancelar" class="btn btn-primary btnEliminar">
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