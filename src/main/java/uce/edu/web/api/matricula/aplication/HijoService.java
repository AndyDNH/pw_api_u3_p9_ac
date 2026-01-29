package uce.edu.web.api.matricula.aplication;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import uce.edu.web.api.matricula.aplication.representation.HijoRepresentation;
import uce.edu.web.api.matricula.domain.Hijo;
import uce.edu.web.api.matricula.infraestructure.HijoRepository;

@ApplicationScoped
public class HijoService {
    @Inject
    private HijoRepository hijoRepository;

    public List<HijoRepresentation> buscarPorIdEstudiante(Integer idEstudiante){
        return this.hijoRepository.buscarPorIdEstudiante(idEstudiante)
        .stream().map(this::mapperToHijoR).collect(Collectors.toList());
        
    }

    private  HijoRepresentation mapperToHijoR(Hijo hijo){
        HijoRepresentation hr = new HijoRepresentation();
        hr.id = hijo.id;
        hr.nombre = hijo.nombre;
        hr.apellido = hijo.apellido;
    
        return hr;
    }

}
