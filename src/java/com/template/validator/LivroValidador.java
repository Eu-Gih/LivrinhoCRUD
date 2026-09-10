package com.template.validator;

import java.util.ArrayList;
import java.util.List;

import static com.template.util.DialogUtil.showWarning;

public class LivroValidador implements ILivroValidador {

    @Override
    public boolean validarLivro(String titulo, String autor, String genero, String paginas) {
        List<Validador<String>> validadores = new ArrayList<>();

        validadores.add(new CampoObrigatorioValidador("Título", titulo));
        validadores.add(new CampoObrigatorioValidador("Autor", autor));
        validadores.add(new CampoObrigatorioValidador("Gênero", genero));
        validadores.add(new CampoObrigatorioValidador("Páginas", paginas));
        validadores.add(new GeneroValidador(genero));
        validadores.add(new NumeroValidador(paginas));

        for (Validador<String> validador : validadores) {
            if (!validador.validar(validador.getValor())) {
                showWarning("Atenção", "Erro de validação", validador.getMensagemErro());
                return false;
            }
        }
        return true;
    }
}
