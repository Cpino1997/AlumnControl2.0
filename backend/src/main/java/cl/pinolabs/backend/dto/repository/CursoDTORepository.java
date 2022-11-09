package cl.pinolabs.backend.dto.repository;

import cl.pinolabs.backend.dto.entity.AlumnoDTO;
import cl.pinolabs.backend.dto.entity.CursoDTO;

import java.util.List;
import java.util.Optional;

public interface CursoDTORepository {
    Optional<List<CursoDTO>> findAll();
    Optional<CursoDTO> findById(int idCurso);
    CursoDTO save(CursoDTO cursoDTO);
    void delete(int idCurso);
}
