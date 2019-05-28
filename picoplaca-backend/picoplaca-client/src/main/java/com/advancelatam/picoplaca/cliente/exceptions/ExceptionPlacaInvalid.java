package com.advancelatam.picoplaca.cliente.exceptions;

public class ExceptionPlacaInvalid extends ExceptionDataInvalid{
    public ExceptionPlacaInvalid(){
        super("La placa ingresada no tiene un formato válido");
    }
}
