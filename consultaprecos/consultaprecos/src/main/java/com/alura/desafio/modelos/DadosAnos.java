package com.alura.desafio.modelos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosAnos(@JsonAlias ("AnoModelo") String ano,
                        @JsonAlias ("Valor") String valor) {
    @Override
    public String toString () {
        return "Ano: " + ano + ", Valor: " + valor;
    }
}
