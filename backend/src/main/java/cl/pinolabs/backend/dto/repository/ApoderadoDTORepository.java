package cl.pinolabs.backend.dto.repository;


import cl.pinolabs.backend.dto.entity.ApoderadoDTO;

import java.util.List;
import java.util.Optional;

public interface ApoderadoDTORepository {
    Optional<List<ApoderadoDTO>> findAll();
    Optional<ApoderadoDTO> findById(int idApoderado);
    ApoderadoDTO save(ApoderadoDTO apoderadoDTO);
    void delete(int idApoderado);
}
