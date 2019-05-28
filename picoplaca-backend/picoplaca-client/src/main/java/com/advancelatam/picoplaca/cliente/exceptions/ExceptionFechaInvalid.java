package com.advancelatam.picoplaca.cliente.exceptions;

public class ExceptionFechaInvalid extends ExceptionDataInvalid{
    public ExceptionFechaInvalid(){
        super("La fecha ingresada no tiene un rango válido");
    }
}
