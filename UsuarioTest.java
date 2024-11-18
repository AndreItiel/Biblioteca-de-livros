import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UsuarioTest {

    private Usuario usuario;
    private Livro livro;

    @BeforeEach
    public void setUp() {
        usuario = new Usuario("João", "joao@email.com", 1);
        livro = new Livro("O Senhor dos Anéis");
    }

    @Test
    public void testEmprestarLivro() {
        
        assertFalse(livro.isEmprestado(), "O livro deveria estar disponível");

        usuario.emprestarLivro(livro);

        assertTrue(livro.isEmprestado(), "O livro deveria estar emprestado");

        assertTrue(usuario.livrosEmprestados.contains(livro), "O livro deveria estar na lista de livros emprestados");
    }

    @Test
    public void testDevolverLivro() {
        usuario.emprestarLivro(livro);

        assertTrue(livro.isEmprestado(), "O livro deveria estar emprestado");

        usuario.devolverLivro(livro);

        assertFalse(livro.isEmprestado(), "O livro deveria estar disponível");

        assertFalse(usuario.livrosEmprestados.contains(livro), "O livro não deveria estar na lista de livros emprestados");
    }

    @Test
    public void testUsuarioSemLivrosEmprestados() {
        assertTrue(usuario.livrosEmprestados.isEmpty(), "A lista de livros emprestados deveria estar vazia");
    }

    @Test
    public void testObterNome() {
        assertEquals("João", usuario.getNome(), "O nome do usuário deveria ser João");
    }

    @Test
    public void testObterId() {
        assertEquals(1, usuario.getId(), "O ID do usuário deveria ser 1");
    }
}
