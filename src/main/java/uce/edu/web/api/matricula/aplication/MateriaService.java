package uce.edu.web.api.matricula.aplication;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.web.api.matricula.domain.Materia;
import uce.edu.web.api.matricula.infraestructure.MateriaRepository;

@ApplicationScoped
public class MateriaService {

    @Inject
    private MateriaRepository materiaRepository;
    
    public List<Materia> listarTodos(){
        return this.materiaRepository.listAll();
    }

    public Materia consultarPorId(Integer id){
        return this.materiaRepository.findById(id.longValue());
    }

    public List<Materia> consultarPorHorario(String horario){
    return materiaRepository.consultarPorHorario(horario);
    }

    public List<Materia> consultarPorEncargado(String encargado){
    return materiaRepository.consultarPorEncargado(encargado);
    }

    @Transactional
    public void crearMateria(Materia materia){
        this.materiaRepository.persist(materia);
    }

    @Transactional
    public void actualizarMateria(Integer id, Materia mat){
        Materia acMat = this.consultarPorId(id);
        acMat.nombre = mat.nombre;
        acMat.horario  = mat.horario;
        acMat.encargado = mat.encargado;
        acMat.creditos = mat.creditos;
    }

    @Transactional
    public void parcialActualizarMateria(Integer id, Materia mat){
        Materia acMat = this.consultarPorId(id);
        if (mat.nombre!=null) {
            acMat.nombre = mat.nombre;    
        }
        if (mat.horario!=null) {
            acMat.horario = mat.horario;    
        }
        if (mat.encargado!=null) {
            acMat.encargado = mat.encargado;    
        }
        if (mat.creditos!=null) {
            acMat.creditos = mat.creditos;    
        }
    }

    @Transactional
    public void eliminarPorId(Integer id){
        this.materiaRepository.deleteById(id.longValue());
    }



}
