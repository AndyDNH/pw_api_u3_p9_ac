package uce.edu.web.api.matricula.interfaces;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import uce.edu.web.api.matricula.aplication.EstudianteService;
import uce.edu.web.api.matricula.aplication.HijoService;
import uce.edu.web.api.matricula.aplication.representation.EstudianteRepresentation;
import uce.edu.web.api.matricula.aplication.representation.HijoRepresentation;
import uce.edu.web.api.matricula.aplication.representation.LinkDTO;

@Path("/estudiantes")
public class EstudianteResource {

    @Inject
    private EstudianteService estudianteService;
    @Inject
    private HijoService hijoService;
    @Context
    private UriInfo uriInfo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTodos() {
        List<EstudianteRepresentation> estudiantes = estudianteService.listarTodos().stream().map(this::construirLink).collect(Collectors.toList());
        return Response.ok(estudiantes).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/provincia/genero")
    public Response buscarPorProvincia(
            @QueryParam("provincia") String provincia,
            @QueryParam("genero") String genero) {
        List<EstudianteRepresentation> estudiantes = estudianteService.buscarPorProvincia(provincia, genero);
        return Response.ok(estudiantes).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")

    public EstudianteRepresentation consultarPorId(@PathParam("id") Integer id) {
        return this.construirLink(this.estudianteService.consultarPorID(id));
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crearEstudiante(EstudianteRepresentation estu) {
        estudianteService.crearEstudiante(estu);

        return Response.status(Response.Status.CREATED)
                .entity(estu)
                .build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizar(@PathParam("id") Integer id, EstudianteRepresentation estu) {
        EstudianteRepresentation existente = estudianteService.consultarPorID(id);

        if (existente == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Estudiante no encontrado")
                    .build();
        }

        estudianteService.actualizarEstudiante(id, estu);
        return Response.noContent().build(); // 204
    }

    @PATCH
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizarParcial(@PathParam("id") Integer id, EstudianteRepresentation estu) {
        EstudianteRepresentation existente = estudianteService.consultarPorID(id);

        if (existente == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Estudiante no encontrado")
                    .build();
        }

        estudianteService.parcialActuEstudiante(id, estu);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response borrarEstudiante(@PathParam("id") Integer id) {
        EstudianteRepresentation existente = estudianteService.consultarPorID(id);

        if (existente == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Estudiante no encontrado")
                    .build();
        }
        estudianteService.eliminarEstudiante(id);
        return Response.noContent().build();
    }

    @GET
    @Path("/{id}/hijos")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<HijoRepresentation> buscarPorIdEstudiante(@PathParam("id")Integer id){
        return this.hijoService.buscarPorIdEstudiante(id);
    }

    private EstudianteRepresentation construirLink( EstudianteRepresentation er){
        String self = this.uriInfo.getBaseUriBuilder().path(EstudianteResource.class).path(String.valueOf(er.id))
        .build().toString();

        String hijos = this.uriInfo.getBaseUriBuilder()
        .path(EstudianteResource.class).path(String.valueOf(er.id))
        .path("hijos").build().toString();

        er.link = List.of(new LinkDTO(self, "self"), new LinkDTO(hijos,"hijos"));
        return er;
    }
}
