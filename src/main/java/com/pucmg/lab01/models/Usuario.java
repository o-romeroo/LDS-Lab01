package com.pucmg.lab01.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.Objects;



@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "usuario")
public class Usuario {

    @Column(nullable = false)
    //@Size(min = 3, max = 50) -> pensar se o nome do usuario será completo ou não, para definir o tamanho
    private String nome;
    
    @Column(nullable = false)
    @Size(min = 3, max = 20)
    @Pattern(regexp = "^[^\\s]*$") // não pode ter espaços  
    private String login;
    
    @Column(nullable = false)
    @Size(min = 3, max = 20)
    @Pattern(regexp = "^[^\\s]*$")
    private String password;
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    @Pattern(regexp = "^[^\\s]*$")
    private String CPF; // decidir se cpf terá ou não máscara

    public Usuario() {
    }   

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

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public boolean realizarLogin(String login, String password) {
        // has to implement
        return this.login.equals(login) && this.password.equals(password);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; 
        if (o == null || getClass() != o.getClass()) return false; 

        Usuario usuario = (Usuario) o; 

        if (id != null ? !id.equals(usuario.id) : usuario.id != null) return false;
        return CPF != null ? CPF.equals(usuario.CPF) : usuario.CPF == null;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, CPF);
    }
}
