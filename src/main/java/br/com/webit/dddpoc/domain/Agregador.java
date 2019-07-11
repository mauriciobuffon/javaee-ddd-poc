package br.com.webit.dddpoc.domain;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import javax.persistence.ElementCollection;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Version;

@Entity
public class Agregador implements br.com.webit.dddpoc.infra.Entity<Agregador, AgregadorId> {

    @EmbeddedId
    private AgregadorId agregadorId;
    @OneToMany(mappedBy = "agregador", fetch = FetchType.EAGER)
    private Set<Entidade> entidades;
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<ValorObjeto> objetos;
    private String dado;
    @Version
    private LocalDateTime version;

    protected Agregador() {
    }

    public Agregador(AgregadorId id, String dado) {
        this();
        this.agregadorId = id;
        this.entidades = new HashSet<>();
        this.objetos = new HashSet<>();
        this.dado = dado;
    }

    public Entidade addEntidade(ValorObjeto objeto) {
        Entidade entidade = new Entidade(Math.abs(UUID.randomUUID().getMostSignificantBits()), this, objeto);
        if (!this.entidades.add(entidade)) {
            throw new IllegalStateException();
        }
        return entidade;
    }

    public void addObjeto(ValorObjeto objeto) {
        if (!this.objetos.add(objeto)) {
            throw new IllegalStateException();
        }
    }

    @Override
    public AgregadorId getId() {
        return agregadorId;
    }

    public Set<Entidade> getEntidades() {
        return entidades;
    }

    public Set<ValorObjeto> getObjetos() {
        return objetos;
    }

    public String getDado() {
        return dado;
    }

    @Override
    public boolean sameIdentityAs(Agregador other) {
        return Objects.equals(this.agregadorId, other.agregadorId);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.agregadorId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        return sameIdentityAs((Agregador) obj);
    }
}
