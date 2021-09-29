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
import restful.Model.UsuarioModel;
import restful.Model.Conexion;

public class UsuarioService {
    
    public ArrayList<UsuarioModel> getUsuarios() {
        ArrayList<UsuarioModel> lista = new ArrayList<>();
        Conexion conn = new Conexion();
        String sql = "SELECT * FROM usuario";

        try {
            Statement stm = conn.getCon().createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                UsuarioModel usuario = new UsuarioModel();
                usuario.setNombre_usu(rs.getString("nombre_usu"));
                usuario.setApellidos_usu(rs.getString("apellidos_usu"));
                usuario.setTipo_usu(rs.getString("tipo_usu"));
                usuario.setUsername(rs.getString("username"));
                usuario.setPassword(rs.getString("password"));
                usuario.setNum_id(rs.getInt("num_id"));
                usuario.setDir_usu(rs.getString("dir_usu"));
                lista.add(usuario);
            }
        } catch (SQLException e) {
        }

        return lista;
    }

    public UsuarioModel getUsuario(int id) {
        UsuarioModel usuario = new UsuarioModel();
        Conexion conex = new Conexion();
        String Sql = "SELECT * FROM usuario WHERE num_id = ?";

        try {

            PreparedStatement pstm = conex.getCon().prepareStatement(Sql);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                usuario.setNombre_usu(rs.getString("nombre_usu"));
                usuario.setApellidos_usu(rs.getString("apellidos_usu"));
                usuario.setTipo_usu(rs.getString("tipo_usu"));
                usuario.setUsername(rs.getString("username"));
                usuario.setPassword(rs.getString("password"));
                usuario.setNum_id(rs.getInt("num_id"));
                usuario.setDir_usu(rs.getString("dir_usu"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return usuario;
    }

    public UsuarioModel addUsuario(UsuarioModel usuario) {
        Conexion conex = new Conexion();
        String Sql = "INSERT INTO usuario (nombre_usu, apellidos_usu, tipo_usu, username, password, num_id, dir_usu)";
        Sql = Sql + "values (?,?,?,?,?,?,?)";

        try {
            PreparedStatement pstm = conex.getCon().prepareStatement(Sql);
            pstm.setString(1, usuario.getNombre_usu());
            pstm.setString(2, usuario.getApellidos_usu());
            pstm.setString(3, usuario.getTipo_usu());
            pstm.setString(4, usuario.getUsername());
            pstm.setString(5, usuario.getPassword());
            pstm.setInt(6, usuario.getNum_id());
            pstm.setString(7, usuario.getDir_usu());
            pstm.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
        return usuario;
    }

    public UsuarioModel updateUsuario(UsuarioModel usuario) {
        Conexion conn = new Conexion();
        String sql = "UPDATE usuario SET nombre_usu=?,apellidos_usu=?,tipo_usu=?,username=?,password=?,dir_usu=?  WHERE num_id= ?";
        try {
            PreparedStatement pstm = conn.getCon().prepareStatement(sql);
            pstm.setString(1, usuario.getNombre_usu());
            pstm.setString(2, usuario.getApellidos_usu());
            pstm.setString(3, usuario.getTipo_usu());
            pstm.setString(4, usuario.getUsername());
            pstm.setString(5, usuario.getPassword());
            pstm.setString(6, usuario.getDir_usu());
            pstm.setInt(7, usuario.getNum_id());
            pstm.executeUpdate();
        } catch (SQLException excepcion) {
            System.out.println("Ha ocurrido un error al eliminar  " + excepcion.getMessage());
            return null;
        }
        return usuario;
    }

    public String delUsuario(int id) {
        Conexion conn = new Conexion();

        String sql = "DELETE FROM usuario WHERE num_id= ?";
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
