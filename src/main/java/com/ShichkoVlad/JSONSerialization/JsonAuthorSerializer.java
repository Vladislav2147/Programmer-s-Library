package com.ShichkoVlad.JSONSerialization;

import com.ShichkoVlad.Book.Author;
import com.ShichkoVlad.Book.Book;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonAuthorSerializer {
    private ObjectMapper mapper;

    public JsonAuthorSerializer() {
        mapper = new ObjectMapper();
    }

    public JsonAuthorSerializer(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public void Serialize(Author author, String path) throws IOException {
        String json = mapper.writeValueAsString(author);
        mapper.writeValue(new File(path), json);
    }

    public Author Deserialize(String path) throws IOException {
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        return mapper.readValue(new File(path), Author.class);
    }
}
