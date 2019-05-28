package com.advancelatam.picoplaca.cliente.exceptions;

public class ExceptionFechaInvalidRange extends ExceptionDataInvalid{
    public ExceptionFechaInvalidRange(){
        super("La fecha ingresada no tiene un rango válido");
    }
}
