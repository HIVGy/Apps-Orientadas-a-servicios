<%@page import="aeropuerto.Aerolinea"%>
<%@page import="aeropuerto.Avion"%>
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
            Datos actuales
        </h1>
        <table>
            <!--Encabezado de la tabla-->
            <tr>
                <td class="encabezado">Aerolinea</td>
                <td class="encabezado">Modelo</td>
                <td class="encabezado">Capacidad</td>
                <td class="encabezado">Estado</td>
            </tr>
            <tr>
            <form action="../Base/guardarAvion.jsp" method="POST">
                <td>
                    <select name="idAe">
                        <%
                            for (Avion a : new Avion().obtenerAviones()) {
                                Aerolinea airo = new Aerolinea();
                                airo.setId_aerolinea(a.getId_aerolinea());
                        %>
                        <option value="<%=airo.obtenerNombrePorId()%>"><%=airo.obtenerNombrePorId()%></option>
                        <%}%>
                    </select>
                </td>
                <td><input type="text" placeholder="Boing 404" name="mod"></td>
                <td><input type="text" placeholder="10,000" name="cap"></td>
                <td><input type="text" value="Disponible" name="est"></td>
                <td><input type="submit" class="btn" value="Agregar"></td>
            </form>
        </tr>
        <tr>
            <%
                for (Avion a : new Avion().obtenerAviones()) {
            %>

        <form action="../Base/modificarAvion.jsp" method="POST">
            <input type="hidden" value="<%=a.getId_avion()%>" name="id">
            <td>
                <select name="idAe">
                    <%
                        Aerolinea airo = new Aerolinea();
                        airo.setId_aerolinea(a.getId_aerolinea());
                    %>
                    <option value="<%=airo.obtenerNombrePorId()%>"><%=airo.obtenerNombrePorId()%></option>
                    <%
                        for (Aerolinea b : new Aerolinea().obtenerAerolineas()) {
                    %>
                    <option value="<%=b.getNombre()%>"><%=b.getNombre()%></option>
                    <%}%>
                </select>
            </td>
            <td>
                <input type="text" value="<%=a.getModelo()%>" name="mod">
            </td>
            <td>
                <input type="text" value="<%=a.getCapacidad()%>" name="cap">
            </td>
            <td>
                <input type="text" value="<%=a.getDisponibilidad()%>" name="est">
            </td>
            <td>
                <input type="submit" class="btn" value="Modificar">
            </td>
        </form>
    </tr>
    <%}%>
</table>
</body>
</html>
