package com.develcode.api.exceptionHandler;

import lombok.Getter;

@Getter
public enum ProblemType {
    DADOS_INVALIDOS("/dados-invalidos", "Dados inválidos"),
    MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel", "Mensagem incompreensível"),
    RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "Recurso não encontrado"),
    ;

    private final String title;
    private final String uri;

    ProblemType(String path, String title) {
        this.uri = "https://testedevelcode.com.br" + path;
        this.title = title;
    }
}
