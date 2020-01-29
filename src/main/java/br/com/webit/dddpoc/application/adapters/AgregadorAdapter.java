package br.com.webit.dddpoc.application.adapters;

import br.com.webit.dddpoc.domain.Agregador;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.bind.adapter.JsonbAdapter;
import javax.json.stream.JsonCollectors;

public class AgregadorAdapter implements JsonbAdapter<Agregador, JsonObject> {

    private AgregadorIdAdapter aia = new AgregadorIdAdapter();
    private EntidadeIdAdapter eia = new EntidadeIdAdapter();
    private ValorObjetoAdapter voa = new ValorObjetoAdapter();

    @Override
    public JsonObject adaptToJson(Agregador original) {
        return Json.createObjectBuilder()
                .add("id", aia.adaptToJson(original.getId()))
                /*
                .add("custom-entidade",
                        original.getEntidades()
                                .stream()
                                .map(entidade
                                        -> Json.createObjectBuilder()
                                        .add("entidade-id", eia.adaptToJson(entidade.getId()))
                                        .add("informação", voa.adaptToJson(entidade.getObjeto()))
                                        .build())
                                .collect(JsonCollectors.toJsonArray()))
                 */
                .add("objetos", original.getObjetos()
                        .stream()
                        .map(voa::adaptToJson)
                        .collect(JsonCollectors.toJsonArray()))
                .build();
    }

    @Override
    public Agregador adaptFromJson(JsonObject adapted) {
        throw new UnsupportedOperationException();
    }
}
