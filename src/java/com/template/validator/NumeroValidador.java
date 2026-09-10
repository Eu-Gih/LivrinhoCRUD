package com.template.validator;

public class NumeroValidador implements Validador<String> {

    private final String valor;

    public NumeroValidador(String valor) {
        this.valor = valor;
    }

    @Override
    public boolean validar(String valor) {
        if (valor == null || valor.trim().isEmpty()) {
            return false;
        }

        try {
            int numero = Integer.parseInt(valor.trim());
            return numero > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public String getMensagemErro() {
        return "O número de páginas deve ser um número inteiro maior que zero.";
    }

    @Override
    public String getValor() {
        return this.valor;
    }
}
