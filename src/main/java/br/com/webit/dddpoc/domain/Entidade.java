package br.com.webit.dddpoc.domain;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Version;

@Entity
public class Entidade implements br.com.webit.dddpoc.infra.Entity<Entidade, EntidadeId> {

    @EmbeddedId
    private EntidadeId entidadeId;
    @ManyToOne
    @MapsId("agregadorId")
    private Agregador agregador;
    @ElementCollection
    private Set<ValorObjeto> objetos;
    @Embedded
    private ValorObjeto objeto;
    private String dado;
    @Version
    private LocalDateTime version;

    protected Entidade() {
        this.objetos = new HashSet<>();
    }

    public Entidade(long id, Agregador agregador, ValorObjeto objeto, String dado) {
        this();
        this.entidadeId = new EntidadeId(agregador.getId(), id);
        this.agregador = agregador;
        this.objeto = objeto;
        this.dado = dado;
    }

    public void addObjeto(ValorObjeto objeto) {
        if (!this.objetos.add(objeto)) {
            throw new IllegalStateException();
        }
    }

    @Override
    public EntidadeId getId() {
        return entidadeId;
    }

    public Set<ValorObjeto> getObjetos() {
        return objetos;
    }

    public ValorObjeto getObjeto() {
        return objeto;
    }

    public String getDado() {
        return dado;
    }

    @Override
    public boolean sameIdentityAs(Entidade other) {
        return Objects.equals(this.entidadeId, other.entidadeId);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.entidadeId);
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
        return sameIdentityAs((Entidade) obj);
    }
}
