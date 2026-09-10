package com.template.service;

import com.template.model.LivroDTO;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class CampoService {

    public void carregarLivros(TableView<LivroDTO> tblLivro) {
        LivroService service = new LivroService();
        ArrayList<LivroDTO> listaLivro = service.listarTodos();
        tblLivro.setItems(FXCollections.observableArrayList(listaLivro));
    }

    public void limparCampos(TextField txtId, TextField txtTitulo, TextField txtAutor,
                              TextField txtPaginas, ComboBox<String> cbGenero) {
        txtId.clear();
        txtTitulo.clear();
        txtAutor.clear();
        txtPaginas.clear();
        cbGenero.setValue(null);
    }
}
