package cl.pinolabs.backend.modelo.crud;

import cl.pinolabs.backend.modelo.entity.Matricula;
import cl.pinolabs.backend.modelo.entity.MatriculaPK;
import org.springframework.data.repository.CrudRepository;

public interface MatriculaCrud extends CrudRepository<Matricula, MatriculaPK> {
}