package com.pucmg.lab01;

import com.pucmg.lab01.models.Aluno;
import com.pucmg.lab01.models.Secretario;
import com.pucmg.lab01.models.Usuario;
import com.pucmg.lab01.services.AlunoService;
import com.pucmg.lab01.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;
import java.util.Scanner;

@SpringBootApplication
public class SistemaDeMatriculasApplication implements CommandLineRunner {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AlunoService alunoService;

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
                        System.out.println("Olá, " + secretario.getNome() + "! O que deseja realizar?");
                        System.out.println("1 - Gerenciar aluno\n2 - Gerenciar professor\n3 - Gerenciar disciplina\n4 - Sair");
                        int opcao = scanner.nextInt();
                        scanner.nextLine();

                        switch (opcao) {
                            case 1:
                                clearScreen();
                                gerenciarAluno(scanner);
                                break;
                            case 2:
                                // Lógica para gerenciar professor
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
                        System.out.println("Tipo de usuário desconhecido.");
                        continuarSistema = false; // Sai do loop principal em caso de tipo desconhecido
                        break;
                }
            }
        } else {
            System.out.println("Credenciais inválidas.");
        }

        scanner.close();
    }

    private void gerenciarAluno(Scanner scanner) {
        boolean continuarGerenciamentoAluno = true;

        while (continuarGerenciamentoAluno) {
            System.out.println("Selecione a opção desejada.\n1 - Cadastrar aluno\n2 - Consultar aluno\n3 - Atualizar aluno\n4 - Remover aluno\n5 - Voltar ao menu principal");
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
                    System.out.println("Aluno cadastrado com sucesso!");
                    System.out.println("\nPressione Enter para voltar ao menu.");
                    scanner.nextLine();  // Espera o usuário pressionar Enter
                    clearScreen();
                    break;
                case 2:
                    clearScreen();
                    System.out.print("Digite o CPF do aluno: ");
                    String cpfPesquisa = scanner.nextLine();
                    Aluno aluno = alunoService.consultarAlunoCPF(cpfPesquisa);
                    clearScreen();
                    if (aluno != null) {
                        System.out.println("Dados do aluno\n" + "Nome completo: " + aluno.getNome() + "\nCPF: " + aluno.getCPF() + "\nMatrícula: " + aluno.getMatricula());
                    } else {
                        System.out.println("Aluno não encontrado.");
                    }
                    System.out.println("\nPressione Enter para voltar ao menu.");
                    scanner.nextLine();  // Espera o usuário pressionar Enter
                    clearScreen();
                    break;
                case 3:
                    // Lógica para atualização de aluno
                    break;
                case 4:
                    // Lógica para remoção de aluno
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
