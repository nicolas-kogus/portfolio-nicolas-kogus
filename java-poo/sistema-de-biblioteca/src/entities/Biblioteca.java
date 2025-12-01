package entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Biblioteca {

    public List<Usuario> usuarios = new ArrayList<>();
    public List<Livro> livros = new ArrayList<>();

    private Livro livro;
    Scanner sc = new Scanner(System.in);
    DateTimeFormatter sdf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public void addLivro(Livro livro) {
        livros.add(livro);
    }

    public void removeLivro(Livro livro) {
        livros.remove(livro);
    }

    public void addUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public void removeUsuario(Usuario usuario) {
        usuarios.remove(usuario);
    }

    public void cadastrarLivro() {
        System.out.println();

        System.out.print("Nome do título: ");
        String titulo = sc.nextLine();

        System.out.print("Nome do autor: ");
        String autor = sc.nextLine();

        System.out.print("Ano da publicação: ");
        String date = sc.next();

        LocalDate anoPublicacao = LocalDate.parse(date, sdf);

        sc.nextLine();

        Livro instanciarLivro = new Livro(titulo, autor, anoPublicacao, true);
        addLivro(instanciarLivro);
    }

    public void cadastrarUsuario() {
        System.out.println();

        System.out.println("Vamos cadastrar um usuário:");
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("ID (4 dígitos): ");
        int id = sc.nextInt();

        Usuario usuario = new Usuario(nome, id);
        addUsuario(usuario);

        System.out.println("Usuário cadastrado com sucesso!");
    }

    public void realizarEmprestimo(Livro livro, Usuario usuario) {
        usuario.pegarLivro(livro);
        livro.setDisponivel(false);
    }

    public void realizarDevolucao(Livro livro, Usuario usuario) {
        usuario.devolverLivro(livro);
        livro.setDisponivel(true);
    }

    public void livrosDisponiveis() {
        System.out.println();
        int i = 1;

        for (Livro livro : livros) {
            if (livro.getDisponivel()) {
                System.out.println(String.format("%d. ", i) + livro.getTitulo());
                i++;
            }
        }
    }
}
