package com.dc.apiresttesting.exceptions;

public class RecordNotFoundException extends Exception {
    private static final long serialVersionUID = 4986334286751337030L;

    public RecordNotFoundException(String errorMessage){
        super(errorMessage);
    }

}
