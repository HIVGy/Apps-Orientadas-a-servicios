<%@page import="aeropuerto.Escalas"%>
<%@page import="aeropuerto.Rutas"%>
<%@page import="aeropuerto.Vuelos"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" href="css/estilos.css" rel="stylesheet">
        <title>Gestionar los vuelos del aeropuerto</title>
    </head>
    <%
        String xd = " ";
        try {
            xd = request.getParameter("error");
        } catch (Exception e) {
            xd = "Error";
        }
    %>
    <body>
        <%if (xd == null) {%>
        <%=" "%>
        <%} else {%>
        <%=xd%>
        <%}%>
        <h1>Añadir una ruta nueva</h1>
        <table>
            <tr>
                <td>País</td>
                <td>Aeropuerto</td>
                <td>Fecha</td>
                <td>Hora</td>
            </tr>
            <form action="../Base/guardarEscala.jsp" method="POST">
                <tr>
                    <td><input type="text" placeholder="País" name="pais"></td>
                    <td><input type="text" placeholder="Aeropuerto" name="aero"></td>
                    <td><input type="date" name="fecha"></td>
                    <td><input type="time" name="hora"></td>
                    <td><input type="submit" value="Agregar"></td>
                </tr>
            </form>
            <%
                for (Escalas a : new Escalas().obtenerEscalas()) {
            %>
            <tr>
                <form action="../Base/modificarEscala.jsp" method="POST">
                    <input type="hidden" value="<%=a.getId_escala()%>" name="id">
                    <td>
                        <input type="text" value="<%=a.getPais()%>" name="pais">
                    </td>
                    <td>
                        <input type="text" value="<%=a.getAeropuerto()%>" name="aero">
                    </td>
                    <td>
                        <input type="date" value="<%=a.getFecha()%>" name="fecha">
                    </td>
                    <td>
                        <input type="time" value="<%=a.getHora()%>" name="hora">
                    </td>
                    <td>
                        <input type="submit" value="Modificar">
                    </td>
                </form>
            </tr>
            <%}%>
        </table>
</html>
