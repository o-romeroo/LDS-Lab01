package com.pucmg.lab01.models;


public class Usuario {

    private String nome;
    private String login;
    private String password;
    private Long id;


    public Usuario(String nome, String login, String password, Long id) {
        this.nome = nome;
        this.login = login;
        this.password = password;
        this.id = id;
    }
    
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean realizarLogin(String login, String password) {
        // has to implement
        return this.login.equals(login) && this.password.equals(password);
    }
}
