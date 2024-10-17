public class Livro {
    private String titulo;
    private String autor;
    private String isbn;
    private boolean disponivel;

    public Livro(String titulo, String autor, String isbn) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.disponivel = true;
    }

    public void marcarComoEmprestado() {
        if (disponivel) {
            disponivel = false;
            System.out.println("O livro '" + titulo + "' foi marcado como emprestado.");
        } else {
            System.out.println("O livro '" + titulo + "' já está emprestado.");
        }
    }

    public void marcarComoDisponivel() {
        if (!disponivel) {
            disponivel = true;
            System.out.println("O livro '" + titulo + "' foi marcado como disponível.");
        } else {
            System.out.println("O livro '" + titulo + "' já está disponível.");
        }
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    @Override
    public String toString() {
        return "Título: " + titulo + ", Autor: " + autor + ", ISBN: " + isbn + ", Disponível: " + (disponivel ? "Sim" : "Não");
    }
}
