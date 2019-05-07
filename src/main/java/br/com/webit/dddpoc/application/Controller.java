package br.com.webit.dddpoc.application;

import br.com.webit.dddpoc.domain.Agregador;
import br.com.webit.dddpoc.domain.AgregadorId;
import br.com.webit.dddpoc.domain.AgregadorRepository;
import br.com.webit.dddpoc.domain.Entidade;
import br.com.webit.dddpoc.domain.ValorObjeto;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("agregadores")
@Produces("application/json")
@Stateless
public class Controller {

    @Inject
    private AgregadorRepository repository;

    @Path("{id : \\d+}")
    @GET
    public Agregador get(@PathParam("id") long id) {
        return repository.find(new AgregadorId(id)).orElseThrow(NotFoundException::new);
    }

    @Path("{id : \\d+}")
    @POST
    public void post(@PathParam("id") long id) {
        Agregador agregador = new Agregador(id, new ValorObjeto("objeto1 agregador " + id, "objeto2 agregador" + id), "dado agregador " + id);

        for (int i : new int[]{1, 2, 3}) {
            Entidade entidade = new Entidade(i, agregador, new ValorObjeto("objeto1 entidade " + i + " agregador " + id, "objeto2 entidade " + i + " agregador " + id), "dado entidade " + i + " agregador " + id);

            for (int j : new int[]{1, 2, 3}) {
                entidade.addObjeto(new ValorObjeto("objeto1 objetos[" + j + "] entidade " + i + " agregador " + id, "objeto2 objetos[" + j + "] entidade " + i + " agregador " + id));
            }

            agregador.addEntidade(entidade);
            agregador.addObjeto(new ValorObjeto("objeto1 objetos[" + i + "] agregador " + id, "objeto2 objetos[" + i + "] agregador " + id));
        }

        repository.put(agregador);
    }

    @Path("{idAgregador : \\d+}/entidades/{id : \\d+}")
    @POST
    public void postEntidade(@PathParam("idAgregador") long idAgregador, @PathParam("id") long id) {
        Agregador agregador = repository.find(new AgregadorId(idAgregador)).orElseThrow(NotFoundException::new);
        agregador.addEntidade(new Entidade(id, agregador, new ValorObjeto("objeto1 entidade " + id + " agregador " + idAgregador, "objeto2 entidade " + id + " agregador " + idAgregador), "dado entidade " + id + " agregador " + idAgregador));
        repository.put(agregador);
    }
}
