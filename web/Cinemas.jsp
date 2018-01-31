<%-- 
    Document   : Cinemas
    Created on : 27 nov. 2017, 10:10:27
    Author     : Jo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Les Cinémas</title>
    </head>
    <body>
        <h1>Cinémas</h1>

        <table border="1">
            <thead>
                <tr>
                    <th>Nom du Cinéma</th>
                    <th>Latitude</th>
                    <th>Longitude</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="element" items="${listeCinemas}">
                    <tr>
                        <td>${element["cinema"]}</td>
                        <td>${element["coords"]["lng"]}</td>
                        <td>${element["coords"]["lat"]}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>
