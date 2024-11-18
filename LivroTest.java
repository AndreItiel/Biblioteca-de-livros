import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LivroTest {
    private Livro livro;

    @BeforeEach
    void setUp() {

        livro = new Livro("O Senhor dos Anéis", "J.R.R. Tolkien", "978-3-16-148410-0");
    }

    @Test
    void testLivroCriacao() {
        assertNotNull(livro);
        assertEquals("O Senhor dos Anéis", livro.getTitulo());
        assertEquals("J.R.R. Tolkien", livro.getAutor());
        assertEquals("978-3-16-148410-0", livro.getIsbn());
        assertTrue(livro.isDisponivel(), "O livro deve estar disponível ao ser criado.");
    }

    @Test
    void testMarcarComoEmprestado() {
        livro.marcarComoEmprestado();
        assertFalse(livro.isDisponivel(), "O livro deve estar emprestado após ser marcado como emprestado.");

        livro.marcarComoEmprestado();
        assertFalse(livro.isDisponivel(), "O livro não deve estar disponível após ser marcado como emprestado duas vezes.");
    }

    @Test
    void testMarcarComoDisponivel() {

        livro.marcarComoEmprestado();
        assertFalse(livro.isDisponivel(), "O livro deve estar emprestado.");


        livro.marcarComoDisponivel();
        assertTrue(livro.isDisponivel(), "O livro deve estar disponível após ser marcado como disponível.");

        livro.marcarComoDisponivel();
        assertTrue(livro.isDisponivel(), "O livro não deve ser marcado como disponível duas vezes.");
    }

    @Test
    void testToString() {

        String expected = "Título: O Senhor dos Anéis, Autor: J.R.R. Tolkien, ISBN: 978-3-16-148410-0, Disponível: Sim";
        assertEquals(expected, livro.toString(), "O método toString não retorna o valor esperado.");
    }
}
