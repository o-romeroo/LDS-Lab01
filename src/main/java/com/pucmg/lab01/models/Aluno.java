package com.pucmg.lab01.models;


public class Aluno extends Usuario {

    Disciplina[] disciplinas;
    Disciplina[] disciplinasCursadas;

    public Aluno(String nome, String login, String password, Disciplina[] disciplinas, Disciplina[] disciplinasCursadas) {
        super(nome, login, password);
        this.disciplinas = disciplinas;
        this.disciplinasCursadas = disciplinasCursadas;
    }

    public Disciplina[] getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(Disciplina[] disciplinas) {
        this.disciplinas = disciplinas;
    }

    public Disciplina[] getDisciplinasCursadas() {
        return disciplinasCursadas;
    }

    public void setDisciplinasCursadas(Disciplina[] disciplinasCursadas) {
        this.disciplinasCursadas = disciplinasCursadas;
    }

    public boolean efetuarMatricula(Disciplina[] disciplinas) {
        // has to implement
        return true;
    }

}
