package uce.edu.web.api.matricula.aplication;

import java.util.List;
import java.util.stream.Collectors;

import uce.edu.web.api.matricula.aplication.representation.EstudianteRepresentation;
import uce.edu.web.api.matricula.domain.Estudiante;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.web.api.matricula.infraestructure.EstudianteRepository;

@ApplicationScoped

public class EstudianteService {
    @Inject
    private EstudianteRepository estudianteRepository;

    public List<EstudianteRepresentation> listarTodos() {
        return this.estudianteRepository.listAll()
        .stream().map(this::mapperToER).collect(Collectors.toList());  
    }

    public EstudianteRepresentation consultarPorID(Integer id) {
        return this.mapperToER(estudianteRepository.findById(id.longValue()));
    }

    @Transactional
    public void crearEstudiante(EstudianteRepresentation estu) {
        this.estudianteRepository.persist(mapperToEstudiante(estu));
    }

    // Se actualiza automaticamente al detectar cambios por dirty checking
    @Transactional
    public void actualizarEstudiante(Integer id, EstudianteRepresentation estuR) {
        Estudiante entity = estudianteRepository.findById(id.longValue());
        
        if (entity != null) {
            entity.nombre = estuR.nombre;
            entity.apellido = estuR.apellido;
            entity.fechaNacimiento = estuR.fechaNacimiento;
            entity.genero = estuR.genero;
            entity.provincia = estuR.provincia;
        }
    }

    @Transactional
    public void parcialActuEstudiante(Integer id, EstudianteRepresentation estuR) {
        Estudiante entity = estudianteRepository.findById(id.longValue());
        
        if (entity != null) {
            if (estuR.nombre != null) entity.nombre = estuR.nombre;
            if (estuR.apellido != null) entity.apellido = estuR.apellido;
            if (estuR.fechaNacimiento != null) entity.fechaNacimiento = estuR.fechaNacimiento;
            if (estuR.genero != null) entity.genero = estuR.genero;
            if (estuR.provincia != null) entity.provincia = estuR.provincia;
        }
    }

    @Transactional
    public void eliminarEstudiante(Integer id) {
        this.estudianteRepository.deleteById(id.longValue());
    }

    public List<EstudianteRepresentation> buscarPorProvincia(String provincia, String genero){
         //return this.estudianteRepository.find("provincia", provincia).list();
        return this.estudianteRepository
        .find("provincia=?1 and genero = ?2", provincia, genero).list()
        .stream().map(this::mapperToER).collect(Collectors.toList());
    }

    private EstudianteRepresentation mapperToER(Estudiante est){
        EstudianteRepresentation estuR = new EstudianteRepresentation();
        estuR.id = est.id;
        estuR.nombre = est.nombre;
        estuR.apellido = est.apellido;
        estuR.fechaNacimiento = est.fechaNacimiento;
        estuR.genero = est.genero;
        estuR.provincia = est.provincia;
        return estuR;
        }

        private Estudiante mapperToEstudiante(EstudianteRepresentation est){
        Estudiante estudiante = new Estudiante();
        estudiante.id = est.id;
        estudiante.nombre = est.nombre;
        estudiante.apellido = est.apellido;
        estudiante.fechaNacimiento = est.fechaNacimiento;
        estudiante.genero = est.genero;
        estudiante.provincia = est.provincia;
        return estudiante;
        }

}
