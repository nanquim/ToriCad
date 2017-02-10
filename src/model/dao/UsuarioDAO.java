package model.dao;

import connection.SQLiteConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.Usuario;

public class UsuarioDAO {
    
    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private Usuario u = null;
    private String sql;

    public UsuarioDAO() {
        this.con = SQLiteConnection.getConnection();        
    }

    public boolean saveUserData(Usuario u){          
        
            sql = "INSERT INTO Conta (id_conta, email, senha) VALUES"
                    + "(?,?,?)";
            try{
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                           
            ps.setString(2, u.getConta().getEmail());
            String pass = new String(u.getConta().getPass());
            ps.setString(3, pass);
            ps.executeUpdate();                
                try{
                ResultSet chave = ps.getGeneratedKeys();
                    if(chave.next()){
                        int fkConta = chave.getInt(1);
                        u.getConta().setId(fkConta);          
                }
                }catch(SQLException e){
                    System.err.println("Erro ao recuperar chave estrangeira\n");
                    e.printStackTrace();
                }
                saveUser(u);               
            return true;
            }catch(SQLException e){
                System.err.println("Erro ao inserir conta\n");
                e.printStackTrace();
                return false;
            }
    }
    
    public boolean saveUser(Usuario u){
        
        sql = "INSERT INTO Usuario (id_conta, nome, sobrenome, "
              + "usuario, dtNascimento, telefone) VALUES (?,?,?,?,?,?)";
        
        try{
            ps = con.prepareStatement(sql);

            ps.setInt(1, u.getConta().getId());
            ps.setString(2, u.getNome());
            ps.setString(3, u.getSobrenome());
            ps.setString(4, u.getUsuario());

            ps.setString(5, u.getDtNascimento());

            ps.setString(6, u.getTelefone());
            ps.executeUpdate();
            return true;
        }catch (SQLException e) {
            System.err.println("Erro ao inserir usuário\n");
            e.printStackTrace();
            return false;
        }finally{
                try {
                    ps.close();
                    con.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }             
    }
}
    
    public ResultSet getUserData(String u){
    
        String sql = "SELECT  nome, sobrenome, usuario, dtNascimento, telefone, "
                + "email FROM Usuario JOIN Conta ON Usuario.id_conta = "
                + "Conta.id_conta WHERE usuario = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, u);
            rs = ps.executeQuery();
            } catch (SQLException e) {
            System.err.println("Erro ao recuperar dados");
            e.printStackTrace();
        }
        return rs;//TODO nao consegui fechar ps e rs
    }
    
    
    public boolean login(String u, String p){ 
        boolean logado = false;
        
        String queryUsuario = "SELECT id_conta FROM Usuario WHERE usuario = ?";
        String querySenha = "SELECT senha FROM Conta WHERE id_conta = ?";
        int id_conta = 0;
        String bdSenha = null;
        try{
            ps = con.prepareStatement(queryUsuario);
            ps.setString(1, u);
            
            rs = ps.executeQuery();
            if(!rs.next()){
                System.err.println("Usuario não encontrado");
            }else{
                do{
                    id_conta = rs.getInt("id_conta");
                }while(rs.next());
            }
            rs.close();
            ps.close();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
        try{
            if(id_conta == 0){
                System.err.println("id_conta sem valor");
            }
            ps = con.prepareStatement(querySenha);
            ps.setInt(1, id_conta);
            rs = ps.executeQuery();
            if(!rs.next()){
                System.err.println("Erro ao buscar senha");
            }
            bdSenha = rs.getString("senha");
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        if(bdSenha == null){
            System.err.println("Senha nao recuperada");
        }else{
            if(bdSenha.equals(p)){
                logado = true;
            }
        }
        return logado;
    }
    }