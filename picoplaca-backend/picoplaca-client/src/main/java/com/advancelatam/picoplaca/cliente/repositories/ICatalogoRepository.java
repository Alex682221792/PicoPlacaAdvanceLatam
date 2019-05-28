package com.advancelatam.picoplaca.cliente.repositories;

import com.advancelatam.picoplaca.cliente.models.Catalogo;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Lazy
public interface ICatalogoRepository extends JpaRepository<Catalogo, Long> {
    List<Catalogo> findAllByCodigo(String codigo);
}
