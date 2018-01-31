<%-- 
    Document   : NearCinema
    Created on : 27 nov. 2017, 11:19:16
    Author     : Jo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Les Cinémas Proches</title>
        <style type="text/css">
            #divMap{width: 700px; height: 500px; border: 1px solid black;}
        </style>
    </head>

    <body>
        <h1>Les Cinémas Proches</h1>
        <label>Veuillez renseigner une latitude, une longitude et une distance :</label><br><br>
        <form name="cinemas" method="GET" action="/MongoDBWeb/NearCinema">
            <label>Latitude : </label>
            <input type="text" name="lat" id="lat" value="48.8756069" />
            <label>Longitude : </label>
            <input type="text" name="lng" id="lng" value="2.2945"/>
            <label>Distance :</label>
            <input type="text" name="distance" id="distance" value="300"/>

            <input type="submit" name="valider" id="valider" value="Valider"/>
        </form>
        <br>
        <br>
        <br>
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
                        <td>${element["coords"]["lat"]}</td>
                        <td>${element["coords"]["lng"]}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div id="divMap"></div>
        <p>
            <label id="lblMessage">Message</label>
        </p>

        <script src="http://maps.google.com/maps/api/js?key=AIzaSyBAYltSH34Y1ONOptW_JftvmpCqg8rxF-4"></script>
        <!--       <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>-->
        <script src="jquery.js"></script>
        <script src="geolocalisation.js"></script>
    </body>
</html>
