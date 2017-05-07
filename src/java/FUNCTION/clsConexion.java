/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FUNCTION;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Lau Cerón
 */
public class clsConexion {
    
         Connection conectar=null;
public Connection conexion(){   
  try{
    Class.forName("org.gjt.mm.mysql.Driver");//.newInstance();
    conectar=(Connection) DriverManager.getConnection("jdbc:mysql://localhost/serfis","root","root");
        
  }catch(SQLException ex) {
             JOptionPane.showMessageDialog(null, "Error de conexion de la base de datos");
                                   }catch(ClassNotFoundException ex) {                                                                }
  return conectar;
}
    
}
