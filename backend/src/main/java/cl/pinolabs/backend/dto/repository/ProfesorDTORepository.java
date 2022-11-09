package cl.pinolabs.backend.dto.repository;

import cl.pinolabs.backend.dto.entity.ProfesorDTO;

import java.util.List;
import java.util.Optional;

public interface ProfesorDTORepository {
    Optional<List<ProfesorDTO>> findAll();
    Optional<ProfesorDTO> findById(int idProfe);
    ProfesorDTO save(ProfesorDTO profesorDTO);
    void delete(int idProfe);
}
