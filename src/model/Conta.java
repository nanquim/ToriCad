package model;

public class Conta {
    private int id; //TODO é uma pk, é necessario?
    private String email;
    private char[] pass;

    public Conta() {
    }

    public Conta(int id_usuario, String email, char[] pass) {
        this.email = email;
        this.pass = pass;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id_conta) {
        this.id = id_conta;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public char[] getPass() {
        return pass;
    }

    public void setPass(char[] pass) {
        this.pass = pass;
    }
    
    
}
