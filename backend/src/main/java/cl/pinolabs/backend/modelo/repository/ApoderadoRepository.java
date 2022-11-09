package cl.pinolabs.backend.modelo.repository;

import cl.pinolabs.backend.dto.entity.ApoderadoDTO;
import cl.pinolabs.backend.dto.repository.ApoderadoDTORepository;
import cl.pinolabs.backend.modelo.crud.ApoderadoCrud;
import cl.pinolabs.backend.modelo.entity.Apoderado;
import cl.pinolabs.backend.modelo.mapper.ApoderadoMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ApoderadoRepository implements ApoderadoDTORepository {

    private final ApoderadoCrud crud;
    private final ApoderadoMapper mapper;

    public ApoderadoRepository(ApoderadoCrud crud, ApoderadoMapper mapper) {
        this.crud = crud;
        this.mapper = mapper;
    }

    @Override
    public Optional<List<ApoderadoDTO>> findAll() {
        return Optional.of(mapper.toApoderadosDTO((List<Apoderado>) crud.findAll()));
    }

    @Override
    public Optional<ApoderadoDTO> findById(int idApoderado) {
        return (Optional<ApoderadoDTO>) crud.findById(idApoderado).map(mapper::toApoderadoDTO);
    }

    @Override
    public ApoderadoDTO save(ApoderadoDTO apoderadoDTO) {
        return mapper.toApoderadoDTO(crud.save(mapper.toApoderado(apoderadoDTO)));
    }

    @Override
    public void delete(int idApoderado) {
        crud.deleteById(idApoderado);
    }
}
