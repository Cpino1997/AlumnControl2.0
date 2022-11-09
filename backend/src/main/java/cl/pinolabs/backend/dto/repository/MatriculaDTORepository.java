package cl.pinolabs.backend.dto.repository;

import cl.pinolabs.backend.dto.entity.MatriculaDTO;

import java.util.List;
import java.util.Optional;

public interface MatriculaDTORepository {
    Optional<List<MatriculaDTO>> findAll();
    Optional<MatriculaDTO> findById(int idAlumno, int idCurso);
    MatriculaDTO save(MatriculaDTO matriculaDTO);
    void delete(int idAlumno, int idCurso);

}
