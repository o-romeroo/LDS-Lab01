package com.pucmg.lab01.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pucmg.lab01.models.Aluno;
import com.pucmg.lab01.models.Disciplina;
import com.pucmg.lab01.repositories.AlunoRepository;

import jakarta.transaction.Transactional;

@Service
public class AlunoService {

    @Autowired
    DisciplinaService disciplinaService;

    @Autowired
    AlunoRepository alunoRepository;

    @Transactional
    public void matricularDisciplina(String nomeDisciplina, Long idAluno) {
        // Busca a disciplina pelo nome e verifica se ela existe
        Disciplina disciplina = disciplinaService.consultarDisciplinaPorNome(nomeDisciplina);

        // Busca o aluno pelo id e verifica se ele existe
        Aluno aluno = consultarAluno(idAluno);

        if(disciplina.getAlunos().contains(aluno) || aluno.getDisciplinas().contains(disciplina)) {
            throw new IllegalStateException("Aluno já está matriculado na disciplina especificada.");
        }           

        // Verifica status e disponibilidade da disciplina
        if (!disciplinaService.verificaStatusDisciplina(nomeDisciplina) || !disciplinaService.verificaDisponibilidadeDisciplina(nomeDisciplina)) {
            throw new IllegalStateException("Disciplina não está disponível para matrícula.");
        }

        int qtdDisciplinasObrigatorias = 0;
        // Verifica condições de matrícula para disciplinas obrigatórias e optativas
        for (Disciplina disciplinaValida : aluno.getDisciplinas()) {
            if (disciplinaValida.getObrigatoria()) {
                qtdDisciplinasObrigatorias++;
            }
        }

        boolean podeMatricularDisciplinaObrigatoria = (qtdDisciplinasObrigatorias < 4);

        int qtdDisciplinasOptativas = 0;
        for (Disciplina disciplinaValida : aluno.getDisciplinas()) {
            if (disciplinaValida.getOptativa()) {
                qtdDisciplinasOptativas++;
            }
        }

        boolean podeMatricularDisciplinaOptativa = (qtdDisciplinasOptativas < 2);

        if (podeMatricularDisciplinaObrigatoria && disciplina.getObrigatoria()) {
            // Adiciona o aluno à lista de alunos da disciplina
            disciplina.getAlunos().add(aluno);
            // Adiciona a disciplina à lista de disciplinas do aluno
            aluno.getDisciplinas().add(disciplina);

            // Salva as mudanças no banco de dados
            disciplinaService.salvarDisciplina(disciplina);
            salvarAluno(aluno);
        } else {
            throw new IllegalStateException("Aluno não pode se matricular em mais disciplinas obrigatórias.");
        }

        if(podeMatricularDisciplinaOptativa && disciplina.getOptativa()){
            // Adiciona o aluno à lista de alunos da disciplina
            disciplina.getAlunos().add(aluno);
            // Adiciona a disciplina à lista de disciplinas do aluno
            aluno.getDisciplinas().add(disciplina);

            // Salva as mudanças no banco de dados
            disciplinaService.salvarDisciplina(disciplina);
            salvarAluno(aluno);
        } else {
            throw new IllegalStateException("Aluno não pode se matricular em mais disciplinas optativas.");
        }
    }

    @Transactional
    public void cancelarMatriculaDisciplina(String nomeDisciplina, Long idAluno) {
        // Busca a disciplina pelo nome e verifica se ela existe
        Disciplina disciplina = disciplinaService.consultarDisciplinaPorNome(nomeDisciplina);
    
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
    
    
    @Transactional
    public Aluno consultarAluno(Long idAluno) {
        return alunoRepository.findById(idAluno)
            .orElseThrow(() -> new IllegalArgumentException("Aluno com ID " + idAluno + " não encontrado."));
    }

    @Transactional
    public Aluno consultarAlunoCPF(String cpf) {
        return alunoRepository.findByCPF(cpf)
            .orElseThrow(() -> new IllegalArgumentException("Aluno com CPF " + cpf + " não encontrado."));
    }
    
    @Transactional
    public void salvarAluno(Aluno aluno){
        alunoRepository.save(aluno);
    }

    @Transactional
    public void removerAluno(Aluno aluno){
        alunoRepository.delete(aluno);
    }


    @Transactional
    public void consultarDisciplinasCursadas(Long idAluno) {
        Aluno aluno = consultarAluno(idAluno);
        System.out.println("Disciplinas cursadas por " + aluno.getNome() + ":");
        aluno.getDisciplinas().forEach(disciplina -> {
            System.out.println(disciplina.getNome());
        });
    }

     public boolean efetuarMatricula(Long idAluno) {
        boolean matriculaValida = true;
        Aluno aluno = consultarAluno(idAluno);
        
        for (Disciplina disciplina : aluno.getDisciplinas()) {
            if(!disciplinaService.verificaStatusDisciplina(disciplina.getNome())) {
                matriculaValida = false;
                throw new IllegalStateException("O aluno "+ aluno.getNome() +" não pode se matricular na disciplina " + disciplina.getNome() + " pois ela não atingiu a quantidade de alunos necessários para ocorrer neste semestre.");
            }

            if(!disciplinaService.verificaDisponibilidadeDisciplina(disciplina.getNome())) {
                matriculaValida = false;
                throw new IllegalStateException("O aluno "+ aluno.getNome() +" não pode se matricular na disciplina " + disciplina.getNome() + " pois ela não está disponível para matrícula.");
            }
        }
        
        return matriculaValida;
    }
}
