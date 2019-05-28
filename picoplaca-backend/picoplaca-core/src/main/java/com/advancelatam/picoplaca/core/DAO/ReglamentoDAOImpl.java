package com.advancelatam.picoplaca.core.DAO;

import com.advancelatam.picoplaca.cliente.DAO.IReglamentoDAO;
import com.advancelatam.picoplaca.cliente.models.QCatalogoValor;
import com.advancelatam.picoplaca.cliente.models.QReglamento;
import com.advancelatam.picoplaca.cliente.models.QReglamentoDetalles;
import com.advancelatam.picoplaca.cliente.models.Reglamento;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.QBean;
import com.querydsl.jpa.impl.JPAQuery;
import org.hibernate.HibernateException;
import org.springframework.context.annotation.Lazy;
import org.springframework.orm.hibernate5.HibernateJdbcException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Time;
import java.util.List;

import static com.querydsl.core.types.Projections.bean;

@Repository
@Lazy
public class ReglamentoDAOImpl implements IReglamentoDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Reglamento> findByDigitoPlacaAndDia(Integer digitoPlaca, String dia, Time hora) {
        try {
            JPAQuery<Reglamento> query = new JPAQuery<>(em);
            QReglamento qReglamento = QReglamento.reglamento;
            QReglamentoDetalles qReglamentoDetalles = QReglamentoDetalles.reglamentoDetalles;
            QCatalogoValor qCatalogoValor = QCatalogoValor.catalogoValor;

            //Projections
            QBean<Reglamento> beanReglamento = bean(
                    qReglamento,
                    qReglamento.dia,
                    qReglamento.horaInicio,
                    qReglamento.horaFin,
                    qCatalogoValor.as(qReglamento.catalogoValorDia)
            );

            //Conditions
            BooleanBuilder conditions = new BooleanBuilder();
            conditions.and(qReglamento.horaInicio.loe(hora));
            conditions.and(qReglamento.horaFin.goe(hora));
            conditions.and(qReglamento.estado.eq(Boolean.TRUE));
            conditions.and(qReglamentoDetalles.id.digitoPlaca.eq(digitoPlaca));
            conditions.and(qCatalogoValor.descripcion.eq(dia));

            return query.select(beanReglamento)
                    .from(qReglamento)
                    .join(qReglamento.detalles, qReglamentoDetalles)
                    .join(qReglamento.catalogoValorDia, qCatalogoValor)
                    .where(conditions)
                    .fetch();
        }catch (HibernateException | HibernateJdbcException e){
            throw e;
        }
    }
}
