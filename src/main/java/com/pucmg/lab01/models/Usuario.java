package com.pucmg.lab01.models;


public class Usuario {

    private String nome;
    private String login;
    private String password;
    private Long id;
    private String CPF;


    public Usuario(String nome, String login, String password, String CPF) {
        this.nome = nome;
        this.login = login;
        this.password = password;
        this.id = generateId();
        this.CPF = CPF;
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

    private Long generateId() {
        return (long) (Math.random() * 1000);
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String cPF) {
        CPF = cPF;
    }

    public boolean realizarLogin(String login, String password) {
        // has to implement
        return this.login.equals(login) && this.password.equals(password);
    }
}
