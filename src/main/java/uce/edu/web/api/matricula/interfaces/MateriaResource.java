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
import uce.edu.web.api.matricula.aplication.MateriaService;
import uce.edu.web.api.matricula.domain.Materia;

@Path("/materia")
public class MateriaResource {

    @Inject
    private MateriaService materiaService;

    @GET
    @Path("/todos")
    public List<Materia> buscarTodos() {
        return this.materiaService.listarTodos();
    }

    @GET
    @Path("/buscarId/{id}")
    public Materia buscarMateriaPorId(@PathParam("id") Integer id) {
        return this.materiaService.consultarPorId(id);
    }

    @GET
    @Path("/buscarHorario/{horario}")
    public List<Materia> buscarPorHorario(@PathParam("horario") String horario) {
        return this.materiaService.consultarPorHorario(horario);
    }

    @GET
    @Path("/buscarEncargado/{encargado}")
    public List<Materia> buscarPorEncargado(@PathParam("encargado") String encargado) {
        return this.materiaService.consultarPorEncargado(encargado);
    }

    @POST
    @Path("/guardarM")
    public void guardarMateria(Materia materia){
        this.materiaService.crearMateria(materia);
    }

    @PUT
    @Path("/actualizar/{id}")
    public void actualizarMateria(@PathParam("id") Integer id, Materia materia){
        this.materiaService.actualizarMateria(id, materia);
    }

    @PATCH
    @Path("/actualizarParcial/{id}")
    public void actualizarParcialMateria(@PathParam("id") Integer id, Materia materia){
        this.materiaService.parcialActualizarMateria(id, materia);
    }

    @DELETE
    @Path("/eliminar/{id}")
    public void elminarMateriaPorId(@PathParam("id") Integer id){
        this.materiaService.eliminarPorId(id);
    }

}
