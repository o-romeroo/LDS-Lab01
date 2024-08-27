package com.pucmg.lab01.models;


public class Professor extends Usuario {

    Disciplina[] disciplinas;

    public Professor(String nome, String login, String password, String CPF, Disciplina[] disciplinas) {
        super(nome, login, password, CPF);
        this.disciplinas = disciplinas;
    }

    public Disciplina[] getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(Disciplina[] disciplinas) {
        this.disciplinas = disciplinas;
    }

    public Aluno[] consultarAlunosMatriculados(Disciplina disciplina) {
        // has to implement
        return null;
    }

}
