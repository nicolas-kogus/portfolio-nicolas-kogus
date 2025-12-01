package entities;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

    private String nome;
    private Integer id;

    Biblioteca biblioteca = new Biblioteca();

    public List<Livro> livrosEmprestados = new ArrayList<>(2);

    public Usuario() {
    }

    public Usuario(String nome, Integer id) {
        this.nome = nome;
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void addLivro(Livro livro) {
        livrosEmprestados.add(livro);
    }

    public void removeLivro(Livro livro) {
        livrosEmprestados.remove(livro);
    }

    // metodo para o usuario pegar o livro caso esteja disponível
    public void pegarLivro(Livro livro) {
        System.out.println();

        if (livro.getDisponivel() && livrosEmprestados.size() < 2) {

            livrosEmprestados.add(livro);
            livro.setDisponivel(false);
            System.out.println("Livro alugado com sucesso!");

        } else {
            System.out.println("[ERRO] Limite atingido OU livro indisponível!");
            System.out.println("Reiniciando programa...");
            System.out.println();
        }
    }

    // metodo para o usuario devolver o livro tornando-o disponivel novamente
    public void devolverLivro(Livro livro) {

        livrosEmprestados.remove(livro);
        livro.devolver();

        System.out.println("Livro devolvido com sucesso!");

    }

    public void listarLivrosEmprestados() {
        int i = 1;

        System.out.println();
        System.out.println("Lista dos seus livros emprestados:");

        for (Livro livroEmprestado : livrosEmprestados) {
            System.out.printf("%d. ", i);
            System.out.println(livroEmprestado.getTitulo());
            i++;
        }
    }
}
