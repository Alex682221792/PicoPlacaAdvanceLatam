package com.advancelatam.picoplaca.core.gestor;

import com.advancelatam.picoplaca.cliente.DAO.IReglamentoDAO;
import com.advancelatam.picoplaca.cliente.VO.ResponseSearchByPlacaAndDiaVO;
import com.advancelatam.picoplaca.cliente.exceptions.ExceptionDataInvalid;
import com.advancelatam.picoplaca.cliente.gestor.IReglamentoGestor;
import com.advancelatam.picoplaca.cliente.models.Auto;
import com.advancelatam.picoplaca.cliente.models.Reglamento;
import com.advancelatam.picoplaca.cliente.repositories.IAutoRepository;
import com.advancelatam.picoplaca.cliente.repositories.IReglamentoRepository;
import com.advancelatam.picoplaca.cliente.utils.Conversores;
import com.advancelatam.picoplaca.cliente.utils.Validadores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
@Lazy
public class ReglamentoGestorImpl implements IReglamentoGestor {

    @Autowired
    private IReglamentoRepository reglamentoRepository;

    @Autowired
    private IReglamentoDAO reglamentoDAO;

    @Autowired
    private IAutoRepository autoRepository;

    @Override
    public List<Reglamento> findByEstado(Boolean estado) {
        return reglamentoRepository.findByEstado(estado);
    }

    @Override
    public Reglamento save(Reglamento reglamento) {
        return reglamentoRepository.save(reglamento);
    }

    @Override
    public ResponseSearchByPlacaAndDiaVO findByPlacaAndDia(String placa, String fecha){
        Validadores validador = new Validadores();
        ResponseSearchByPlacaAndDiaVO result = null;
        try {
            placa=placa.toUpperCase();
            Conversores conversor = new Conversores();
            Date fechaRequest = conversor.stringToDate(fecha);
            if (validador.isValidPlaca(placa) && validador.isValidDate(fechaRequest)) {
                String ultimoDigito = new StringBuilder()
                    .append(placa.charAt(placa.length() - 1)).toString();
                Integer digitoPlaca = Integer.parseInt(ultimoDigito);
                StringBuilder dia = new StringBuilder();
                Time hora = new Time(0L);

                conversor.getDayAndTimeFromString(fecha, dia, hora);

                List<Reglamento> reglamentos = reglamentoDAO.findByDigitoPlacaAndDia(digitoPlaca, dia.toString(), hora);

                Auto auto = null;
                if (reglamentos.size() == 0) {
                    Optional<Auto> optionalAuto = autoRepository.findByPlaca(placa);
                    auto = optionalAuto.isPresent() ? optionalAuto.get() : null;
                }

                result = new ResponseSearchByPlacaAndDiaVO(
                        reglamentos,
                        auto,
                        null
                );
            }
        }catch (ExceptionDataInvalid e){
            result = new ResponseSearchByPlacaAndDiaVO(
                    null,
                    null,
                    e.getMessage()
            );
        }
        return result;
    }
}
