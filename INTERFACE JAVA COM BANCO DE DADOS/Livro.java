public class Livro {
    private String titulo;
    private String autor;
    private String isbn;
    private String codigo;
    private String status;
    private String emprestador;
    private int diasEmprestado;

    // Construtor
    public Livro(String titulo, String autor, String isbn, String codigo) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.codigo = codigo;
        this.status = "Disponível"; // Inicializa o status como disponível
    }

    // Getters e setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmprestador() {
        return emprestador;
    }

    public void setEmprestador(String emprestador) {
        this.emprestador = emprestador;
    }

    public int getDiasEmprestado() {
        return diasEmprestado;
    }

    public void setDiasEmprestado(int diasEmprestado) {
        this.diasEmprestado = diasEmprestado;
    }
}
