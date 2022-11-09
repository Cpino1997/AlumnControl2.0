package cl.pinolabs.backend.dto.repository;
import cl.pinolabs.backend.dto.entity.AlumnoDTO;
import java.util.List;
import java.util.Optional;

public interface AlumnoDTORepository {
    Optional<List<AlumnoDTO>> findAll();
    Optional<AlumnoDTO> findById(int idAlumno);
    AlumnoDTO save(AlumnoDTO alumnoDTO);
    void delete(int idAlumno);
}