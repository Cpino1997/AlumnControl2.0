package cl.pinolabs.backend.controller.restController;

import cl.pinolabs.backend.dto.entity.AlumnoDTO;
import cl.pinolabs.backend.dto.service.AlumnoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/api/alumnos")
public class AlumnoController {
        private final AlumnoService service;
        public AlumnoController(AlumnoService service) {
            this.service = service;
        }
        @GetMapping
        public ResponseEntity<List<AlumnoDTO>> findAll(){
            System.out.println(" ---- Se ha solicitado la lista de alumnos ----");
            return service.findAll()
                    .map(alumnos -> new ResponseEntity<>(alumnos, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }
        @GetMapping("/{idAlumno}")
        public ResponseEntity<AlumnoDTO> findById(@PathVariable("idAlumno") int idAlumno){
            System.out.print("---- Se ha solicitado un Alumno ----" +idAlumno);
            return service.findById(idAlumno)
                    .map(alumnoDTO -> new ResponseEntity<>(alumnoDTO, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }
        @PutMapping("/{idAlumno}")
        public ResponseEntity<AlumnoDTO> updateUser(@PathVariable(value = "idAlumno") int idAlumno,@Valid @RequestBody AlumnoDTO alumnoD) {
            System.out.println("Se ha modificado un alumno");
            AlumnoDTO alumno = service.findById(idAlumno).get();
            alumno.setNombre(alumnoD.getNombre());
            alumno.setApellido(alumnoD.getApellido());
            alumno.setRut(alumnoD.getRut());
            alumno.setId(alumnoD.getId());
            final AlumnoDTO updateAlumn = service.save(alumno);
            return ResponseEntity.ok(updateAlumn);
        }
        @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
        @PostMapping
        public ResponseEntity<AlumnoDTO> save(@RequestBody AlumnoDTO alumnoDTO){
            System.out.print("---- Se ha creado un Alumno ----");
            return new ResponseEntity<>(service.save(alumnoDTO), HttpStatus.OK);
        }
        @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
        @DeleteMapping("/{idAlumno}")
        public ResponseEntity delete(@PathVariable("idAlumno") int idAlumno){
            System.out.print("---- Se ha eliminado un Alumno ----");
            if (service.delete(idAlumno)){
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
    }