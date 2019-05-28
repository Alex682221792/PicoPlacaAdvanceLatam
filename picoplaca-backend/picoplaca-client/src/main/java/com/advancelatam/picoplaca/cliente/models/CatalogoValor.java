package com.advancelatam.picoplaca.cliente.models;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "catalogo_valor")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CatalogoValor implements Serializable {

    @Id
    @SequenceGenerator(name="catalogo_valor_cvl_id_seq", sequenceName="catalogo_valor_cvl_id_seq",allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "catalogo_valor_cvl_id_seq")
    @Column(name = "cvl_id")
    @EqualsAndHashCode.Include private Long id;

    @Column(name = "cvl_descripcion", length = 30)
    private String descripcion;

    @Column(name = "ctg_id")
    private Long catalogoId;

/*
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ctg_id", referencedColumnName = "ctg_id", insertable = false, updatable = false)
    private Catalogo catalogoPadre;
*/

}
