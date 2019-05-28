package com.advancelatam.picoplaca.core.services;

import com.advancelatam.picoplaca.cliente.VO.ResponseSaveAuto;
import com.advancelatam.picoplaca.cliente.gestor.IAutoGestor;
import com.advancelatam.picoplaca.cliente.models.Auto;
import com.advancelatam.picoplaca.cliente.services.IAutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Lazy
public class AutoServiceImpl implements IAutoService {

    @Autowired
    private IAutoGestor autoGestor;

    @Override
    public ResponseSaveAuto save(Auto auto) {
        return autoGestor.save(auto);
    }

    @Override
    public Optional<Auto> findByPlaca(String placa) {
        return autoGestor.findByPlaca(placa);
    }
}
