package com.pucmg.lab01;

import com.pucmg.lab01.models.Aluno;
import com.pucmg.lab01.models.Disciplina;
import com.pucmg.lab01.models.Professor;
import com.pucmg.lab01.models.Secretario;
import com.pucmg.lab01.models.Usuario;
import com.pucmg.lab01.services.AlunoService;
import com.pucmg.lab01.services.ProfessorService;
import com.pucmg.lab01.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;
import java.util.stream.Collectors;

@SpringBootApplication
public class SistemaDeMatriculasApplication implements CommandLineRunner {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private ProfessorService professorService;

    public static void main(String[] args) {
        SpringApplication.run(SistemaDeMatriculasApplication.class, args);
    }

    @Override
    public void run(String... args) {
        startSystem();
    }

    private void startSystem() {
        Scanner scanner = new Scanner(System.in);
        clearScreen();
        System.out.println("Bem-vindo ao Sistema de Matrículas!\n");

        System.out.print("Digite seu login: ");
        String login = scanner.nextLine();

        System.out.print("Digite sua senha: ");
        String password = scanner.nextLine();

        Optional<Usuario> usuarioOpt = usuarioService.autenticarUsuario(login, password);

        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            String tipoUsuario = usuarioService.determinarTipoUsuario(usuario);

            boolean continuarSistema = true; // Controla o loop principal do sistema

            while (continuarSistema) {
                switch (tipoUsuario) {
                    case "Secretário":
                        clearScreen();
                        Secretario secretario = (Secretario) usuario;
                        System.out.println("Olá, " + secretario.getNome() + "! O que deseja realizar?\n");
                        System.out.println("1 - Gerenciar aluno\n2 - Gerenciar professor\n3 - Gerenciar disciplina\n4 - Sair");
                        int opcao = scanner.nextInt();
                        scanner.nextLine();

                        switch (opcao) {
                            case 1:
                                clearScreen();
                                gerenciarAluno(scanner);
                                break;
                            case 2:
                                clearScreen();
                                gerenciarProfessor(scanner);
                                break;
                            case 3:
                                // Lógica para gerenciar disciplina
                                break;
                            case 4:
                                startSystem(); // Sai do loop principal
                                break;
                            default:
                                clearScreen();
                                System.out.println("Opção inválida.");
                                break;
                        }
                        break;
                    case "Professor":
                        // Lógica para Professor
                        break;
                    case "Aluno":
                        // Lógica para Aluno
                        break;
                    default:
                        System.out.println("Tipo de usuário desconhecido.\n");
                        startSystem(); 
                        break;
                }
            }
        } else {
            System.out.println("Credenciais inválidas.");
            startSystem();
        }

        scanner.close();
    }

    private void gerenciarAluno(Scanner scanner) {
        boolean continuarGerenciamentoAluno = true;

        while (continuarGerenciamentoAluno) {
            System.out.println("Selecione a opção desejada:\n1 - Cadastrar aluno\n2 - Consultar aluno\n3 - Atualizar aluno\n4 - Remover aluno\n5 - Voltar ao menu principal");
            int opcaoAluno = scanner.nextInt();
            scanner.nextLine();

            switch (opcaoAluno) {
                case 1:
                    clearScreen();
                    System.out.print("Digite o nome completo do aluno: ");
                    String nomeCompleto = scanner.nextLine();

                    System.out.print("Digite o CPF do aluno: ");
                    String cpf = scanner.nextLine();

                    usuarioService.cadastrarAluno(nomeCompleto, cpf);
                    clearScreen();
                    System.out.println("Aluno cadastrado com sucesso!\n");
                    break;
                case 2:
                    clearScreen();
                    System.out.print("Digite o CPF do aluno: ");
                    String cpfPesquisa = scanner.nextLine();
                    Aluno aluno = alunoService.consultarAlunoCPF(cpfPesquisa);
                    clearScreen();
                    if (aluno != null) {
                        System.out.println("Dados do aluno:\n" + "Nome completo: " + aluno.getNome() + "\nCPF: " + aluno.getCPF() + "\nMatrícula: " + aluno.getMatricula());
                    } else {
                        System.out.println("Aluno não encontrado.\n");
                    }
                    System.out.println("\nPressione Enter para voltar ao menu.");
                    scanner.nextLine();  // Espera o usuário pressionar Enter
                    clearScreen();
                    break;
                case 3:
                    clearScreen();
                    System.out.print("Digite o CPF do aluno: ");
                    String cpfPesquisa2 = scanner.nextLine();
                    Aluno aluno2 = alunoService.consultarAlunoCPF(cpfPesquisa2);
                    clearScreen();
                    if (aluno2 != null) {
                        System.out.println("Dados do aluno:\n" + "Nome completo: " + aluno2.getNome() + "\nCPF: " + aluno2.getCPF() + "\nMatrícula: " + aluno2.getMatricula());
                        System.out.print("\n\nDigite o novo nome completo do aluno: ");
                        String novoNomeCompleto = scanner.nextLine();
                        aluno2.setNome(novoNomeCompleto);
                        alunoService.salvarAluno(aluno2);
                        clearScreen();
                        System.out.println("Aluno atualizado com sucesso!\n");
                    } else {
                        clearScreen();
                        System.out.println("Aluno não encontrado.\n");
                    }
                    break;
                case 4:
                    clearScreen();
                    System.out.print("Digite o CPF do aluno: ");
                    String cpfPesquisa3 = scanner.nextLine();
                    Aluno aluno3 = alunoService.consultarAlunoCPF(cpfPesquisa3);
                    clearScreen();
                    if (aluno3 != null) {
                        System.out.println("Dados do aluno:\n" + "Nome completo: " + aluno3.getNome() + "\nCPF: " + aluno3.getCPF() + "\nMatrícula: " + aluno3.getMatricula());
                        System.out.println("\nTem certeza que deseja remover o aluno do sistema?\n1 - Sim\n2 - Não");
                        int confirmacao = scanner.nextInt();
                        scanner.nextLine();
                        if (confirmacao == 1) {
                            alunoService.removerAluno(aluno3);
                            clearScreen();
                            System.out.println("Aluno removido com sucesso!\n");
                        } else {
                            clearScreen();
                            System.out.println("Operação cancelada.\n");
                        }
                    } else {
                        System.out.println("Aluno não encontrado.\n");
                    }
                    break;
                case 5:
                    continuarGerenciamentoAluno = false; // Sai do loop de gerenciamento de alunos
                    break;
                default:
                    clearScreen();
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }    

    private void gerenciarProfessor(Scanner scanner) {
        boolean continuarGerenciamentoProfessor = true;
    
        while (continuarGerenciamentoProfessor) {
            System.out.println("Selecione a opção desejada:\n1 - Cadastrar professor\n2 - Consultar professor\n3 - Atualizar professor\n4 - Remover professor\n5 - Voltar ao menu principal");
            int opcaoProfessor = scanner.nextInt();
            scanner.nextLine();
    
            switch (opcaoProfessor) {
                case 1:
                    clearScreen();
                    System.out.print("Digite o nome completo do professor: ");
                    String nomeCompleto = scanner.nextLine();
    
                    System.out.print("Digite o CPF do professor: ");
                    String cpf = scanner.nextLine();
    
                    usuarioService.cadastrarProfessor(nomeCompleto, cpf);
                    clearScreen();
                    System.out.println("Professor cadastrado com sucesso!\n");
                    break;
                case 2:
                    clearScreen();
                    System.out.print("Digite o CPF do professor: ");
                    String cpfPesquisa = scanner.nextLine();
                    Professor professor = professorService.consultarProfessorCPF(cpfPesquisa);
                    clearScreen();
                    if (professor != null) {
                        String disciplinasLeciona = (professor.getDisciplinas() != null && !professor.getDisciplinas().isEmpty()) ?
                            professor.getDisciplinas().stream()
                                    .map(Disciplina::getNome) // Assuming Disciplina has a getNome() method
                                    .collect(Collectors.joining(", ")) :
                            "Não está vinculado em nenhuma disciplina no momento!";
                        
                        System.out.println("Dados do professor:\n" + 
                                           "Nome completo: " + professor.getNome() + 
                                           "\nCPF: " + professor.getCPF() + 
                                           "\nDisciplinas que leciona: " + disciplinasLeciona);
                    } else {
                        System.out.println("Professor não encontrado.\n");
                    }
                    System.out.println("\nPressione Enter para voltar ao menu.");
                    scanner.nextLine();  // Espera o usuário pressionar Enter
                    clearScreen();
                    break;
                case 3:
                    clearScreen();
                    System.out.print("Digite o CPF do professor: ");
                    String cpfPesquisa2 = scanner.nextLine();
                    Professor professor2 = professorService.consultarProfessorCPF(cpfPesquisa2);
                    clearScreen();
                    if (professor2 != null) {
                        System.out.println("Dados do professor:\n" + 
                                           "Nome completo: " + professor2.getNome() + 
                                           "\nCPF: " + professor2.getCPF());
                        System.out.print("\n\nDigite o novo nome completo do professor: ");
                        String novoNomeCompleto = scanner.nextLine();
                        professor2.setNome(novoNomeCompleto);
                        professorService.salvarProfessor(professor2);
                        clearScreen();
                        System.out.println("Professor atualizado com sucesso!\n");
                    } else {
                        clearScreen();
                        System.out.println("Professor não encontrado.\n");
                    }
                    break;
                case 4:
                    clearScreen();
                    System.out.print("Digite o CPF do professor: ");
                    String cpfPesquisa3 = scanner.nextLine();
                    Professor professor3 = professorService.consultarProfessorCPF(cpfPesquisa3);
                    clearScreen();
                    if (professor3 != null) {
                        System.out.println("Dados do professor:\n" + 
                                           "Nome completo: " + professor3.getNome() + 
                                           "\nCPF: " + professor3.getCPF());
                        System.out.println("\nTem certeza que deseja remover o professor do sistema?\n1 - Sim\n2 - Não");
                        int confirmacao = scanner.nextInt();
                        scanner.nextLine();
                        if (confirmacao == 1) {
                            professorService.removerProfessor(professor3);
                            clearScreen();
                            System.out.println("Professor removido com sucesso!\n");
                        } else {
                            clearScreen();
                            System.out.println("Operação cancelada.\n");
                        }
                    } else {
                        System.out.println("Professor não encontrado.\n");
                    }
                    break;
                case 5:
                    continuarGerenciamentoProfessor = false; // Sai do loop de gerenciamento de professores
                    break;
                default:
                    clearScreen();
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }
    


    private static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
