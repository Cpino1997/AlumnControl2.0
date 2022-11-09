package cl.pinolabs.backend.controller.restController;

import cl.pinolabs.backend.dto.entity.AlumnoDTO;
import cl.pinolabs.backend.dto.entity.ProfesorDTO;
import cl.pinolabs.backend.dto.service.ProfesorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/api/profes")
public class ProfesorController {
    private final ProfesorService service;

    public ProfesorController(ProfesorService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ProfesorDTO>> findAll(){
        System.out.println(" ---- Se ha solicitado la lista de profes ----");
        return service.findAll()
                .map(profesores -> new ResponseEntity<>(profesores, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/{idProfe}")
    public ResponseEntity<ProfesorDTO> findById(@PathVariable("idProfe") int idProfe){
        System.out.print("---- Se ha solicitado un Alumno ----" +idProfe);
        return service.findById(idProfe)
                .map(profesorDTO -> new ResponseEntity<>(profesorDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PutMapping("/{idProfe}")
    public ResponseEntity<ProfesorDTO> updateUser(@PathVariable(value = "idProfe") int idProfe, @Valid @RequestBody ProfesorDTO profeDTO) {
        System.out.println("Se ha modificado un Profesor");
        ProfesorDTO profe = service.findById(idProfe).get();
        profe.setId(profeDTO.getId());
        profe.setRut(profeDTO.getRut());
        profe.setNombre(profeDTO.getNombre());
        profe.setApellido(profeDTO.getApellido());
        profe.setCorreo(profeDTO.getCorreo());
        profe.setAsignatura(profeDTO.getAsignatura());
        profe.setNumero(profeDTO.getNumero());
        profe.setBanco(profeDTO.getBanco());
        profe.setCuenta(profeDTO.getCuenta());
        profe.setValor(profeDTO.getValor());
        final ProfesorDTO profeupdate = service.save(profe);
        return ResponseEntity.ok(profeupdate);
    }
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ProfesorDTO> save(@RequestBody ProfesorDTO profesorDTO){
        System.out.print("---- Se ha creado un Alumno ----");
        return new ResponseEntity<>(service.save(profesorDTO), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    @DeleteMapping("/{idProfe}")
    public ResponseEntity delete(@PathVariable("idProfe") int idProfe){
        System.out.print("---- Se ha eliminado un Profe ----");
        if (service.delete(idProfe)){
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}