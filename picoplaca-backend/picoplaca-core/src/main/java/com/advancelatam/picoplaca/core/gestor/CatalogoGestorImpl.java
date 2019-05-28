package com.advancelatam.picoplaca.core.gestor;

import com.advancelatam.picoplaca.cliente.gestor.ICatalogoGestor;
import com.advancelatam.picoplaca.cliente.models.Catalogo;
import com.advancelatam.picoplaca.cliente.repositories.ICatalogoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Lazy
public class CatalogoGestorImpl implements ICatalogoGestor {

    @Autowired
    private ICatalogoRepository catalogoRepository;

    @Override
    public Catalogo save(Catalogo catalogo) {
        return catalogoRepository.save(catalogo);
    }

    @Override
    public List<Catalogo> findAllByCodigo(String codigo) {
        return catalogoRepository.findAllByCodigo(codigo);
    }
}
