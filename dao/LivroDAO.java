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
import br.com.sistema.model.Livro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import br.com.sistema.jdbc.ConexaoBanco;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class LivroDAO {
    private Connection connection;

    // Construtor com conexão
    public LivroDAO(Connection connection) {
        this.connection = connection;
    }

    // Método para inserir um livro
    public void inserirLivro(Livro livro) throws SQLException {
        String sql = "INSERT INTO Livro (titulo, autor, isbn, status) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setString(3, livro.getIsbn());
            stmt.setBoolean(4, livro.isStatus());
            stmt.executeUpdate();
        }
    }

    // Método para buscar um livro por ID
    public Livro buscarLivro(int id) throws SQLException {
        String sql = "SELECT * FROM Livro WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Livro(
                    rs.getInt("id"),
                    rs.getString("titulo"),
                    rs.getString("autor"),
                    rs.getString("isbn"),
                    rs.getBoolean("status")
                );
            }
        }
        return null;
    }
}

