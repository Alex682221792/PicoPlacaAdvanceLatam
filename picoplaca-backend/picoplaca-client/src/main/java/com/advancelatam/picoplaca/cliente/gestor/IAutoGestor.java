package com.advancelatam.picoplaca.cliente.gestor;

import com.advancelatam.picoplaca.cliente.VO.ResponseSaveAuto;
import com.advancelatam.picoplaca.cliente.models.Auto;

import java.util.Optional;

public interface IAutoGestor {
    ResponseSaveAuto save(Auto auto);

    Optional<Auto> findByPlaca(String placa);
}
