<%@page import="entidades.Persona"%>
<%@page import="entidades.Elemento"%>
<%@page import="entidades.TipoElemento"%>
<%@page import="java.util.ArrayList"%>
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
    
    <style>
    #content {
    position:absolute;
    min-height:50%;
    width:80%;
    top:20%;
    left:5%;
    }
    .selected{
    cursor:pointer;
    background:rgba(0, 0, 0, 0.2);
    }
    .selected:hover{
    background-color:#0585c0;
    color:white;
    }
	.table-striped tbody tr:hover{
  	background-color: rgba(0, 0, 0, 0.5);
  	}
  	.table-striped tbody tr:seleccionada {
  	background-color: rgba(0, 0, 0, 0.5);
  	}
  	.seleccionada{
    color:red;
 	}
    .seleccionada:hover{
    background-color:#05858c0;
    color:white;
 	}
    </style>
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
    		
    		idSeleccionada=(document.getElementById("tabla").rows[id_fila_selected].cells.namedItem("idElemento").innerHTML);
    		
    	}
    	function completarInput() {
    		
    		if (idSeleccionada != 0){ 
    		document.getElementById("inputId").value = idSeleccionada;	
    		}
    		
    	}
    	
    </script>
    <title>Listado Elemento</title>
</head>
 <body onload="cantFilas();">
    <div class="container-fluid">
       <div class="row">
           <div class="col-12">
               <nav class="navbar navbar-expand-lg navbar-light bg-light">
                  <div class="collapse navbar-collapse" id="navbarText">
                      <div class="caja izquierda">
                          <a class="navbar-brand" href="Start"><img src="assets/icono.PNG" width="30" height="30" class="d-inline-block align-top" alt="">   SYSRES</a>
                      </div>
                      <a class="breadcrumb-item" href="Start"><i class="iconoInicio fa fa-home" aria-hidden="true"></i></a>
                      <a class="breadcrumb-item" href="#"><%=((Persona) session.getAttribute("user")).getCategoria()%></a>
                      <a class="breadcrumb-item" href="#">Elemento</a>
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
                  <form class="formulario" action="" method="post">
	                  <div class="tituloFormularioRes">
		            		<h3>Listado de Elementos</h3>
		              </div>
		              <div>
	                  <table id="tabla" class="table table-striped">
						  <thead>
						    <tr>
						      <th>Id</th>
						      <th>Nombre</th>
						      <th>Id Tipo de Elemento</th>
						      <th>Descripción Tipo</th>
						    </tr>
						  </thead>
						  <tbody>
						   		<% 	int i=0;
						   			ArrayList<Elemento> listadoEle = (ArrayList<Elemento>)request.getAttribute("listadoElementos");
						   	  		for(Elemento ele : listadoEle){
						   	  		i++;
						   	  	%>
						    	<tr class="selected" id=<%=i%> onclick="seleccionar(this.id);">
						      		<td id="idElemento"><%=ele.getId()%></td>
						      		<td><%=ele.getNombre()%></td>
						      		<td><%=ele.getTipo().getId()%></td>
						      		<td><%=ele.getTipo().getNombre()%></td>
						    	</tr>
						    	<%
						    	} 
						    	%>
						   </tbody>
						 </table>
						 <input class="form-control" name="inputId" id="inputId" type="hidden" value="-1"/> 
						 <div class="botones">
							<input type="button" onclick = "completarInput(); this.form.action = 'EliminarElemento';  this.form.submit();" class="btn btn-primary btnEliminar" value="Eliminar" />
							<input type="button" onclick = "completarInput(); this.form.action = 'ModificarElemento';  this.form.submit();"  class="btn btn-primary" value="Modificar" />
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