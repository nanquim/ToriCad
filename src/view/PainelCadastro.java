package view;

import controller.ButtonHandler;
import java.awt.Color;
import java.awt.Dimension;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;
import model.Usuario;
import model.dao.UsuarioDAO;
import net.miginfocom.swing.MigLayout;

public class PainelCadastro extends JPanel {
    
    private JPanel formCad;
    private JLabel lbNome;
    private JTextField txtNome;
    private JLabel lbSobrenome;
    private JTextField txtSobrenome;
    private JLabel lbUsuario;
    private JTextField txtUsuario;
    private JLabel lbDtNasc;
    private JFormattedTextField txtDtNasc;
    private JLabel lbTelefone;
    private JFormattedTextField txtTelefone;
    private JLabel lbEmail;
    private JFormattedTextField txtEmail;
    private JLabel lbSenha;
    private JPasswordField pass;
    private JButton btGravar;
    //private Border borda;
    private final Color azul;
    private final int txtSize = 20;
    private static PainelCadastro pnlCad = new PainelCadastro();
    
    public PainelCadastro() {
        
        azul = new Color(109, 171, 191); 
        
        this.setPreferredSize(new Dimension(500, 580));
        this.setLayout(new MigLayout("align 50% 50%"));
        this.setBackground(azul);
       
        formCad = new JPanel();
        
        formCad.setLayout(new MigLayout("align 50% 50%"));
        formCad.setPreferredSize(new Dimension(450, 400));
        formCad.setBackground(azul);        

        Border bordaS = BorderFactory.createLineBorder(Color.BLACK, 1);
        TitledBorder bordaT = BorderFactory.createTitledBorder(bordaS, "Cadastre-se");
        
        formCad.setBorder(bordaT);
        //TODO VER ABAIXO
        //DateFormat formatoData = new SimpleDateFormat("dd/mm/yyyy");
        
        lbNome = new JLabel("Nome:");
        txtNome = new JTextField(20);
        lbSobrenome = new JLabel("Sobrenome");
        txtSobrenome = new JTextField(20);
        lbUsuario = new JLabel("Usuário:");
        txtUsuario = new JTextField(20);
        lbDtNasc = new JLabel("Data de Nascimento:");
        txtDtNasc = new JFormattedTextField();
        lbTelefone = new JLabel("Telefone:");
        txtTelefone = new JFormattedTextField(); //TODO rever construtor
        lbEmail = new JLabel("Email:");
        txtEmail = new JFormattedTextField();//TODO rever construtor
        lbSenha = new JLabel("Senha:");
        pass = new JPasswordField(20);
        btGravar = new JButton("Gravar");
        
        //Seta tamanho dos JFormattedTexts:
        txtDtNasc.setColumns(txtSize);
        txtTelefone.setColumns(txtSize);
        txtEmail.setColumns(txtSize);
        
        //Cria e aplica máscaras dos JFormattedTexts:
        try {
            MaskFormatter maskData = new MaskFormatter("##/##/####");
            maskData.install(txtDtNasc);
            MaskFormatter maskTelefone = new MaskFormatter("(##)####-####");
            maskTelefone.install(txtTelefone);
        } catch (ParseException ex) {
            System.err.println("Erro na criação das máscaras");
            Logger.getLogger(PainelCadastro.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }

        btGravar.addActionListener(new ButtonHandler());
                
        //TODO está faltando sobrenome (tbm no metodo getValues abaixo)
        formCad.add(lbNome);
        formCad.add(txtNome, "wrap 15");
        formCad.add(lbSobrenome);
        formCad.add(txtSobrenome, "wrap 15");
        formCad.add(lbUsuario);
        formCad.add(txtUsuario, "wrap 15");
        formCad.add(lbDtNasc);
        formCad.add(txtDtNasc, "wrap 15");
        formCad.add(lbTelefone);
        formCad.add(txtTelefone, "wrap 15");
        formCad.add(lbEmail);
        formCad.add(txtEmail, "wrap 15");
        formCad.add(lbSenha);
        formCad.add(pass, "wrap 15");
        formCad.add(btGravar);
        
        this.add(formCad);    
    }
    
    public static PainelCadastro getInstance(){
        return pnlCad;
    }
    
    public void createUserObject(){
        //TODO Esse objeto é destruído quando o metodo for chamado outr vez?  
        Usuario temp = new Usuario();
        UsuarioDAO usrDAO = new UsuarioDAO();        
        
        temp.setNome(txtNome.getText());
        temp.setSobrenome(txtSobrenome.getText());
        temp.setUsuario(txtUsuario.getText());
        
        temp.setTelefone(txtTelefone.getText());
        if(emailValido(txtEmail.getText()) == true){
            temp.setConta(txtEmail.getText(), pass.getPassword());
        }else{
            JOptionPane.showMessageDialog(null, "Email Inválido");
        }
        
        temp.setDtNascimento(txtDtNasc.getText());
        
        if(!usrDAO.saveUserData(temp)){
            JOptionPane.showMessageDialog(null, "Erro ao salavar dados no BD");           
        }else{JOptionPane.showMessageDialog(null, "Salvo com sucesso");}     
    }    
    
    public boolean emailValido(String email){
        if(email != null && email.trim().length() > 0){
            String regexEmail = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(regexEmail, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);
            if(matcher.matches()){
                return true;
            }   
        }
        return false;
    }
}
