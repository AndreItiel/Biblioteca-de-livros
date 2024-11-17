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
import br.com.sistema.model.Funcionario;
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

public class FuncionarioDAO {
    private Connection connection;

    // Construtor com conexão
    public FuncionarioDAO(Connection connection) {
        this.connection = connection;
    }

    // Método para inserir um funcionário
    public void inserirFuncionario(Funcionario funcionario) throws SQLException {
        String sql = "INSERT INTO Funcionario (idFuncionario, nome, cargo) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, funcionario.getIdFuncionario());
            stmt.setString(2, funcionario.getNome());
            stmt.setString(3, funcionario.getCargo());
            stmt.executeUpdate();
        }
    }

    // Método para buscar um funcionário por ID
    public Funcionario buscarFuncionario(String idFuncionario) throws SQLException {
        String sql = "SELECT * FROM Funcionario WHERE idFuncionario = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, idFuncionario);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Funcionario(
                    rs.getString("idFuncionario"),
                    rs.getString("nome"),
                    rs.getString("cargo")
                );
            }
        }
        return null;
    }
}

