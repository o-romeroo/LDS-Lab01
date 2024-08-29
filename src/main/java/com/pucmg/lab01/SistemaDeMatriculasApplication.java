package com.pucmg.lab01;

import com.pucmg.lab01.models.Secretario;
import com.pucmg.lab01.models.Usuario;
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

    public static void main(String[] args) {
        SpringApplication.run(SistemaDeMatriculasApplication.class, args);
    }

    @Override
    public void run(String... args) {
        startSystem();
    }

    private void startSystem() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bem-vindo ao Sistema de Matrículas!");

        System.out.print("Digite seu login: ");
        String login = scanner.nextLine();

        System.out.print("Digite sua senha: ");
        String password = scanner.nextLine();

        Optional<Usuario> usuarioOpt = usuarioService.autenticarUsuario(login, password);

        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            String tipoUsuario = usuarioService.determinarTipoUsuario(usuario);

            switch (tipoUsuario) {
                case "Secretário":
                    Secretario secretario = (Secretario) usuario;
                    System.out.println("Olá, " + secretario.getNome() + "! O que deseja realizar?");
                    System.out.println("1 - Cadastrar aluno\n2 - Consultar aluno\n3 - Gerar curriculo");
                    int opcao = scanner.nextInt();
                    scanner.nextLine(); // Consome a nova linha após o nextInt()

                    switch (opcao) {
                        case 1:
                            clearScreen(); // Limpa a tela antes de continuar
                            System.out.print("Digite o nome completo do aluno: ");
                            String nomeCompleto = scanner.nextLine();

                            System.out.print("Digite o CPF do aluno: ");
                            String cpf = scanner.nextLine();

                            usuarioService.cadastrarAluno(nomeCompleto, cpf);
							clearScreen();
                            System.out.println("Aluno cadastrado com sucesso!");
                            break;
                        case 2:
                            // Lógica para consulta de aluno
                            break;
                        case 3:
                            // Lógica para geração de currículo
                            break;
                        default:
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
                    break;
            }
        } else {
            System.out.println("Credenciais inválidas.");
        }

        scanner.close();
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
