import javax.swing.*;
import java.awt.*;

public class LivroCadastro {
    private JFrame frame;

    public LivroCadastro() {
        frame = new JFrame("Biblioteca - Cadastro de Livros");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel pesquisaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton backButton = new JButton("Voltar");
        backButton.addActionListener(e -> {
            frame.dispose();
            new Selecionar(BancoDados.livros); // Passando a lista de livros para Selecionar
        });
        pesquisaPanel.add(backButton);

        JTabbedPane tabbedPane = new JTabbedPane();
        JPanel livrosPanel = new JPanel();

        tabbedPane.addTab("Livros", livrosPanel);
        livrosPanel.setLayout(new GridLayout(5, 2, 5, 5));

        JLabel tituloLabel = new JLabel("Título:");
        JTextField tituloField = new JTextField();

        JLabel autorLabel = new JLabel("Autor:");
        JTextField autorField = new JTextField();

        JLabel isbnLabel = new JLabel("ISBN:");
        JTextField isbnField = new JTextField();

        JLabel codLabel = new JLabel("Cod.:");
        JTextField codField = new JTextField();

        JButton cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.addActionListener(e -> {
            String titulo = tituloField.getText();
            String autor = autorField.getText();
            String isbn = isbnField.getText();
            String codigo = codField.getText();

            if (!titulo.isEmpty() && !autor.isEmpty() && !isbn.isEmpty() && !codigo.isEmpty()) {
                Livro novoLivro = new Livro(titulo, autor, isbn, codigo);
                BancoDados.livros.add(novoLivro);
                JOptionPane.showMessageDialog(frame, "Livro cadastrado com sucesso!");
                tituloField.setText("");
                autorField.setText("");
                isbnField.setText("");
                codField.setText("");
            } else {
                JOptionPane.showMessageDialog(frame, "Preencha todos os campos!");
            }
        });

        livrosPanel.add(tituloLabel);
        livrosPanel.add(tituloField);
        livrosPanel.add(autorLabel);
        livrosPanel.add(autorField);
        livrosPanel.add(isbnLabel);
        livrosPanel.add(isbnField);
        livrosPanel.add(codLabel);
        livrosPanel.add(codField);
        livrosPanel.add(new JLabel());
        livrosPanel.add(cadastrarButton);

        panel.add(pesquisaPanel, BorderLayout.NORTH);
        panel.add(tabbedPane, BorderLayout.CENTER);

        frame.add(panel);
        frame.setVisible(true);
    }
}
