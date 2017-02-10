package model;

import java.util.Date;

public class Usuario {
    
	private int id_usuario; //TODO é uma pk, é necessario?
	private String nome; 
	private String sobrenome;
	private String usuario;
	private String dtNascimento; //TODO pesquisar
	private String telefone;
	private Conta conta; //TODO redundante com id_conta?

    public Usuario() {
    }
    
    public Usuario(String nome, String sobrenome, String usuario, String dtNascimento, 
                                                String telefone, Conta conta) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.usuario = usuario;
        this.dtNascimento = dtNascimento;
        this.telefone = telefone;
        this.conta = conta;
    }
    
    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(String dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(String email, char[] pass) {
        conta = new Conta();
        this.conta.setEmail(email);
        this.conta.setPass(pass);
    }
	  
    public void setConta(char[] pass) {
        conta = new Conta();
        this.conta.setPass(pass);    
    }   
        
}
