import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String nome;
    private String email;
    private int id;
    private List<Livro> livrosEmprestados;

    public Usuario(String nome, String email, int id) {
        this.nome = nome;
        this.email = email;
        this.id = id;
        this.livrosEmprestados = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public int getId() {
        return id;
    }

    public void emprestarLivro(Livro livro) {
        livrosEmprestados.add(livro);
        livro.marcarComoEmprestado();
    }

    public void devolverLivro(Livro livro) {
        livrosEmprestados.remove(livro);
        livro.marcarComoDisponivel();
    }
}
