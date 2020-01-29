package br.com.webit.dddpoc.application.adapters;

import br.com.webit.dddpoc.domain.EntidadeId;
import javax.json.Json;
import javax.json.JsonNumber;
import javax.json.bind.adapter.JsonbAdapter;

public class EntidadeIdAdapter implements JsonbAdapter<EntidadeId, JsonNumber> {

    @Override
    public JsonNumber adaptToJson(EntidadeId original) {
        return Json.createValue(Long.parseLong(original.toString()));
    }

    @Override
    public EntidadeId adaptFromJson(JsonNumber adapted) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
