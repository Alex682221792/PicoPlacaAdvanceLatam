package com.advancelatam.picoplaca.cliente.utils;

import com.advancelatam.picoplaca.cliente.exceptions.ExceptionFechaInvalidFormat;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Conversores {
    public void getDayAndTimeFromString(String fecha, StringBuilder diaTarget, Time timeTarget) throws ExceptionFechaInvalidFormat {
        try{
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm");
            Date date = formatter.parse(fecha.replace("T"," "));
            Locale spanishLocale=new Locale("es", "ES");
            diaTarget.append(new SimpleDateFormat("EEEE", spanishLocale).format(date).toUpperCase());
            timeTarget.setHours(date.getHours());
            timeTarget.setMinutes(date.getMinutes());
        }catch (ParseException e){
            throw new ExceptionFechaInvalidFormat();
        }
    }

    public Date stringToDate(String fecha) throws ExceptionFechaInvalidFormat {
        try {
            Validadores validador = new Validadores();
            if (validador.isValidFormatDate(fecha)) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm");
                return formatter.parse(fecha.replace("T", " "));
            }
        }catch (ParseException | ExceptionFechaInvalidFormat e){
            throw new ExceptionFechaInvalidFormat();
        }
        return null;
    }
}
