package com.template.service;

import com.template.model.LivroDTO;
import java.util.ArrayList;

public interface ILivroService {

    void salvarLivro(LivroDTO livro);

    void modificarLivro(LivroDTO livro);

    void apagarLivro(int id);

    ArrayList<LivroDTO> listarTodos();
}
