import java.util.Date;

public class Emprestimo {
    private Livro livro;
    private Usuario usuario;
    private Date dataEmprestimo;
    private Date dataDevolucao;

    public Emprestimo(Livro livro, Usuario usuario, Date dataEmprestimo) {
        this.livro = livro;
        this.usuario = usuario;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = null; // Ainda não devolvido
    }

    public void registrarEmprestimo() {
        usuario.emprestarLivro(livro);
        System.out.println("Empréstimo registrado para o livro: " + livro.getTitulo() + ", Usuário: " + usuario.getNome());
    }

    public void registrarDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
        usuario.devolverLivro(livro);
        System.out.println("Devolução registrada para o livro: " + livro.getTitulo() + ", Usuário: " + usuario.getNome());
    }

    @Override
    public String toString() {
        return "Empréstimo [Livro: " + livro.getTitulo() + ", Usuário: " + usuario.getNome() +
                ", Data de Empréstimo: " + dataEmprestimo + ", Data de Devolução: " + (dataDevolucao != null ? dataDevolucao : "Ainda não devolvido") + "]";
    }
}
