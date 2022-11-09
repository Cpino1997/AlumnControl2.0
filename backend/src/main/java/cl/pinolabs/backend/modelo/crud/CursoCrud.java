package cl.pinolabs.backend.modelo.crud;

import cl.pinolabs.backend.modelo.entity.Curso;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CursoCrud extends CrudRepository<Curso,Integer> {
}
