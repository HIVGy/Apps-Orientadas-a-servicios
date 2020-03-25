<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" href="css/estilos.css" rel="stylesheet">
        <title>Administración del aeropuerto</title>
    </head>
    <body>
        <h1>Selecciona que necesitas editar</h1>
        <!--
            Las clases fila y columna se encargan de acomodar en dos columnas
            los 3 elementos de la página web
        -->
        <div class="fila">
            <div class="columna">
                <a href="GestionAerolinea.jsp">
                    <image src="../Images/aerolinea.png" width="200" height="200">
                    <p>Gestionar Aerolineas</p>
                </a><br>
                <a href="GestionAvion.jsp">
                    <image src="../Images/avion.png" width="200" height="200">
                    <p>Gestionar Aviones</p>
                </a>
            </div>
            <div class="columna">
                <a href="GestionEscala.jsp">
                    <image src="../Images/destino.png" width="200" height="200">
                    <p>Gestionar Destinos</p>
                </a>
            </div>
        </div>
    </body>
</html>
