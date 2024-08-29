package com.pucmg.lab01.models;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.pucmg.lab01.services.SecretarioService;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "secretario")
public class Secretario extends Usuario {
    

    public Secretario(String nome, String login, String password, String CPF, List<Disciplina> disciplinas) {
        super(nome, login, password, CPF);
    }

    public Secretario() {
    }   

    public List<Disciplina> gerarCurriculo(String nomeCurso){
        // has to implement
        return null;
    }
}

