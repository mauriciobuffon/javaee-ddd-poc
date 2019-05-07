package br.com.webit.dddpoc.infra;

import java.io.Serializable;

public interface ValueObject<T> extends Serializable {

    boolean sameValueAs(T other);
}
