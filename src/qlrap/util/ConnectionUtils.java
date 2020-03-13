/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlrap.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hieu Vo
 */
public class ConnectionUtils {

   /* public static Connection getOracleConnection() throws SQLException,
            ClassNotFoundException {
        String hostName = "localhost";
        String sid = "orcl";
        String userName = "hieutrung123";
        String password = "hieu123";

        return getOracleConnection(hostName, sid, userName, password);
    }

    public static Connection getOracleConnection(String hostName, String sid, String userName, String password) throws ClassNotFoundException, SQLException {

        Class.forName("oracle.jdbc.driver.OracleDriver");
        String connectionURL = "jdbc:oracle:thin:@" + hostName + ":1521:" + sid;

        Connection conn = DriverManager.getConnection(connectionURL, userName, password);
        return conn;
    }*/
    
     public static Connection getJDBCConnection() 
    {
       
  final String url="jdbc:mysql://localhost:3306/qlbanve?useUnicode=true&characterEncoding=utf8";
  final String user="root";
  final String password="";
  Connection conn=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
      try {
         conn= DriverManager.getConnection(url, user, password);
      } catch (SQLException ex) {
          Logger.getLogger(ConnectionUtils.class.getName()).log(Level.SEVERE, null, ex);
          
      }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
}
