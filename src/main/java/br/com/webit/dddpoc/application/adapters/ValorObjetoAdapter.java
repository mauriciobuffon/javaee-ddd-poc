package br.com.webit.dddpoc.application.adapters;

import br.com.webit.dddpoc.domain.ValorObjeto;
import javax.json.Json;
import javax.json.JsonString;
import javax.json.bind.adapter.JsonbAdapter;

public class ValorObjetoAdapter implements JsonbAdapter<ValorObjeto, JsonString> {

    @Override
    public JsonString adaptToJson(ValorObjeto original) {
        return Json.createValue(original.getDado());
    }

    @Override
    public ValorObjeto adaptFromJson(JsonString adapted) {
        return new ValorObjeto(adapted.getString());
    }
}
