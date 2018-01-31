/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ja.mongodb.controls;

import com.mongodb.*;
import com.mongodb.client.*;
import fr.ja.mongodb.daos.ConnexionsLocales;
import java.io.*;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import org.bson.Document;
import org.json.simple.*;

/**
 *
 * @author Jo
 */
@WebServlet(name = "NearCinema", urlPatterns = {"/NearCinema"})
public class NearCinema extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();

        MongoClient mongoClient = null;
        MongoDatabase db;
        MongoCollection collection;
        FindIterable<Document> resultat;
        
        Document docProjection = new Document();
        docProjection.put("_id", 0);
        docProjection.put("nomCinema", 1);
        docProjection.put("coords", 1);

        JSONObject objetJSON;
        JSONArray tableauJSON = new JSONArray();

        try {

            mongoClient = ConnexionsLocales.getConnexionMongoDB();
            db = mongoClient.getDatabase("cinescope");
            collection = db.getCollection("cinemas");
            
            double lng = Double.valueOf(request.getParameter("lng"));
            double lat = Double.valueOf(request.getParameter("lat"));
            int liMaxDistance = Integer.valueOf(request.getParameter("distance"));

            List<Double> listeCoordonnees = new ArrayList();
            listeCoordonnees.add(lng);
            listeCoordonnees.add(lat);
            

            BasicDBObject requeteNear = (BasicDBObject) BasicDBObjectBuilder.start()
                    .push("coords")
                    .push("$near")
                    .add("$maxDistance", liMaxDistance)
                    .push("$geometry")
                    .add("coordinates", listeCoordonnees)
                    .get();

            resultat = collection.find(requeteNear);

            for (Document doc : resultat) {
//                out.println(doc.get("nomCinema"));
//                out.println(doc.get("coords"));
                objetJSON = new JSONObject();
                objetJSON.put("cinema", doc.get("nomCinema"));
                objetJSON.put("coords", doc.get("coords"));

                tableauJSON.add(objetJSON);
            }

        } catch (NumberFormatException e) {
            out.print(e.getMessage());
        } finally {
            if (mongoClient != null) {
                mongoClient.close();
            }
        }

        request.setAttribute("listeCinemas", tableauJSON);
        String lsURL = "NearCinema.jsp";
        getServletContext().getRequestDispatcher("/" + lsURL).forward(request, response);
        //out.print(tableauJSON);
    }
}
