package com.template.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.template.util.DialogUtil.showError;

// Classe com os métodos de CRUD do livro
public class LivroDAO {

    public void cadastrarLivro(LivroDTO livro) {
        String sql = "INSERT INTO livros (titulo, autor, genero, paginas) VALUES (?, ?, ?, ?)";

        try (Connection c = new Conexao().ConectaBD(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, livro.getTitulo());
            ps.setString(2, livro.getAutor());
            ps.setString(3, livro.getGenero());
            ps.setInt(4, livro.getPaginas());
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(LivroDAO.class.getName()).log(Level.SEVERE, null, ex);
            showError("Erro ao cadastrar o livro.");
        }
    }

    public ArrayList<LivroDTO> selecionarLivro() {
        String sql = "SELECT * FROM livros ORDER BY id";
        ArrayList<LivroDTO> listaLivros = new ArrayList<>();

        try (Connection c = new Conexao().ConectaBD(); PreparedStatement ps = c.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                LivroDTO livro = new LivroDTO();
                livro.setId(rs.getInt("id"));
                livro.setTitulo(rs.getString("titulo"));
                livro.setAutor(rs.getString("autor"));
                livro.setGenero(rs.getString("genero"));
                livro.setPaginas(rs.getInt("paginas"));
                listaLivros.add(livro);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LivroDAO.class.getName()).log(Level.SEVERE, null, ex);
            showError("Erro ao listar os livros.");
        }

        return listaLivros;
    }

    public void atualizarLivro(LivroDTO livro) {
        String sql = "UPDATE livros SET titulo = ?, autor = ?, genero = ?, paginas = ? WHERE id = ?";

        try (Connection c = new Conexao().ConectaBD(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, livro.getTitulo());
            ps.setString(2, livro.getAutor());
            ps.setString(3, livro.getGenero());
            ps.setInt(4, livro.getPaginas());
            ps.setInt(5, livro.getId());
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(LivroDAO.class.getName()).log(Level.SEVERE, null, ex);
            showError("Erro ao atualizar o livro.");
        }
    }

    public void deletarLivro(int idLivro) {
        String sql = "DELETE FROM livros WHERE id = ?";

        try (Connection c = new Conexao().ConectaBD(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, idLivro);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(LivroDAO.class.getName()).log(Level.SEVERE, null, ex);
            showError("Erro ao deletar o livro.");
        }
    }
}
