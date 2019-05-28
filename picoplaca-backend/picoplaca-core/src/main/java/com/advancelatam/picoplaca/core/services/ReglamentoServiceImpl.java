package com.advancelatam.picoplaca.core.services;

import com.advancelatam.picoplaca.cliente.VO.ResponseSearchByPlacaAndDiaVO;
import com.advancelatam.picoplaca.cliente.exceptions.ExceptionDataInvalid;
import com.advancelatam.picoplaca.cliente.gestor.IReglamentoGestor;
import com.advancelatam.picoplaca.cliente.models.Reglamento;
import com.advancelatam.picoplaca.cliente.services.IReglamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Lazy
public class ReglamentoServiceImpl implements IReglamentoService {

    @Autowired
    private IReglamentoGestor reglamentoGestor;

    @Override
    public Reglamento save(Reglamento reglamento) {
        return reglamentoGestor.save(reglamento);
    }

    @Override
    public ResponseSearchByPlacaAndDiaVO findByPlacaAndDia(String placa, String dia){
        return reglamentoGestor.findByPlacaAndDia(placa, dia);
    }

    @Override
    public List<Reglamento> findByEstado(Boolean estado) {
        return reglamentoGestor.findByEstado(estado);
    }
}
