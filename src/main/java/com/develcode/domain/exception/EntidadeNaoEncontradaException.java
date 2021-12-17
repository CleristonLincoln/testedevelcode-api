package com.develcode.domain.exception;

public class EntidadeNaoEncontradaException extends  RuntimeException{
    public EntidadeNaoEncontradaException(String mensagem) {
        super(mensagem);
    }
}
