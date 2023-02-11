package interfaz;

import bd.GestionBD;
import modelos.Producto;
import modelos.Usuario;

public class Pruebas {

    public static void main(String[] args) {
        GestionBD gestion = new GestionBD("localhost", "root", "", "gestortpv");

//        //INSERTAR
//        Producto producto = new Producto("Cerveza", 2.50, 50, "imgCerveza.png");
//        Usuario usuario = new Usuario("rbarea", "raul", "barea", "123456", 0);
//
//        gestion.insertarProducto(producto);
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
    }
}
