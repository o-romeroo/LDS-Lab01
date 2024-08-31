package com.pucmg.lab01.services;

import com.pucmg.lab01.models.Aluno;
import com.pucmg.lab01.models.Professor;
import com.pucmg.lab01.models.Secretario;
import com.pucmg.lab01.models.Usuario;
import com.pucmg.lab01.repositories.AlunoRepository;
import com.pucmg.lab01.repositories.ProfessorRepository;
import com.pucmg.lab01.repositories.UsuarioRepository; // Add this import statement

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    public String determinarTipoUsuario(Usuario usuario) {
        if (usuario instanceof Secretario) {
            return "Secretário";
        } else if (usuario instanceof Professor) {
            return "Professor";
        } else if (usuario instanceof Aluno) {
            return "Aluno";
        } else {
            return "Desconhecido";
        }
    }
    
    public Optional<Usuario> autenticarUsuario(String login, String password) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByLogin(login);
    
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            if (usuario.getPassword().equals(password)) {
                return Optional.of(usuario);
            }
        }
    
        return Optional.empty();
    }

    // Função para cadastrar um novo aluno
    public Aluno cadastrarAluno(String nomeCompleto, String cpf) {
        // Gerar login e senha aleatórios
        String login = gerarLoginAleatorio(nomeCompleto);
        String senha = gerarSenhaAleatoria(nomeCompleto);
        int matricula = new Random().nextInt(90000000) + 10000000; // Gera um número aleatório de 100000 a 999999

        // Criar a instância de Aluno
        Aluno novoAluno = new Aluno(nomeCompleto, login, senha, cpf, List.of(), matricula);

        // Verificar se o aluno já existe pelo CPF
        if (alunoRepository.findByCPF(cpf).isPresent()) {
            throw new IllegalArgumentException("Aluno com CPF " + cpf + " já está cadastrado.");
        }

        // Salvar no banco de dados
        return alunoRepository.save(novoAluno);
    }

    // Função para cadastrar um novo aluno
    public Professor cadastrarProfessor(String nomeCompleto, String cpf) {
        // Gerar login e senha aleatórios
        String login = gerarLoginAleatorio(nomeCompleto);
        String senha = gerarSenhaAleatoria(nomeCompleto);// Gera um número aleatório de 100000 a 999999

        // Criar a instância de Professor
        Professor novoProfessor = new Professor(nomeCompleto, login, senha, cpf, List.of());

        // Verificar se o Professor já existe pelo CPF
        if (professorRepository.findByCPF(cpf).isPresent()) {
            throw new IllegalArgumentException("Aluno com CPF " + cpf + " já está cadastrado.");
        }

        // Salvar no banco de dados
        return professorRepository.save(novoProfessor);
    }

    private String gerarLoginAleatorio(String nomeCompleto) {
        String prefixoNome = nomeCompleto.substring(0, Math.min(nomeCompleto.length(), 3)).toLowerCase();
        int randomNum = new Random().nextInt(9000) + 1000; // Gera um número aleatório de 1000 a 9999
        return prefixoNome + randomNum;
    }

    private String gerarSenhaAleatoria(String nomeCompleto) {
        String[] nomes = nomeCompleto.split(" ");
        String prefixoSobrenome = nomes.length > 1 ? nomes[nomes.length - 1].substring(0, Math.min(nomes[nomes.length - 1].length(), 3)).toLowerCase() : "usr";
        int randomNum = new Random().nextInt(9000) + 1000; // Gera um número aleatório de 1000 a 9999
        return prefixoSobrenome + randomNum;
    }
    
    public String recuperarSenhaUser(String login){
        Optional<Usuario> usuarioOpt = usuarioRepository.findByLogin(login);

        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            return usuario.getPassword();
        }else {
            return "";
        }
    }
}
