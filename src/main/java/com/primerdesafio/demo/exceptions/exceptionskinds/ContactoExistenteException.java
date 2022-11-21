package com.primerdesafio.demo.exceptions.exceptionskinds;

public class ContactoExistenteException extends RuntimeException {
    
    public ContactoExistenteException(String mensaje) {
        super(mensaje);
    }
}
