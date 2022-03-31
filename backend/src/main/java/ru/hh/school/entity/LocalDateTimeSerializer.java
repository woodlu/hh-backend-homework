package ru.hh.school.entity;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeSerializer extends StdSerializer<LocalDateTime> {
    public LocalDateTimeSerializer(Class<LocalDateTime> t) {
        super(t);
    }
    public LocalDateTimeSerializer() {
        this(null);
    }

    @Override
    public void serialize(LocalDateTime localDateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss").format(localDateTime));
    }
}
