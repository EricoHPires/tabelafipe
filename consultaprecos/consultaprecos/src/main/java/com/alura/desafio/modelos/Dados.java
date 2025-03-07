package com.alura.desafio.modelos;

import com.fasterxml.jackson.annotation.JsonAlias;

public record Dados(@JsonAlias ("codigo") String codigo,
                    @JsonAlias ("nome") String nome) {
    @Override
    public String toString(){
        return "Código: " + codigo() + ", Nome: " + nome();
    }
}
