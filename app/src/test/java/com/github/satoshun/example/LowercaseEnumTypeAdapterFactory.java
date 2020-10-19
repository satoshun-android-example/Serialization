package com.github.satoshun.example;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class LowercaseEnumTypeAdapterFactory implements TypeAdapterFactory {

    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        Class<T> rawType = (Class<T>) type.getRawType();
        if (!rawType.isEnum()) {
            return null;
        }
        final Map<String, T> lowercaseToConstant = new HashMap<>();
        for (T constant : rawType.getEnumConstants()) {
            try {
                Enum a = (Enum) constant;
                a.name();
                if (rawType.getField(a.name()).getAnnotation(SerializedName.class) != null) {
                    return null;
                }
            } catch (Exception e) {
                // do nothing
            }

            lowercaseToConstant.put(toLowercase(constant), constant);
        }

        return new TypeAdapter<T>() {
            public void write(JsonWriter out, T value) throws IOException {
                if (value == null) {
                    out.nullValue();
                } else {
                    out.value(toLowercase(value));
                }
            }

            public T read(JsonReader reader) throws IOException {
                if (reader.peek() == JsonToken.NULL) {
                    reader.nextNull();
                    return null;
                } else {
                    return lowercaseToConstant.get(reader.nextString().toLowerCase(Locale.ENGLISH));
                }
            }
        };
    }

    public static String toLowercase(Object o) {
        return o.toString().toLowerCase(Locale.ENGLISH);
    }
}
