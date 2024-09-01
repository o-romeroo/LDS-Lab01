package com.pucmg.lab01.services;

import java.util.ArrayList;
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
    if (disciplina == null) {
        throw new RuntimeException("Disciplina não encontrada.");
    }

    // Busca o aluno pelo id e verifica se ele existe
    Aluno aluno = consultarAluno(idAluno);

    // Verifica se o aluno já está matriculado na disciplina
    if (disciplina.getAlunos().contains(aluno)) {
        throw new IllegalStateException("Aluno já está matriculado na disciplina especificada.");
    }

    // Verifica disponibilidade da disciplina
    if (!disciplinaService.verificaDisponibilidadeDisciplina(nomeDisciplina)) {
        throw new IllegalStateException("Disciplina não está disponível para matrícula.");
    }

    // Conta as disciplinas obrigatórias e optativas do aluno
    long qtdDisciplinasObrigatorias = aluno.getDisciplinas().stream().filter(Disciplina::getObrigatoria).count();
    long qtdDisciplinasOptativas = aluno.getDisciplinas().stream().filter(Disciplina::getOptativa).count();

    // Verifica condições de matrícula
    boolean podeMatricular = false;
    if (disciplina.getObrigatoria() && qtdDisciplinasObrigatorias < 4) {
        podeMatricular = true;
    } else if (disciplina.getOptativa() && qtdDisciplinasOptativas < 2) {
        podeMatricular = true;
    }

    if (!podeMatricular) {
        throw new IllegalStateException("Aluno não pode se matricular na disciplina devido a limites de matrícula.");
    }

    // Matricula o aluno na disciplina
    disciplina.getAlunos().add(aluno);
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
    if (disciplina == null) {
        throw new IllegalArgumentException("Disciplina com nome " + nomeDisciplina + " não encontrada.");
    }

    // Busca o aluno pelo id e verifica se ele existe
    Aluno aluno = consultarAluno(idAluno);
    if (aluno == null) {
        throw new IllegalArgumentException("Aluno com ID " + idAluno + " não encontrado.");
    }

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
    // Modifica a consulta para fazer o join fetch das disciplinas
    return alunoRepository.findById(idAluno)
        .map(aluno -> {
            aluno.getDisciplinas().size(); // Inicializa a coleção disciplinas
            return aluno;
        })
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
        StringBuilder disciplinasCursadas = new StringBuilder("Disciplinas cursadas por " + aluno.getNome() + ":\n");
        aluno.getDisciplinas().forEach(disciplina -> disciplinasCursadas.append(disciplina.getNome()).append(", "));
        disciplinasCursadas.append("realizada com sucesso!\n");
        System.out.println(disciplinasCursadas.toString());
    }

    @Transactional
public void efetuarMatricula(Long idAluno) {
    Aluno aluno = consultarAluno(idAluno); // Certifique-se de que este método está carregando todas as disciplinas corretamente
    
    if (aluno.getDisciplinas().isEmpty()) {
        System.err.println("Não há disciplinas disponíveis para matrícula.");
    } else {
        // Use uma cópia da lista de disciplinas para evitar problemas de ConcurrentModificationException ao remover disciplinas da lista original
        List<Disciplina> disciplinasParaVerificar = new ArrayList<>(aluno.getDisciplinas());

        for (Disciplina disciplina : disciplinasParaVerificar) {
            if (disciplinaService.verificaStatusDisciplina(disciplina.getNome())) {
                System.out.println("O aluno " + aluno.getNome() + " foi matriculado na disciplina " + disciplina.getNome());
            } else {
                cancelarMatriculaDisciplina(disciplina.getNome(), idAluno);
                System.out.println("O aluno " + aluno.getNome() + " não pode se matricular na disciplina " + disciplina.getNome() + " pois ela não atingiu a quantidade de alunos necessários para ocorrer neste semestre.");
            }
        }
    }

    salvarAluno(aluno); // Garanta que todas as mudanças no aluno sejam persistidas
}


}
