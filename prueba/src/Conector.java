import java.sql.*;
import javax.swing.JOptionPane;
public class Conector {
    Connection conect= null;
    public Connection conexion(){
        try {
            String driver="org.postgresql.Driver";
            String url="jdbc:postgresql://localhost:5432/prueba";
            String user="postgres";
            String password="34448544";
            Class.forName(driver);
            conect=DriverManager.getConnection(url,user,password);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error:"+e, "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
        }
    return conect;
    }
    
}
