package com.advancelatam.picoplaca.cliente.DAO;

import com.advancelatam.picoplaca.cliente.models.Reglamento;

import java.sql.Time;
import java.util.List;

public interface IReglamentoDAO {
    List<Reglamento> findByDigitoPlacaAndDia(Integer digitoPlaca, String dia, Time hora);
}
