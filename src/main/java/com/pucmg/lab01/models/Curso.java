package com.pucmg.lab01.models;

import jakarta.persistence.*;
import java.util.List;

@Entity // Indica que esta classe é uma entidade JPA
@Table(name = "curso") // Define o nome da tabela no banco de dados
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private int creditos;

    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL)
    private List<Disciplina> disciplinas;

    // Construtor padrão
    public Curso() {}

    // Construtor com parâmetros
    public Curso(String nome, int creditos, List<Disciplina> disciplinas) {
        this.nome = nome;
        this.creditos = creditos;
        this.disciplinas = disciplinas;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }
}
