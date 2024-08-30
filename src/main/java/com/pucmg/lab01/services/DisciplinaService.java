package com.pucmg.lab01.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pucmg.lab01.models.Curso;
import com.pucmg.lab01.models.Disciplina;
import com.pucmg.lab01.models.Professor;
import com.pucmg.lab01.repositories.DisciplinaRepository;

import jakarta.transaction.Transactional;


@Service
public class DisciplinaService {

    @Autowired
    private DisciplinaRepository disciplinaRepository;
    
    @Transactional
    public Disciplina consultarDisciplina(Long idDisciplina) {
        return disciplinaRepository.findById(idDisciplina)
            .orElseThrow(() -> new IllegalArgumentException("Disciplina com ID " + idDisciplina + " não encontrada."));
    }

    @Transactional
    public Disciplina consultarDisciplinaPorNome(String nome) {
        return disciplinaRepository.findByNome(nome)
            .orElseThrow(() -> new IllegalArgumentException("Disciplina com nome '" + nome + "' não encontrada."));
    }

    @Transactional
    public void removerDisciplina(Disciplina disciplina) {
        disciplinaRepository.delete(disciplina);
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

    public Disciplina cadastrarDisciplina(String nome, boolean obrigatoria, boolean optativa, double preco, Curso curso, Professor professor) {
        // Criar a instância de Disciplina
        Disciplina novaDisciplina = new Disciplina(nome, obrigatoria,optativa, preco, curso, professor);
    
        // Verificar se a Disciplina já existe pelo nome
        if (disciplinaRepository.findByNome(nome).isPresent()) {
            throw new IllegalArgumentException("Disciplina com nome " + nome + " já está cadastrada.");
        }
    
        // Salvar no banco de dados
        return disciplinaRepository.save(novaDisciplina);
    }
    

}
