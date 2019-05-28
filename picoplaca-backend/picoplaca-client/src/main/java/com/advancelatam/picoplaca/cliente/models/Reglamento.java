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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Time;
import java.util.List;

@Entity
@Table(name = "reglamento")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Reglamento implements Serializable {

    @Id
    @SequenceGenerator(name="reglamento_rgl_id_seq", sequenceName="reglamento_rgl_id_seq",allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reglamento_rgl_id_seq")
    @Column(name = "rgl_id")
    @EqualsAndHashCode.Include private Integer id;

    @Column(name = "rgl_dia", unique = true)
    private Integer dia;
    @Column(name = "rgl_hora_inicio")
    private Time horaInicio;
    @Column(name = "rgl_hora_fin")
    private Time horaFin;
    @Column(name = "rgl_estado")
    private Boolean estado;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="rgl_id", referencedColumnName="rgl_id")
    private List<ReglamentoDetalles> detalles;

    @ManyToOne
    @JoinColumn(name = "rgl_dia", referencedColumnName = "cvl_id", insertable = false, updatable = false)
    private CatalogoValor catalogoValorDia;

}
