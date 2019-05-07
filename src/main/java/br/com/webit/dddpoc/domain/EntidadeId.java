package br.com.webit.dddpoc.domain;

import br.com.webit.dddpoc.infra.ValueObject;
import java.util.Objects;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class EntidadeId implements ValueObject<EntidadeId> {

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "agregador_id"))
    private AgregadorId agregadorId;
    private long id;

    protected EntidadeId() {
    }

    EntidadeId(AgregadorId agregadorId, long id) {
        this.agregadorId = agregadorId;
        this.id = id;
    }

    @Override
    public boolean sameValueAs(EntidadeId other) {
        if (this.id != other.id) {
            return false;
        } else if (!Objects.equals(this.agregadorId, other.agregadorId)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.agregadorId);
        hash = 61 * hash + (int) (this.id ^ (this.id >>> 32));
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
        return sameValueAs((EntidadeId) obj);
    }
}
