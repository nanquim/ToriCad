package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class SQLiteConnection {
    private static final String DRIVER = "org.sqlite.JDBC";
    private static final String URL = "jdbc:sqlite:C:/SQLiteStudio/dbCadastro.db";
    //private static final String USER = "";
    //private static final String PASS = "";
  
    
    //Abre conexao   
    public static Connection getConnection(){
        try {
            //Carregar o driver
            Class.forName(DRIVER);
            
            return DriverManager.getConnection(URL);
            //TODO separar? a 1o é erro no carregamento do driver e a 2o é erro na conexao
        } catch (ClassNotFoundException | SQLException ex) {
           throw new RuntimeException("Erro na conexão", ex);
        }
    }
    //TODO metodo nao utilizado?
    //Fecha conexao
    public static void closeConnection(Connection con){
        
        if(con != null){
            try {
                con.close();
            } catch (SQLException ex) {
                System.err.println("Erro "+ ex);   
            }
        
        }
    }
    public static void closeConnection(Connection con, PreparedStatement stmt){
        
        if(stmt != null){
            try {
                stmt.close();
            } catch (SQLException ex) {
                System.err.println("Erro "+ ex);                 
            }    
        }
        closeConnection(con);
    }
    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs){
        
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException ex) {
                System.err.println("Erro "+ ex);                  
            }    
        }
        closeConnection(con);
    }
}
