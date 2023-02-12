package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import modelos.Producto;
import modelos.Productos;
import modelos.Usuario;
import modelos.Usuarios;
import modelos.Venta;

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
    
    //INSERTAR VENTA
    public boolean insertarVenta(Venta venta){
        boolean resultado = true;
        try {
            //Nos conectamos a la BD
            conectar();
            //Creamos la sentencia
            Statement stmt = conexion.createStatement();
            //Preparamos la sentencia SQL
            String sentencia = String.format(
                    "INSERT INTO `ventas`"
                    + "(`nickname_usuario`, `fecha_venta`, `num_mesa`)"
                    + " VALUES ('%s',NOW(),%s)",
                    venta.getUsuario().getNickname(),
                     venta.getNum_mesa());
            //Mostramos la consulta por consola
            System.out.println("Consulta SQL: " + sentencia);
            //Ejecutamos la consulta
            resultado = stmt.execute(sentencia);

            // Obtenemos la venta que acabamos de insertar
            Venta ventaInsertada = buscarVenta(venta);

            //Por cada producto insertamos una nueva linea en la tabla detalle ventas
            int numLinea = 0;
            for (Producto producto : venta.getProductos()) {
                sentencia = String.format(
                        "INSERT INTO `detalle_venta`"
                        + "(`id_venta`, `id_producto`, `num_linea`)"
                        + " VALUES ('%s','%s','%s')",
                        ventaInsertada.getId(),
                        producto.getId_producto(),
                        numLinea
                );
                numLinea++;
                resultado = stmt.execute(sentencia);
                
            }
            //Cerrar la sentencia
            stmt.close();
            //Desconectar
            desconectar();

            return resultado;
        } catch (SQLException ex) {
            System.out.println("Error al insertar venta. " + ex.getMessage());
            resultado = false;
        }

        return resultado;
    }
    //LISTAR VENTAS
    
    //MODIFICAR VENTA
    
    //BORRAR VENTA
    
    //BUSCAR VENTA
    public Venta buscarVenta(Venta venta){
        Venta venta_buscada = null;
        try {
            //Nos conectamos a la BD
            conectar();
            //Creamos la sentencia
            Statement stmt = conexion.createStatement();
            //Preparamos la sentencia SQL
            String sentencia = String.format(
                    "SELECT * FROM `ventas` "
                            + "INNER JOIN `usuarios` ON usuarios.nickname = ventas.nickname_usuario "
                            + "WHERE `nickname_usuario` = '%s' "
                            + "AND `num_mesa` = '%s' "
                            + "AND `fecha_venta` = (SELECT MAX(fecha_venta) from ventas WHERE `nickname_usuario` = '%s' AND `num_mesa` = %s) ",
                    venta.getUsuario().getNickname(),
                    venta.getNum_mesa(),
                    venta.getUsuario().getNickname(),
                    venta.getNum_mesa());
            //Mostramos la consulta por consola
            System.out.println("Consulta SQL: " + sentencia);
            //Ejecutamos la consulta
            ResultSet rs = stmt.executeQuery(sentencia);
            while(rs.next()){
                venta_buscada = new Venta(
                        rs.getInt("id_venta"), 
                        rs.getInt("num_mesa"), 
                        new Usuario(
                                rs.getString("nickname_usuario"), 
                                rs.getString("nombre"), 
                                rs.getString("apellidos"), 
                                rs.getString("user_pass"), 
                                rs.getInt("rol")), 
                        LocalDateTime.parse(rs.getString("fecha_venta").replaceAll(" ", "T")), 
                        null
                );
                
                
            }
            //Cierro el resultSet
            rs.close();
            //Cerrar la sentencia
            stmt.close();
            
            if (venta_buscada != null) {
                //Creamos una sentencia nueva
                stmt = conexion.createStatement();
                //Preparamos la sentencia SQL
                sentencia = String.format(
                        "SELECT * FROM `detalle_venta` "
                                + "INNER JOIN productos ON "
                                + "detalle_venta.id_producto = productos.id_producto "
                                + "WHERE `id_venta` = %s",
                        venta_buscada.getId());
                //Mostramos la consulta por consola
                System.out.println("Consulta SQL: " + sentencia);
                //Ejecutamos la consulta
                rs = stmt.executeQuery(sentencia);
                ArrayList<Producto> productos = new ArrayList();
                while(rs.next()){
                    productos.add(
                            new Producto(rs.getInt("id_producto"),
                                    rs.getString("nombre"), 
                                    rs.getDouble("precio"), 
                                    rs.getInt("stock"), 
                                    rs.getString("imagen"))
                    );
                }
                venta_buscada.setProductos(productos);
                //Cierro el resultSet
                rs.close();
                //Cerrar la sentencia
                stmt.close();
            }            
            //Desconectar
            desconectar();
        } catch (SQLException ex) {
            System.out.println("Error al buscar la venta " + venta.toString() + ex.getMessage());
        }
        
        return venta_buscada;
    }
    
    //METODOS················································································

    
    /**
     * Comprueba el stock de un producto en la BD
     * @param producto 
     * @return  Si hay stock o no
     */
    public boolean compruebaStock(Producto producto){
        try {
            //Nos conectamos a la BD
            conectar();
            //Creamos la sentencia
            Statement stmt = conexion.createStatement();
            //Preparamos la sentencia SQL
            String sentencia = String.format(
                    "SELECT `stock` FROM `productos` WHERE `id_producto` = %s",
                    producto.getId_producto());
            //Mostramos la consulta por consola
            System.out.println("Consulta SQL: " + sentencia);
            //Ejecutamos la consulta
            ResultSet rs = stmt.executeQuery(sentencia);
            while(rs.next()){
                return rs.getInt(1) >= 1;
            }
            //Cierro el resultSet
            rs.close();
            //Cerrar la sentencia
            stmt.close();
            //Desconectar
            desconectar();
        } catch (SQLException ex) {
            System.out.println("Error al encontrar el producto. " + producto.getNombre() + ". " + ex.getMessage());
        }
        
        return false;
    }
}
