package br.com.sistema.dao;

import br.com.sistema.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.sistema.jdbc.ConexaoBanco;

public class UsuarioDAO {
    private Connection connection;

    // Construtor com conexão
    public UsuarioDAO(Connection connection) {
        this.connection = connection;
    }

    // Construtor sem conexão, usando a ConexaoBanco para obter a conexão
    public UsuarioDAO() {
        this.connection = ConexaoBanco.getConnection();
    }

    // Método para inserir um usuário
    public void inserirUsuario(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO Usuario (nome) VALUES (?)"; // Removi 'idUsuario' assumindo que seja auto-incremento
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNome());
            stmt.executeUpdate();
        }
    }

    // Método para buscar um usuário por ID
    public Usuario buscarUsuario(String idUsuario) throws SQLException {
        String sql = "SELECT * FROM Usuario WHERE idUsuario = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, idUsuario);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Usuario(
                    rs.getString("idUsuario"),
                    rs.getString("nome")
                );
            }
        }
        return null;
    }

    // Método para buscar um usuário por CPF
    public Usuario buscarPorCpf(String cpf) throws SQLException {
        String sql = "SELECT * FROM Usuario WHERE cpf = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Usuario(
                    rs.getString("idUsuario"),
                    rs.getString("nome")
                );
            }
        }
        return null;
    }

    // Método para listar todos os usuários
    public List<Usuario> listarUsuarios() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuario";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario(
                    rs.getString("idUsuario"),
                    rs.getString("nome")
                );
                usuarios.add(usuario);
            }
        }
        return usuarios;
    }

    // Método para editar um usuário
    public void editarUsuario(Usuario usuario) throws SQLException {
        String sql = "UPDATE usuario SET nome = ? WHERE idUsuario = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getIdUsuario());
            stmt.executeUpdate();
        }
    }

    // Método para excluir um usuário
    public void excluirUsuario(String idUsuario) throws SQLException {
        String sql = "DELETE FROM usuario WHERE idUsuario = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, idUsuario);
            stmt.executeUpdate();
        }
    }

    // Método para filtrar usuários por nome (por exemplo)
    public List<Usuario> filtrarUsuariosPorNome(String nome) throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuario WHERE nome LIKE ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "%" + nome + "%"); // Usando LIKE para buscar por parte do nome
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario(
                    rs.getString("idUsuario"),
                    rs.getString("nome")
                );
                usuarios.add(usuario);
            }
        }
        return usuarios;
    }
}
