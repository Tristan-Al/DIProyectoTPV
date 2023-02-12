package interfaz;

import bd.GestionBD;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import modelos.Producto;
import modelos.Usuario;
import modelos.Venta;
import modelos.Ventas;

public class Pruebas {

    public static void main(String[] args) {
//        GestionBD gestion = new GestionBD("localhost", "root", "", "gestortpv");
        GestionBD gestion = new GestionBD("localhost", "root", "dam_21017245_sge", "gestortpv");

//        //INSERTAR
        Producto producto = new Producto("Cerveza", 2.50, 50, "imgCerveza.png");
        Producto producto2 = new Producto("Vino", 2.50, 50, "imgVino.png");
        Usuario usuario = new Usuario("rbarea", "raul", "barea", 1234, 0);
//
//        gestion.insertarProducto(producto);
//        gestion.insertarProducto(producto2);
//        gestion.insertarUsuario(usuario);
//
//        //LISTAR
//        Producto producto = new Producto("Cerveza", 2.50, 50, "imgCerveza.png");
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
//        Producto producto = new Producto("Cerveza", 2.50, 50, "imgCerveza.png");
//        Producto producto1 = gestion.buscarProductoNom(producto.getNombre());
//        Usuario usuario1 = new Usuario("rbarea", "raul", "barea", "123456", 0);
//        Producto producto2 = new Producto("Vino", 2.50, 50, "imgVino.png");
//        Usuario usuario2 = new Usuario("rbarea", "Raul", "Barea Rodriguez", "123456", 0);
//
//        gestion.modificarProducto(producto1, producto2);
//        gestion.modificarUsuario(usuario1, usuario2);
//
//        //BUSCAR
//        Producto producto = new Producto("Cerveza", 2.50, 50, "imgCerveza.png");
//        Usuario usuario = new Usuario("rbarea", "raul", "barea", "123456", 0);
//
//        Producto producto2 = gestion.buscarProductoNom(producto.getNombre());
//        Usuario usuario2 = gestion.buscarUsuarioNick(usuario.getNickname());
//
//        System.out.println(producto2);
//        System.out.println(usuario2);
//
//        //BORRAR
//        Producto producto = new Producto("Vino", 2.50, 50, "imgVino.png");
//        Producto producto2 = gestion.buscarProductoNom(producto.getNombre());
//
//        Usuario usuario2 = new Usuario("rbarea", "Raul", "Barea Rodriguez", "123456", 0);
//
//        gestion.borrarProducto(producto2);
//        gestion.borrarUsuario(usuario2);

        //VENTAS
//        ArrayList<Producto> productos = new ArrayList();
//        producto.setId_producto(2);
//        producto2.setId_producto(3);
//
//        productos.add(producto);
//        productos.add(producto2);
//        
//        Venta ventaPrueba = new Venta(12, 1, usuario, LocalDateTime.of(2023, 2, 12, 15, 54, 03), productos);

//        gestion.insertarVenta(ventaPrueba);
//        Ventas ventas = gestion.listarVentas();
//        for (int i = 0; i < ventas.size(); i++) {
//            System.out.println(ventas.getVenta(i));
//        }
        //System.out.println(gestion.buscarVenta(ventaPrueba));;
        Usuario usuarioModificado = new Usuario("talonso2", "Tristan", "Alonso", 1234, 1);
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
