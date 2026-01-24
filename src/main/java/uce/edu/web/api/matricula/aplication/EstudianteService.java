package uce.edu.web.api.matricula.aplication;

import java.util.ArrayList;
import java.util.List;

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
        List<EstudianteRepresentation> list = new ArrayList<>();
        for(Estudiante estudiante : this.estudianteRepository.list()){
            
        }
    }

    public EstudianteRepresentation consultarPorID(Integer id) {
        return this.mapperToER(estudianteRepository.findById(id.longValue()));
    }

    @Transactional
    public void crearEstudiante(Estudiante estu) {
        this.estudianteRepository.persist(estu);
    }

    // Se actualiza automaticamente al detectar cambios por dirty checking
     @Transactional
    public void actualizarEstudiante(Integer id, Estudiante estu){
        Estudiante est = this.consultarPorID(id);
        est.apellido=estu.apellido;
        est.nombre=estu.nombre;
        est.fechaNacimiento = estu.fechaNacimiento;

    }
    @Transactional
    public void parcialActuEstudiante(Integer id, Estudiante estu){
        Estudiante est = this.consultarPorID(id);
        if (estu.nombre!=null) {
            est.nombre=estu.nombre;
        }
        if (estu.apellido!=null) {
            est.apellido=estu.apellido;
        }
        if (estu.fechaNacimiento!=null) {
            est.fechaNacimiento=estu.fechaNacimiento;
        }
        //se actualiza en dirtycheck
    }
    @Transactional
    public void eliminarEstudiante(Integer id){
        this.estudianteRepository.deleteById(id.longValue());
    }

    public List<Estudiante> buscarPorProvincia(String provincia, String genero){
         //return this.estudianteRepository.find("provincia", provincia).list();
        return this.estudianteRepository.find("provincia=?1 and genero = ?2", provincia, genero).list();
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
