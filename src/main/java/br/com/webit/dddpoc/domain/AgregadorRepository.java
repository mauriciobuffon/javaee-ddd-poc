package br.com.webit.dddpoc.domain;

import br.com.webit.dddpoc.infra.Repository;
import javax.persistence.NamedQuery;

@NamedQuery(name = AgregadorRepository.FIND_ALL, query = "SELECT a FROM Agregador a ORDER BY a.agregadorId.id ASC")
public interface AgregadorRepository extends Repository<Agregador, AgregadorId> {

    static final String FIND_ALL = "Agregador.findAll";
}
