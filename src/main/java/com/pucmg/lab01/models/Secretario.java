package com.pucmg.lab01.models;


import java.util.List;

import com.pucmg.lab01.repositories.AlunoRepository;
import com.pucmg.lab01.services.SecretarioService;

public class Secretario extends Usuario {
    AlunoRepository alunoRepository;
    SecretarioService secretarioService;

    public Secretario(String nome, String login, String password, Disciplina[] disciplinas) {
        super(nome, login, password);
    }

    public void cadastrarAluno(Aluno aluno) {
        secretarioService.cadastrarAluno(aluno);
    }

    public void removerAluno(Long idAluno) {
        secretarioService.removerAluno(idAluno);
    }

    public Aluno consultarAluno(Long idAluno) {
        return secretarioService.consultarAluno(idAluno);
    }

    public boolean atualizarDadosAluno(Long idAluno, Aluno aluno) {
        return secretarioService.atualizarDadosAluno(idAluno, aluno);
    }

    public List<Disciplina> gerarCurriculo(String nomeCurso){
        // has to implement
        return null;
    }
}

