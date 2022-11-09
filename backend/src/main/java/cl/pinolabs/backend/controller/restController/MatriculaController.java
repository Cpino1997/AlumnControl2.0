package cl.pinolabs.backend.controller.restController;


import cl.pinolabs.backend.dto.entity.MatriculaDTO;
import cl.pinolabs.backend.dto.service.MatriculaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/api/matriculas")
public class MatriculaController {
    private final MatriculaService service;

    public MatriculaController(MatriculaService service) {
        this.service = service;
    }
    @GetMapping
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<List<MatriculaDTO>> findAll(){
        return service.findAll()
                .map(matriculas -> new ResponseEntity<>(matriculas, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/{idAlumno}/{idCurso}")
    public ResponseEntity<MatriculaDTO> findById(@PathVariable("idAlumno, idCurso") int idAlumno, int idCurso){
        return service.findById(idAlumno, idCurso)
                .map(matriculaDTO -> new ResponseEntity<>(matriculaDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<MatriculaDTO> save(@RequestBody MatriculaDTO matriculaDTO){
        return new ResponseEntity<>(service.save(matriculaDTO), HttpStatus.OK);
    }
    @DeleteMapping("/{idAlumno}/{idCurso}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity delete(@PathVariable("idAlumno, idCurso") int idCurso, int idAlumno){
        if (service.delete(idCurso, idAlumno)){
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }


}
