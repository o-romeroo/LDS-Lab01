package com.pucmg.lab01.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pucmg.lab01.models.Aluno;
import com.pucmg.lab01.models.Disciplina;
import com.pucmg.lab01.models.Professor;
import com.pucmg.lab01.repositories.ProfessorRepository;

import jakarta.transaction.Transactional;

@Service
public class ProfessorService {
    @Autowired
    private DisciplinaService disciplinaService;
    
    @Autowired
    private ProfessorRepository professorRepository;

    public List<Disciplina> consultarDisciplinasProfessor(Long idProfessor) {
        Professor professor = professorRepository.findById(idProfessor).orElseThrow(() -> new IllegalArgumentException("Professor com id " + idProfessor + 
        " não encontrado."));

        return professor.getDisciplinas();
    }

    public List<Aluno> consultarAlunosDisciplina(String nomeDisciplina) {
        Disciplina disciplina = disciplinaService.consultarDisciplinaPorNome(nomeDisciplina);

        if (!disciplinaService.verificaStatusDisciplina(nomeDisciplina) || !disciplinaService.verificaDisponibilidadeDisciplina(nomeDisciplina)) {
            throw new IllegalStateException("Disciplina não está disponível, logo não possui alunos.");
        }

        return disciplina.getAlunos();
    }

    @Transactional
    public Professor consultarProfessor(Long idProfessor) {
        return professorRepository.findById(idProfessor)
            .orElseThrow(() -> new IllegalArgumentException("Professor com ID " + idProfessor + " não encontrado."));
    }

    @Transactional
    public Professor consultarProfessorCPF(String cpf) {
        return professorRepository.findByCPF(cpf)
            .orElseThrow(() -> new IllegalArgumentException("Professor com CPF " + cpf + " não encontrado."));
    }
    
    @Transactional
    public void salvarProfessor(Professor professor){
        professorRepository.save(professor);
    }

    @Transactional
    public void removerProfessor(Professor professor){
        professorRepository.delete(professor);
    }

}
