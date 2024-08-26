package com.pucmg.lab01.services;

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

}
