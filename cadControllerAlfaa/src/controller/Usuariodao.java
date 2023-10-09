package controller;

import model.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class Usuariodao extends ConectarDao {
    private PreparedStatement ps;
    public Usuariodao() {
        super();
    }

public void incluir(Usuario obj) {
          sql = "INSERT INTO USUARIOS VALUES (?, ?, ?, ?, ?, ?)";
        try {
            ps = mycon.prepareStatement(sql);
            ps.setString(1, obj.getCpf());
            ps.setString(2, obj.getNome());
            ps.setString(3, obj.getEmail());
            ps.setString(4, obj.getCelular());
            ps.setInt(5, obj.getIdNivel());
            ps.setString ( 6, obj.getSenha());
            ps.execute();
            ps.close();
            JOptionPane.showMessageDialog(null,"Registro Incluído com Sucesso!");
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, "Erro ao incluir usuário!" + err.getMessage());
        }
    }


public ResultSet validarLogin (String login, String senha)   {
    
    sql = "SELECT * , if(idnivel = 1, 'Gerente', 'Operador') as nivel "
            + "FROM USUARIOS WHERE ucase(email) = ucase('"+login+"') "
            + "and senha = ucase('"+ senha +"')";
   
    try {
            ps = mycon.prepareStatement(sql);
            return ps.executeQuery();
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, "Erro ao Buscar usuário!" + err.getMessage());
            return null; 
        }
}


}

