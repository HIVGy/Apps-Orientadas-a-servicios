<%@page import="java.util.Stack"%>
<%@page import="conPostgres.Validaciones"%>
<%@page import="aeropuerto.Aerolinea"%>
<%
    String error = "";
    try {
        String nombre = request.getParameter("nomb");
        String rfc = request.getParameter("rfc");
        String creacion = request.getParameter("fecha");

        Aerolinea aerol = new Aerolinea();
        aerol.setNombre(nombre);
        aerol.setRfc(rfc);
        aerol.setCreacion(creacion);
        Validaciones val = new Validaciones();
        Stack<String> xd = val.validar(aerol);
        if (xd.size()==0){
            aerol.registrarAerolinea();
        }else{
            error=xd.peek();
            response.sendRedirect("../Administracion/GestionAerolinea.jsp");
        }
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
        <form action="../Administracion/GestionAerolinea.jsp" method="POST" id="holi">
            <input type="hidden" value="<%=error%>" name="error">
            Si no es redirigido haga clic en el botón
            <input type="submit" value="Redirigir">
        </form>
    </body>
</html>