<!DOCTYPE html>
<html lang="es">
<head>
 	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/css/bootstrap.css">
    <link rel="stylesheet" href="/css/estilo.css" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">
    <link rel="stylesheet" href="/font-awesome/css/font-awesome.min.css">
    <link rel="icon" href="/assets/icono.ico">
    <title>Agregar Persona</title>
</head>    
<body>
 <div class="container-fluid">
       <div class="row">
           <div class="col-12">
               <nav class="navbar navbar-expand-lg navbar-light bg-light">
                  <div class="collapse navbar-collapse" id="navbarText">
                      <div class="caja izquierda">
                          <a class="navbar-brand" href="#"><img src="/assets/icono.PNG" width="30" height="30" class="d-inline-block align-top" alt="">   SYSRES</a>
                      </div>
                      <a class="breadcrumb-item" href="/WEB-INF/Principal.jsp">Inicio</a>
                      <a class="breadcrumb-item" href="#">Administracion</a>
                      <a class="breadcrumb-item" href="#">Persona</a>
                      <span class="breadcrumb-item active">Agregar</span>
                  </div>
                  <div class="caja derecha">
                      <p><i class="icono izquierda fa fa-user" aria-hidden="true"></i>Usuario</p>
                  </div>
                  <form class="form-inline my-2 my-lg-0">
                      <a class="caja derecha salir" href="index.html"><i class="icono izquierda fa fa-times-circle" aria-hidden="true"></i>SALIR</a>
                  </form>
              </nav>
           </div>
       </div>
        <div class="row todo">
            <div class="col-2 contenedor">
              <!-- <a href="" class="link">Menu<i class="icono derecha fa fa-bars" aria-hidden="true"></i></a> -->
               <ul class="menu">
                  <li class="">
                    <a class="link" href="#"><i class="icono izquierda fa fa-wrench" aria-hidden="true"></i>Administracion<i class="icono derecha fa fa-chevron-down" aria-hidden="true"></i></a>
                       <ul>
                          <li>
                            <a class="link" href="#">Persona<i class="icono derecha fa fa-chevron-down" aria-hidden="true"></i></a>
                               <ul>
                                  <li><a class="link" href="/WEB-INF/AgregarPersona.html">Agregar</a></li>
                                  <li><a class="link" href="/WEB-INF/ListadoPersona.html">Listado</a></li>
                                </ul> 
                          </li>
                          <li>
                            <a class="link" href="#">Tipo de Elemento<i class="icono derecha fa fa-chevron-down" aria-hidden="true"></i></a>
                                <ul>
                                  <li><a class="link" href="/WEB-INF/AgregarTipoElemento.html">Agregar</a></li>
                                  <li><a class="link" href="/WEB-INF/ListadoTipoElemento.html">Listado</a></li>
                                </ul>
                          </li>
                          <li>
                            <a class="link" href="#">Elemento<i class="icono derecha fa fa-chevron-down" aria-hidden="true"></i></a>
                                <ul>
                                  <li><a class="link" href="/WEB-INF/AgregarElemento.html">Agregar</a></li>
                                  <li><a class="link" href="/WEB-INF/ListadoElemento.html">Listado</a></li>
                                </ul>
                          </li>
                          <li>
                            <a class="link" href="#">Reserva<i class="icono derecha fa fa-chevron-down" aria-hidden="true"></i></a>
                                <ul>
                                  <li><a class="link" href="/WEB-INF/AnularReserva.html">Anular</a></li>
                                </ul>
                          </li>
                        </ul>
                  </li>
                  <li>
                    <a class="link" href="#"><i class="icono izquierda fa fa-ticket" aria-hidden="true"></i>Reserva<i class="icono derecha fa fa-chevron-down" aria-hidden="true"></i></a>
                        <ul class="">
                              <li><a class="link" href="/WEB-INF/ReservarElemento.html">Reservar</a></li>
                              <li><a class="link" href="/WEB-INF/MisReservas.html">Mis Reservas</a></li>
                        </ul>
                  </li>
                </ul>
            </div>
            <div class="col-10 contenido">
                <form action="" class="formulario">
                    <div class="columnaIzquierda">
                        <label for="">Dni</label>
                        <input type="text" name="personaDni" class="text"><br>
                        <label for="">Nombre</label> 
                        <input type="text" name="personaNombre" class="text"><br>
                        <label for="">Usuario</label> 
                        <input type="text" name="personaUsuario" class="text"><br>
                        <label for="">Habilitado</label>
                        <input type="checkbox" name="personaHabilitado" class="text" value="Habilitado">
                    </div>
                    <div class="columnaDerecha">
                       <label for="">Categoria</label>
                       <select class="text">
                           <option value="Categoria1">TipoElemento1</option>
                       </select> <br>
                       <label for="">Apellido</label> 
                       <input type="text" name="personaApellido" class="text"><br>
                       <label for="">ContraseÃ±a</label> 
                       <input type="text" name="personaPass" class="text"> <br>    
                    </div> 
                   <div class="botones">
                        <input type="reset" name="limpiar" value="Limpiar" class="boton">
                        <input type="submit" name="guardarPersona" value="Aceptar" class="boton">
                        <button class="boton">Cancelar</button>
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