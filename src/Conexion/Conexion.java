/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

/**
 *
 * @author Enzo
 */
public class Conexion {
    Connection conect= null;
    public Connection conexion(){
    	try {
			String driver="org.postgresql.Driver";
			String url="jdbc:postgresql://localhost:5432/podologia";
			String user="postgres";
			String password="34448544";
			Class.forName(driver);
			conect= DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Error:" +e, "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
		}
		return conect;
    }    
}
