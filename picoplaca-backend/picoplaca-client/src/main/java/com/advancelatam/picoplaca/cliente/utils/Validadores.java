package com.advancelatam.picoplaca.cliente.utils;

import com.advancelatam.picoplaca.cliente.exceptions.ExceptionFechaInvalidFormat;
import com.advancelatam.picoplaca.cliente.exceptions.ExceptionFechaInvalidRange;
import com.advancelatam.picoplaca.cliente.exceptions.ExceptionPlacaInvalid;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validadores {

    public boolean isValidPlaca(String placa) throws ExceptionPlacaInvalid{
        Pattern pattern = Pattern.compile("[aA-zA]{3}-\\d{3,4}");
        Matcher mat = pattern.matcher(placa);
        if(mat.matches()){
            return true;
        }else {
            throw new ExceptionPlacaInvalid();
        }
    }

    public boolean isValidDate(Date fecha) throws ExceptionFechaInvalidRange {
        Date currentDate = new Date();
        if(fecha.after(currentDate)){
            return true;
        }else{
            throw new ExceptionFechaInvalidRange();
        }
    }

    public boolean isValidFormatDate(String fecha) throws ExceptionFechaInvalidFormat {
        Pattern pattern = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}$");
        Matcher mat = pattern.matcher(fecha);
        if(mat.matches()){
            return true;
        }else{
            throw new ExceptionFechaInvalidFormat();
        }
    }
}
