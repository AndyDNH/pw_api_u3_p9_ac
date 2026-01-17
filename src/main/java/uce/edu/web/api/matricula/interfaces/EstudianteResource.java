package uce.edu.web.api.matricula.interfaces;

import java.util.List;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import uce.edu.web.api.matricula.aplication.EstudianteService;
import uce.edu.web.api.matricula.domain.Estudiante;


@Path("/estudiantes")
public class EstudianteResource {
    @Inject
    private EstudianteService estudianteService;
    @GET
    @Path("/todos")
    public List<Estudiante> listarTodos(){ 
        return this.estudianteService.listarTodos();
    }
    
    @GET
    @Path("/consultarPorId/{id}")
    public Estudiante consultarPotId (@PathParam("id") Integer id){
        return this.estudianteService.consultarPorID(id);
    }

    @POST
    @Path("/crear")
    public void crearEstudiante(Estudiante estu){
        this.estudianteService.crearEstudiante(estu);
    }

    @PUT
    @Path("/actualizar/{id}")
    public void actualizar(@PathParam("id") Integer id, Estudiante estu){
        this.estudianteService.actualizarEstudiante(id, estu);
    }

    @PATCH
    @Path("/actualizarParcial/{id}")
    public void actualizarParcial(@PathParam("id") Integer id, Estudiante estu){
        this.estudianteService.parcialActuEstudiante(id, estu);
    }

    @DELETE
    @Path("/borrarEstudiante/{id}")
    public void borrarestudiante(@PathParam("id") Integer id){
        this.estudianteService.eliminarEstudiante(id);
    }
    
    
}
