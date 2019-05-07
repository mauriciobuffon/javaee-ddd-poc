package br.com.webit.dddpoc.domain;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Version;

@Entity
public class Agregador implements br.com.webit.dddpoc.infra.Entity<Agregador, AgregadorId> {

    @EmbeddedId
    private AgregadorId agregadorId;
    @OneToMany
    @JoinColumn(name = "agregador_id")
    private Set<Entidade> entidades;
    @ElementCollection
    private Set<ValorObjeto> objetos;
    @Embedded
    private ValorObjeto objeto;
    private String dado;
    @Version
    private LocalDateTime version;

    protected Agregador() {
        this.entidades = new HashSet<>();
        this.objetos = new HashSet<>();
    }

    public Agregador(long id, ValorObjeto objeto, String dado) {
        this();
        this.agregadorId = new AgregadorId(id);
        this.objeto = objeto;
        this.dado = dado;
    }

    public void addEntidade(Entidade entidade) {
        if (!this.entidades.add(entidade)) {
            throw new IllegalStateException();
        }
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

    public ValorObjeto getObjeto() {
        return objeto;
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
