package com.neurotech.desafio.neurotech_desafio.controllers.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

    public static class FieldMessage {
        private String fieldName;
        private String message;
        public FieldMessage() {}
        public FieldMessage(String fieldName, String message) { this.fieldName = fieldName; this.message = message; }

        public String getFieldName() {
            return fieldName;
        }

        public void setFieldName(String fieldName) {
            this.fieldName = fieldName;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
        
    }

    private List<FieldMessage> errors = new ArrayList<>();
    public List<FieldMessage> getErrors() { return errors; }
    public void addError(String field, String message) { errors.add(new FieldMessage(field, message)); }
}