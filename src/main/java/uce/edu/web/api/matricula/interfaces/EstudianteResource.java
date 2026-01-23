package uce.edu.web.api.matricula.interfaces;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import uce.edu.web.api.matricula.aplication.EstudianteService;
import uce.edu.web.api.matricula.domain.Estudiante;

@Path("/estudiantes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EstudianteResource {

    @Inject
    private EstudianteService estudianteService;

    @GET
    public Response listarTodos() {
        List<Estudiante> estudiantes = estudianteService.listarTodos();
        return Response.ok(estudiantes).build();
    }

    @GET
    @Path("/provincia/genero")
    public Response buscarPorProvincia(
            @QueryParam("provincia") String provincia,
            @QueryParam("genero") String genero) {
        List<Estudiante> estudiantes =
                estudianteService.buscarPorProvincia(provincia, genero);
        return Response.ok(estudiantes).build();
    }

    @GET
    @Path("/{id}")
    public Response consultarPorId(@PathParam("id") Integer id) {
        Estudiante est = estudianteService.consultarPorID(id);

        if (est == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Estudiante no encontrado")
                    .build();
        }

        return Response.ok(est).build(); 
    }

    @POST
    public Response crearEstudiante(Estudiante estu) {
        estudianteService.crearEstudiante(estu);

        return Response.status(Response.Status.CREATED)
                .entity(estu)
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response actualizar(@PathParam("id") Integer id, Estudiante estu) {
        Estudiante existente = estudianteService.consultarPorID(id);

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
    public Response actualizarParcial(@PathParam("id") Integer id, Estudiante estu) {
        Estudiante existente = estudianteService.consultarPorID(id);

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
        Estudiante existente = estudianteService.consultarPorID(id);

        if (existente == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Estudiante no encontrado")
                    .build();
        }
        estudianteService.eliminarEstudiante(id);
        return Response.noContent().build(); 
    }
}
