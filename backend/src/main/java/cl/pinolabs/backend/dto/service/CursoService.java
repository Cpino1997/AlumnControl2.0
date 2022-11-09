package cl.pinolabs.backend.dto.service;

import cl.pinolabs.backend.dto.entity.CursoDTO;
import cl.pinolabs.backend.dto.repository.CursoDTORepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {
    private final CursoDTORepository repo;
    public CursoService(CursoDTORepository repo) {
        this.repo = repo;
    }
    public Optional<List<CursoDTO>> findAll(){
        return repo.findAll();
    }
    public Optional<CursoDTO> findById(int idCurso){
        return repo.findById(idCurso);
    }
    public CursoDTO save(CursoDTO cursoDTO){
        return repo.save(cursoDTO);
    }
    public boolean delete(int idCurso){
        return findById(idCurso)
                .map(cursoDTO -> {
                    repo.delete(idCurso);
                    return true;})
                .orElse(false);
    }
}
