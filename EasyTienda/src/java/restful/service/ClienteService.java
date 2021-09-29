package restful.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import restful.Model.ClienteModel;
import restful.Model.Conexion;

public class ClienteService {

    public ArrayList<ClienteModel> getClientes() {
        ArrayList<ClienteModel> lista = new ArrayList<>();
        Conexion conn = new Conexion();
        String sql = "SELECT * FROM clientes";

        try {
            Statement stm = conn.getCon().createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                ClienteModel cliente = new ClienteModel();
                cliente.setId_cliente(rs.getInt("id_cliente"));
                cliente.setNombre_cliente(rs.getString("nombre_cliente"));
                cliente.setApellidos_cliente(rs.getString("apellidos_cliente"));
                cliente.setTel_cliente(rs.getString("tel_cliente"));
                lista.add(cliente);
            }
        } catch (SQLException e) {
        }

        return lista;
    }

    public ClienteModel getCliente(int id) {
        ClienteModel cliente = new ClienteModel();
        Conexion conex = new Conexion();
        String Sql = "SELECT * FROM clientes WHERE id_cliente = ?";

        try {

            PreparedStatement pstm = conex.getCon().prepareStatement(Sql);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                cliente.setId_cliente(rs.getInt("id_cliente"));
                cliente.setNombre_cliente(rs.getString("nombre_cliente"));
                cliente.setApellidos_cliente(rs.getString("apellidos_cliente"));
                cliente.setTel_cliente(rs.getString("tel_cliente"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return cliente;
    }

    public ClienteModel addCliente(ClienteModel cliente) {
        Conexion conex = new Conexion();
        String Sql = "INSERT INTO clientes(id_cliente,nombre_cliente,apellidos_cliente,tel_cliente)";
        Sql = Sql + "values (?,?,?,?)";

        try {
            PreparedStatement pstm = conex.getCon().prepareStatement(Sql);
            pstm.setInt(1, cliente.getId_cliente());
            pstm.setString(2, cliente.getNombre_cliente());
            pstm.setString(3, cliente.getApellidos_cliente());
            pstm.setString(4, cliente.getTel_cliente());
            pstm.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
        return cliente;
    }

    public ClienteModel updateCliente(ClienteModel cliente) {
        Conexion conn = new Conexion();
        String sql = "UPDATE clientes SET nombre_cliente=?,apellidos_cliente=?,tel_cliente=? WHERE id_cliente= ?";
        try {
            PreparedStatement pstm = conn.getCon().prepareStatement(sql);
            pstm.setString(1, cliente.getNombre_cliente());
            pstm.setString(2, cliente.getApellidos_cliente());
            pstm.setString(3, cliente.getTel_cliente());
            pstm.setInt(4, cliente.getId_cliente());
            pstm.executeUpdate();
        } catch (SQLException excepcion) {
            System.out.println("Ha ocurrido un error al eliminar  " + excepcion.getMessage());
            return null;
        }
        return cliente;
    }

    public String delCliente(int id) {
        Conexion conn = new Conexion();

        String sql = "DELETE FROM clientes WHERE id_cliente= ?";
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
