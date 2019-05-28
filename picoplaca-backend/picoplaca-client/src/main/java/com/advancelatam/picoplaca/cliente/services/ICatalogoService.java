package com.advancelatam.picoplaca.cliente.services;

import com.advancelatam.picoplaca.cliente.models.Catalogo;

import java.util.List;

public interface ICatalogoService {
    List<Catalogo> findAllByCodigo(String codigo);

    Catalogo save(Catalogo catalogo);
}
