package com.pucmg.lab01.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pucmg.lab01.models.Aluno;
import com.pucmg.lab01.models.Disciplina;
import com.pucmg.lab01.repositories.AlunoRepository;

@Service
public class AlunoService {

    @Autowired
    DisciplinaService disciplinaService;

    @Autowired
    AlunoRepository alunoRepository;

    public void matricularDisciplina(String nomeDisciplina, Long idAluno) {
        // Busca a disciplina pelo nome e verifica se ela existe
        Disciplina disciplina = disciplinaService.consultarDisciplina(nomeDisciplina);

        // Busca o aluno pelo id e verifica se ele existe
        Aluno aluno = consultarAluno(idAluno);

        // Verifica status e disponibilidade da disciplina
        if (!disciplina.verificaStatusDisciplina() || !disciplina.verificaDisponibilidadeDisciplina()) {
            throw new IllegalStateException("Disciplina não está disponível para matrícula.");
        }

        // Verifica condições de matrícula para disciplinas obrigatórias e optativas
        boolean podeMatricular = (disciplina.getObrigatoria() && aluno.getDisciplinas().size() <= 4) ||
                                 (disciplina.getOptativa() && aluno.getDisciplinas().size() <= 2);

        if (podeMatricular) {
            // Adiciona o aluno à lista de alunos da disciplina
            disciplina.getAlunos().add(aluno);
            // Adiciona a disciplina à lista de disciplinas do aluno
            aluno.getDisciplinas().add(disciplina);

            // Salva as mudanças no banco de dados
            disciplinaService.salvarDisciplina(disciplina);
            salvarAluno(aluno);
        } else {
            throw new IllegalStateException("Aluno não pode se matricular em mais disciplinas " + 
                (disciplina.getObrigatoria() ? "obrigatórias." : "optativas."));
        }
    }

    public void cancelarMatriculaDisciplina(String nomeDisciplina, Long idAluno) {
        // Busca a disciplina pelo nome e verifica se ela existe
        Disciplina disciplina = disciplinaService.consultarDisciplina(nomeDisciplina);
    
        // Busca o aluno pelo id e verifica se ele existe
        Aluno aluno = consultarAluno(idAluno);
    
        // Verifica se a disciplina e o aluno estão associados
        if (!disciplina.getAlunos().contains(aluno) || !aluno.getDisciplinas().contains(disciplina)) {
            throw new IllegalStateException("Aluno não está matriculado na disciplina especificada.");
        }
    
        // Remove o aluno da lista de alunos da disciplina
        disciplina.getAlunos().remove(aluno);
    
        // Remove a disciplina da lista de disciplinas do aluno
        aluno.getDisciplinas().remove(disciplina);
    
        // Salva as mudanças no banco de dados
        disciplinaService.salvarDisciplina(disciplina);
        salvarAluno(aluno);
    }
    
    
    
    public Aluno consultarAluno(Long idAluno) {
        return alunoRepository.findById(idAluno)
            .orElseThrow(() -> new IllegalArgumentException("Aluno com ID " + idAluno + " não encontrado."));
    }
    
    public void salvarAluno(Aluno aluno){
        alunoRepository.save(aluno);
    }

}
