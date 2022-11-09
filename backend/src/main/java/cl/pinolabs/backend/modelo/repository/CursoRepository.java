package cl.pinolabs.backend.modelo.repository;

import cl.pinolabs.backend.dto.entity.CursoDTO;
import cl.pinolabs.backend.dto.repository.CursoDTORepository;
import cl.pinolabs.backend.modelo.crud.CursoCrud;
import cl.pinolabs.backend.modelo.entity.Curso;
import cl.pinolabs.backend.modelo.mapper.CursoMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CursoRepository implements CursoDTORepository {
    private final CursoCrud crud;
    private final CursoMapper mapper;

    public CursoRepository(CursoCrud crud, CursoMapper mapper) {
        this.crud = crud;
        this.mapper = mapper;
    }
    @Override
    public Optional<List<CursoDTO>> findAll() {
        return Optional.of(mapper.toCursosDTO((List<Curso>) crud.findAll()));
    }
    @Override
    public Optional<CursoDTO> findById(int idCurso) {
        return crud.findById(idCurso)
                .map(mapper::toCursoDTO);
    }
    @Override
    public CursoDTO save(CursoDTO cursoDTO) {
        return mapper.toCursoDTO(crud.save(mapper.toCurso(cursoDTO)));
    }

    @Override
    public void delete(int idCurso) {
        crud.deleteById(idCurso);
    }
}
