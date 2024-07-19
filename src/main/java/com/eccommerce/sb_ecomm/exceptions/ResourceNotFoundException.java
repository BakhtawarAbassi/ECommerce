package com.eccommerce.sb_ecomm.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    String resourcName;
    String field;
    String fieldName;
    Long fieldId;

    public ResourceNotFoundException() {

    }

    public ResourceNotFoundException(String resourcName, String field, String fieldName) {
        super(String.format("%s not found with %s :%d" ,resourcName,field,fieldName));
        this.resourcName = resourcName;
        this.field = field;
        this.fieldName = fieldName;
    }

    public ResourceNotFoundException(String resourcName, String field, Long fieldId) {
        super(String.format("%s not found with %s :%d" ,resourcName,field,fieldId));
        this.resourcName = resourcName;
        this.field = field;
        this.fieldId = fieldId;
    }
}
