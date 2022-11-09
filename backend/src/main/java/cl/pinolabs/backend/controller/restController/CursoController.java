package cl.pinolabs.backend.controller.restController;

import cl.pinolabs.backend.dto.entity.CursoDTO;

import cl.pinolabs.backend.dto.service.CursoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/api/cursos")
public class CursoController {
        private final CursoService service;
        public CursoController(CursoService service) {
            this.service = service;
        }
        @GetMapping
        public ResponseEntity<List<CursoDTO>> findAll(){
            System.out.println(" ---- Se ha solicitado la lista de Curso ----");
            return service.findAll()
                    .map(cursos -> new ResponseEntity<>(cursos, HttpStatus.OK))
                    .orElse( new ResponseEntity<>(HttpStatus.NO_CONTENT));
        }
        @GetMapping("/{idCurso}")
        public ResponseEntity<CursoDTO> findById(@PathVariable("idAlumno") int idAlumno){
            System.out.print("---- Se ha solicitado un Alumno ----" +idAlumno);
            return service.findById(idAlumno)
                    .map(cursoDTO -> new ResponseEntity<>(cursoDTO, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }
        @PutMapping("/{idCurso}")
        public ResponseEntity<CursoDTO> updateCurso(@PathVariable(value = "idAlumno") int idCurso,@Valid @RequestBody CursoDTO cursoDTO) {
            System.out.println("Se ha modificado un Curso");
            CursoDTO curso = service.findById(idCurso).get();
            curso.setId(cursoDTO.getId());
            curso.setNombre(cursoDTO.getNombre());
            curso.setCodigo(cursoDTO.getCodigo());
            curso.setProfesorDTO(cursoDTO.getProfesorDTO());
            final CursoDTO cursoupdate = service.save(curso);
            return ResponseEntity.ok(cursoupdate);
        }
        @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
        @PostMapping
        public ResponseEntity<CursoDTO> save(@RequestBody CursoDTO cursoDTO){
            System.out.print("---- Se ha creado un Curso ----");
            return new ResponseEntity<>(service.save(cursoDTO), HttpStatus.OK);
        }
        @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
        @DeleteMapping("/{idCurso}")
        public ResponseEntity delete(@PathVariable("idCurso") int idCurso){
            System.out.print("---- Se ha eliminado un Curso ----");
            if (service.delete(idCurso)){
                return ResponseEntity.ok().body("Curso eliminado con exito!");
            } else {
                return ResponseEntity.status(500).body("Error al eliminar el curso: "+idCurso);
                                    }
        }
    }