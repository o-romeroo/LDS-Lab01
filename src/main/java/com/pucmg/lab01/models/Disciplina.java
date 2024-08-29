package com.pucmg.lab01.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "disciplina")
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private boolean disciplinaAtiva;

    @Column(nullable = false)
    private boolean disciplinaDisponivel;

    @Column(nullable = false)
    private boolean obrigatoria;

    @Column(nullable = false)
    private boolean optativa;

    @Column(nullable = false)
    private double preco;

    @ManyToOne
    @JoinColumn(name = "professor_id") // Define a chave estrangeira para a entidade Professor
    private Professor professor;

    @ManyToMany(mappedBy = "disciplinas") // Define o lado inverso do relacionamento ManyToMany
    private List<Aluno> alunos; // Relacionamento bidirecional com Aluno

    @ManyToOne
    @JoinColumn(name = "curso_id") // Define a chave estrangeira para a entidade Curso
    private Curso curso;




    public Disciplina(String nome, boolean disciplinaAtiva, boolean disciplinaDisponivel, boolean obrigatoria, boolean optativa, double preco, Professor professor, List<Aluno> alunos, Long id) {
        this.nome = nome;
        this.disciplinaAtiva = disciplinaAtiva;
        this.disciplinaDisponivel = disciplinaDisponivel;
        this.obrigatoria = obrigatoria;
        this.optativa = optativa;
        this.preco = preco;
        this.professor = professor;
        this.alunos = alunos;
        this.id = id;
    }

    public Disciplina() {
    }   

    public boolean getDisciplinaAtiva() {
        return this.disciplinaAtiva;
    }


    public boolean getDisciplinaDisponivel() {
        return this.disciplinaDisponivel;
    }


    public boolean getObrigatoria() {
        return this.obrigatoria;
    }


    public boolean getOptativa() {
        return this.optativa;
    }


    public Professor getProfessor() {
        return this.professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public List<Aluno> getAlunos() {
        return this.alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
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

    public boolean isDisciplinaAtiva() {
        return disciplinaAtiva;
    }

    public void setDisciplinaAtiva(boolean disciplinaAtiva) {
        this.disciplinaAtiva = disciplinaAtiva;
    }

    public boolean isDisciplinaDisponivel() {
        return disciplinaDisponivel;
    }

    public void setDisciplinaDisponivel(boolean disciplinaDisponivel) {
        this.disciplinaDisponivel = disciplinaDisponivel;
    }

    public boolean isObrigatoria() {
        return obrigatoria;
    }

    public void setObrigatoria(boolean obrigatoria) {
        this.obrigatoria = obrigatoria;
    }

    public boolean isOptativa() {
        return optativa;
    }

    public void setOptativa(boolean optativa) {
        this.optativa = optativa;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public boolean verificaStatusDisciplina() {
        // need to discuss
        return true;
    }


    public boolean verificaDisponibilidadeDisciplina() {
        // need to discuss
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Disciplina disciplina = (Disciplina) o;

        if (id != null ? !id.equals(disciplina.id) : disciplina.id != null) return false;
        return nome != null ? nome.equals(disciplina.nome) : disciplina.nome == null;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome);
    }
}
