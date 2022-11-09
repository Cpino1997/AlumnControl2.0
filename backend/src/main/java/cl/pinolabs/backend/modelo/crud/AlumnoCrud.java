package cl.pinolabs.backend.modelo.crud;

import cl.pinolabs.backend.modelo.entity.Alumno;
import org.springframework.data.repository.CrudRepository;

public interface AlumnoCrud extends CrudRepository<Alumno,Integer> {
}
