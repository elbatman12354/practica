
package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public abstract class Coneccion {
    
    protected final String JDBC_DRIVER = "org.postgresql.Driver";
    protected final String DB_URL = "jdbc:postgresql://localhost:5432/Torneo";
    protected final String USER = "postgres";
    protected final String PASS = "fede1221";
    protected Connection coneccionDB = null;
    protected PreparedStatement preStatement;
    
    public abstract boolean insertarRegistro(String parametrosInsert[]) throws SQLException, ClassNotFoundException;
    public abstract boolean consultarRegistro() throws SQLException, ClassNotFoundException;
    public abstract boolean actualizarRegistro(String parametrosUpdate[]) throws SQLException, ClassNotFoundException;
    public abstract boolean eliminarRegistro(String parametrosDelete[]) throws SQLException, ClassNotFoundException;

    public void conectarDB() throws SQLException {
        
        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("Conectando a la DB...");
            coneccionDB = DriverManager.getConnection(DB_URL, USER, PASS);
            
        } catch (ClassNotFoundException ex) {
            System.err.println("Errror ->"+ ex.getMessage());
        }
    }
    
    public void cerrarDB() throws SQLException{
        if(coneccionDB != null){
            coneccionDB.close();
            System.out.println("Desconectando a la DB...");
        } else {
            System.out.println("No existe coneccion a la DB...");
        }
    }
    
    public boolean validarConeccion(){
        if (coneccionDB == null)
            return false;
        return true;
    }

}
