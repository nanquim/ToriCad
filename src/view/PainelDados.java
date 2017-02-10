package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

public class PainelDados extends JPanel {

    private final Color azul;
    private final Color vinho;
    private JPanel form;
    
    private JLabel titulo;
    private JLabel lbNome;
    private JLabel txtNome;
    private JLabel lbSobrenome;
    private JLabel txtSobrenome;
    private JLabel lbUsuario;
    private JLabel txtUsuario;
    private JLabel lbDtNasc;
    private JLabel txtDtNasc;
    private JLabel lbTelefone;
    private JLabel txtTelefone; 
    private JLabel lbEmail;
    private JLabel txtEmail;
    private ResultSet rs;
    
    public PainelDados(ResultSet rs) {
        azul = new Color(109, 171, 191); 
        
        this.setPreferredSize(new Dimension(500, 580));
        this.setLayout(new MigLayout());
        this.setBackground(azul);
        
        form = new JPanel();
        
        form.setLayout(new MigLayout());
        form.setPreferredSize(new Dimension(400, 480));
        form.setBackground(azul);    
        titulo = new JLabel("Informações de Cadastro:");
        lbNome = new JLabel("Nome:");
        txtNome = new JLabel();
        lbSobrenome = new JLabel("Sobrenome:");
        txtSobrenome = new JLabel();
        lbUsuario = new JLabel("Usuário:");
        txtUsuario = new JLabel();
        lbDtNasc = new JLabel("Data de Nascimento:");
        txtDtNasc = new JLabel();
        lbTelefone = new JLabel("Telefone:");
        txtTelefone = new JLabel(); 
        lbEmail = new JLabel("Email:");
        txtEmail = new JLabel();
        
        vinho = new Color(175, 8, 63);
        Font fTitulo = new Font("MV Boli", Font.PLAIN, 22);
        Font fLabels = new Font("Rockwell", Font.PLAIN, 16);
        
        form.add(titulo, "wrap 30");
        titulo.setForeground(vinho); 
        titulo.setFont(fTitulo);
        form.add(lbNome);
        lbNome.setForeground(vinho);
        form.add(txtNome, "wrap 25");
        form.add(lbSobrenome);
        lbSobrenome.setForeground(vinho);
        form.add(txtSobrenome, "wrap 25");
        form.add(lbUsuario);
        lbUsuario.setForeground(vinho);
        form.add(txtUsuario, "wrap 25");
        form.add(lbDtNasc);
        lbDtNasc.setForeground(vinho);        
        form.add(txtDtNasc, "wrap 25");
        form.add(lbTelefone);
        lbTelefone.setForeground(vinho);
        form.add(txtTelefone, "wrap 25");
        form.add(lbEmail);
        lbEmail.setForeground(vinho);
        form.add(txtEmail, "wrap 25");
        
        try {
            while(rs.next()){
                txtNome.setText(rs.getString("nome"));
                txtSobrenome.setText(rs.getString("sobrenome"));
                txtUsuario.setText(rs.getString("usuario"));
                txtDtNasc.setText(rs.getString("dtNascimento"));
                txtTelefone.setText(rs.getString("telefone"));
                txtEmail.setText(rs.getString("email"));               
            }
        }catch(NullPointerException e){//TODO rs está vindo nulo
            System.err.println("RS vazio");
            e.printStackTrace();
        } catch (SQLException ex) {
            System.err.println("Erro ao percorrer resultset");
            ex.printStackTrace();
        }
  
       this.add(form, "gapleft 30, gaptop 20");        
    }
}
