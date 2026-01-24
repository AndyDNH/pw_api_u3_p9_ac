package uce.edu.web.api.matricula.interfaces;

import java.util.List;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import uce.edu.web.api.matricula.aplication.EstudianteService;
import uce.edu.web.api.matricula.aplication.HijoService;
import uce.edu.web.api.matricula.domain.Estudiante;
import uce.edu.web.api.matricula.domain.Hijo;

@Path("/estudiantes")
public class EstudianteResource {

    @Inject
    private EstudianteService estudianteService;
    @Inject
    private HijoService hijoService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTodos() {
        List<Estudiante> estudiantes = estudianteService.listarTodos();
        return Response.ok(estudiantes).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/provincia/genero")
    public Response buscarPorProvincia(
            @QueryParam("provincia") String provincia,
            @QueryParam("genero") String genero) {
        List<Estudiante> estudiantes = estudianteService.buscarPorProvincia(provincia, genero);
        return Response.ok(estudiantes).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
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
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crearEstudiante(Estudiante estu) {
        estudianteService.crearEstudiante(estu);

        return Response.status(Response.Status.CREATED)
                .entity(estu)
                .build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
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
    @Consumes(MediaType.APPLICATION_JSON)
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

    @GET
    @Path("/{id}/hijos")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Hijo> buscarPorIdEstudiante(@PathParam("id")Integer id){
        return this.hijoService.buscarPorIdEstudiante(id);
    }
}
