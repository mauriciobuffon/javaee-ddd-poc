package br.com.webit.dddpoc.infra;

import java.io.Serializable;

public interface Entity<T, U> extends Serializable {

    U getId();

    boolean sameIdentityAs(T other);
}
