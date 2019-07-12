package Menu;

import Conexion.ConeccionEquipo;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MenuEquipo implements IMenu {

    private BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
    private ConeccionEquipo conEqui = new ConeccionEquipo();
    
    @Override
    public void mostrarMenu() {
        int opcion = 0;
        while (opcion != 99) {
            try {
                System.out.println("**Eliga una iopcion - Operaciones Equipo**");
                System.out.println("1. Instertar");
                System.out.println("2. Consultar");
                System.out.println("3. Actualizar");
                System.out.println("4. Eliminar");
                 System.out.println("5. Cargar equipos en un Array");
                 System.out.println("6. Imprimir datos del Array");
                System.out.println("99. Salir");
                opcion = Integer.parseInt(entrada.readLine());

                switch (opcion) {
                    case 1:
                        insertarEquipo();
                        break;
                    case 2:
                        consultarEquipo();
                        break;
                    case 3:
                        actualizarEquipo();
                        break;
                    case 4:
                        eliminarEquipo();
                        break;
                    case 5:
                        cargarArreglo();
                        break;
                         case 6:
                       // imprimir();
                        break;
                        
                        
                    case 99:
                        System.out.println("Adios");
                        break;
                    default:
                        System.out.println("Opcion invalida");
                        break;
                }

            } catch (IOException ex) {
                System.err.println("Error ->" + ex.getMessage());
            } catch (SQLException ex) {
                 System.err.println("Error ->" + ex.getMessage());
            } catch (ClassNotFoundException ex) {
                 System.err.println("Error ->" + ex.getMessage());
            }

        }

    }

    private void insertarEquipo() throws IOException, SQLException, ClassNotFoundException {
        System.out.println("Ingrese el codigo del equipo:");
        int codigo = Integer.parseInt(entrada.readLine());
        String codigoIngresado = String.valueOf(codigo);
        
        System.out.println("Ingrese el nombre del equipo:");
        String nombre = entrada.readLine();
        
        System.out.println("Ingrese la promocion del equipo:");
        String promocion = entrada.readLine();
        
        System.out.println("Ingrese la url de la imagen del equipo:");
        String url = entrada.readLine();
        
        String[] parametros = {codigoIngresado, nombre, promocion, url };
        conEqui.insertarRegistro(parametros);                 
        
    }

    private void consultarEquipo() throws SQLException, ClassNotFoundException {
        conEqui.consultarRegistro();       
    }
    
    private void cargarArreglo() throws SQLException, ClassNotFoundException {
        conEqui.cargarArreglo();       
    }
    
    
    
    
    private void actualizarEquipo() throws SQLException, ClassNotFoundException, IOException {
       conEqui.consultarRegistro();
       
        System.out.println("Ingrese el codigo del equipo a actualizar:");
        int codigo = Integer.parseInt(entrada.readLine());
        String codigoIngresado = String.valueOf(codigo);
        
        System.out.println("Ingrese el nombre del equipo a actualizar:");
        String nombre = entrada.readLine();
        
        System.out.println("Ingrese la promocion del equipo a actualizar:");
        String promocion = entrada.readLine();
        
        System.out.println("Ingrese la url de la imagen del equipo  a actualizar:");
        String url = entrada.readLine();
        
        String[] parametros = {codigoIngresado, nombre, promocion, url };
        conEqui.actualizarRegistro(parametros);
       
    }

    private void eliminarEquipo() throws SQLException, ClassNotFoundException, IOException {
        
        conEqui.consultarRegistro();
        
        System.out.println("Ingrese el codigo del equipo a eliminar:");
        int codigo = Integer.parseInt(entrada.readLine());
        String codigoIngresado = String.valueOf(codigo);
        String[] parametros = {codigoIngresado};
        conEqui.eliminarRegistro(parametros);
        
    }
 
     
    }

     
 
         
      
