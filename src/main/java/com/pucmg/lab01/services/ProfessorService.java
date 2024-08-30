package com.pucmg.lab01.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pucmg.lab01.models.Aluno;
import com.pucmg.lab01.models.Disciplina;
import com.pucmg.lab01.models.Professor;
import com.pucmg.lab01.repositories.ProfessorRepository;

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
        Disciplina disciplina = disciplinaService.consultarDisciplina(nomeDisciplina);

        if (!disciplinaService.verificaStatusDisciplina(nomeDisciplina) || !disciplinaService.verificaDisponibilidadeDisciplina(nomeDisciplina)) {
            throw new IllegalStateException("Disciplina não está disponível, logo não possui alunos.");
        }

        return disciplina.getAlunos();
    }
}
