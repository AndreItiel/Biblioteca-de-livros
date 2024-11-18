import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BibliotecaTest {
    private Biblioteca biblioteca;
    private Livro livro1;
    private Livro livro2;
    private Usuario usuario1;
    private Usuario usuario2;

    @BeforeEach
    public void setUp() {

        biblioteca = new Biblioteca();
        livro1 = new Livro("Java para Iniciantes", "Autor X", 2021);
        livro2 = new Livro("Design Patterns", "Autor Y", 2020);
        usuario1 = new Usuario(1, "João");
        usuario2 = new Usuario(2, "Maria");

        biblioteca.adicionarLivro(livro1);
        biblioteca.adicionarLivro(livro2);
        biblioteca.registrarUsuario(usuario1);
        biblioteca.registrarUsuario(usuario2);
    }

    @Test
    public void testAdicionarLivro() {
        Livro livroBuscado = biblioteca.buscarLivro("Java para Iniciantes");
        assertNotNull(livroBuscado, "Livro deveria ter sido encontrado.");
        assertEquals("Java para Iniciantes", livroBuscado.getTitulo(), "Título do livro não corresponde.");
    }

    @Test
    public void testBuscarLivro() {
        Livro livroBuscado = biblioteca.buscarLivro("Design Patterns");
        assertNotNull(livroBuscado, "Livro deveria ter sido encontrado.");
        assertEquals("Design Patterns", livroBuscado.getTitulo(), "Título do livro não corresponde.");
    }

    @Test
    public void testBuscarLivroInexistente() {
        Livro livroBuscado = biblioteca.buscarLivro("Livro Inexistente");
        assertNull(livroBuscado, "Livro inexistente deveria retornar null.");
    }

    @Test
    public void testBuscarUsuario() {
        Usuario usuarioBuscado = biblioteca.buscarUsuario(1);
        assertNotNull(usuarioBuscado, "Usuário deveria ter sido encontrado.");
        assertEquals("João", usuarioBuscado.getNome(), "Nome do usuário não corresponde.");
    }

    @Test
    public void testBuscarUsuarioInexistente() {
        Usuario usuarioBuscado = biblioteca.buscarUsuario(99);
        assertNull(usuarioBuscado, "Usuário inexistente deveria retornar null.");
    }

    @Test
    public void testListarLivros() {

        biblioteca.listarLivros();

abordagem

verificar
    }

    @Test
    public void testSalvarLivrosNoArquivo() throws IOException {
        String nomeArquivo = "livros_test.txt";
        biblioteca.salvarLivrosNoArquivo(nomeArquivo);
        
        assertTrue(Files.exists(Paths.get(nomeArquivo)), "Arquivo de livros não foi criado.");

        String conteudo = Files.readString(Paths.get(nomeArquivo));
        assertTrue(conteudo.contains("Java para Iniciantes"), "Arquivo não contém o livro esperado.");
        assertTrue(conteudo.contains("Design Patterns"), "Arquivo não contém o livro esperado.");

        Files.delete(Paths.get(nomeArquivo));
    }
}
