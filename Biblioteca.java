import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private List<Usuario> listaDeUsuarios;
    private List<Livro> listaDeLivros;

    public Biblioteca() {
        listaDeUsuarios = new ArrayList<>();
        listaDeLivros = new ArrayList<>();
    }

    public void adicionarLivro(Livro livro) {
        listaDeLivros.add(livro);
        System.out.println("Livro adicionado: " + livro.getTitulo());
    }

    public void registrarUsuario(Usuario usuario) {
        listaDeUsuarios.add(usuario);
        System.out.println("Usuário registrado: " + usuario.getNome());
    }

    public Livro buscarLivro(String titulo) {
        for (Livro livro : listaDeLivros) {
            if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                return livro;
            }
        }
        System.out.println("Livro não encontrado: " + titulo);
        return null;
    }

    public Usuario buscarUsuario(int id) {
        for (Usuario usuario : listaDeUsuarios) {
            if (usuario.getId() == id) {
                return usuario;
            }
        }
        System.out.println("Usuário não encontrado: " + id);
        return null;
    }

    public void listarLivros() {
        if (listaDeLivros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado na biblioteca.");
            return;
        }
        for (Livro livro : listaDeLivros) {
            System.out.println(livro);
        }
    }

    public void salvarLivrosNoArquivo(String nomeDoArquivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeDoArquivo))) {
            for (Livro livro : listaDeLivros) {
                writer.write(livro.toString());
                writer.newLine();
            }
            System.out.println("Livro(s) salvo(s) no arquivo " + nomeDoArquivo + ".");
        } catch (IOException e) {
            System.err.println("Erro ao salvar: " + e.getMessage());
        }
    }
}
