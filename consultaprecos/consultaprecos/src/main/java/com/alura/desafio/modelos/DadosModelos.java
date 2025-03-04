package com.alura.desafio.modelos;

import com.alura.desafio.modelos.Dados;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosModelos(@JsonAlias("modelos") List<Dados> dadosMarcas) {
    @Override
    public String toString () {
        StringBuilder builder = new StringBuilder();
        dadosMarcas.forEach(d -> builder.append(d).append("\n"));
        return builder.toString();
    }
}