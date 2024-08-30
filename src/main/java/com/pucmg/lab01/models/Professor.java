package com.pucmg.lab01.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "professor")
public class Professor extends Usuario {

    @OneToMany(mappedBy = "professor", cascade = CascadeType.ALL, fetch = FetchType.EAGER) // Define o relacionamento de um-para-muitos
    private List<Disciplina> disciplinas;

    public Professor(String nome, String login, String password, String CPF, List<Disciplina> disciplinas) {
        super(nome, login, password, CPF);
        this.disciplinas = disciplinas;
    }

    public Professor() {
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Professor professor = (Professor) o;

        return disciplinas != null ? disciplinas.equals(professor.disciplinas) : professor.disciplinas == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (disciplinas != null ? disciplinas.hashCode() : 0);
        return result;
    }
}
