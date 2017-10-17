<%@page import="entidades.Persona"%>
<%@page import="java.util.ArrayList"%>
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
    <title>Listado Persona</title>
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
                      <a class="breadcrumb-item" href="#">Administracion</a>
                      <a class="breadcrumb-item" href="#">Persona</a>
                      <span class="breadcrumb-item active">Listado</span>
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
           		 <form action="" class="formulario">
	                  <div class="tituloFormularioRes">
		            		<h3>Listado de Personas</h3>
		              </div>
	                  <table class="table table-striped">
						  <thead>
						    <tr>
						      <th>Id</th>
						      <th>Dni</th>
						      <th>Nombre</th>
						      <th>Apellido</th>
						      <th>Usuario</th>
						      <th>Contraseña</th>
						      <th>Categoria</th>
						      <th>Habilitado</th>
						    </tr>
						  </thead>
						  <tbody>     
						      <%    ArrayList<Persona>listaPers= (ArrayList<Persona>)request.getAttribute("listadoPersonas");
									for(Persona p : listaPers){%>
							    <tr>
							   		<th scope="row"><%=p.getId()%></th>
									<td><%=p.getDni() %></td>
									<td><%=p.getNombre() %></td>
									<td><%=p.getApellido() %></td>
									<td><%=p.getUsuario() %></td>
									<td><%=p.getContrasena() %></td>
									<td><%=p.getCategoria() %></td>
									<td><%=p.isHabilitado() %></td>
								</tr>
								<%
									}
								%>
						    </tbody>
						 </table>
						 <div class="botones">
							<input type="submit" name="siguiente" value="Eliminar" class="btn btn-primary btnEliminar">
							<input type="submit" name="siguiente" value="Modificar" class="btn btn-primary">
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