package com.usta.mindbridge.exception.custom;

public class DuplicateResourceException extends RuntimeException {
    public DuplicateResourceException(String resource, String field, String value) {
        super(resource + " with " + field + " '" + value + "' already exists");
    }
}
