import com.advancelatam.picoplaca.cliente.exceptions.ExceptionDataInvalid;
import com.advancelatam.picoplaca.cliente.exceptions.ExceptionFechaInvalidRange;
import com.advancelatam.picoplaca.cliente.exceptions.ExceptionPlacaInvalid;
import com.advancelatam.picoplaca.cliente.utils.Validadores;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class TestValidadores {

    Validadores validador = new Validadores();

    @Test
    public void testValidarPlacaCorrecta() throws ExceptionPlacaInvalid {
        String placa = "ACY-450";
            Assert.assertEquals(validador.isValidPlaca(placa),Boolean.TRUE);
        }

    @Test(expected = ExceptionDataInvalid.class)
    public void testValidarPlacaIncorrecta() throws ExceptionPlacaInvalid {
        String placa = "AC2A-0100";
        validador.isValidPlaca(placa);
    }

    @Test
    public void testFechaValida() throws ExceptionFechaInvalidRange {
        Date fechaMañana = new Date(new Date().getTime() + (1000 * 60 * 60 * 24));
        Assert.assertEquals(validador.isValidDate(fechaMañana), Boolean.TRUE);
    }

    @Test(expected = ExceptionDataInvalid.class)
    public void testFechaInvalida() throws ExceptionFechaInvalidRange {
        Date fechaAyer = new Date(new Date().getTime() - (1000 * 60 * 60 * 24));
        Assert.assertEquals(validador.isValidDate(fechaAyer), Boolean.FALSE);
    }
}
