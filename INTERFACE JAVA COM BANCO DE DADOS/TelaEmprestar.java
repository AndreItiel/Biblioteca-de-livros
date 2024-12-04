import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaEmprestar {

    public TelaEmprestar() {
        JFrame frame = new JFrame("Empréstimo");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel labelSolicitante = new JLabel("Solicitante:");
        labelSolicitante.setBounds(20, 20, 100, 25);
        frame.add(labelSolicitante);

        JTextField nomeSolicitante = new JTextField();
        nomeSolicitante.setBounds(120, 20, 200, 25);
        frame.add(nomeSolicitante);

        JLabel labelLivro = new JLabel("Livro:");
        labelLivro.setBounds(20, 60, 100, 25);
        frame.add(labelLivro);

        // Popula o JComboBox com os títulos dos livros disponíveis
        String[] livrosDisponiveis = BancoDados.livros.stream()
                .filter(livro -> livro.getStatus().equals("Disponível"))
                .map(Livro::getTitulo)
                .toArray(String[]::new);

        JComboBox<String> Livros = new JComboBox<>(livrosDisponiveis);
        Livros.setBounds(120, 60, 200, 25);
        frame.add(Livros);

        JLabel lblDias = new JLabel("Dias:");
        lblDias.setBounds(20, 100, 100, 25);
        frame.add(lblDias);

        JTextField Dias = new JTextField();
        Dias.setBounds(120, 100, 200, 25);
        frame.add(Dias);

        JLabel labelCod = new JLabel("Cód.:");
        labelCod.setBounds(20, 140, 100, 25);
        frame.add(labelCod);

        JTextField txtCodigo = new JTextField();
        txtCodigo.setBounds(120, 140, 200, 25);
        txtCodigo.setEditable(false); // Não pode ser editado pelo usuário
        frame.add(txtCodigo);

        JButton botaoCadastrar = new JButton("Cadastrar");
        botaoCadastrar.setBounds(120, 190, 100, 30);
        frame.add(botaoCadastrar);

        JButton botaoVoltar = new JButton("Voltar");
        botaoVoltar.setBounds(10, 190, 100, 30);
        frame.add(botaoVoltar);

        // Atualiza o código automaticamente quando o título do livro for selecionado
        Livros.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String livroSelecionado = (String) Livros.getSelectedItem();
                Livro livro = BancoDados.livros.stream()
                        .filter(l -> l.getTitulo().equals(livroSelecionado) && l.getStatus().equals("Disponível"))
                        .findFirst()
                        .orElse(null);
                if (livro != null) {
                    txtCodigo.setText(livro.getCodigo()); // Atualiza o código do livro
                }
            }
        });

        botaoVoltar.addActionListener(e -> {
            frame.dispose();
            new Selecionar(BancoDados.livros); // Passa a lista de livros para a tela Selecionar
        });

        botaoCadastrar.addActionListener(e -> {
            String solicitante = nomeSolicitante.getText();
            String livroSelecionado = (String) Livros.getSelectedItem();
            Livro livro = BancoDados.livros.stream()
                    .filter(l -> l.getTitulo().equals(livroSelecionado) && l.getStatus().equals("Disponível"))
                    .findFirst()
                    .orElse(null);

            if (livro != null) {
                livro.setStatus("Emprestado");  // Atualiza o status do livro para "Emprestado"
                livro.setEmprestador(solicitante);  // Registra o nome do emprestador
                JOptionPane.showMessageDialog(frame, "Empréstimo realizado com sucesso!");
                frame.dispose(); // Fecha a tela
                new Selecionar(BancoDados.livros); // Retorna à tela principal
            } else {
                JOptionPane.showMessageDialog(frame, "Este livro não está disponível para empréstimo.");
            }
        });

        frame.setVisible(true);
    }
}
