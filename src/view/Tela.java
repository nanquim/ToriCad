package view;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Tela extends JFrame {
    
    private CardLayout cLayout;
    private JPanel container;
    private PainelInicial pnlIni;
    private PainelCadastro pnlCad;
    private PainelDados pnlDados;
    private static Tela tela = new Tela();
    
    private Tela() throws HeadlessException {
        //TODO adicionar paineis no cardlayout e tornar variavel cLayout acessivel de outra classe
        this.cLayout = new CardLayout();
        initTela();
        
        this.container = new JPanel(cLayout);
        this.pnlIni = new PainelInicial();
        this.pnlCad = PainelCadastro.getInstance();
        
        getContentPane().setLayout(cLayout);
        
        getContentPane().add(pnlIni);
        getContentPane().add(pnlCad);
 
        
        cLayout.addLayoutComponent(pnlIni, "pnlIni");
        cLayout.addLayoutComponent(pnlCad, "pnlCad");
 
        pack();
        setVisible(true);    
    }
    

    public static Tela getInstance(){
        return tela;
    }
    
    private void initTela(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 580));
        setResizable(false);
        setLocation(440, 40);   
    }
    
    public void setPainelInicial(){
        this.setTitle("Início");
        cLayout.show(this.getContentPane(), "pnlIni");
    }
    
    public void setPainelCadastro(){
        this.setTitle("Cadastro");
        cLayout.show(this.getContentPane(), "pnlCad");
    }
    
    public void setPainelDados(ResultSet rs){
        this.pnlDados = new PainelDados(rs);
        getContentPane().add(pnlDados);
        cLayout.addLayoutComponent(pnlDados, "pnlDados");
        this.setTitle("Olá!"); //TODO pegar nome do user logado (está no rs!)
        cLayout.show(this.getContentPane(), "pnlDados");
    }
}
