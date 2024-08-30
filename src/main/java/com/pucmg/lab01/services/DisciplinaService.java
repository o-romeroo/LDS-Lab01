package com.pucmg.lab01.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pucmg.lab01.models.Disciplina;
import com.pucmg.lab01.repositories.DisciplinaRepository;


@Service
public class DisciplinaService {

    @Autowired
    private DisciplinaRepository disciplinaRepository;
    
    public Disciplina consultarDisciplina(String nomeDisciplina) {
        return disciplinaRepository.findByNome(nomeDisciplina)
            .orElseThrow(() -> new IllegalArgumentException("Disciplina com nome " + nomeDisciplina + " não encontrada."));
    }
    
    public void salvarDisciplina(Disciplina disciplina){
        disciplinaRepository.save(disciplina);
    }

    public List<Disciplina> listarDisciplinas(){
        return disciplinaRepository.findAll();
    }

    public Boolean verificaStatusDisciplina(String nomeDisciplina){
        Boolean status = false;
        Disciplina disciplina = disciplinaRepository.findByNome(nomeDisciplina)
            .orElseThrow(() -> new IllegalArgumentException("Disciplina com nome " + nomeDisciplina + " não encontrada."));

        if (disciplina.getAlunos().size() >= 3) {
            status = true;
            disciplina.setDisciplinaAtiva(true);
            disciplinaRepository.save(disciplina);
        }

        return status;
    }

    public Boolean verificaDisponibilidadeDisciplina(String nomeDisciplina){
        Boolean disponibilidade = true;

        Disciplina disciplina = disciplinaRepository.findByNome(nomeDisciplina)
            .orElseThrow(() -> new IllegalArgumentException("Disciplina com nome " + nomeDisciplina + " não encontrada."));

        if (disciplina.getAlunos().size() >= 60) {
            disponibilidade = false;
            disciplina.setDisciplinaDisponivel(false);
            disciplinaRepository.save(disciplina);
        }

        return disponibilidade;
    }
}
