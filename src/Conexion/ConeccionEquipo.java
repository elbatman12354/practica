package Conexion;

import Menu.Equipo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConeccionEquipo extends Coneccion {

    @Override
    public boolean insertarRegistro(String[] parametrosInsert) throws SQLException, ClassNotFoundException {

        conectarDB();
        if (validarConeccion()) {
            String sentenciaSQL = "INSERT INTO public.equipo(id_equipo, nombre, promocion, url_imagen_equipo) "
                    + "VALUES (?, ?, ?, ?);";

            preStatement = coneccionDB.prepareStatement(sentenciaSQL);
            preStatement.setInt(1, Integer.parseInt(parametrosInsert[0]));
            preStatement.setString(2, parametrosInsert[1]);
            preStatement.setString(3, parametrosInsert[2]);
            preStatement.setString(4, parametrosInsert[3]);

            int resultado = preStatement.executeUpdate();

            if (resultado == 1) {
                System.out.println("Inserccion correcta");
                preStatement.close();
                cerrarDB();
                return true;
            } else {
                System.err.println("Sentencia ejecutada, no se insertaron registros");
                preStatement.close();
                cerrarDB();
                return false;
            }

        }
        System.err.println("Primeramente, debe conectarse a la DB");
        return false;

    }

    @Override
    public boolean consultarRegistro() throws SQLException, ClassNotFoundException {
        conectarDB();
        if (validarConeccion()) {
            int contador = 0;
            String sentenciaSQL = "SELECT id_equipo, nombre, promocion, url_imagen_equipo "
                    + "FROM public.equipo;";

            Statement st = coneccionDB.createStatement();
            ResultSet result = st.executeQuery(sentenciaSQL);

            while (result.next()) {
                contador++;
                System.out.printf("Codigo %d, Nombre %s, Promocin %s, Url Imagen %s \n",
                        result.getInt("id_equipo"),
                        result.getString("nombre"),
                        result.getString("promocion"),
                        result.getString("url_imagen_equipo")
                );

            }

            System.out.println("La cantidad de registros recuperados es de:" + contador);
            st.close();
            cerrarDB();
            return true;

        }
        System.err.println("Primeramente, debe conectarse a la DB");
        return false;
    }

    @Override
    public boolean actualizarRegistro(String[] parametrosUpdate) throws SQLException, ClassNotFoundException {
        conectarDB();
        if (validarConeccion()) {
            String sentenciaSQL = "UPDATE public.equipo SET nombre=?, promocion=?, url_imagen_equipo=? "
                    + "WHERE id_equipo=?;";

            preStatement = coneccionDB.prepareStatement(sentenciaSQL);
            preStatement.setString(1, parametrosUpdate[1]);
            preStatement.setString(2, parametrosUpdate[2]);
            preStatement.setString(3, parametrosUpdate[3]);
            preStatement.setInt(4, Integer.parseInt(parametrosUpdate[0]));

            int resultado = preStatement.executeUpdate();
            if (resultado == 0) {
                System.err.println("El equipo con id: " + parametrosUpdate[0] + " no existe en la base de datos");
                preStatement.close();
                cerrarDB();
                return false;
            } else {
                System.out.println(" Se actualizo " + resultado + " registros de la tabla Equipo");
                preStatement.close();
                cerrarDB();
                return true;
            }

        }
        System.err.println("Primeramente, debe conectarse a la DB");
        return false;
    }

    @Override
    public boolean eliminarRegistro(String[] parametrosDelete) throws SQLException, ClassNotFoundException {
        conectarDB();
        if (validarConeccion()) {
            String sentenciaSQL = "DELETE FROM public.equipo "
                    + "WHERE id_equipo = ?;";

            preStatement = coneccionDB.prepareStatement(sentenciaSQL);
            preStatement.setInt(1, Integer.parseInt(parametrosDelete[0]));

            int resultado = preStatement.executeUpdate();
            if (resultado == 0) {
                System.out.println("El equipo con id " + parametrosDelete[0] + " no existe en la base de datos");
                preStatement.close();
                cerrarDB();
                return false;
            } else {
                System.out.println("Se eliminaron " + resultado + " registros de la tabal Equipo");
                preStatement.close();
                cerrarDB();
                return true;
            }

        }
        System.err.println("Primeramente, debe conectarse a la DB");
        return false;
    }

    public int retornaCantidadRegistros() throws SQLException {

        String sentenciaSQL = "SELECT count(*) FROM public.equipo ";
        Statement st = coneccionDB.createStatement();
        try (ResultSet rs = st.executeQuery(sentenciaSQL)) {
            if (rs.next()) {
                int columCount = rs.getInt(1);
                System.out.println("Cantidad de registros: " + columCount);
                return columCount;
            } else {
                System.out.println("Sin registros");
                return 0;
            }
        } finally {
            st.close();
        }
    }

    public Equipo[] cargarArreglo() throws SQLException {
        conectarDB();

        if (validarConeccion()) {
            int contador = 0;
            String sentenciaSQL = "SELECT * FROM public.equipo ";
            Statement st = coneccionDB.createStatement();
            ResultSet rs = st.executeQuery(sentenciaSQL);
            //Obtiene la cantidad de registros de la base
            int cantidadRegistros = retornaCantidadRegistros();
            //Inicializa el arreglo con la cantidad de datos a ingresar
            Equipo[] consultaEquipo = new Equipo[cantidadRegistros];
            
            int contadorArreglo=0;
            while (rs.next()) {
                Equipo equipo = new Equipo(rs
                                .getInt("id_equipo"), rs
                                .getString("nombre"), rs
                                .getString("promocion"), rs
                                .getString("url_imagen_equipo"));
                //Asigna datos al arreglo
                consultaEquipo[contadorArreglo] = equipo;
                contadorArreglo++;

            }

            st.close();
            for (int i= 0; i< consultaEquipo.length;i++) {
                System.out.println("VALORES GUARDADOS: " + consultaEquipo[i].toString());
            }
        }
        return null;
    }
}
