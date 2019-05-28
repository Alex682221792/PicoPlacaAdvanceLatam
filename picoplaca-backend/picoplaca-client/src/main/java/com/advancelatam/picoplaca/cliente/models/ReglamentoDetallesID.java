package com.advancelatam.picoplaca.cliente.models;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Embeddable
public class ReglamentoDetallesID implements Serializable {
    @Column(name = "rgl_id")
    @EqualsAndHashCode.Include public Long reglamentoId;

    @Column(name = "rde_digito_placa")
    @EqualsAndHashCode.Include public Integer digitoPlaca;
}
