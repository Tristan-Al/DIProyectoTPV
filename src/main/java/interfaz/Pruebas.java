package interfaz;

import bd.GestionBD;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import models.Pair;
import models.Producto;
import models.Usuario;
import models.Venta;
import models.Ventas;

public class Pruebas {

    public static void main(String[] args) {
//        GestionBD gestion = new GestionBD("localhost", "root", "", "gestortpv");
        GestionBD gestion = new GestionBD("localhost", "root", "", "gestortpv");

//        //INSERTAR
        Producto producto = new Producto("Cerveza", 2.50, 50);
        Producto producto2 = new Producto("Vino", 2.50, 50);
        Usuario usuario = new Usuario("rbarea", "raul", "barea", 1234, 0);
//
//        gestion.insertarProducto(producto);
//        gestion.insertarProducto(producto2);
//        gestion.insertarUsuario(usuario);
//
//        //LISTAR
//        Producto producto = new Producto("Cerveza", 2.50, 50);
//        Usuario usuario = new Usuario("rbarea", "raul", "barea", "123456", 0);
//
//        Productos listadoProducto = gestion.listarProductos();
//        Usuarios listadoUsuario = gestion.listarUsuarios();
//
//        for (int i = 0; i < listadoProducto.size(); i++) {
//            System.out.println(listadoProducto.getProducto(i));
//        }
//
//        for (int i = 0; i < listadoUsuario.size(); i++) {
//            System.out.println(listadoUsuario.getUsuario(i));
//        }
//
//        //MODIFICAR
//        Producto producto = new Producto("Cerveza", 2.50, 50);
//        Producto producto1 = gestion.buscarProductoNom(producto.getNombre());
//        Usuario usuario1 = new Usuario("rbarea", "raul", "barea", "123456", 0);
//        Producto producto2 = new Producto("Vino", 2.50, 50);
//        Usuario usuario2 = new Usuario("rbarea", "Raul", "Barea Rodriguez", "123456", 0);
//
//        gestion.modificarProducto(producto1, producto2);
//        gestion.modificarUsuario(usuario1, usuario2);
//
//        //BUSCAR
//        Producto producto = new Producto("Cerveza", 2.50, 50);
//        Usuario usuario = new Usuario("rbarea", "raul", "barea", "123456", 0);
//
//        Producto producto2 = gestion.buscarProductoNom(producto.getNombre());
//        Usuario usuario2 = gestion.buscarUsuarioNick(usuario.getNickname());
//
//        System.out.println(producto2);
//        System.out.println(usuario2);
//
//        //BORRAR
//        Producto producto = new Producto("Vino", 2.50, 50);
//        Producto producto2 = gestion.buscarProductoNom(producto.getNombre());
//
//        Usuario usuario2 = new Usuario("rbarea", "Raul", "Barea Rodriguez", "123456", 0);
//
//        gestion.borrarProducto(producto2);
//        gestion.borrarUsuario(usuario2);

        //VENTAS
        ArrayList<Pair> productos = new ArrayList();
        producto.setId_producto(2);
        producto2.setId_producto(3);
//
//        productos.add(new Pair(producto,2));
//        
        Venta ventaPrueba = new Venta(1, 1, usuario, LocalDateTime.of(2023, 2, 26, 19, 49, 28), productos);

//        System.out.println(ventaPrueba.toString());
//        
//        System.out.println(" Anadido una cocacola");
//        
//        ventaPrueba.addProducto(producto);
//        
//        System.out.println(ventaPrueba.toString());
//        gestion.insertarVenta(ventaPrueba);

//        Ventas ventas = gestion.listarVentas();
//        for (int i = 0; i < ventas.size(); i++) {
//            System.out.println(ventas.getVenta(i));
//        }

        
        
        java.sql.Date fecha = java.sql.Date.valueOf(ventaPrueba.getFecha_venta().toLocalDate());
        java.sql.Time hora = java.sql.Time.valueOf(ventaPrueba.getFecha_venta().toLocalTime());
        System.out.println(fecha + " " + hora);
        
        System.out.println("");

        System.out.println(ventaPrueba.getFecha_venta().toString().replaceAll("T", " "));

//        gestion.borrarVenta(ventaPrueba);
        //System.out.println(gestion.buscarVenta(ventaPrueba));;
//        Usuario usuarioModificado = new Usuario("talonso2", "Tristan", "Alonso", 1234, 1);
//        gestion.insertarUsuario(usuarioModificado);
//        
//        ArrayList<Producto> productos2 = new ArrayList();
//        producto2.setId_producto(2);
//        productos2.add(producto2);
//
//        Venta ventaModificada = new Venta(1, 3, usuarioModificado, LocalDateTime.of(2023, 1, 12, 15, 41, 52), productos2);
//        
//        gestion.modificarVenta(ventaPrueba, ventaModificada);
        

        //METODOS
//        producto.setId_producto(2);
//        System.out.println(gestion.compruebaStock(producto, 10));
        
//        System.out.println(gestion.esAdmin(usuario));
    }
}
