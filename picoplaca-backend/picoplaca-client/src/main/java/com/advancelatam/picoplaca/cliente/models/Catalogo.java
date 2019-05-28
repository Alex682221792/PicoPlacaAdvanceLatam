package com.advancelatam.picoplaca.cliente.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

/**
 * @author Alexander
 */

@Entity
@Table(name = "catalogo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder(toBuilder = true)
public class Catalogo implements Serializable {

    @Column(name = "ctg_id")
    @Id
    @SequenceGenerator(name="catalogo_ctg_id_seq", sequenceName="catalogo_ctg_id_seq",allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "catalogo_ctg_id_seq")
    @EqualsAndHashCode.Include private Long id;

    @Column(name = "ctg_codigo")
    private String codigo;

    @Column(name = "ctg_nombre")
    private String nombre;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="ctg_id", referencedColumnName="ctg_id")
    private List<CatalogoValor> valores;
}
