package com.advancelatam.picoplaca.cliente.exceptions;

public class ExceptionFechaInvalidFormat extends ExceptionDataInvalid{
    public ExceptionFechaInvalidFormat(){
        super("La fecha ingresada no tiene un formato válido");
    }
}
