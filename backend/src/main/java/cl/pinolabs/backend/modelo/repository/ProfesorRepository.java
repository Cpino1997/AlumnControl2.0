package cl.pinolabs.backend.modelo.repository;


import cl.pinolabs.backend.dto.entity.ProfesorDTO;
import cl.pinolabs.backend.dto.repository.ProfesorDTORepository;
import cl.pinolabs.backend.modelo.crud.ProfesorCrud;
import cl.pinolabs.backend.modelo.entity.Profesor;
import cl.pinolabs.backend.modelo.mapper.ProfesorMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProfesorRepository implements ProfesorDTORepository {
    private final ProfesorCrud crud;
    private final ProfesorMapper mapper;
    public ProfesorRepository(ProfesorCrud crud, ProfesorMapper mapper) {
        this.crud = crud;
        this.mapper = mapper;
    }
    @Override
    public Optional<List<ProfesorDTO>> findAll() {
        return Optional.of(mapper.toProfesoresDTO((List<Profesor>) crud.findAll()));
    }
    @Override
    public Optional<ProfesorDTO> findById(int idProfe) {

        return crud.findById(idProfe)
                .map(mapper::toProfesorDTO);
    }
    @Override
    public ProfesorDTO save(ProfesorDTO profesorDTO) {
        return mapper.toProfesorDTO(crud.save(mapper.toProfesor(profesorDTO)));
    }
    @Override
    public void delete(int idProfe) {
        crud.deleteById(idProfe);
    }
}