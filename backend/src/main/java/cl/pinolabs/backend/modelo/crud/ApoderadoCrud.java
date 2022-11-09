package cl.pinolabs.backend.modelo.crud;

import cl.pinolabs.backend.modelo.entity.Apoderado;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ApoderadoCrud extends CrudRepository<Apoderado,Integer> {
}
