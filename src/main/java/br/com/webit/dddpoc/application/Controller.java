package br.com.webit.dddpoc.application;

import br.com.webit.dddpoc.domain.Agregador;
import br.com.webit.dddpoc.domain.AgregadorId;
import br.com.webit.dddpoc.domain.AgregadorRepository;
import br.com.webit.dddpoc.domain.Entidade;
import br.com.webit.dddpoc.domain.ValorObjeto;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("agregadores")
@Produces("application/json")
@Stateless
public class Controller {

    @Context
    private UriInfo uriInfo;

    @Inject
    private AgregadorRepository repository;

    @GET
    public Collection<Agregador> getAll(@QueryParam("offset") @DefaultValue("0") int offset, @QueryParam("limit") @DefaultValue("50") int limit) {
        return repository.findAll(offset, limit);
    }

    @POST
    public Response post(String dado) {
        AgregadorId id = repository.nextIdentity();
        Agregador agregador = new Agregador(id, dado);
        repository.put(agregador);

        return Response
                .created(uriInfo
                        .getAbsolutePathBuilder()
                        .path("{id}")
                        .build(agregador.getId()))
                .entity(agregador)
                .build();
    }

    @Path("{id : \\d+}")
    @GET
    public Agregador get(@PathParam("id") long id) {
        return repository.find(new AgregadorId(id)).orElseThrow(NotFoundException::new);
    }

    @Path("{id : \\d+}")
    @DELETE
    public void delete(@PathParam("id") long id) {
        repository.remove(repository.find(new AgregadorId(id)).orElseThrow(NotFoundException::new));
    }

    @Path("{id : \\d+}/entidades")
    @POST
    public Response postEntidade(@PathParam("id") long id, String dado) {
        Agregador agregador = repository.find(new AgregadorId(id)).orElseThrow(NotFoundException::new);
        Entidade entidade = agregador.addEntidade(new ValorObjeto(dado));
        repository.put(agregador);

        return Response
                .created(uriInfo
                        .getAbsolutePathBuilder()
                        .path("{id}")
                        .build(entidade.getId()))
                .entity(entidade)
                .build();
    }
}
