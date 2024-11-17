/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistema.dao;

/**
 *
 * @author PC
 */
import br.com.sistema.model.Emprestimo;
import br.com.sistema.model.Livro;
import br.com.sistema.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import br.com.sistema.jdbc.ConexaoBanco;
import java.awt.HeadlessException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class EmprestimoDAO {
    private Connection connection;

    // Construtor com conexão
    public EmprestimoDAO(Connection connection) {
        this.connection = connection;
    }

    // Método para registrar um empréstimo
    public void registrarEmprestimo(Emprestimo emprestimo) throws SQLException {
        String sql = "INSERT INTO Emprestimo (idUsuario, idLivro, dataEmprestimo, dataDevolucao) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, emprestimo.getUsuario().getIdUsuario());
            stmt.setInt(2, emprestimo.getLivro().getId());
            stmt.setDate(3, new java.sql.Date(emprestimo.getDataEmprestimo().getTime()));
            stmt.setDate(4, emprestimo.getDataDevolucao() != null ? new java.sql.Date(emprestimo.getDataDevolucao().getTime()) : null);
            stmt.executeUpdate();
        }
    }

    // Método para buscar um empréstimo por ID
    public Emprestimo buscarEmprestimo(int id) throws SQLException {
        String sql = "SELECT * FROM Emprestimo WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Usuario usuario = new UsuarioDAO(connection).buscarUsuario(rs.getString("idUsuario"));
                Livro livro = new LivroDAO(connection).buscarLivro(rs.getInt("idLivro"));
                Date dataEmprestimo = rs.getDate("dataEmprestimo");
                Date dataDevolucao = rs.getDate("dataDevolucao");

                return new Emprestimo(
                    rs.getInt("id"),
                    usuario,
                    livro,
                    dataEmprestimo,
                    dataDevolucao
                );
            }
        }
        return null;
    }
}

