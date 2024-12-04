import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TelaLogin {
    private JFrame frame;

    public TelaLogin() {
        // Cria o frame da janela
        frame = new JFrame("Login");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Centraliza a janela na tela

        // Define o layout da janela
        JPanel panel = new JPanel();
        panel.setLayout(null); // Layout livre para posicionar os componentes
        frame.add(panel);

        // Ícone (carrega a imagem do recurso)
        JLabel iconLabel = new JLabel(new ImageIcon(TelaLogin.class.getResource("imu.png"))); // Ajuste o caminho se necessário
        iconLabel.setBounds(150, 20, 100, 100); // Posicionamento e tamanho do ícone
        panel.add(iconLabel);

        // Campo de texto para usuário
        JTextField userText = new JTextField(20);
        userText.setBounds(100, 120, 200, 25);
        panel.add(userText);

        // Campo de senha
        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100, 160, 200, 25);
        panel.add(passwordText);

        // Rótulo para os campos
        JLabel userLabel = new JLabel("Usuário");
        userLabel.setBounds(100, 100, 80, 25);
        panel.add(userLabel);

        JLabel passwordLabel = new JLabel("Senha");
        passwordLabel.setBounds(100, 140, 80, 25);
        panel.add(passwordLabel);

        // Botão de login
        JButton loginButton = new JButton("Entrar");
        loginButton.setBounds(150, 200, 100, 25);
        panel.add(loginButton);

        // Adiciona ação ao botão de login
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = userText.getText();
                String senha = new String(passwordText.getPassword());

                // Verifica as credenciais
                if ("a".equals(usuario) && "a".equals(senha)) {
                    JOptionPane.showMessageDialog(frame, "Login realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose(); // Fecha a janela de login

                    // Cria uma lista vazia de livros e abre a tela Selecionar
                    List<Livro> livros = new ArrayList<>();
                    new Selecionar(livros); // Abre a tela de seleção com a lista de livros
                } else {
                    JOptionPane.showMessageDialog(frame, "Usuário ou senha inválidos!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Torna o frame visível
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new TelaLogin();
    }
}
