package com.pucmg.lab01.services;

import java.util.List;

import org.hibernate.Hibernate;
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

    @Transactional
    public List<Aluno> consultarAlunosDisciplina(String nomeDisciplina) {
    // Consulta a disciplina pelo nome
    Disciplina disciplina = disciplinaService.consultarDisciplinaPorNome(nomeDisciplina);

    // Verifica se a disciplina foi encontrada
    if (disciplina == null) {
        throw new IllegalArgumentException("Disciplina não encontrada com o nome: " + nomeDisciplina);
    }

    // Inicializa a lista de alunos para evitar LazyInitializationException, se necessário
    Hibernate.initialize(disciplina.getAlunos());

    // Retorna a lista de alunos matriculados na disciplina
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
