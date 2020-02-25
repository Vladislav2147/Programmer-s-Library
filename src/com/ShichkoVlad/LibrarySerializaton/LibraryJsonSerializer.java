package com.ShichkoVlad.LibrarySerializaton;

import com.ShichkoVlad.Library.Library;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class LibraryJsonSerializer {

    public static String serialize(Library library) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(library);
    }
    public static Library deserialize(String json) {
        return null;
    }
}
