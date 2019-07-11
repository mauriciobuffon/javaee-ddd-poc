package br.com.webit.dddpoc.application;

import br.com.webit.dddpoc.domain.Agregador;
import br.com.webit.dddpoc.domain.AgregadorId;
import br.com.webit.dddpoc.domain.AgregadorRepository;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ApplicationScoped
public class AgregadorJPARepository implements AgregadorRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<Agregador> find(AgregadorId id) {
        return Optional.ofNullable(em.find(Agregador.class, id));
    }

    @Override
    public AgregadorId nextIdentity() {
        return new AgregadorId(Math.abs(UUID.randomUUID().getMostSignificantBits()));
    }

    @Override
    public Collection<Agregador> find(Predicate<Agregador> criteria) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<Agregador> findAll(int offset, int limit) {
        return em.createNamedQuery(FIND_ALL, Agregador.class)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    @Override
    public void put(Agregador obj) {
        em.persist(obj);
    }

    @Override
    public void remove(Agregador obj) {
        em.remove(obj);
    }
}
