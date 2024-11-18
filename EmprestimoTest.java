import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;

class EmprestimoTest {

    private Livro livro;
    private Usuario usuario;
    private Emprestimo emprestimo;
    private Date dataEmprestimo;

    @BeforeEach
    void setUp() {
        livro = mock(Livro.class);
        usuario = mock(Usuario.class);

        when(livro.getTitulo()).thenReturn("O Hobbit");
        when(usuario.getNome()).thenReturn("João");

        dataEmprestimo = new Date();

        emprestimo = new Emprestimo(livro, usuario, dataEmprestimo);
    }

    @Test
    void testRegistrarEmprestimo() {
        emprestimo.registrarEmprestimo();
        
        verify(usuario).emprestarLivro(livro);
        
    }

    @Test
    void testRegistrarDevolucao() {
        Date dataDevolucao = new Date();

        emprestimo.registrarDevolucao(dataDevolucao);
        
        verify(usuario).devolverLivro(livro);

        assertEquals(dataDevolucao, emprestimo.dataDevolucao);
    }

    @Test
    void testToString() {
        String expectedString = "Empréstimo [Livro: O Hobbit, Usuário: João, Data de Empréstimo: " + dataEmprestimo + ", Data de Devolução: Ainda não devolvido]";

        assertEquals(expectedString, emprestimo.toString());
    }
}
