<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Inicio</title>
    <link rel="stylesheet" href="css/estilo.css" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">
</head>
<body>
   <header class="encabezado">
        
        <nav>
            <ul class="menu">
                <li><a href="">ADMINISTRACION</a>
                    <ul>
                        <li><a href="">PERSONA</a>
                            <ul>
                                <li><a href="AgregarPersona.html">AGREGAR</a></li>
                                <li><a href="ListadoPersona.html">LISTADO</a></li>
                            </ul>
                        </li>
                        <li><a href="">TIPO DE ELEMENTO</a>
                            <ul>
                                <li><a href="AgregarTipoElemento.html">AGREGAR</a></li>
                                <li><a href="ListadoTipoElemento.html">LISTADO</a></li>
                            </ul>
                        </li>
                        <li><a href="">ELEMENTO</a>
                            <ul>
                                <li><a href="AgregarElemento.html">AGREGAR</a></li>
                                <li><a href="ListadoElemento.html">LISTADO</a></li>
                            </ul>
                        </li>
                        <li><a href="">RESERVA</a>
                            <ul>
                                <li><a href="AnularReserva.html">ANULAR</a></li>
                            </ul>
                        </li>
                    </ul>
                </li>

                <li><a href="">RESERVA</a>
                     <ul>
                        <li><a href="ReservarElemento.html">RESERVAR ELEMENTO</a></li>
                        <li><a href="MisReservas.html">MIS RESERVAS PENDIENTES</a></li>
                    </ul>
                </li>

                <li style="float: right;"><a href="">SALIR</a>
                    <ul>
                        <li><a href="login.html">CERRAR SESION</a></li>
                        <li><a href="">SALIR</a></li>
                    </ul>
                </li>
            </ul>
        </nav>
    </header>    
    <body class="contenido">
         <div>
            <p class="textoBienvenida">BIENVENIDO A SYSRES</p>
            
        </div>
    </body>
    
    <footer>
        <p>Derechos reservados | SANKIP &copy;</p>
    </footer>
</body>
</html>