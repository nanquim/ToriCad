package view;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;
import controller.*;

public class PainelInicial extends JPanel{
    
    private JPanel pnlBotao;
    private JButton btCad;
    private JPanel pnlImg;
    private ImageIcon img;
    private JLabel lbImg;
    private JPanel pnlFormulario;
    private JLabel lblUsuario;
    private JTextField txtUsuario;
    private JLabel lblSenha;
    private JPasswordField pass;
    private JButton btEnviar;    
    private final Color azul;
    private Color vinho;
    private ButtonHandler handler;
    
    public PainelInicial() {
        setPreferredSize(new Dimension(400, 480));
        setLayout(new MigLayout());
        azul = new Color(109, 171, 191); 
        setBackground(azul);
        
        setCorpoNoroeste();
        setCorpoCentro();
        setCorpoSul();
    }

    public void setCorpoNoroeste(){//Botao Cadastro
        pnlBotao = new JPanel();
        pnlBotao.setPreferredSize(new Dimension(100, 100));
        pnlBotao.setLayout(new MigLayout());
        pnlBotao.setForeground(Color.BLACK); //TODO Não funciona
        pnlBotao.setBackground(new Color(0,0,0,0));             
        
        btCad = new JButton("Cadastre-se");
        btCad.setPreferredSize(new Dimension(10, 50));
        vinho = new Color(175, 8, 63);
        btCad.setBackground(vinho);
        btCad.setFocusPainted(false);
        btCad.addActionListener(new ButtonHandler());
 
        pnlBotao.add(btCad, "gapleft 10");
        
        add(pnlBotao, "cell 0 0");
    }
    
    public void setCorpoCentro(){//Imagem
        pnlImg = new JPanel();
        pnlImg.setPreferredSize(new Dimension(100, 100));
        pnlImg.setBackground(new Color(0,0,0,0));
        
        img = new ImageIcon("C:\\Users\\julia\\Documents\\NetBeansProjects\\Cadastro\\src\\view\\torii-gate.png");
        lbImg = new JLabel(img);

        pnlImg.add(lbImg, "gapleft 5");
        
        add(pnlImg, "cell 1 1");
    }
    
    public void setCorpoSul(){//Formulario
        pnlFormulario = new JPanel();
        pnlFormulario.setPreferredSize(new Dimension(220, 220));
        pnlFormulario.setBackground(new Color(0,0,0,0));
        pnlFormulario.setLayout(new MigLayout());
        lblUsuario = new JLabel("Usuário");
        txtUsuario = new JTextField(20);
        lblSenha = new JLabel("Senha");
        pass = new JPasswordField(20);
        btEnviar = new JButton("Enviar");
        
        ButtonHandler handlerEnviar = new ButtonHandler(this);
        btEnviar.addActionListener(handlerEnviar);
        
        vinho = new Color(175, 8, 63);
        btEnviar.setBackground(vinho); //TODO código repetido (mesma cor do btCad)
        btEnviar.setFocusPainted(false);
        
        pnlFormulario.add(lblUsuario, "cell 0 0, gaptop 5");
        pnlFormulario.add(txtUsuario, "cell 0 1, gaptop 5 ");
        pnlFormulario.add(lblSenha, "cell 0 2, gaptop 10");
        pnlFormulario.add(pass, "cell 0 3, gaptop 5");
        pnlFormulario.add(btEnviar, "cell 0 4, gapleft 125, gaptop 20");
        
        add(pnlFormulario, "cell 1 2");
        
    }
    
    public String[] getDadosLogin(){
        String[] dados = new String[2];
        dados[0] = txtUsuario.getText();
        dados[1] = String.valueOf(pass.getPassword());
        return dados;
    }
}
