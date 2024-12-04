import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;

public class TelaDevolucao extends JFrame {

    private JComboBox<String> listaDeLivros; // Dropdown para selecionar livros
    private JTextField nomeDevedor; // Campo de texto para o nome do devedor
    private JTextField codLivro; // Campo de texto para o código do livro
    private JTextField dias; // Campo de texto para os dias emprestados
    private JButton botaoDevolver; // Botão para registrar a devolução
    private JButton botaoVoltar; // Botão para voltar à tela anterior
    private JLabel messageLabel; // Label para exibir mensagens de feedback
    private List<Livro> livros; // Lista de livros da biblioteca

    public TelaDevolucao(List<Livro> livros) {
        // Configurando o frame
        setTitle("Devolução");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        this.livros = livros;

        // Inicializando os componentes
        listaDeLivros = new JComboBox<>();
        nomeDevedor = new JTextField(20);
        codLivro = new JTextField(10);
        dias = new JTextField(5);
        botaoDevolver = new JButton("Registrar Devolução");
        botaoVoltar = new JButton("Voltar");
        messageLabel = new JLabel("");

        setLayout(null); // Usando layout absoluto

        // Adicionando os livros emprestados ao JComboBox
        for (Livro livro : livros) {
            if (livro.getStatus().equals("Emprestado")) {
                listaDeLivros.addItem(livro.getTitulo());
            }
        }

        // Configurando e posicionando os componentes
        JLabel listaLivrosLabel = new JLabel("Selecione o Livro:");
        listaLivrosLabel.setBounds(30, 30, 120, 25);
        listaDeLivros.setBounds(160, 30, 200, 25);

        JLabel devedorLabel = new JLabel("Nome do Devedor:");
        devedorLabel.setBounds(30, 70, 120, 25);
        nomeDevedor.setBounds(160, 70, 200, 25);

        JLabel codLivroLabel = new JLabel("Código do Livro:");
        codLivroLabel.setBounds(30, 110, 120, 25);
        codLivro.setBounds(160, 110, 200, 25);

        JLabel diasEmprestadosLabel = new JLabel("Dias Emprestados:");
        diasEmprestadosLabel.setBounds(30, 150, 120, 25);
        dias.setBounds(160, 150, 100, 25);

        botaoDevolver.setBounds(130, 190, 150, 30); // Botão "Registrar Devolução"
        botaoVoltar.setBounds(10, 190, 100, 30); // Botão "Voltar"
        messageLabel.setBounds(30, 230, 300, 25); // Mensagem de feedback

        // Adicionando os componentes ao frame
        add(listaLivrosLabel);
        add(listaDeLivros);
        add(devedorLabel);
        add(nomeDevedor);
        add(codLivroLabel);
        add(codLivro);
        add(diasEmprestadosLabel);
        add(dias);
        add(botaoDevolver);
        add(botaoVoltar);
        add(messageLabel);

        // Ação para o botão de devolução
        botaoDevolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtendo os valores dos campos
                String nomeDoDevedor = nomeDevedor.getText();
                String codDoLivro = codLivro.getText();
                String diasEmprestados = dias.getText();

                // Validação dos campos
                if (nomeDoDevedor.isEmpty() || codDoLivro.isEmpty() || diasEmprestados.isEmpty()) {
                    messageLabel.setText("Por favor, preencha todos os campos.");
                } else {
                    // Encontrar o livro pelo código
                    Livro livro = buscarLivroPorCodigo(codDoLivro);

                    if (livro != null && livro.getStatus().equals("Emprestado")) {
                        // Atualizar o status do livro para "Disponível"
                        livro.setStatus("Disponível");
                        livro.setEmprestador(""); // Limpar o nome do emprestador

                        // Calcular o valor a ser pago se houver atraso
                        int diasAtraso = Integer.parseInt(diasEmprestados) - 7; // 7 dias como prazo máximo
                        if (diasAtraso > 0) {
                            double valorDevido = diasAtraso * 5.0;
                            messageLabel.setText("Livro devolvido! Valor devido: R$ " + valorDevido);
                        } else {
                            messageLabel.setText("Livro devolvido com sucesso!");
                        }

                        // Atualizar a lista de livros disponíveis
                        listaDeLivros.removeItem(livro.getTitulo());
                    } else {
                        messageLabel.setText("Livro não encontrado ou já foi devolvido.");
                    }
                }
            }
        });

        // Ação para o botão Voltar (agora com a funcionalidade de voltar para a tela Selecionar)
        botaoVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fecha a tela atual
                new Selecionar(livros); // Passa a lista de livros para a tela Selecionar
            }
        });

        setVisible(true); // Torna a tela visível
    }

    // Método para buscar um livro por código
    private Livro buscarLivroPorCodigo(String codigo) {
        for (Livro livro : livros) {
            if (livro.getCodigo().equals(codigo)) {
                return livro;
            }
        }
        return null; // Caso não encontre o livro
    }

    public static void main(String[] args) {
        // Criar a lista de livros (sem exemplos)
        List<Livro> livros = new ArrayList<>();
        // A lista de livros é passada de outro lugar no seu código

        // Iniciar a tela de devolução
        SwingUtilities.invokeLater(() -> new TelaDevolucao(livros));
    }
}
