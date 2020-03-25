<%@page import="java.sql.Time"%>
<%@page import="java.sql.Date"%>
<%@page import="aeropuerto.Escalas"%>
<%
    String error = "";
    try {
        String pais = request.getParameter("pais");
        String aeropuerto = request.getParameter("aero");
        String fecha = request.getParameter("fecha");
        String hora = request.getParameter("hora");

        Escalas aerol = new Escalas();
        aerol.setPais(pais);
        aerol.setAeropuerto(aeropuerto);
        aerol.setFecha(fecha);
        aerol.setHora(hora);
        aerol.registrarEscalas();
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
        <form action="../Administracion/GestionEscala.jsp" method="POST" id="holi">
            <input type="hidden" value="<%=error%>" name="error">
            Si no es redirigido haga clic en el botón
            <input type="submit" value="Redirigir">
        </form>
    </body>
</html>