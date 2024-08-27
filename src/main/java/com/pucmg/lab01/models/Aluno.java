package com.pucmg.lab01.models;
import java.util.List;


public class Aluno extends Usuario {

    List<Disciplina> disciplinas;
    List<Disciplina> disciplinasCursadas;

    public Aluno(String nome, String login, String password, String CPF, List<Disciplina> disciplinas, List<Disciplina> disciplinasCursadas) {
        super(nome, login, password, CPF);
        this.disciplinas = disciplinas;
        this.disciplinasCursadas = disciplinasCursadas;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    public List<Disciplina> getDisciplinasCursadas() {
        return disciplinasCursadas;
    }

    public void setDisciplinasCursadas(List<Disciplina> disciplinasCursadas) {
        this.disciplinasCursadas = disciplinasCursadas;
    }

    public boolean efetuarMatricula(List<Disciplina> disciplinas) {
        // has to implement
        return true;
    }

}
