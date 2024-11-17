/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistema.model;
import javax.swing.JTextField;

/**
 *
 * @author PC
 */
public class Livro {
    private int id;
    private String titulo;
    private String autor;
    private String isbn;
    private boolean status;

    // Construtor
    public Livro(int id, String titulo, String autor, String isbn, boolean status) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.status = status;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
     public void marcarComoEmprestado() {
        this.status = false;
    }

    public void marcarComoDisponivel() {
        this.status = true;
    }
}
