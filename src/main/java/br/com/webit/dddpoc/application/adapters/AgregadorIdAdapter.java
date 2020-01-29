package br.com.webit.dddpoc.application.adapters;

import br.com.webit.dddpoc.domain.AgregadorId;
import javax.json.Json;
import javax.json.JsonNumber;
import javax.json.bind.adapter.JsonbAdapter;

public class AgregadorIdAdapter implements JsonbAdapter<AgregadorId, JsonNumber> {

    @Override
    public JsonNumber adaptToJson(AgregadorId original) {
        return Json.createValue(Long.parseLong(original.toString()));
    }

    @Override
    public AgregadorId adaptFromJson(JsonNumber adapted) {
        return new AgregadorId(adapted.longValueExact());
    }
}
