import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Biblioteca biblioteca = new Biblioteca();

        while (true) {
            System.out.println("\nSistema de Biblioteca:");
            System.out.println("1. Adicionar Livro");
            System.out.println("2. Registrar Usuário");
            System.out.println("3. Emprestar Livro");
            System.out.println("4. Devolver Livro");
            System.out.println("5. Listar Livros");
            System.out.println("6. Salvar Livros no Arquivo");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do Scanner

            switch (opcao) {
                case 1:
                    System.out.print("Título do livro: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Autor do livro: ");
                    String autor = scanner.nextLine();
                    System.out.print("ISBN do livro: ");
                    String isbn = scanner.nextLine();
                    Livro novoLivro = new Livro(titulo, autor, isbn);
                    biblioteca.adicionarLivro(novoLivro);
                    break;

                case 2:
                    System.out.print("Nome do usuário: ");
                    String nomeUsuario = scanner.nextLine();
                    System.out.print("Email do usuário: ");
                    String emailUsuario = scanner.nextLine();
                    System.out.print("ID do usuário: ");
                    int idUsuario = scanner.nextInt();
                    scanner.nextLine(); // Limpar o buffer
                    Usuario novoUsuario = new Usuario(nomeUsuario, emailUsuario, idUsuario);
                    biblioteca.registrarUsuario(novoUsuario);
                    break;

                case 3:
                    System.out.print("Título do livro para empréstimo: ");
                    String tituloEmprestimo = scanner.nextLine();
                    Livro livroEmprestimo = biblioteca.buscarLivro(tituloEmprestimo);
                    if (livroEmprestimo != null && livroEmprestimo.isDisponivel()) {
                        System.out.print("ID do usuário que fará o empréstimo: ");
                        int idEmprestimo = scanner.nextInt();
                        scanner.nextLine(); // Limpar o buffer
                        Usuario usuarioEmprestimo = biblioteca.buscarUsuario(idEmprestimo);
                        if (usuarioEmprestimo != null) {
                            Emprestimo emprestimo = new Emprestimo(livroEmprestimo, usuarioEmprestimo, new Date());
                            emprestimo.registrarEmprestimo();
                        } else {
                            System.out.println("Usuário não encontrado.");
                        }
                    } else {
                        System.out.println("Livro não disponível para empréstimo.");
                    }
                    break;

                case 4:
                    System.out.print("Título do livro para devolução: ");
                    String tituloDevolucao = scanner.nextLine();
                    Livro livroDevolucao = biblioteca.buscarLivro(tituloDevolucao);
                    if (livroDevolucao != null) {
                        System.out.print("ID do usuário que está devolvendo o livro: ");
                        int idDevolucao = scanner.nextInt();
                        scanner.nextLine(); // Limpar o buffer
                        Usuario usuarioDevolucao = biblioteca.buscarUsuario(idDevolucao);
                        if (usuarioDevolucao != null) {
                            Emprestimo devolucao = new Emprestimo(livroDevolucao, usuarioDevolucao, new Date());
                            devolucao.registrarDevolucao(new Date());
                        } else {
                            System.out.println("Usuário não encontrado.");
                        }
                    } else {
                        System.out.println("Livro não encontrado.");
                    }
                    break;

                case 5:
                    biblioteca.listarLivros();
                    break;

                case 6:
                    System.out.print("Nome do arquivo para salvar os livros: ");
                    String nomeArquivo = scanner.nextLine();
                    biblioteca.salvarLivrosNoArquivo(nomeArquivo);
                    break;

                case 0:
                    System.out.println("Encerrando o sistema.");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
