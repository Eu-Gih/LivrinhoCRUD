package com.template.validator;

// Interface usada para OCP.
// O T é um tipo genérico, não importando se é int, String, ele vai se tornar do tipo do valor enviado.
public interface Validador<T> {

    boolean validar(T valor);
    String getMensagemErro();
    T getValor();
}
