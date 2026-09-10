package com.template.validator;

public class GeneroValidador implements Validador<String> {

    private static final String[] GENEROS_VALIDOS = {
            "ROMANCE", "FICÇÃO CIENTÍFICA", "FANTASIA", "TERROR",
            "SUSPENSE", "DRAMA", "AVENTURA", "BIOGRAFIA", "HISTÓRIA", "ACADÊMICO"
    };

    private final String genero;

    public GeneroValidador(String genero) {
        this.genero = genero;
    }

    @Override
    public boolean validar(String genero) {
        if (genero == null) {
            return false;
        }
        for (String generoValido : GENEROS_VALIDOS) {
            if (generoValido.equalsIgnoreCase(genero.trim())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getMensagemErro() {
        return "Selecione um gênero válido dentre as opções disponíveis.";
    }

    @Override
    public String getValor() {
        return this.genero;
    }
}
