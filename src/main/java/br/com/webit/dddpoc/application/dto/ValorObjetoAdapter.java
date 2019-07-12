package br.com.webit.dddpoc.application.dto;

import br.com.webit.dddpoc.domain.ValorObjeto;
import javax.json.Json;
import javax.json.JsonValue;
import javax.json.bind.adapter.JsonbAdapter;

public class ValorObjetoAdapter implements JsonbAdapter<ValorObjeto, JsonValue> {

    @Override
    public JsonValue adaptToJson(ValorObjeto obj) {
        return Json.createValue(obj.toString());
    }

    @Override
    public ValorObjeto adaptFromJson(JsonValue obj) {
        return new ValorObjeto(obj.toString());
    }
}
