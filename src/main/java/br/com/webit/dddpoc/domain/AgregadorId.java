package br.com.webit.dddpoc.domain;

import br.com.webit.dddpoc.application.adapters.AgregadorIdAdapter;
import javax.json.bind.annotation.JsonbTypeAdapter;
import javax.persistence.Embeddable;

@Embeddable
@JsonbTypeAdapter(AgregadorIdAdapter.class)
public class AgregadorId implements br.com.webit.dddpoc.infra.ValueObject<AgregadorId> {

    private long id;

    protected AgregadorId() {
    }

    public AgregadorId(long id) {
        this();
        this.id = id;
    }

    @Override
    public boolean sameValueAs(AgregadorId other) {
        return this.id == other.id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + (int) (this.id ^ (this.id >>> 32));
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
        return sameValueAs((AgregadorId) obj);
    }

    @Override
    public String toString() {
        return Long.toString(id);
    }
}
