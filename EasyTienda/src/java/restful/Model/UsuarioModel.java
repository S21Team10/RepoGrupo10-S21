/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restful.Model;
import java.util.ArrayList;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UsuarioModel {
    
    private int num_id;
    private String nombre_usu;
    private String apellidos_usu;
    private String tipo_usu;
    private String username;
    private String password;
    private String dir_usu;

    public int getNum_id() {
        return num_id;
    }

    public void setNum_id(int num_id) {
        this.num_id = num_id;
    }

    public String getNombre_usu() {
        return nombre_usu;
    }

    public void setNombre_usu(String nombre_usu) {
        this.nombre_usu = nombre_usu;
    }

    public String getApellidos_usu() {
        return apellidos_usu;
    }

    public void setApellidos_usu(String apellidos_usu) {
        this.apellidos_usu = apellidos_usu;
    }

    public String getTipo_usu() {
        return tipo_usu;
    }

    public void setTipo_usu(String tipo_usu) {
        this.tipo_usu = tipo_usu;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDir_usu() {
        return dir_usu;
    }

    public void setDir_usu(String dir_usu) {
        this.dir_usu = dir_usu;
    }
}
    


