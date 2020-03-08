package com.shichko.jsonSerialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonListSerializer<T> {

    final static Logger logger = Logger.getLogger(JsonListSerializer.class);
    private final ObjectMapper mapper;

    public JsonListSerializer() {
        mapper = new ObjectMapper();
    }

    public void Serialize(List<T> items, String path) throws IOException {

        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(path), items);
        logger.info("List was successfully serialized");

    }

    public List<T> Deserialize(String path) throws IOException {

        List<T> items = mapper.readValue(new File(path), List.class);
        logger.info("List was successfully deserialized");
        return items;

    }
}
