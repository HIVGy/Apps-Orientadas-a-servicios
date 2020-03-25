<%@page import="aeropuerto.Vuelos"%>
<%
    String error = "";
    try {
        int id_avion = Integer.parseInt(request.getParameter("idA"));
        int id_ruta = Integer.parseInt(request.getParameter("idR"));

        Vuelos aerol = new Vuelos();
        aerol.setId_avion(id_avion);
        aerol.setId_ruta(id_ruta);
        aerol.registrarVuelo();
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
        <form action="../Administracion/GestionVuelo.jsp" method="POST" id="holi">
            <input type="hidden" value="<%=error%>" name="error">
            Si no es redirigido haga clic en el botón
            <input type="submit" value="Redirigir">
        </form>
    </body>
</html>
