package cl.pinolabs.backend.controller.restController;

import cl.pinolabs.backend.dto.entity.ApoderadoDTO;
import cl.pinolabs.backend.dto.service.ApoderadoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/api/apoderados")
public class ApoderadoController {
    private final ApoderadoService service;
    public ApoderadoController(ApoderadoService service) {
        this.service = service;
    }
    @GetMapping
    public ResponseEntity<List<ApoderadoDTO>> findAll(){
        System.out.println(" ---- Se ha solicitado la lista de apoderados ----");
        return service.findAll()
                .map(apoderados -> new ResponseEntity<>(apoderados, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/{idApoderado}")
    public ResponseEntity<ApoderadoDTO> findById(@PathVariable("idApoderado") int idApoderado){
        System.out.print("---- Se ha solicitado un apoderado ----" +idApoderado);
        return service.findById(idApoderado)
                .map(apoderadoDTO -> new ResponseEntity<>(apoderadoDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PutMapping("/{idApoderado}")
    public ResponseEntity<ApoderadoDTO> updateUser(@PathVariable(value = "idApoderado") int idApoderado,@Valid @RequestBody ApoderadoDTO apoderadoDTO) {
        System.out.println("Se ha modificado un Apoderado");
        ApoderadoDTO apoderado = service.findById(idApoderado).get();
        apoderado.setId(apoderadoDTO.getId());
        apoderado.setRut(apoderadoDTO.getRut());
        apoderado.setNombre(apoderadoDTO.getNombre());
        apoderado.setApellido(apoderadoDTO.getApellido());
        apoderado.setCorreo(apoderadoDTO.getCorreo());
        apoderado.setTelefono(apoderadoDTO.getTelefono());
        final ApoderadoDTO updateApo = service.save(apoderado);
        return ResponseEntity.ok(updateApo);
    }
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ApoderadoDTO> save(@RequestBody ApoderadoDTO apoderadoDTO){
        System.out.print("---- Se ha creado un Alumno ----");
        return new ResponseEntity<>(service.save(apoderadoDTO), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    @DeleteMapping("/{idApoderado}")
    public ResponseEntity delete(@PathVariable("idApoderado") int idApoderado){
        System.out.print("---- Se ha eliminado un Apoderado ----");
        if (service.delete(idApoderado)){
            return ResponseEntity.ok().body("Apoderado eliminado con exito!");
        } else {
            return ResponseEntity.badRequest().body("Error al eliminar este Apoderado");
        }
    }
}