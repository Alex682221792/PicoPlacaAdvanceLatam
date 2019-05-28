package com.advancelatam.picoplaca.core.services;

import com.advancelatam.picoplaca.cliente.gestor.ICatalogoGestor;
import com.advancelatam.picoplaca.cliente.models.Catalogo;
import com.advancelatam.picoplaca.cliente.services.ICatalogoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Lazy
public class CatalogoServiceImpl implements ICatalogoService {

    @Autowired
    private ICatalogoGestor catalogoGestor;

    @Override
    public List<Catalogo> findAllByCodigo(String codigo) {
        return catalogoGestor.findAllByCodigo(codigo);
    }

    @Override
    public Catalogo save(Catalogo catalogo) {
        return catalogoGestor.save(catalogo);
    }
}
