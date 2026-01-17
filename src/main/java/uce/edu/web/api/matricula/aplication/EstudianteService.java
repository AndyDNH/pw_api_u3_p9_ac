package uce.edu.web.api.matricula.aplication;

import java.util.List;

import uce.edu.web.api.matricula.domain.Estudiante;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.web.api.matricula.infraestructure.EstudianteRepository;

@ApplicationScoped

public class EstudianteService {
    @Inject
    private EstudianteRepository estudianteRepository;

    public List<Estudiante> listarTodos() {
        return this.estudianteRepository.listAll();
    }

    public Estudiante consultarPorID(Integer id) {
        return this.estudianteRepository.findById(id.longValue());
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
        //se actualiza en dirtuycheck
    }
    @Transactional
    public void eliminarEstudiante(Integer id){
        this.estudianteRepository.deleteById(id.longValue());
    }
}
