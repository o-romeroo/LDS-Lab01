package com.pucmg.lab01.services;

import org.springframework.stereotype.Service;

import com.pucmg.lab01.models.Aluno;

@Service
public class FinanceiroService {
    

    public double calcularMensalidade(Aluno alunoId){
        double mensalidade = 0;
        for (int i = 0; i < alunoId.getDisciplinas().size(); i++) {
            mensalidade += alunoId.getDisciplinas().get(i).getPreco();       
    }
    return mensalidade;
    }
}
