package com.alura.desafio.servicos;

import java.util.List;

public interface IConverteDados {
    <T> T converterDados(String json, Class<T> classe);
    <T> List<T> converterListas(String json, Class<T> classe);
}
