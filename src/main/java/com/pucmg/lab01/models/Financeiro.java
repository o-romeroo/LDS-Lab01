package com.pucmg.lab01.models;

import java.util.List;

public class Financeiro {

    private List<Aluno> alunos;

    public Financeiro(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public double gerarBoletoAluno(Long idAluno) {
        // Lógica para gerar o boleto do aluno
        return 0.0; // Retorne o valor do boleto aqui
    }
}
