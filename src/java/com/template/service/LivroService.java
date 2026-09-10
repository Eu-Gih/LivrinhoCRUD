package com.template.service;

import com.template.model.LivroDAO;
import com.template.model.LivroDTO;
import java.util.ArrayList;

public class LivroService implements ILivroService {

    private final LivroDAO livroDAO = new LivroDAO();

    @Override
    public void salvarLivro(LivroDTO livro) {
        livroDAO.cadastrarLivro(livro);
    }

    @Override
    public void modificarLivro(LivroDTO livro) {
        livroDAO.atualizarLivro(livro);
    }

    @Override
    public void apagarLivro(int id) {
        livroDAO.deletarLivro(id);
    }

    @Override
    public ArrayList<LivroDTO> listarTodos() {
        return livroDAO.selecionarLivro();
    }
}
