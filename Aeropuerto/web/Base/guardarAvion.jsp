<%@page import="aeropuerto.Aerolinea"%>
<%@page import="aeropuerto.Avion"%>

<%
    String error = "";
    try {
        int id_aerolinea = new Aerolinea(request.getParameter("idAe")).obtenerIdPorNombre();
        String modelo = request.getParameter("mod");
        int capacidad = Integer.parseInt(request.getParameter("cap"));

        Avion aerol = new Avion();
        aerol.setId_aerolinea(id_aerolinea);
        aerol.setModelo(modelo);
        aerol.setCapacidad(capacidad);
        aerol.setDisponibilidad("disponible");
        aerol.registrarAvion();
    } catch (Exception e) {
        error = "Ocurrio un error, intentalo de nuevo";
    }
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Almacenando...</title>
        <script type="text/javascript">
            function subir() {
                document.getElementById("holi").submit();
            }
        </script>
    </head>
    <body onload="subir()">
        <h1>Espere mientras es redirigido...</h1>
        <form action="../Administracion/GestionAvion.jsp" method="POST" id="holi">
            <input type="hidden" value="<%=error%>" name="error">
            Si no es redirigido haga clic en el botón
            <input type="submit" value="Redirigir">
        </form>
    </body>
</html>
