package com.Alkemy.demo.excepcion;

public class ErrorServicio extends RuntimeException{
    public ErrorServicio(String error) {
        super(error);
    }
}
