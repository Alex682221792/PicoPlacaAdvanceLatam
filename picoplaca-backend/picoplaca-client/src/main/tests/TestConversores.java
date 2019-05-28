import com.advancelatam.picoplaca.cliente.exceptions.ExceptionFechaInvalidFormat;
import com.advancelatam.picoplaca.cliente.utils.Conversores;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Time;

public class TestConversores {

    private Conversores conversor = new Conversores();

    @Test
    public void testGetDayAndTimeFromStringValid() throws ExceptionFechaInvalidFormat {
        String fecha="2019-05-26T23:59";
        StringBuilder diaTarget=new StringBuilder();
        Time timeTarget = new Time(0L);
        conversor.getDayAndTimeFromString(fecha, diaTarget,timeTarget);
        Assert.assertEquals(diaTarget.toString(),"DOMINGO");
        Assert.assertEquals(timeTarget.getHours(),23);
        Assert.assertEquals(timeTarget.getMinutes(),59);
    }

    @Test(expected = ExceptionFechaInvalidFormat.class)
    public void testGetDayAndTimeFromStringInvalid() throws ExceptionFechaInvalidFormat {
        String fecha="05-2019T12:00";
        StringBuilder diaTarget=new StringBuilder();
        Time timeTarget = new Time(0L);
        conversor.getDayAndTimeFromString(fecha, diaTarget,timeTarget);
        Assert.assertEquals(diaTarget.toString(),"DOMINGO");
        Assert.assertEquals(timeTarget.getHours(),12);
        Assert.assertEquals(timeTarget.getMinutes(),00);
    }
}
