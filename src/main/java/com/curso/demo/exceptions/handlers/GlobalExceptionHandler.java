package com.curso.demo.exceptions.handlers;

import com.curso.demo.exceptions.dtos.ErrorMessageDTO;
import com.curso.demo.exceptions.exceptionskinds.ContactoExistenteException;
import com.curso.demo.exceptions.exceptionskinds.ContactoInexistenteException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(value = RuntimeException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessageDTO> defaultErrorHandler(HttpServletRequest req, Exception e){
        return new ResponseEntity<>(new ErrorMessageDTO(111, e.getMessage(), req.getRequestURI()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(value = ContactoInexistenteException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessageDTO> notFoundExceptionHandler(HttpServletRequest req, Exception e){
        return new ResponseEntity<>(new ErrorMessageDTO(222, e.getMessage(), req.getRequestURI()), HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(value = ContactoExistenteException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessageDTO> badRequestExceptionHandler(HttpServletRequest req, Exception e){
        return new ResponseEntity<>(new ErrorMessageDTO(333, e.getMessage(), req.getRequestURI()), HttpStatus.BAD_REQUEST);
    }
    
    
    
}
