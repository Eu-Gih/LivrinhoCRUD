package com.template.controller;

import com.template.model.LivroDTO;
import com.template.service.CampoService;
import com.template.service.LivroService;
import com.template.validator.ILivroValidador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainController {

    private final ILivroValidador livroValidador;
    private final CampoService campoService = new CampoService();
    private final LivroService livroService = new LivroService();

    public MainController(ILivroValidador livroValidador) {
        this.livroValidador = livroValidador;
    }

    @FXML private TextField txtId;
    @FXML private TextField txtTitulo;
    @FXML private TextField txtAutor;
    @FXML private TextField txtPaginas;
    @FXML private Button btnSalvar;
    @FXML private Button btnAlterar;
    @FXML private Button btnDeletar;
    @FXML private Button btnLimpar;
    @FXML private TableView<LivroDTO> tblLivro;
    @FXML private TableColumn<LivroDTO, Integer> colId;
    @FXML private TableColumn<LivroDTO, String> colTitulo;
    @FXML private TableColumn<LivroDTO, String> colAutor;
    @FXML private TableColumn<LivroDTO, String> colGenero;
    @FXML private TableColumn<LivroDTO, Integer> colPaginas;
    @FXML private ComboBox<String> cbGenero;
    @FXML private Label lblMensagem;
    @FXML private Label lblTotal;

    @FXML
    private void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        colAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
        colGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
        colPaginas.setCellValueFactory(new PropertyValueFactory<>("paginas"));

        cbGenero.getItems().addAll("Romance", "Ficção Científica", "Fantasia", "Terror",
                "Suspense", "Drama", "Aventura", "Biografia", "História", "Acadêmico");

        campoService.carregarLivros(tblLivro);
        atualizarTotal();

        tblLivro.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                carregarCampos();
            }
        });
    }

    @FXML
    private void btnSalvarAction(ActionEvent event) {
        String titulo = txtTitulo.getText();
        String autor = txtAutor.getText();
        String genero = cbGenero.getValue();
        String paginas = txtPaginas.getText();

        if (!livroValidador.validarLivro(titulo, autor, genero, paginas)) {
            return;
        }

        LivroDTO objLivroDTO = new LivroDTO();
        objLivroDTO.setTitulo(titulo);
        objLivroDTO.setAutor(autor);
        objLivroDTO.setGenero(genero);
        objLivroDTO.setPaginas(Integer.parseInt(paginas.trim()));

        livroService.salvarLivro(objLivroDTO);
        campoService.carregarLivros(tblLivro);
        campoService.limparCampos(txtId, txtTitulo, txtAutor, txtPaginas, cbGenero);
        atualizarTotal();

        lblMensagem.setText("Livro cadastrado com sucesso!");
    }

    @FXML
    private void btnAlterarAction(ActionEvent event) {
        if (txtId.getText().isEmpty()) {
            lblMensagem.setText("Selecione um livro na tabela para alterar.");
            return;
        }

        String titulo = txtTitulo.getText();
        String autor = txtAutor.getText();
        String genero = cbGenero.getValue();
        String paginas = txtPaginas.getText();

        if (!livroValidador.validarLivro(titulo, autor, genero, paginas)) {
            return;
        }

        LivroDTO objLivroDTO = new LivroDTO();
        objLivroDTO.setId(Integer.parseInt(txtId.getText()));
        objLivroDTO.setTitulo(titulo);
        objLivroDTO.setAutor(autor);
        objLivroDTO.setGenero(genero);
        objLivroDTO.setPaginas(Integer.parseInt(paginas.trim()));

        livroService.modificarLivro(objLivroDTO);
        campoService.carregarLivros(tblLivro);
        campoService.limparCampos(txtId, txtTitulo, txtAutor, txtPaginas, cbGenero);
        atualizarTotal();

        lblMensagem.setText("Livro atualizado com sucesso!");
    }

    @FXML
    private void btnDeletarAction(ActionEvent event) {
        if (txtId.getText().isEmpty()) {
            lblMensagem.setText("Selecione um livro na tabela para excluir.");
            return;
        }

        int id = Integer.parseInt(txtId.getText());

        livroService.apagarLivro(id);
        campoService.carregarLivros(tblLivro);
        campoService.limparCampos(txtId, txtTitulo, txtAutor, txtPaginas, cbGenero);
        atualizarTotal();

        lblMensagem.setText("Livro excluído com sucesso!");
    }

    @FXML
    private void btnLimparAction(ActionEvent event) {
        campoService.limparCampos(txtId, txtTitulo, txtAutor, txtPaginas, cbGenero);
        tblLivro.getSelectionModel().clearSelection();
    }

    @FXML
    private void carregarCampos() {
        LivroDTO livroDTO = tblLivro.getSelectionModel().getSelectedItem();

        if (livroDTO != null) {
            txtId.setText(String.valueOf(livroDTO.getId()));
            txtTitulo.setText(livroDTO.getTitulo());
            txtAutor.setText(livroDTO.getAutor());
            cbGenero.setValue(livroDTO.getGenero());
            txtPaginas.setText(String.valueOf(livroDTO.getPaginas()));
        }
    }

    private void atualizarTotal() {
        lblTotal.setText(String.valueOf(tblLivro.getItems().size()));
    }
}
