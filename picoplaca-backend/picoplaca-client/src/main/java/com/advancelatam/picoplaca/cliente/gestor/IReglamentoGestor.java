package com.advancelatam.picoplaca.cliente.gestor;

import com.advancelatam.picoplaca.cliente.VO.ResponseSearchByPlacaAndDiaVO;
import com.advancelatam.picoplaca.cliente.exceptions.ExceptionDataInvalid;
import com.advancelatam.picoplaca.cliente.models.Reglamento;

import java.util.List;

public interface IReglamentoGestor {
    Reglamento save(Reglamento reglamento);

    ResponseSearchByPlacaAndDiaVO findByPlacaAndDia(String placa, String dia);

    List<Reglamento> findByEstado(Boolean estado);
}
