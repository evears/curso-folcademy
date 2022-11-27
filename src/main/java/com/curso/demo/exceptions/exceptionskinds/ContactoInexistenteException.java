package com.curso.demo.exceptions.exceptionskinds;

public class ContactoInexistenteException extends RuntimeException {

    public ContactoInexistenteException(String mensaje) {
        super(mensaje);
    }
    
}
