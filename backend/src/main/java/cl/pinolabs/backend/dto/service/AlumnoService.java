package cl.pinolabs.backend.dto.service;

import cl.pinolabs.backend.dto.entity.AlumnoDTO;
import cl.pinolabs.backend.dto.repository.AlumnoDTORepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlumnoService {
        private final AlumnoDTORepository repo;
        public AlumnoService(AlumnoDTORepository repo) {
            this.repo = repo;
        }
        public Optional<List<AlumnoDTO>> findAll(){
            return repo.findAll();
        }
        public Optional<AlumnoDTO> findById(int idAlumno){
            return repo.findById(idAlumno);
        }
        public AlumnoDTO save(AlumnoDTO alumnoDTO){
            return repo.save(alumnoDTO);
        }
        public boolean delete(int idAlumno){
            return findById(idAlumno)
                    .map(alumnoDTO -> {
                        repo.delete(idAlumno);
                        return true;})
                    .orElse(false);
        }
    }