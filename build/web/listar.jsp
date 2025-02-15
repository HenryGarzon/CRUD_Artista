<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de Artistas</title>
    </head>
    <body>
        <h2>Lista de Artistas</h2>
        <table border="1">
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Género Musical</th>
            </tr>
            <c:forEach var="artista" items="${listaArtistas}">
                <tr>
                    <td>${artista.id}</td>
                    <td>${artista.nombre}</td>
                    <td>${artista.generoMusical}</td>
                </tr>
            </c:forEach>
        </table>
        
        <h3>Agregar Nuevo Artista</h3>
        <form action="ArtistaServlet" method="post">
            Nombre: <input type="text" name="nombre" required>
            Género Musical: <input type="text" name="generoMusical" required>
            <input type="submit" value="Agregar Artista">
        </form>
    </body>
</html>