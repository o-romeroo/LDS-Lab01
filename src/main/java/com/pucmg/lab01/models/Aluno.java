package com.pucmg.lab01.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "aluno")
public class Aluno extends Usuario {

    @ManyToMany
    @JoinTable(
        name = "aluno_disciplinas", // Nome da tabela de junção para disciplinas ativas
        joinColumns = @JoinColumn(name = "aluno_id"),
        inverseJoinColumns = @JoinColumn(name = "disciplina_id")
    )
    private List<Disciplina> disciplinas;

    @ManyToOne
    @JoinColumn(name = "financeiro_id") // Essa é a coluna de chave estrangeira no lado do aluno
    private Financeiro financeiro;

    @Column(nullable = false)
    private int matricula;

    public Aluno() {
       
    }

    public Aluno(String nome, String login, String password, String CPF, List<Disciplina> disciplinas, int matricula) {
        super(nome, login, password, CPF);
        this.disciplinas = disciplinas;
        this.matricula = matricula;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public Financeiro getFinanceiro() {
        return this.financeiro;
    }

    public void setFinanceiro(Financeiro financeiro) {
        this.financeiro = financeiro;
    }

    public int getMatricula() {
        return this.matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Aluno aluno = (Aluno) o;

        return disciplinas != null ? disciplinas.equals(aluno.disciplinas) : aluno.disciplinas == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (disciplinas != null ? disciplinas.hashCode() : 0);
        return result;
    }

}
