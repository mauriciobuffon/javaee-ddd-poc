package br.com.webit.dddpoc.domain;

import br.com.webit.dddpoc.infra.ValueObject;
import java.util.Objects;
import javax.persistence.Embeddable;

@Embeddable
public class ValorObjeto implements ValueObject<ValorObjeto> {

    private String dado;

    protected ValorObjeto() {
    }

    public ValorObjeto(String dado) {
        this();
        this.dado = dado;
    }

    public String getDado() {
        return dado;
    }

    @Override
    public boolean sameValueAs(ValorObjeto other) {
        return Objects.equals(this.dado, other.dado);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.dado);
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
        return sameValueAs((ValorObjeto) obj);
    }
}
