package com.advancelatam.picoplaca.cliente.repositories;

import com.advancelatam.picoplaca.cliente.models.Reglamento;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Lazy
public interface IReglamentoRepository extends JpaRepository<Reglamento, Long> {
    List<Reglamento> findByEstado(Boolean estado);
}
