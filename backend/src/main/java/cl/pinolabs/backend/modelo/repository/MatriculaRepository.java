package cl.pinolabs.backend.modelo.repository;

import cl.pinolabs.backend.dto.entity.MatriculaDTO;
import cl.pinolabs.backend.dto.repository.MatriculaDTORepository;
import cl.pinolabs.backend.modelo.crud.MatriculaCrud;
import cl.pinolabs.backend.modelo.entity.Matricula;
import cl.pinolabs.backend.modelo.entity.MatriculaPK;
import cl.pinolabs.backend.modelo.mapper.MatriculaMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MatriculaRepository implements MatriculaDTORepository {

    private final MatriculaCrud crud;
    private final MatriculaMapper mapper;

    public MatriculaRepository(MatriculaCrud crud, MatriculaMapper mapper) {
        this.crud = crud;
        this.mapper = mapper;
    }

    @Override
    public Optional<List<MatriculaDTO>> findAll() {
        return Optional.of(mapper.toMatriculaDTOs((List<Matricula>)crud.findAll()));
    }

    @Override
    public Optional<MatriculaDTO> findById(int idAlumno, int idCurso) {
        return crud.findById(new MatriculaPK(idAlumno, idCurso))
                .map(mapper::toMatriculaDTO);
    }

    @Override
    public MatriculaDTO save(MatriculaDTO matriculaDTO) {
        return mapper.toMatriculaDTO(crud.save(mapper.toMatricula(matriculaDTO)));
    }

    @Override
    public void delete(int idAlumno, int idCurso) {
        crud.deleteById(new MatriculaPK(idAlumno, idCurso));
    }
}