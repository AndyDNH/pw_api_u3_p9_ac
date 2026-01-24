package uce.edu.web.api.matricula.interfaces;

import java.util.List;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import uce.edu.web.api.matricula.aplication.MateriaService;
import uce.edu.web.api.matricula.domain.Materia;

@Path("/materia")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MateriaResource {

    @Inject
    private MateriaService materiaService;

    @GET
    @Path("")
    public Response buscarTodos() {
        List<Materia> materias = materiaService.listarTodos();
        // return Response.ok(materias).build(); se puede intercambiar segun se necesite c:
        return Response.status(200).entity(materias).build(); 
    }

    @GET
    @Path("/{id}")
    public Response buscarMateriaPorId(@PathParam("id") Integer id) {
        Materia materia = materiaService.consultarPorId(id);

        if (materia == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Materia no encontrada")
                    .build();
        }

        return Response.ok(materia).build(); 
    }

    @GET
    @Path("/horario/{horario}")
    public Response buscarPorHorario(@PathParam("horario") String horario) {
        List<Materia> materias = materiaService.consultarPorHorario(horario);
        return Response.ok(materias).build();
    }

    @GET
    @Path("/encargado/{encargado}")
    public Response buscarPorEncargado(@PathParam("encargado") String encargado) {
        List<Materia> materias = materiaService.consultarPorEncargado(encargado);
        return Response.ok(materias).build();
    }

    @POST
    public Response guardarMateria(Materia materia) {
        materiaService.crearMateria(materia);

        return Response.status(Response.Status.CREATED)
                .entity(materia)
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response actualizarMateria(
            @PathParam("id") Integer id,
            Materia materia) {

        Materia existente = materiaService.consultarPorId(id);

        if (existente == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Materia no encontrada")
                    .build();
        }

        materiaService.actualizarMateria(id, materia);
        return Response.noContent().build();
    }

    @PATCH
    @Path("/{id}")
    public Response actualizarParcialMateria(
            @PathParam("id") Integer id,
            Materia materia) {

        Materia existente = materiaService.consultarPorId(id);

        if (existente == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Materia no encontrada")
                    .build();
        }

        materiaService.parcialActualizarMateria(id, materia);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response eliminarMateriaPorId(@PathParam("id") Integer id) {
        Materia existente = materiaService.consultarPorId(id);

        if (existente == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Materia no encontrada")
                    .build();
        }

        materiaService.eliminarPorId(id);
        return Response.noContent().build();
    }
}
