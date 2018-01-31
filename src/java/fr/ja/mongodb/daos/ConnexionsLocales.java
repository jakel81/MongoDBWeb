/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ja.mongodb.daos;

import com.mongodb.MongoClient;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Jo
 */
public class ConnexionsLocales {

    public static Connection getConnexionMySQL() {
        Connection cnx = null;
        try {
            cnx = DriverManager.getConnection("jdbc:mysql://127.0.0.1:8889/cinescope2017", "root", "root");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return cnx;
    } /// getConnexionMySQL

    /**
     *
     * @return
     */
    public static MongoClient getConnexionMongoDB() {
        
        MongoClient mongoClient = null;
        
        try {
            mongoClient = new MongoClient("172.26.11.144");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return mongoClient;
    } /// getConnexionMongoDB

    /**
     *
     * @param pcnx
     * @return
     */
    public static boolean deconnexionSQL(Connection pcnx) {
        boolean lbOK = true;
        try {
            pcnx.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return lbOK;
    } /// deconnexionSQL

    /**
     *
     * @param pcnx
     * @return
     */
    public static boolean deconnexionMDB(MongoClient pcnx) {
        boolean lbOK = true;
        try {
            pcnx.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return lbOK;
    } /// deconnexionMDB

}
