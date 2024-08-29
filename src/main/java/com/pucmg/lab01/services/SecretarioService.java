package com.pucmg.lab01.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pucmg.lab01.models.Aluno;
import com.pucmg.lab01.models.Disciplina;
import com.pucmg.lab01.repositories.AlunoRepository;

import jakarta.transaction.Transactional;

@Service
public class SecretarioService {
    
    @Autowired
    AlunoRepository alunoRepository;

    @Transactional
    public void cadastrarAluno(Aluno aluno){
        if (alunoRepository.findByCPF(aluno.getCPF()).isPresent()){
            throw new IllegalArgumentException("Aluno " + aluno.getNome() + " já cadastrado");
        }
        alunoRepository.save(aluno);
    }

    @Transactional
    public void removerAluno(Long idAluno){
        if (!alunoRepository.existsById(idAluno)){
            throw new IllegalArgumentException("Aluno não encontrado");
        }
        alunoRepository.deleteById(idAluno);
    }

    @Transactional
    public Aluno consultarAluno(Long idAluno){
        if (!alunoRepository.existsById(idAluno)){
            throw new IllegalArgumentException("Aluno não encontrado");
        }
        return alunoRepository.findById(idAluno).get();
    }

    @Transactional
    public boolean atualizarDadosAluno(Long idAluno, Aluno aluno){
        if(alunoRepository.existsById(idAluno)){
            alunoRepository.save(aluno);
            System.out.println("Aluno " + aluno.getNome() + " atualizado com sucesso");
            return true;
        }
        System.out.println("Aluno não encontrado");
        return false;
    }

    public List<Disciplina> gerarCurriculo(String nomeCurso){
        // has to implement
        return null;
    }

}
