package uce.edu.web.api.matricula.infraestructure;

import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import uce.edu.web.api.matricula.domain.Materia;

@ApplicationScoped
public class MateriaRepository implements PanacheRepository<Materia> {
    
    public List<Materia> consultarPorHorario(String horario){
    return list("horario", horario);
    }

    public List<Materia> consultarPorEncargado(String encargado){
    return list("encargado", encargado);
    }

}
