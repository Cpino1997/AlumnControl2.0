package cl.pinolabs.backend.modelo.repository;


import cl.pinolabs.backend.dto.entity.AlumnoDTO;
import cl.pinolabs.backend.dto.repository.AlumnoDTORepository;
import cl.pinolabs.backend.modelo.crud.AlumnoCrud;
import cl.pinolabs.backend.modelo.entity.Alumno;
import cl.pinolabs.backend.modelo.mapper.AlumnoMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AlumnoRepository implements AlumnoDTORepository {
    private final AlumnoCrud crud;
    private final AlumnoMapper mapper;
    public AlumnoRepository(AlumnoCrud crud, AlumnoMapper mapper) {
        this.crud = crud;
        this.mapper = mapper;
    }
    @Override
    public Optional<List<AlumnoDTO>> findAll() {
        return Optional.of(mapper.toALumnosDTO((List<Alumno>) crud.findAll()));
    }
    @Override
    public Optional<AlumnoDTO> findById(int idAlumno) {

        return crud.findById(idAlumno)
                .map(mapper::toALumnoDTO);
    }
    @Override
    public AlumnoDTO save(AlumnoDTO alumnoDTO) {
        return mapper.toALumnoDTO(crud.save(mapper.toAlumno(alumnoDTO)));
    }
    @Override
    public void delete(int idAlumno) {
        crud.deleteById(idAlumno);
    }
}