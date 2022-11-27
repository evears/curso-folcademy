package com.curso.demo.exceptions.exceptionskinds;

public class ContactoExistenteException extends RuntimeException {
    
    public ContactoExistenteException(String mensaje) {
        super(mensaje);
    }
}
