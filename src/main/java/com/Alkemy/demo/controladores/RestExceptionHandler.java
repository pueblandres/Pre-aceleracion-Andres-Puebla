package com.Alkemy.demo.controladores;

import com.Alkemy.demo.dto.ApiErrorDTO;
import com.Alkemy.demo.excepcion.ErrorServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.http.HttpHeaders;
import java.util.Arrays;

public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ErrorServicio.class})
    protected ResponseEntity<Object> handleParamNotFOund(RuntimeException ex, WebRequest request) {
        ApiErrorDTO errorDTO = new ApiErrorDTO(
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                Arrays.asList("Parametro no Encontrado"));
        return  handleExceptionInternal(ex, errorDTO, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
