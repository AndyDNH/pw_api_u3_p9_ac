package uce.edu.web.api.matricula.interfaces;

import java.util.List;

import org.jboss.resteasy.annotations.Query;

import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import uce.edu.web.api.matricula.aplication.EstudianteService;
import uce.edu.web.api.matricula.domain.Estudiante;

@Path("/estudiantes")
public class EstudianteResource {
    @Inject
    private EstudianteService estudianteService;

    @GET
    @Path("")
    public List<Estudiante> listarTodos() {
        System.out.println("Listar todos xxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        return this.estudianteService.listarTodos();
    }

    @GET
    @Path("/provincia/genero")
    public List<Estudiante> buscarPorProvinvia(@QueryParam("provincia") String provincia,
            @QueryParam("genero") String genero) {
        System.out.println("Listar provincia y genero xxxxxxxxxxxxxxxxxxxxxxxxxxxx");

        return this.estudianteService.buscarPorProvincia(provincia, genero);
    }

    @GET
    @Path("/{id}")
    public Estudiante consultarPotId(@PathParam("id") Integer id) {
        return this.estudianteService.consultarPorID(id);
    }

    @POST
    @Path("")
    public void crearEstudiante(Estudiante estu) {
        this.estudianteService.crearEstudiante(estu);
    }

    @PUT
    @Path("/{id}")
    public void actualizar(@PathParam("id") Integer id, Estudiante estu) {
        this.estudianteService.actualizarEstudiante(id, estu);
    }

    @PATCH
    @Path("/{id}")
    public void actualizarParcial(@PathParam("id") Integer id, Estudiante estu) {
        this.estudianteService.parcialActuEstudiante(id, estu);
    }

    @DELETE
    @Path("/{id}")
    public void borrarestudiante(@PathParam("id") Integer id) {
        this.estudianteService.eliminarEstudiante(id);
    }

}