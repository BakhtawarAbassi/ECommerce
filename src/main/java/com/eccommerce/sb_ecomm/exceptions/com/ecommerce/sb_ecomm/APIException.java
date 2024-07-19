package com.eccommerce.sb_ecomm.exceptions.com.ecommerce.sb_ecomm;

public class APIException extends RuntimeException{
    private static final long serialVersionId=1L;

    public APIException(String message) {
        super(message);
    }
}
