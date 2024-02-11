package com.ggamangso.gptutorproject.constant;

import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

@Getter
public enum MessageType {
    SYSTEM("system"),
    ASSISTANT("assistant"),
    USER("user");

      private final String value;

    MessageType( String value) {
        this.value = value;
    }

    public static MessageType of(String value){
        return Arrays.stream(values())
                .filter(val -> Objects.equals(value, val.value))
                .findFirst()
                .orElse(null);
    }


}
