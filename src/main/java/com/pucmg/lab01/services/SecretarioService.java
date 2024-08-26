package com.pucmg.lab01.services;

import com.pucmg.lab01.models.Aluno;
import com.pucmg.lab01.repositories.AlunoRepository;

import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SecretarioService {
    private final AlunoRepository alunoRepository;
    
    public void cadastrarAluno(Aluno aluno){
        alunoRepository.save(aluno);
    }

    public void removerAluno(Long idAluno){
        alunoRepository.deleteById(idAluno);
    }

    public Aluno consultarAluno(Long idAluno){
        return alunoRepository.findById(idAluno).get();
    }

    public boolean atualizarDadosAluno(Long idAluno, Aluno aluno){
        if(alunoRepository.existsById(idAluno)){
            alunoRepository.save(aluno);
            return true;
        }
        return false;
    }
}
