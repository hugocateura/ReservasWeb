<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="entidades.Persona"%>
<%@page import="entidades.TipoElemento"%>
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
    		
    		idSeleccionada=(document.getElementById("tabla").rows[id_fila_selected].cells.namedItem("idTipo").innerHTML);
    		
    	}
    	function completarInput() {
    		
    		if (idSeleccionada != 0){ 
    		document.getElementById("inputId").value = idSeleccionada;	
    		}
    		
    	}
    	
    </script>
    <title>Listado Tipo de Elemento</title>
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
                      <a class="breadcrumb-item" href="#">Tipo Elemento</a>
                      <span class="breadcrumb-item active">Listado</span>
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
                <form  class="formulario" action="" method="post">
	                  <div class="tituloFormularioRes">
		            		<h3>Listado Tipo de Elementos</h3>
		              </div>
	                  <table id="tabla" class="table table-striped">
						  <thead>
						    <tr>
						      <th>Id</th>
						      <th>Nombre</th>
						      <th>Cant. máxima de Reservas</th>
						      <th>Duracion máxima</th>
						      <th>Máxima anticipación</th>
						      <th>Reserva solo encargado</th>
						    </tr>
						  </thead>
						  <tbody> 
						     <% int i=0;
						     	ArrayList<TipoElemento> listadoTipoEle = (ArrayList<TipoElemento>)request.getAttribute("listadoTipoElementos");
						     	for(TipoElemento tipo : listadoTipoEle){
						     		i++;
						     %>
						     <tr class="selected" id=<%=i %> onclick="seleccionar(this.id);">
						      <td id="idTipo"><%=tipo.getId() %></td>
						      <td><%=tipo.getNombre() %></td>
						      <td><%=tipo.getCant_max_reservas() %></td>
						      <td><%=tipo.getLimiteMaxHorasReserva() %></td>
						      <td><%=tipo.getCantMaxDiasAnticipacion() %></td>
						      <td><%=tipo.getReservaEncargado() %></td>
						    </tr>
						    <%}%>
						   </tbody>
						 </table>
						 <input class="form-control" name="inputId" id="inputId" type="hidden" value="-1"/> 
						 <div class="botones">
							<input type="button" onclick = "completarInput(); this.form.action = 'EliminarTipoElemento';  this.form.submit();" class="btn btn-primary btnEliminar" value="Eliminar" />
							<input type="button" onclick = "completarInput(); this.form.action = 'ModificarTipoElemento';  this.form.submit();"  class="btn btn-primary" value="Modificar" />
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