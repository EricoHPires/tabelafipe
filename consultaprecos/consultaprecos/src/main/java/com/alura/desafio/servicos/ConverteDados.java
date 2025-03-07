package com.alura.desafio.servicos;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.util.List;

public class ConverteDados implements IConverteDados {
    ObjectMapper mapper = new ObjectMapper();
    @Override
    public <T> T converterDados(String json, Class<T> classe) {
        try{
            return mapper.readValue(json, classe);
        }
        catch (JsonProcessingException e){
            throw new RuntimeException();
        }
    }

    @Override
    public <T> List<T> converterListas(String json, Class<T> classe) {
        CollectionType collection = mapper.getTypeFactory()
                .constructCollectionType(List.class, classe);
        try{
            return mapper.readValue(json, collection);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    ;
}
