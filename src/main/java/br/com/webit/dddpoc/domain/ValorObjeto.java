package br.com.webit.dddpoc.domain;

import br.com.webit.dddpoc.infra.ValueObject;
import java.util.Objects;
import javax.persistence.Embeddable;

@Embeddable
public class ValorObjeto implements ValueObject<ValorObjeto> {

    private String dado1;
    private String dado2;

    protected ValorObjeto() {
    }

    public ValorObjeto(String dado1, String dado2) {
        this.dado1 = dado1;
        this.dado2 = dado2;
    }

    public String getDado1() {
        return dado1;
    }

    public String getDado2() {
        return dado2;
    }

    @Override
    public boolean sameValueAs(ValorObjeto other) {
        if (!Objects.equals(this.dado1, other.dado1)) {
            return false;
        } else if (!Objects.equals(this.dado2, other.dado2)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.dado1);
        hash = 23 * hash + Objects.hashCode(this.dado2);
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
