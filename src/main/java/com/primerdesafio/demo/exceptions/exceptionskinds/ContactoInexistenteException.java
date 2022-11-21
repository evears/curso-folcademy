package com.primerdesafio.demo.exceptions.exceptionskinds;

public class ContactoInexistenteException extends RuntimeException {

    public ContactoInexistenteException(String mensaje) {
        super(mensaje);
    }
    
}
