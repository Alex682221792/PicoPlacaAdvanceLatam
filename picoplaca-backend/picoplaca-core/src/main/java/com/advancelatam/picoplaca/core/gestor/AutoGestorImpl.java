package com.advancelatam.picoplaca.core.gestor;

import com.advancelatam.picoplaca.cliente.VO.ResponseSaveAuto;
import com.advancelatam.picoplaca.cliente.exceptions.ExceptionPlacaInvalid;
import com.advancelatam.picoplaca.cliente.gestor.IAutoGestor;
import com.advancelatam.picoplaca.cliente.models.Auto;
import com.advancelatam.picoplaca.cliente.repositories.IAutoRepository;
import com.advancelatam.picoplaca.cliente.utils.Validadores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Lazy
public class AutoGestorImpl implements IAutoGestor {

    @Autowired
    private IAutoRepository autoRepository;

    @Override
    public ResponseSaveAuto save(Auto auto) {
        Validadores validador = new Validadores();
        ResponseSaveAuto result = new ResponseSaveAuto();
        try {
            if(validador.isValidPlaca(auto.getPlaca())) {
                auto.setPlaca(auto.getPlaca().toUpperCase());
                auto.setChasis(auto.getChasis().toUpperCase());
                result.setAuto(autoRepository.save(auto));
            }
        } catch (ExceptionPlacaInvalid exceptionPlacaInvalid) {
            result.setMensajeError(exceptionPlacaInvalid.getMessage());
        }
        return result;
    }

    @Override
    public Optional<Auto> findByPlaca(String placa) {
        return autoRepository.findByPlaca(placa);
    }
}
