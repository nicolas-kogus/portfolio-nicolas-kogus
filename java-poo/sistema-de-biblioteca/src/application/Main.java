package application;

import entities.Biblioteca;
import entities.Livro;
import entities.Usuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        boolean reiniciarPrograma = true;

        while (reiniciarPrograma) {
            reiniciarPrograma = false;

            Biblioteca biblioteca = new Biblioteca();
            Usuario usuario = new Usuario();

            // Livros pré-registrados
            biblioteca.addLivro(new Livro("O Senhor dos Anéis", "J.R.R. Tolkien", LocalDate.of(1954, 7, 29), true));
            biblioteca.addLivro(new Livro("Harry Potter e a Pedra Filosofal", "J.K. Rowling", LocalDate.of(1997, 6, 26), true));
            biblioteca.addLivro(new Livro("Dom Quixote", "Miguel de Cervantes", LocalDate.of(1605, 1, 1), true));

            // Cadastro do usuário
            System.out.println("Vamos cadastrar um usuário:");
            System.out.print("Nome: ");
            String nome = sc.nextLine();
            System.out.print("ID (4 dígitos): ");
            int id = sc.nextInt();
            sc.nextLine();
            usuario = new Usuario(nome, id);
            biblioteca.addUsuario(usuario);

            String escolha;

            do {
                System.out.println("\nO que você deseja fazer?");
                System.out.println("[PEGAR] Realizar o empréstimo de um livro.");
                System.out.println("[DEVOLVER] Devolver um livro emprestado.");
                System.out.println("[CADASTRAR] Cadastrar um novo livro.");
                System.out.println("[SAIR] Encerrar o programa.");
                System.out.print("Digite a sua escolha: ");
                escolha = sc.next().toLowerCase();
                sc.nextLine();

                if (escolha.equals("pegar")) {
                    // Filtra apenas livros disponíveis
                    List<Livro> disponiveis = new ArrayList<>();
                    for (Livro livro : biblioteca.livros) {
                        if (livro.getDisponivel()) {
                            disponiveis.add(livro);
                        }
                    }

                    if (disponiveis.isEmpty()) {
                        System.out.println("Nenhum livro disponível para empréstimo.");
                        continue;
                    }

                    // Mostra livros disponíveis
                    System.out.println("\nLivros disponíveis:");
                    for (int i = 0; i < disponiveis.size(); i++) {
                        System.out.println((i + 1) + ". " + disponiveis.get(i).getTitulo());
                    }

                    System.out.print("Digite o número do livro que deseja pegar: ");
                    int numeroLivro = sc.nextInt();
                    sc.nextLine();

                    if (numeroLivro < 1 || numeroLivro > disponiveis.size()) {
                        System.out.println("[ERRO] Número inválido!");
                        continue;
                    }

                    Livro livroEscolhido = disponiveis.get(numeroLivro - 1);
                    usuario.pegarLivro(livroEscolhido);

                } else if (escolha.equals("devolver")) {
                    if (usuario.livrosEmprestados.isEmpty()) {
                        System.out.println("Você não tem nenhum livro emprestado, reiniciando o programa...");
                        reiniciarPrograma = true;
                        break;
                    }

                    // Lista livros emprestados
                    usuario.listarLivrosEmprestados();
                    System.out.print("Digite o número do livro que deseja devolver: ");
                    int numeroDevolucao = sc.nextInt();
                    sc.nextLine();

                    if (numeroDevolucao < 1 || numeroDevolucao > usuario.livrosEmprestados.size()) {
                        System.out.println("[ERRO] Número inválido!");
                        continue;
                    }

                    Livro livroDevolver = usuario.livrosEmprestados.get(numeroDevolucao - 1);
                    usuario.devolverLivro(livroDevolver);

                } else if (escolha.equals("cadastrar")) {
                    biblioteca.cadastrarLivro();

                } else if (escolha.equals("sair")) {
                    System.out.println("Programa encerrado!");
                    return;

                } else {
                    System.out.println("[ERRO] Opção inválida, digite novamente.");
                }

            } while (true);
        }
    }
}
