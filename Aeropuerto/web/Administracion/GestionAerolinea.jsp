<%@page import="aeropuerto.Aerolinea"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" href="css/estilos.css" rel="stylesheet">
        <title>Gestionar los aviones del aeropuerto</title>
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
        <h1>
            Tabla de referencia de los datos
        </h1>
        <table>
            <tr>
                <td class="encabezado">Nombre de la aerolinea</td>
                <td class="encabezado">RFC</td>
                <td class="encabezado"></td>
                <td class="encabezado"></td>
            </tr>
            <form action="../Base/guardarAerolinea.jsp" method="POST">
                <tr>
                    <td><input type="text" placeholder="Aerolinea" name="nomb"></td>
                    <td><input type="text" placeholder="RFC" name="rfc"></td>
                    <td><input type="date" name="fecha"></td>
                    <td><input type="submit" value="Agregar"></td>
                </tr>
            </form>
            <%
                for (Aerolinea a : new Aerolinea().obtenerAerolineas()) {
            %>
            <tr>
            <form action="../Base/modificarAerolinea.jsp" method="POST">
                <input type="hidden" value="<%=a.getId_aerolinea()%>" name="id">
                <td>
                    <input type="text" value="<%=a.getNombre()%>" name="nomb">
                </td>
                <td>
                    <input type="text" value="<%=a.getRfc()%>" name="rfc">
                </td>
                <td>
                    <input type="text" value="<%=a.getCreacion()%>" name="fecha">
                </td>
                <td>
                    <input type="submit" value="Modificar">
                </td>
            </form>
        </tr>
        <%}%>
    </table>
</body>
</html>