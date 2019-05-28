package com.advancelatam.picoplaca.cliente.models;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "reglamento_detalles")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ReglamentoDetalles implements Serializable {
    @EmbeddedId
    @EqualsAndHashCode.Include private ReglamentoDetallesID id;

    @Column(name = "rde_estado")
    private Boolean estado;

}
