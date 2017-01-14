package com.example.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.example.model.serializer.LocalDateDeserializer;
import com.example.model.serializer.LocalDateSerializer;
import com.example.model.serializer.LocalDateTimeDeserializer;
import com.example.model.serializer.LocalDateTimeSerializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

@JsonInclude(Include.NON_NULL)
@Data
public class ExampleModel {
    private String stringElement;
    private Integer integerElement;

    // LocalDateTime with custom serializer/deserializer
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime localDateTimeElement;

    // LocalDate with custom serializer/deserializer
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate localDateElement;
}
