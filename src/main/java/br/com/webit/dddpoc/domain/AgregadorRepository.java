package br.com.webit.dddpoc.domain;

import br.com.webit.dddpoc.infra.Repository;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Predicate;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ApplicationScoped
public class AgregadorRepository implements Repository<Agregador, AgregadorId> {

    @PersistenceContext
    private EntityManager em;

    public Optional<Agregador> find(AgregadorId id) {
        return Optional.ofNullable(em.find(Agregador.class, id));
    }

    @Override
    public AgregadorId nextIdentity() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Agregador> find(Predicate<Agregador> criteria) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Agregador> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void put(Agregador obj) {
        em.persist(obj);
    }

    @Override
    public void remove(Agregador obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
