package com.github.server.exceptions;

public class JsonParseException extends RuntimeException {

    public JsonParseException() {
    }

    public JsonParseException(String message) {
        super(message);
    }

}
