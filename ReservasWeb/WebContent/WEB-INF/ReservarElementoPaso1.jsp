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
               <nav class="navbar navbar-expand-lg navbar-light bg-light">
                  <div class="collapse navbar-collapse" id="navbarText">
                      <div class="caja izquierda">
                          <a class="navbar-brand" href="#"><img src="assets/icono.PNG" width="30" height="30" class="d-inline-block align-top" alt="">   SYSRES</a>
                      </div>
                      <!-- <div class="migajaPan">
                      	<a class="breadcrumb-item" href="Principal.jsp">Inicio</a>
                      </div> -->
                      <a class="breadcrumb-item" href="Principal.jsp">Inicio</a>
                      <a class="breadcrumb-item" href="#">Reserva</a>
                      <a class="breadcrumb-item" href="#">Reservar Elemento</a>
                      <span class="breadcrumb-item active">Paso 1</span>
                  </div>
                  <div class="caja derecha">
                      <p><i class="icono izquierda fa fa-user" aria-hidden="true"></i>Usuario</p>
                  </div>
                  <form class="form-inline my-2 my-lg-0">
                      <a class="caja derecha salir" href="/index.html"><i class="icono izquierda fa fa-times-circle" aria-hidden="true"></i>SALIR</a>
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
                <form class="formulario" action="ReservarElemento2">
	                <div class="tituloFormularioRes">
	            		<h3>PASO 1: Seleccionar Tipo de Elemento y Fecha</h3>
	            	</div>
                   <div class="form-group row">
						    <label class="col-2 col-form-label">Tipo Elemento</label>
						    <div class="col-10">
							    <select multiple class="form-control" name="elementoTipo" aria-describedby="tipoHelp">
							      <option>1</option>
							      <option>2</option>
							      <option>3</option>
							    </select>
							    <small id="tipoHelp" class="form-text text-muted">Seleccione el tipo de elemento a reservar.</small>
							</div>
					</div>
					
									
					<div class="form-inline dia">
					  <label class="col-2">Desde</label>
					  <input type="date" class=" form-control mb-2 mr-sm-2 mb-sm-0" name="diaDesdeReserva" aria-describedby="desdeHelp">
					  <input type="time" value="00:00:00" class="form-control mb-2 mr-sm-2 mb-sm-0" name="horaDesdeReserva">
					  <small id="desdeHelp" class="form-text text-muted">Seleccione el dia y la hora desde la cual desea reservar el elemento.</small>
					</div>
					<div class="form-inline dia">
					  <label class="col-2">Hasta</label>
					  <input type="date" class="form-control mb-2 mr-sm-2 mb-sm-0" name="diaHastaReserva" aria-describedby="hastaHelp">
					  <input type="time" value="00:00:00" class="form-control mb-2 mr-sm-2 mb-sm-0" name="horaHastaReserva">
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