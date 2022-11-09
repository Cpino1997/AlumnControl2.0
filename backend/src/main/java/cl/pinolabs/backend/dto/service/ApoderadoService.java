package cl.pinolabs.backend.dto.service;

import cl.pinolabs.backend.dto.entity.AlumnoDTO;
import cl.pinolabs.backend.dto.entity.ApoderadoDTO;
import cl.pinolabs.backend.dto.repository.AlumnoDTORepository;
import cl.pinolabs.backend.dto.repository.ApoderadoDTORepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApoderadoService {
    private final ApoderadoDTORepository repo;
    public ApoderadoService(ApoderadoDTORepository repo) {
        this.repo = repo;
    }
    public Optional<List<ApoderadoDTO>> findAll(){
        return repo.findAll();
    }
    public Optional<ApoderadoDTO> findById(int idApoderado){
        return repo.findById(idApoderado);
    }
    public ApoderadoDTO save(ApoderadoDTO apoderadoDTO){
        return repo.save(apoderadoDTO);
    }
    public boolean delete(int idApoderado){
        return findById(idApoderado)
                .map(apoderadoDTO -> {
                    repo.delete(idApoderado);
                    return true;})
                .orElse(false);
    }
}
