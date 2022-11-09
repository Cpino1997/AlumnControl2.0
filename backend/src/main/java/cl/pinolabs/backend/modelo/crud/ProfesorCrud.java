package cl.pinolabs.backend.modelo.crud;

import cl.pinolabs.backend.modelo.entity.Profesor;
import org.springframework.data.repository.CrudRepository;

public interface ProfesorCrud extends CrudRepository<Profesor,Integer> {
}
