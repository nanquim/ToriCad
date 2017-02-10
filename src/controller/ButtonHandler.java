package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import model.dao.UsuarioDAO;
import view.PainelCadastro;
import view.PainelInicial;
import view.Tela;
            //TODO essa classe nao devia estar em um pacote controller?
public class ButtonHandler implements ActionListener {

    private JButton btCad;
    private JButton btGravar;
    private UsuarioDAO userDAO;
    private PainelInicial painelAtual;
    private String u;
    private String p;

    public ButtonHandler() { 
    }
    
    public ButtonHandler(PainelInicial p) {
        this.painelAtual = p;
    }
    
  
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Cadastre-se")){
            Tela.getInstance().setPainelCadastro();
            Tela.getInstance().pack();
            Tela.getInstance().validate();
            Tela.getInstance().setVisible(true);           
        }
        if(e.getActionCommand().equals("Gravar")){
            PainelCadastro.getInstance().createUserObject();
            Tela.getInstance().setPainelInicial();
            Tela.getInstance().pack();
            Tela.getInstance().validate();
            Tela.getInstance().setVisible(true);
        }
        if(e.getActionCommand().equals("Enviar")){
            String[] dados = painelAtual.getDadosLogin();
            
            UsuarioDAO usr = new UsuarioDAO();
            if(!usr.login(dados[0], dados[1])){
                JOptionPane.showMessageDialog(null, "Erro ao logar");
            }else{
                        ResultSet rs = usr.getUserData(dados[0]);       
                        Tela.getInstance().setPainelDados(rs);
                        Tela.getInstance().pack();
                        Tela.getInstance().validate();
                        Tela.getInstance().setVisible(true);
                    }               
                }
            }            
        
    public void dadosLogin(String u, String p){
        this.u = u;
        this.p = u;
    }
}
