/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restful.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import restful.Model.ProductoModel;
import restful.Model.Conexion;

public class ProductoService {
    
    public ArrayList<ProductoModel> getProductos() {
        ArrayList<ProductoModel> lista = new ArrayList<>();
        Conexion conn = new Conexion();
        String sql = "SELECT * FROM productos";

        try {
            Statement stm = conn.getCon().createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                ProductoModel producto = new ProductoModel();
                producto.setCodigo_prod(rs.getInt("codigo_prod"));
                producto.setNombre_prod(rs.getString("nombre_prod"));
                producto.setStock(rs.getInt("stock"));
                producto.setPrecio_venta(rs.getFloat("precio_venta"));
                producto.setNombre_prov(rs.getString("nombre_prov"));
                lista.add(producto);
            }
        } catch (SQLException e) {
        }

        return lista;
    }

    public ProductoModel getProducto(int id) {
        ProductoModel producto = new ProductoModel();
        Conexion conex = new Conexion();
        String Sql = "SELECT * FROM productos WHERE codigo_prod = ?";

        try {

            PreparedStatement pstm = conex.getCon().prepareStatement(Sql);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                producto.setCodigo_prod(rs.getInt("codigo_prod"));
                producto.setNombre_prod(rs.getString("nombre_prod"));
                producto.setStock(rs.getInt("stock"));
                producto.setPrecio_venta(rs.getFloat("precio_venta"));
                producto.setNombre_prov(rs.getString("nombre_prov"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return producto;
    }

    public ProductoModel addProducto(ProductoModel producto) {
        Conexion conex = new Conexion();
        String Sql = "INSERT INTO productos (codigo_prod,nombre_prod,stock,precio_venta,nombre_prov)";
        Sql = Sql + "values (?,?,?,?,?)";

        try {
            PreparedStatement pstm = conex.getCon().prepareStatement(Sql);
            pstm.setInt(1, producto.getCodigo_prod());
            pstm.setString(2, producto.getNombre_prod());
            pstm.setInt(3, producto.getStock());
            pstm.setFloat(4, producto.getPrecio_venta());
            pstm.setString(5, producto.getNombre_prov());
            pstm.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
        return producto;
    }

    public ProductoModel updateProducto(ProductoModel producto) {
        Conexion conn = new Conexion();
        String sql = "UPDATE productos SET nombre_prod=?,stock=?,precio_venta=?, nombre_prov=? WHERE codigo_prod= ?";
        try {
            PreparedStatement pstm = conn.getCon().prepareStatement(sql);
            pstm.setString(1, producto.getNombre_prod());
            pstm.setInt(2, producto.getStock());
            pstm.setFloat(3, producto.getPrecio_venta());
            pstm.setString(4, producto.getNombre_prov());
            pstm.setInt(5, producto.getCodigo_prod());
            pstm.executeUpdate();
        } catch (SQLException excepcion) {
            System.out.println("Ha ocurrido un error al eliminar  " + excepcion.getMessage());
            return null;
        }
        return producto;
    }

    public String delProducto(int id) {
        Conexion conn = new Conexion();

        String sql = "DELETE FROM productos WHERE codigo_prod= ?";
        try {
            PreparedStatement pstm = conn.getCon().prepareStatement(sql);
            pstm.setInt(1, id);
            pstm.executeUpdate();
        } catch (SQLException excepcion) {
            System.out.println("Ha ocurrido un error al eliminar  " + excepcion.getMessage());
            return "{\"Accion\":\"Error\"}";
        }
        return "{\"Accion\":\"Registro Borrado\"}";
    }
}
