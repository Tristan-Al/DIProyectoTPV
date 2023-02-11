package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import modelos.Producto;
import modelos.Productos;
import modelos.Usuario;
import modelos.Usuarios;

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
    
//PRODUCTOS············································································
    
    //INSERTAR PRODUCTO
    public boolean insertarProducto(Producto producto){
        boolean resultado = true;
        try {
            //Nos conectamos a la BD
            conectar();
            //Creamos la sentencia
            Statement stmt = conexion.createStatement();
            //Preparamos la sentencia SQL
            String sentencia = String.format(
                    "INSERT INTO `productos`(`nombre`, `precio`, `stock`, `imagen`) VALUES ('%s',%s,%s,'%s')",
                    producto.getNombre(),
                    producto.getPrecio(),
                    producto.getStock(),
                    producto.getImagen());
            //Mostramos la consulta por consola
            System.out.println("Consulta SQL: " + sentencia);
            //Ejecutamos la consulta
            resultado = stmt.execute(sentencia);
            //Cerrar la sentencia
            stmt.close();
            //Desconectar
            desconectar();
            
            return resultado;
        } catch (SQLException ex) {
            System.out.println("Error al insertar el producto" + producto.getNombre() + ". " + ex.getMessage());
            resultado = false;
        }
        
        return resultado;
    }
    
    //LISTAR PRODUCTOS
    public Productos listarProductos(){
        Productos listado = new Productos();
        try {
            //Nos conectamos a la BD
            conectar();
            //Creamos la sentencia
            Statement stmt = conexion.createStatement();
            //Preparamos la sentencia SQL
            String sentencia = String.format(
                    "SELECT * FROM `productos`");
            //Mostramos la consulta por consola
            System.out.println("Consulta SQL: " + sentencia);
            //Ejecutamos la consulta
            ResultSet rs = stmt.executeQuery(sentencia);
            while(rs.next()){
                listado.addProducto(new Producto(
                        rs.getInt("id_producto"),
                        rs.getString("nombre"),
                        rs.getDouble("precio"),
                        rs.getInt("stock"),
                        rs.getString("imagen")
                ));
            }
            //Cierro el resultSet
            rs.close();
            //Cerrar la sentencia
            stmt.close();
            //Desconectar
            desconectar();
        } catch (SQLException ex) {
            System.out.println("Error al listar los productos. " + ex.getMessage());
        }
        
        return listado;
    }
    
    //MODIFICAR PRODUCTOS
    public boolean modificarProducto(Producto productoAnterior, Producto productoPosterior){
        boolean resultado = true;
        try {
            //Nos conectamos a la BD
            conectar();
            //Creamos la sentencia
            Statement stmt = conexion.createStatement();
            //Preparamos la sentencia SQL
            String sentencia = String.format(
                    "UPDATE `productos` SET `nombre`='%s', `precio`=%s, `stock`=%s, `imagen`='%s' WHERE `id_producto`=%s",
                    productoPosterior.getNombre(),
                    productoPosterior.getPrecio(),
                    productoPosterior.getStock(),
                    productoPosterior.getImagen(),
                    productoAnterior.getId_producto()
            );
            //Mostramos la consulta por consola
            System.out.println("Consulta SQL: " + sentencia);
            //Ejecutamos la consulta
            resultado = stmt.execute(sentencia);
            //Cerrar la sentencia
            stmt.close();
            //Desconectar
            desconectar();
            
            return resultado;
        } catch (SQLException ex) {
            System.out.println("Error al modificar el producto" + productoAnterior.getNombre() + ". " + ex.getMessage());
            resultado = false;
        }
        return resultado;
    }
    
    //BORRAR PRODUCTOS
    public boolean borrarProducto(Producto producto){
        boolean resultado = true;
        try {
            //Nos conectamos a la BD
            conectar();
            //Creamos la sentencia
            Statement stmt = conexion.createStatement();
            //Preparamos la sentencia SQL
            String sentencia = String.format(
                    "DELETE FROM `productos` WHERE id_producto=%s",
                    producto.getId_producto());
            //Mostramos la consulta por consola
            System.out.println("Consulta SQL: " + sentencia);
            //Ejecutamos la consulta
            resultado = stmt.execute(sentencia);
            //Cerrar la sentencia
            stmt.close();
            //Desconectar
            desconectar();
            
            return resultado;
        } catch (SQLException ex) {
            System.out.println("Error al borrar el producto" + producto.getNombre() + ". " + ex.getMessage());
            resultado = false;
        }
        
        return resultado;
    }
    
    //BUSCAR PRODUCTO
    public Producto buscarProductoNom(String nombreProducto){
        Producto producto_buscado = null;
        try {
            //Nos conectamos a la BD
            conectar();
            //Creamos la sentencia
            Statement stmt = conexion.createStatement();
            //Preparamos la sentencia SQL
            String sentencia = String.format(
                    "SELECT * FROM `productos` WHERE nombre='%s'",
                    nombreProducto);
            //Mostramos la consulta por consola
            System.out.println("Consulta SQL: " + sentencia);
            //Ejecutamos la consulta
            ResultSet rs = stmt.executeQuery(sentencia);
            while(rs.next()){
                producto_buscado = new Producto(
                        rs.getInt("id_producto"),
                        rs.getString("nombre"),
                        rs.getDouble("precio"),
                        rs.getInt("stock"),
                        rs.getString("imagen")
                );
            }
            //Cierro el resultSet
            rs.close();
            //Cerrar la sentencia
            stmt.close();
            //Desconectar
            desconectar();
        } catch (SQLException ex) {
            System.out.println("Error al buscar el producto" + nombreProducto + ". " + ex.getMessage());
        }
        
        return producto_buscado;
    }
    
//USUARIOS··············································································
    
    //INSERTAR USUARIO
    public boolean insertarUsuario(Usuario usuario){
        boolean resultado = true;
        try {
            //Nos conectamos a la BD
            conectar();
            //Creamos la sentencia
            Statement stmt = conexion.createStatement();
            //Preparamos la sentencia SQL
            String sentencia = String.format(
                    "INSERT INTO `usuarios`(`nickname`, `nombre`, `apellidos`, `user_pass`, `rol`) VALUES ('%s','%s','%s','%s',%s)",
                    usuario.getNickname(),
                    usuario.getNombre(),
                    usuario.getApellidos(),
                    usuario.getPassword(),
                    usuario.getRol());
            //Mostramos la consulta por consola
            System.out.println("Consulta SQL: " + sentencia);
            //Ejecutamos la consulta
            resultado = stmt.execute(sentencia);
            //Cerrar la sentencia
            stmt.close();
            //Desconectar
            desconectar();
            
            return resultado;
        } catch (SQLException ex) {
            System.out.println("Error al insertar el usuario" + usuario.getNombre() + ". " + ex.getMessage());
            resultado = false;
        }
        
        return resultado;
    }
    
    //LISTAR USUARIO
    public Usuarios listarUsuarios(){
        Usuarios listado = new Usuarios();
        try {
            //Nos conectamos a la BD
            conectar();
            //Creamos la sentencia
            Statement stmt = conexion.createStatement();
            //Preparamos la sentencia SQL
            String sentencia = String.format(
                    "SELECT * FROM `usuarios`");
            //Mostramos la consulta por consola
            System.out.println("Consulta SQL: " + sentencia);
            //Ejecutamos la consulta
            ResultSet rs = stmt.executeQuery(sentencia);
            while(rs.next()){
                listado.addUsuario(new Usuario(
                        rs.getString("nickname"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getString("user_pass"),
                        rs.getInt("rol")
                ));
            }
            //Cierro el resultSet
            rs.close();
            //Cerrar la sentencia
            stmt.close();
            //Desconectar
            desconectar();
        } catch (SQLException ex) {
            System.out.println("Error al listar los usuarios. " + ex.getMessage());
        }
        
        return listado;
    }
    
    //MODIFICAR USUARIO
    public boolean modificarUsuario(Usuario usuarioAnterior, Usuario usuarioPosterior){
        boolean resultado = true;
        try {
            //Nos conectamos a la BD
            conectar();
            //Creamos la sentencia
            Statement stmt = conexion.createStatement();
            //Preparamos la sentencia SQL
            String sentencia = String.format(
                    "UPDATE `usuarios` SET `nickname`='%s', `nombre`='%s', `apellidos`='%s', `user_pass`='%s', `rol`=%s WHERE `nickname`='%s'",
                    usuarioPosterior.getNickname(),
                    usuarioPosterior.getNombre(),
                    usuarioPosterior.getApellidos(),
                    usuarioPosterior.getPassword(),
                    usuarioPosterior.getRol(),
                    usuarioAnterior.getNickname()
            );
            //Mostramos la consulta por consola
            System.out.println("Consulta SQL: " + sentencia);
            //Ejecutamos la consulta
            resultado = stmt.execute(sentencia);
            //Cerrar la sentencia
            stmt.close();
            //Desconectar
            desconectar();
            
            return resultado;
        } catch (SQLException ex) {
            System.out.println("Error al modificar el usuario" + usuarioAnterior.getNombre() + usuarioAnterior.getApellidos() + ". " + ex.getMessage());
            resultado = false;
        }
        return resultado;
    }
    
    //BORRAR USUARIO
    public boolean borrarUsuario(Usuario usuario){
        boolean resultado = true;
        try {
            //Nos conectamos a la BD
            conectar();
            //Creamos la sentencia
            Statement stmt = conexion.createStatement();
            //Preparamos la sentencia SQL
            String sentencia = String.format(
                    "DELETE FROM `usuarios` WHERE `nickname`='%s'",
                    usuario.getNickname());
            //Mostramos la consulta por consola
            System.out.println("Consulta SQL: " + sentencia);
            //Ejecutamos la consulta
            resultado = stmt.execute(sentencia);
            //Cerrar la sentencia
            stmt.close();
            //Desconectar
            desconectar();
            
            return resultado;
        } catch (SQLException ex) {
            System.out.println("Error al borrar el usuario" + usuario.getNickname() + ". " + ex.getMessage());
            resultado = false;
        }
        
        return resultado;
    }
    
    //BUSCAR USUARIO
    public Usuario buscarUsuarioNick(String nicknameUsuario){
        Usuario usuario_buscado = null;
        try {
            //Nos conectamos a la BD
            conectar();
            //Creamos la sentencia
            Statement stmt = conexion.createStatement();
            //Preparamos la sentencia SQL
            String sentencia = String.format(
                    "SELECT * FROM `usuarios` WHERE `nickname`='%s'",
                    nicknameUsuario);
            //Mostramos la consulta por consola
            System.out.println("Consulta SQL: " + sentencia);
            //Ejecutamos la consulta
            ResultSet rs = stmt.executeQuery(sentencia);
            while(rs.next()){
                usuario_buscado = new Usuario(
                        rs.getString("nickname"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getString("user_pass"),
                        rs.getInt("rol")
                );
            }
            //Cierro el resultSet
            rs.close();
            //Cerrar la sentencia
            stmt.close();
            //Desconectar
            desconectar();
        } catch (SQLException ex) {
            System.out.println("Error al buscar el usuario" + nicknameUsuario + ". " + ex.getMessage());
        }
        
        return usuario_buscado;
    }
    
//VENTAS················································································
    
    //INSERTAR PRODUCTO
    
    //LISTAR PRODUCTOS
    
    //MODIFICAR PRODUCTOS
    
    //BORRAR PRODUCTOS
    
    //BUSCAR PRODUCTO
    
    
    
    
    //Insertar Departamento
    
    
    //Modificar Departamento
    
    //Borrar Departamento
    
    
    //Listar Departamentos
    
    
    //Buscar Departamentos
        
}
