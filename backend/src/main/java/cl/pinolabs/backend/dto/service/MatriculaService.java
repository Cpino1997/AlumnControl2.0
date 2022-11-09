package cl.pinolabs.backend.dto.service;

import cl.pinolabs.backend.dto.entity.MatriculaDTO;
import cl.pinolabs.backend.dto.repository.MatriculaDTORepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatriculaService {
    private final MatriculaDTORepository repo;

    public MatriculaService(MatriculaDTORepository repo) {
        this.repo = repo;
    }

    public Optional<List<MatriculaDTO>> findAll(){
        return repo.findAll();
    }
    public Optional<MatriculaDTO> findById(int idAlumno, int idCurso){
        return repo.findById(idAlumno, idCurso);
    }

    public MatriculaDTO save(MatriculaDTO matriculaDTO){
        return repo.save(matriculaDTO);
    }

    public boolean delete(int idAlumno, int idCurso){
        return findById(idAlumno, idCurso)
                .map(matriculaDTO -> {
                    repo.delete(idAlumno, idCurso);
                    return true;})
                .orElse(false);

    }
}