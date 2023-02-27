package bd;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import modelos.Pair;
import modelos.Producto;
import modelos.Productos;
import modelos.Usuario;
import modelos.Usuarios;
import modelos.Venta;
import modelos.Ventas;

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
    public boolean insertarProducto(Producto producto) {
        boolean resultado = true;
        try {
            //Nos conectamos a la BD
            conectar();
            //Creamos la sentencia
            Statement stmt = conexion.createStatement();
            //Preparamos la sentencia SQL
            String sentencia = String.format(
                    "INSERT INTO `productos`(`nombre`, `precio`, `stock`) VALUES ('%s',%s,%s)",
                    producto.getNombre(),
                    producto.getPrecio(),
                    producto.getStock());
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
            System.err.println("Error al insertar el producto " + producto.getNombre() + ". " + ex.getMessage());
            resultado = false;
        }

        return resultado;
    }

    //LISTAR PRODUCTOS
    public Productos listarProductos() {
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
            while (rs.next()) {
                listado.addProducto(new Producto(
                        rs.getInt("id_producto"),
                        rs.getString("nombre"),
                        rs.getDouble("precio"),
                        rs.getInt("stock")
                ));
            }
            //Cierro el resultSet
            rs.close();
            //Cerrar la sentencia
            stmt.close();
            //Desconectar
            desconectar();
        } catch (SQLException ex) {
            System.err.println("Error al listar los productos. " + ex.getMessage());
        }

        return listado;
    }

    //MODIFICAR PRODUCTOS
    public boolean modificarProducto(Producto producto) {
        boolean resultado = true;
        try {
            //Nos conectamos a la BD
            conectar();
            //Creamos la sentencia
            Statement stmt = conexion.createStatement();
            //Preparamos la sentencia SQL
            String sentencia = String.format(
                    "UPDATE `productos` SET `nombre`='%s', `precio`=%s, `stock`=%s WHERE `id_producto`=%s",
                    producto.getNombre(),
                    producto.getPrecio(),
                    producto.getStock(),
                    producto.getId_producto()
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
            System.err.println("Error al modificar el producto" + producto.getNombre() + ". " + ex.getMessage());
            resultado = false;
        }
        return resultado;
    }

    //BORRAR PRODUCTOS
    public boolean borrarProducto(Producto producto) {
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
            System.err.println("Error al borrar el producto" + producto.getNombre() + ". " + ex.getMessage());
            resultado = false;
        }

        return resultado;
    }

    //BUSCAR PRODUCTO
    public Producto buscarProductoNom(String nombreProducto) {
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
            while (rs.next()) {
                producto_buscado = new Producto(
                        rs.getInt("id_producto"),
                        rs.getString("nombre"),
                        rs.getDouble("precio"),
                        rs.getInt("stock")
                );
            }
            //Cierro el resultSet
            rs.close();
            //Cerrar la sentencia
            stmt.close();
            //Desconectar
            desconectar();
        } catch (SQLException ex) {
            System.err.println("Error al buscar el producto" + nombreProducto + ". " + ex.getMessage());
        }

        return producto_buscado;
    }

    public Producto buscarProductoId(int id_producto) {
        Producto producto_buscado = null;
        try {
            //Nos conectamos a la BD
            conectar();
            //Creamos la sentencia
            Statement stmt = conexion.createStatement();
            //Preparamos la sentencia SQL
            String sentencia = String.format(
                    "SELECT * FROM `productos` WHERE id_producto='%s'",
                    id_producto);
            //Mostramos la consulta por consola
            System.out.println("Consulta SQL: " + sentencia);
            //Ejecutamos la consulta
            ResultSet rs = stmt.executeQuery(sentencia);
            while (rs.next()) {
                producto_buscado = new Producto(
                        rs.getInt("id_producto"),
                        rs.getString("nombre"),
                        rs.getDouble("precio"),
                        rs.getInt("stock")
                );
            }
            //Cierro el resultSet
            rs.close();
            //Cerrar la sentencia
            stmt.close();
            //Desconectar
            desconectar();
        } catch (SQLException ex) {
            System.err.println("Error al buscar el producto " + id_producto + ". " + ex.getMessage());
        }

        return producto_buscado;
    }

//USUARIOS··············································································
    //INSERTAR USUARIO
    public boolean insertarUsuario(Usuario usuario) {
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
            System.err.println("Error al insertar el usuario" + usuario.getNombre() + ". " + ex.getMessage());
            resultado = false;
        }

        return resultado;
    }

    //LISTAR USUARIO
    public Usuarios listarUsuarios() {
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
            while (rs.next()) {
                listado.addUsuario(new Usuario(
                        rs.getString("nickname"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getInt("user_pass"),
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
            System.err.println("Error al listar los usuarios. " + ex.getMessage());
        }

        return listado;
    }

    //MODIFICAR USUARIO
    public boolean modificarUsuario(Usuario usuario) {
        boolean resultado = true;
        try {
            //Nos conectamos a la BD
            conectar();
            //Creamos la sentencia
            Statement stmt = conexion.createStatement();
            //Preparamos la sentencia SQL
            String sentencia = String.format(
                    "UPDATE `usuarios` SET `nickname`='%s', `nombre`='%s', `apellidos`='%s', `user_pass`='%s', `rol`=%s WHERE `nickname`='%s'",
                    usuario.getNickname(),
                    usuario.getNombre(),
                    usuario.getApellidos(),
                    usuario.getPassword(),
                    usuario.getRol(),
                    usuario.getNickname()
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
            System.err.println("Error al modificar el usuario" + usuario.getNombre() + usuario.getApellidos() + ". " + ex.getMessage());
            resultado = false;
        }
        return resultado;
    }

    //BORRAR USUARIO
    public boolean borrarUsuario(Usuario usuario) {
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
            System.err.println("Error al borrar el usuario" + usuario.getNickname() + ". " + ex.getMessage());
            resultado = false;
        }

        return resultado;
    }

    //BUSCAR USUARIO
    public Usuario buscarUsuarioNick(String nicknameUsuario) {
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
            while (rs.next()) {
                usuario_buscado = new Usuario(
                        rs.getString("nickname"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getInt("user_pass"),
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
            System.err.println("Error al buscar el usuario" + nicknameUsuario + ". " + ex.getMessage());
        }

        return usuario_buscado;
    }

//VENTAS················································································
    //INSERTAR VENTA
    public boolean insertarVenta(Venta venta) {
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
            for (Pair par : venta.getProductos()) {
                sentencia = String.format(
                        "INSERT INTO `detalle_venta`"
                                + "(`id_venta`, `id_producto`, `num_linea`, `cantidad`) "
                                + "VALUES ('%s','%s','%s','%s')",
                        ventaInsertada.getId(),
                        par.getProducto().getId_producto(),
                        numLinea,
                        par.getCantidad()
                );
                numLinea++;
                System.out.println("Consulta SQL: " + sentencia);
                resultado = stmt.execute(sentencia);

            }
            //Cerrar la sentencia
            stmt.close();
            //Desconectar
            desconectar();

            return resultado;
        } catch (SQLException ex) {
            System.err.println("Error al insertar venta. " + ex.getMessage());
            resultado = false;
        }

        return resultado;
    }

    //LISTAR VENTAS
    public Ventas listarVentas() {
        Ventas listado = new Ventas();
        try {
            //Nos conectamos a la BD
            conectar();
            //Creamos la sentencia
            Statement stmt = conexion.createStatement();
            //Preparamos la sentencia SQL
            String sentencia = String.format(
                    "SELECT * FROM `ventas` INNER JOIN `usuarios` ON usuarios.nickname = ventas.nickname_usuario");
            //Mostramos la consulta por consola
            System.out.println("Consulta SQL: " + sentencia);
            //Ejecutamos la consulta
            ResultSet rs = stmt.executeQuery(sentencia);
            //Annadimos la venta a la lista
            while (rs.next()) {
                listado.addVenta(getVenta(rs));
            }
            //Cierro el resultSet
            rs.close();
            //Cerrar la sentencia
            stmt.close();
            //Desconectar
            desconectar();
        } catch (SQLException ex) {
            System.err.println("Error al listar los productos. " + ex.getMessage());
        }

        return listado;

    }
    //MODIFICAR VENTA

    public boolean modificarVenta(Venta venta) {
        boolean resultado = true;
        try {
            //Nos conectamos a la BD
            conectar();
            //Creamos la sentencia
            Statement stmt = conexion.createStatement();
            //Preparamos la sentencia SQL
            String sentencia;

            //METER LOS PRODUCTOS DE LA VENTA POSTERIOR
            //Por cada producto insertamos una nueva linea en la tabla detalle ventas
            int numLinea = 0;
            for (Pair par : venta.getProductos()) {
                sentencia = String.format(
                        "INSERT INTO `detalle_venta`"
                                + "(`id_venta`, `id_producto`, `num_linea`, `cantidad`) "
                                + "VALUES ('','','','')",
                        venta.getId(),
                        par.getProducto().getId_producto(),
                        numLinea,
                        par.getCantidad()
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
            System.err.println("Error al modificar la venta" + venta + ". " + ex.getMessage());
            resultado = false;
        }
        return resultado;
    }

    //BORRAR VENTA
    public boolean borrarVentaId(int id_venta) {
        boolean resultado = true;
        try {
            //Nos conectamos a la BD
            conectar();
            //Creamos la sentencia
            Statement stmt = conexion.createStatement();
            //Preparamos la sentencia SQL
            String sentencia = String.format(
                    "DELETE FROM `ventas` WHERE `id_venta` = %s",
                    id_venta);
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
            System.err.println("Error al borrar la venta " + id_venta + ". " + ex.getMessage());
            resultado = false;
        }
        return resultado;
    }

    public boolean borrarVenta(Venta venta) {
        boolean resultado = true;
        try {
            //Nos conectamos a la BD
            conectar();
            //Creamos la sentencia
            Statement stmt = conexion.createStatement();
            //Preparamos la sentencia SQL
            String sentencia = String.format(
                    "DELETE FROM `ventas` "
                    + "WHERE `nickname_usuario` = '%s' "
                    + "AND `fecha_venta` = '%s' "
                    + "AND `num_mesa` = '%s'",
                    venta.getUsuario().getNickname(),
                    venta.getFecha_venta().toString().replaceAll("T", " "),
                    venta.getNum_mesa());
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
            System.err.println("Error al borrar la venta . " + ex.getMessage());
            resultado = false;
        }
        return resultado;
    }


    //BUSCAR VENTA
    public Venta buscarVenta(Venta venta) {
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
            while (rs.next()) {
                venta_buscada = getVenta(rs);

            }
            //Cierro el resultSet
            rs.close();
            //Cerrar la sentencia
            stmt.close();
            //Desconectar
            desconectar();
        } catch (SQLException ex) {
            System.err.println("Error al buscar la venta " + venta.toString() + ex.getMessage());
        }

        return venta_buscada;
    }
    
    //BUSCAR VENTA POR ID
    public Venta buscarVentaID(Venta venta) {
        Venta venta_buscada = null;
        try {
            //Nos conectamos a la BD
            conectar();
            //Creamos la sentencia
            Statement stmt = conexion.createStatement();
            //Preparamos la sentencia SQL
            String sentencia = String.format(
                    "SELECT * FROM `ventas` WHERE id_venta=%s",
                    venta.getId());
            //Mostramos la consulta por consola
            System.out.println("Consulta SQL: " + sentencia);
            //Ejecutamos la consulta
            ResultSet rs = stmt.executeQuery(sentencia);
            while (rs.next()) {
                venta_buscada = getVenta(rs);

            }
            //Cierro el resultSet
            rs.close();
            //Cerrar la sentencia
            stmt.close();
            //Desconectar
            desconectar();
        } catch (SQLException ex) {
            System.err.println("Error al buscar la venta por id." + ex.getMessage());
        }

        return venta_buscada;
    }
    
    //METODOS················································································
    /**
     * Metodo privado para obtener una Venta con todos sus productos a partir de
     * un ResultSet
     */
    private Venta getVenta(ResultSet rs) throws SQLException {
        Venta venta = new Venta(
                rs.getInt("id_venta"),
                rs.getInt("num_mesa"),
                new Usuario(
                        rs.getString("nickname_usuario"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getInt("user_pass"),
                        rs.getInt("rol")),
                LocalDateTime.parse(rs.getString("fecha_venta").replaceAll(" ", "T")),
                new ArrayList()
        );

        //Creamos una sentencia nueva
        Statement stmt = conexion.createStatement();
        //Preparamos la sentencia SQL para coger todos los productos de la venta
        String sentencia = String.format(
                "SELECT * FROM `detalle_venta` "
                + "INNER JOIN productos ON "
                + "detalle_venta.id_producto = productos.id_producto "
                + "WHERE `id_venta` = %s",
                venta.getId());
        //Mostramos la consulta por consola
        System.out.println("Consulta SQL: " + sentencia);
        //Ejecutamos la consulta
        rs = stmt.executeQuery(sentencia);
        // Mientras que haya productos los anade al arrayList de productos de
        // la venta
        while (rs.next()) {
            venta.addPairProducto(
                    new Pair(
                        new Producto(rs.getInt("id_producto"),
                            rs.getString("nombre"),
                            rs.getDouble("precio"),
                            rs.getInt("stock")),
                 rs.getInt("cantidad")));
        }
        //Cierro el resultSet
        rs.close();
        //Cerrar la sentencia
        stmt.close();

        return venta;
    }

    /**
     * Borra todos los productos de la venta en la tabla detalle_venta
     *
     * @return Si lo ha conseguido borrar o no
     */
    private boolean borraDetalleVenta(int id_venta) {
        boolean resultado = true;
        try {
            //Nos conectamos a la BD
            conectar();
            //Creamos la sentencia
            Statement stmt = conexion.createStatement();
            //Preparamos la sentencia SQL
            String sentencia = String.format(
                    "DELETE FROM `detalle_venta` WHERE `id_venta` = %s",
                    id_venta);
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
            System.err.println("Error al borrar los detalled e  " + id_venta + ". " + ex.getMessage());
            resultado = false;
        }
        return resultado;
    }

    /**
     * Comprueba el stock de un producto en la BD
     *
     * @param producto
     * @param cantidad
     * @return Si hay stock o no
     */
    public boolean compruebaStock(Producto producto, int cantidad) {
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
            while (rs.next()) {
                return rs.getInt(1) >= cantidad;
            }
            //Cierro el resultSet
            rs.close();
            //Cerrar la sentencia
            stmt.close();
            //Desconectar
            desconectar();
        } catch (SQLException ex) {
            System.err.println("Error al encontrar el producto. " + producto.getNombre() + ". " + ex.getMessage());
        }

        return false;
    }

    /**
     * Comprueba si hay stock suficiente y en caso de que hubiera resta la
     * cantidad al stock actual del producto
     *
     * @param producto
     * @param cantidad
     * @return
     */
    public boolean actualizaStock(Producto producto, int cantidad) {
        boolean resultado = false;
        Producto producto_buscado = null;

        //Buscamos el producto en la BD
        producto_buscado = buscarProductoId(producto.getId_producto());

        //Cogemos el stock actual
        int stockActual = producto_buscado.getStock();

        //Comprobamos que hay stock suficiente
        if (compruebaStock(producto_buscado, cantidad)) {

            //Actualizamos el stock restando la cantidad indicada
            producto_buscado.setStock(stockActual - cantidad);

            //Modificamos el producto con el stock cambiado en la BD
            modificarProducto(producto_buscado);

            resultado = true;

        } else {
            System.out.println("No hay suficiente stock para el producto: "
                    + producto.getNombre() + ", stock: " + producto.getStock());
            resultado = false;
        }

        return resultado;
    }

    /**
     * Comprueba si el usuario pasado es administrador
     *
     * @param usuario
     * @return true si es administrador o false si no
     */
    public boolean esAdmin(Usuario usuario) {
        Usuario usuario_buscado = buscarUsuarioNick(usuario.getNickname());
        return usuario_buscado.getRol() == 0;
    }

    public void setFoto(int idProducto, File f) {
        PreparedStatement pstmt;
        FileInputStream fis;
        String sql;

        try {
            conectar();

            sql = "UPDATE `productos` SET imagen = ? WHERE id_producto = ?";
            fis = new FileInputStream(f);

            pstmt = conexion.prepareStatement(sql);
            pstmt.setBinaryStream(1, fis, (int) f.length());
            pstmt.setInt(2, idProducto);
            pstmt.executeUpdate();

            desconectar();
        } catch (SQLException | FileNotFoundException ex) {
            System.err.println("Error al insertar la imagen del producto." + ex.getMessage());
        }

    }

    public ImageIcon getBlobBD(int idProducto) {
        ImageIcon imgProducto = null;
        PreparedStatement pstmt;
        int blodlength;
        byte[] blobAsBytes;
        String sql;

        try {
            conectar();

            sql = "SELECT `imagen` FROM `productos` WHERE id_producto = " + idProducto;
            System.out.println(sql  );
            pstmt = conexion.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Blob blob = rs.getBlob("imagen");
                blodlength = (int) blob.length();
                blobAsBytes = blob.getBytes(1, blodlength);
                final BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(blobAsBytes));
                imgProducto = new ImageIcon(bufferedImage);
            }
        } catch (SQLException | IOException ex) {
            System.err.println("Error al recuperar la imagen del producto. " + ex.getMessage());
        }
        return imgProducto;
    }

    public static BufferedImage resizeToBufferedImage(File f, int newW, int newH) {
        BufferedImage bufferedImage = null;

        try {
            bufferedImage = ImageIO.read(f);
        } catch (IOException e) {

        }
        int w = bufferedImage.getWidth();
        int h = bufferedImage.getHeight();
        BufferedImage bufim = new BufferedImage(newW, newH, bufferedImage.getType());
        Graphics2D g = bufim.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(bufferedImage, 0, 0, newW, newH, 0, 0, w, h, null);
        g.dispose();

        return bufim;
    }

    public InputStream resizeFromFile(File f, int newW, int newH) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        RenderedImage bufferedImage = resizeToBufferedImage(f, newW, newH);
        try {
            ImageIO.write(bufferedImage, "png", baos);
        } catch (IOException ex) {
            Logger.getLogger(GestionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ByteArrayInputStream(baos.toByteArray());
    }

    /**
     * Metodo para comprobar si el id y la contrasena del usuario
     * son correctas 
     * 
     * @param nickname
     * @param password
     * @return Si son correctas o no
     */
    public boolean compruebaPassword(String nickname, int password) {
        boolean salida = false;
        ResultSet rs;
        conectar();
        try {
            Statement sentencia = conexion.createStatement();
            String sql = String.format("SELECT * FROM `usuarios` WHERE `nickname` = '%s' AND `user_pass` = %s",
                    nickname,
                    password
            );
            sentencia.execute(sql);
            rs = sentencia.getResultSet();
            while (rs.next()) {
                salida = true;
            }
            rs.close();
            sentencia.close();
            conexion.close();

        } catch (SQLException e) {
            System.err.println("Error al devolver la lista: " + e.getMessage());
        }
        return salida;
    }
    
    
}
