<%@page import="entidades.Persona"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <script type="text/javascript" src="/ReservasWeb/js/jquery-2.1.1.min.js"></script>
    <script src="js/jquery-2.1.1.min.js"></script>
    <script src="js/bootstrap.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/bootstrap.css" type="text/css">
    <link rel="stylesheet" href="css/estilo.css" type="text/css">
    <link rel="stylesheet" href="font-awesome/css/font-awesome.min.css">
    <link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">
    <link rel="icon" href="assets/icono.ico">   
    
    <script type="text/javascript">
    	$(document).ready(function(){
    	
    	});
    	var cont=0;
    	var id_fila_selected;
    	var idSeleccionada=0;
    	
    	function cantFilas(){
    		cont=document.getElementById("tabla").rows.length;
    		}
    	function agregar(){
    		cont++;
    		var fila='<tr class="selected" id="fila'+cont+'" onclick="seleccionar(this.id);"><td>'+cont+'</td>valor por defecto<td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>';
    		$('#tabla').append(fila)
   		}
    	function seleccionar(id_fila){
    		var i;
    		for (i=0;i<=cont;i++){					//Deselecciona las demas filas
    			if($('#'+i).hasClass('seleccionada')){
        			$('#'+i).removeClass('seleccionada');
        		}
    		};
    		if($('#'+id_fila).hasClass('seleccionada')){
    			$('#'+id_fila).removeClass('seleccionada');
    		}
    		else{
    			$('#'+id_fila).addClass('seleccionada');
    		}
    		id_fila_selected=id_fila; //el id de la fila seleccionada
    		
    		idSeleccionada=(document.getElementById("tabla").rows[id_fila_selected].cells.namedItem("idPersona").innerHTML);
    		
    	}
    	function completarInput() {
    		
    		if (idSeleccionada != 0){ 
    		document.getElementById("inputId").value = idSeleccionada;
    		}
    		
    	}
    	
    </script>
    <title>Seleccionar Persona</title>
</head>
 <body onload="cantFilas();">
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
                      <a class="breadcrumb-item" href="#">Reserva</a>
                      <a class="breadcrumb-item" href="#">Reservar Elemento</a>
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
           		<form class="formulario" action="" method="post">
	                  <div class="tituloFormularioRes">
		            		<h3>Seleccione la persona para la reserva</h3>
		              </div>
	                  	<table id="tabla" class="table table-striped">
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
						      <%    int i=0;
						      		ArrayList<Persona>listaPers= (ArrayList<Persona>)request.getAttribute("listadoPersonas");
									for(Persona p : listaPers){
									i++;
								%>
							    <tr class="selected" id=<%=i %> onclick="seleccionar(this.id);">
							   		<td id="idPersona"><%=p.getId()%></td>
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
						 <input class="form-control" name="inputId" id="inputId" type="hidden" value="-1"/>
						 <div class="botones">
							<input type="button" onclick = "completarInput(); this.form.action = 'ReservarElemento';  this.form.submit();" class="btn btn-primary btnEliminar" value="Siguiente" />
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
    <script src="https://code.jquery.com/jquery-3.1.1.slim.min.js" integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
    <script src="js/bootstrap.js" crossorigin="anonymous"></script>
    <script src="js/main.js"></script>
  </body>
</html>