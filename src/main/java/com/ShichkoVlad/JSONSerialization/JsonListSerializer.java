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

public class JsonListSerializer<T> {
    private ObjectMapper mapper;

    public JsonListSerializer() {
        mapper = new ObjectMapper();
    }

    public void Serialize(List<T> items, String path) throws IOException {

        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(path), items);

    }

    public List<T> Deserialize(String path) throws IOException {

        return mapper.readValue(new File("readers.json"), List.class);

    }
}
