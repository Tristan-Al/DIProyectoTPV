package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GestionBD {

    private final String HOST;
    private final String USUARIO;
    private final String PASSWORD;
    private final String BD;
    private Connection conexion;

    public GestionBD(String HOST, String USUARIO, String PASSWORD, String BD) {
        this.HOST = HOST;
        this.USUARIO = USUARIO;
        this.PASSWORD = PASSWORD;
        this.BD = BD;
    }

    /**
     * Metodo de conexion con la BD.
     *
     * @return Si ha podido o no conectar a la bd
     */
    private boolean conectar() {
        boolean salida = false;
        try {
            //Driver que vamos a usar
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Inicializar la conexion
            conexion = DriverManager.getConnection("jdbc:mysql://" + HOST
                    + "/" + BD, USUARIO, PASSWORD);
            salida = true;
        } catch (ClassNotFoundException ex) {
            System.err.println("Error al cargar el driver MySQL " + ex.getMessage());
        } catch (SQLException ex) {
            System.err.println("Error SQL: " + ex.getMessage());
        }
        return salida;
    }

    /**
     * Metodo de desconexion con la BD.
     *
     * @return Si ha conseguido o no la desconexion
     */
    private boolean desconectar() {
        boolean salida = false;
        try {
            conexion.close();
            salida = true;
        } catch (SQLException ex) {
            System.err.println("Error al desconectar: " + ex.getMessage());
        }
        return salida;
    }
}
