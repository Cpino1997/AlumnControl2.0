package cl.pinolabs.backend.controller.restController;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class InicioController {

    @GetMapping("api")
    ResponseEntity<String> index() {
       return ResponseEntity.status(HttpStatus.OK).body("Hola, I Am Backend and Estoy Funcionado =D");
    }

    @GetMapping("/ping")
    private ResponseEntity<String> ping(){
        return ResponseEntity.status(HttpStatus.OK).body("Pong");
    }
}