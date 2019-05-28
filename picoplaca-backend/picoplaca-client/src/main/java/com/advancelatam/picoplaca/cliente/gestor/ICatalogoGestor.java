package com.advancelatam.picoplaca.cliente.gestor;

import com.advancelatam.picoplaca.cliente.models.Catalogo;

import java.util.List;

public interface ICatalogoGestor {

    Catalogo save (Catalogo catalogo);

    List<Catalogo> findAllByCodigo(String codigo);
}
