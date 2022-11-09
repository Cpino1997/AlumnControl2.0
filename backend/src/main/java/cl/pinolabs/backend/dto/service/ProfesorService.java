package cl.pinolabs.backend.dto.service;

import cl.pinolabs.backend.dto.entity.AlumnoDTO;
import cl.pinolabs.backend.dto.entity.ProfesorDTO;
import cl.pinolabs.backend.dto.repository.AlumnoDTORepository;
import cl.pinolabs.backend.dto.repository.ProfesorDTORepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfesorService {
    private final ProfesorDTORepository repo;
    public ProfesorService(ProfesorDTORepository repo) {
        this.repo = repo;
    }
    public Optional<List<ProfesorDTO>> findAll(){
        return repo.findAll();
    }
    public Optional<ProfesorDTO> findById(int idProfe){
        return repo.findById(idProfe);
    }
    public ProfesorDTO save(ProfesorDTO profesorDTO){
        return repo.save(profesorDTO);
    }
    public boolean delete(int idProfe){
        return findById(idProfe)
                .map(profesorDTO -> {
                    repo.delete(idProfe);
                    return true;})
                .orElse(false);
    }
}
