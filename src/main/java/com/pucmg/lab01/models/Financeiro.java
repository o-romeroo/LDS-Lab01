package com.pucmg.lab01.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "financeiro")
public class Financeiro {

    @OneToMany(mappedBy = "financeiro")
    private List<Aluno> alunos;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private double valorTotal;

    public Financeiro(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public Financeiro() {
    }

    public double gerarBoletoAluno(Long idAluno) {
        // Lógica para gerar o boleto do aluno
        return 0.0; // Retorne o valor do boleto aqui
    }
}
