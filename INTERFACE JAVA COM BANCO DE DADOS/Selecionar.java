import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Selecionar {
    private JFrame frame;

    public Selecionar(List<Livro> livros) {
        // Criando o frame principal
        frame = new JFrame("Sistema de Biblioteca");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(null);

        // Ícone da biblioteca
        JLabel iconLabel = new JLabel(new ImageIcon(Selecionar.class.getResource("imu.png"))); // Ajuste o caminho da imagem
        iconLabel.setBounds(150, 10, 100, 100); // Posicionando o ícone
        frame.add(iconLabel);

        // Botão "Cadastrar Livro"
        JButton btnCadastrarLivro = new JButton("Cadastrar Livro");
        btnCadastrarLivro.setBounds(50, 120, 300, 30);
        frame.add(btnCadastrarLivro);

        // Botão "Livros Emprestados/Disponíveis"
        JButton btnLivrosStatus = new JButton("Livros Emprestados/Disponíveis");
        btnLivrosStatus.setBounds(50, 160, 300, 30);
        frame.add(btnLivrosStatus);

        // Botão "Emprestar Livros"
        JButton btnEmprestar = new JButton("Emprestar Livros");
        btnEmprestar.setBounds(50, 200, 300, 30);
        frame.add(btnEmprestar);

        // Botão "Devolver Livros"
        JButton btnDevolver = new JButton("Devolver Livros");
        btnDevolver.setBounds(50, 240, 300, 30);
        frame.add(btnDevolver);

        // Configurando fundo
        frame.getContentPane().setBackground(Color.decode("#87CEEB")); // Cor azul-claro

        // Listener para o botão "Cadastrar Livro"
        btnCadastrarLivro.addActionListener(e -> {
            frame.dispose(); // Fecha a tela atual
            new LivroCadastro(); // Abre a tela de cadastro de livros
        });

        // Listener para o botão "Livros Emprestados/Disponíveis"
        btnLivrosStatus.addActionListener(e -> {
            frame.dispose(); // Fecha a tela atual
            new BibliotecaBuscar(); // Abre a tela de busca
        });

        // Listener para o botão "Emprestar Livros"
        btnEmprestar.addActionListener(e -> {
            frame.dispose(); // Fecha a tela atual
            new TelaEmprestar(); // Abre a tela de empréstimo
        });

        // Listener para o botão "Devolver Livros"
        btnDevolver.addActionListener(e -> {
            frame.dispose(); // Fecha a tela atual
            new TelaDevolucao(livros); // Passa a lista de livros para a TelaDevolucao
        });

        frame.setVisible(true); // Torna a tela visível
    }
}
