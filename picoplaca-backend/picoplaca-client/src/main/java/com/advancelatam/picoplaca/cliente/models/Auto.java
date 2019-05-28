package com.advancelatam.picoplaca.cliente.models;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "auto")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Auto implements Serializable {

    @Id
    @SequenceGenerator(name="auto_aut_id_seq", sequenceName="auto_aut_id_seq",allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "auto_aut_id_seq")
    @Column(name = "aut_id")
    @EqualsAndHashCode.Include private Long id;

    @Column(name = "aut_placa", unique = true)
    private String placa;
    @Column(name = "aut_chasis", length = 30)
    private String chasis;
    @Column(name = "aut_capacidad")
    private Integer capacidad;
    @Column(name = "aut_color")
    private Long color;
    @Column(name = "aut_marca")
    private Long marca;
    @Column(name = "aut_modelo")
    private Long modelo;

    @ManyToOne
    @JoinColumn(name = "aut_color", referencedColumnName = "cvl_id", insertable = false, updatable = false)
    private CatalogoValor catalogoValorColor;

    @ManyToOne
    @JoinColumn(name = "aut_marca", referencedColumnName = "cvl_id", insertable = false, updatable = false)
    private CatalogoValor catalogoValorMarca;

    @ManyToOne
    @JoinColumn(name = "aut_modelo", referencedColumnName = "cvl_id", insertable = false, updatable = false)
    private CatalogoValor catalogoValorModelo;
}
