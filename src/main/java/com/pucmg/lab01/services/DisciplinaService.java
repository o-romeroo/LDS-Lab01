package com.pucmg.lab01.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pucmg.lab01.models.Curso;
import com.pucmg.lab01.models.Disciplina;
import com.pucmg.lab01.models.Professor;
import com.pucmg.lab01.repositories.CursoRepository;
import com.pucmg.lab01.repositories.DisciplinaRepository;
import com.pucmg.lab01.repositories.ProfessorRepository;

import jakarta.transaction.Transactional;

@Service
public class DisciplinaService {

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Transactional
    public Disciplina consultarDisciplina(Long idDisciplina) {
        return disciplinaRepository.findById(idDisciplina)
            .orElseThrow(() -> new IllegalArgumentException("Disciplina com ID " + idDisciplina + " não encontrada."));
    }

    @Transactional
    public Disciplina consultarDisciplinaPorNome(String nome) {
        return disciplinaRepository.findByNome(nome)
            .orElseThrow(() -> new IllegalArgumentException("Disciplina " + nome + " não encontrada."));
    }

    @Transactional
    public void removerDisciplina(Long disciplinaId) {
        // Encontrar a disciplina ou lançar uma exceção se não for encontrada
        Disciplina disciplina = disciplinaRepository.findById(disciplinaId)
                .orElseThrow(() -> new IllegalArgumentException("Disciplina com ID " + disciplinaId + " não encontrada."));

        // Remover associação com o professor (se existir)
        if (disciplina.getProfessor() != null) {
            Professor professor = disciplina.getProfessor();
            professor.getDisciplinas().remove(disciplina); // Remove a disciplina da lista de disciplinas do professor
            disciplina.setProfessor(null);
            professorRepository.save(professor); // Salva o professor atualizado
        }

        // Remover associação com o curso (se existir)
        if (disciplina.getCurso() != null) {
            Curso curso = disciplina.getCurso();
            curso.getDisciplinas().remove(disciplina); // Remove a disciplina da lista de disciplinas do curso
            disciplina.setCurso(null);
            cursoRepository.save(curso); // Salva o curso atualizado
        }

        // Salvar a entidade disciplina para garantir que as associações sejam removidas
        disciplinaRepository.save(disciplina);

        // Finalmente, remover a disciplina
        disciplinaRepository.delete(disciplina);
    }

    public void salvarDisciplina(Disciplina disciplina) {
        disciplinaRepository.save(disciplina);
    }

    public List<Disciplina> listarDisciplinas() {
        return disciplinaRepository.findAll();
    }

    public Boolean verificaStatusDisciplina(String nomeDisciplina) {
        Boolean status = false;
        Disciplina disciplina = disciplinaRepository.findByNome(nomeDisciplina)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Disciplina com nome " + nomeDisciplina + " não encontrada."));

        if (disciplina.getAlunos().size() >= 3) {
            status = true;
            disciplina.setDisciplinaAtiva(true);
            disciplinaRepository.save(disciplina);
        }
        return status;
    }

    public Boolean verificaDisponibilidadeDisciplina(String nomeDisciplina) {
        Boolean disponibilidade = true;

        Disciplina disciplina = disciplinaRepository.findByNome(nomeDisciplina)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Disciplina com nome " + nomeDisciplina + " não encontrada."));

        if (disciplina.getAlunos().size() >= 60) {
            disponibilidade = false;
            disciplina.setDisciplinaDisponivel(false);
            disciplinaRepository.save(disciplina);
           
        }
        return disponibilidade;
    }

    public Disciplina cadastrarDisciplina(String nome, boolean obrigatoria, boolean optativa, double preco, Curso curso, Professor professor) {
        // Criar a instância de Disciplina
        Disciplina novaDisciplina = new Disciplina(nome, obrigatoria, optativa, preco, curso, professor);

        // Verificar se a Disciplina já existe pelo nome
        if (disciplinaRepository.findByNome(nome).isPresent()) {
            throw new IllegalArgumentException("Disciplina com nome " + nome + " já está cadastrada.");
        }

        // Salvar no banco de dados
        return disciplinaRepository.save(novaDisciplina);
    }
}
