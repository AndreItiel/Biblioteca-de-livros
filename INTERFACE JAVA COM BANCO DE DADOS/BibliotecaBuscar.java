import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class BibliotecaBuscar extends JFrame {

    public BibliotecaBuscar() {
        setTitle("Biblioteca - Buscar Livros");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel topPanel = new JPanel(new BorderLayout());
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JButton backButton = new JButton("Voltar");
        backButton.addActionListener(e -> {
            dispose();
            new Selecionar(BancoDados.livros); // Passa a lista de livros para a tela Selecionar
        });
        searchPanel.add(backButton);

        JTextField searchField = new JTextField(20);
        searchField.setToolTipText("Pesquisar livro...");
        searchPanel.add(searchField);

        topPanel.add(searchPanel, BorderLayout.NORTH);

        String[] columns = {"Cod.", "Livro", "Status", "Emprestador"};  // Nova coluna para emprestador
        DefaultTableModel model = new DefaultTableModel(null, columns);
        JTable table = new JTable(model);

        atualizarTabela(model);

        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    String codigo = (String) model.getValueAt(row, 0);
                    Livro livroSelecionado = BancoDados.livros.stream()
                            .filter(livro -> livro.getCodigo().equals(codigo))
                            .findFirst().orElse(null);

                    if (livroSelecionado != null) {
                        int escolha = JOptionPane.showOptionDialog(null,
                                "Escolha uma opção para o livro: " + livroSelecionado.getTitulo(),
                                "Editar/Excluir",
                                JOptionPane.DEFAULT_OPTION,
                                JOptionPane.INFORMATION_MESSAGE,
                                null,
                                new String[]{"Editar", "Excluir", "Cancelar"},
                                "Cancelar");

                        if (escolha == 0) {
                            editarLivro(livroSelecionado, model);
                        } else if (escolha == 1) {
                            BancoDados.livros.remove(livroSelecionado);
                            atualizarTabela(model);
                        }
                    }
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);

        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    private void editarLivro(Livro livroSelecionado, DefaultTableModel model) {
        JPanel panel = new JPanel(new GridLayout(5, 2, 5, 5));

        JTextField tituloField = new JTextField(livroSelecionado.getTitulo());
        JTextField autorField = new JTextField(livroSelecionado.getAutor());
        JTextField isbnField = new JTextField(livroSelecionado.getIsbn());
        JTextField codigoField = new JTextField(livroSelecionado.getCodigo());

        panel.add(new JLabel("Título:"));
        panel.add(tituloField);
        panel.add(new JLabel("Autor:"));
        panel.add(autorField);
        panel.add(new JLabel("ISBN:"));
        panel.add(isbnField);
        panel.add(new JLabel("Código:"));
        panel.add(codigoField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Editar Livro", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            livroSelecionado.setTitulo(tituloField.getText());
            livroSelecionado.setAutor(autorField.getText());
            livroSelecionado.setIsbn(isbnField.getText());
            livroSelecionado.setCodigo(codigoField.getText());
            atualizarTabela(model);
            JOptionPane.showMessageDialog(this, "Livro atualizado com sucesso!");
        }
    }

    private void atualizarTabela(DefaultTableModel model) {
        model.setRowCount(0);
        for (Livro livro : BancoDados.livros) {
            String status = livro.getStatus();
            String emprestador = status.equals("Emprestado") ? livro.getEmprestador() : "";
            model.addRow(new Object[]{livro.getCodigo(), livro.getTitulo(), status, emprestador});
        }
    }
}
