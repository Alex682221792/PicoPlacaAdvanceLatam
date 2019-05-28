package com.advancelatam.picoplaca.cliente.repositories;

import com.advancelatam.picoplaca.cliente.models.Auto;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Lazy
public interface IAutoRepository extends JpaRepository<Auto, Long> {
    Optional<Auto> findByPlaca(String placa);
}
