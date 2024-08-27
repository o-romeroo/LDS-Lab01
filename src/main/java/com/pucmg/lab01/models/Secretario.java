package com.pucmg.lab01.models;


import java.util.List;

import com.pucmg.lab01.repositories.AlunoRepository;
import com.pucmg.lab01.services.SecretarioService;

public class Secretario extends Usuario {
    AlunoRepository alunoRepository;
    SecretarioService secretarioService;

    public Secretario(String nome, String login, String password, String CPF, Disciplina[] disciplinas) {
        super(nome, login, password, CPF);
    }

    public List<Disciplina> gerarCurriculo(String nomeCurso){
        // has to implement
        return null;
    }
}

