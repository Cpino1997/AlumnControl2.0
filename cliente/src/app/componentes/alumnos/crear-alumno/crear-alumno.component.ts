import { Component } from '@angular/core';
import { Alumnos } from 'src/app/modelos/alumnos.model';
import { AlumnosService } from 'src/app/_services/alumnos.service';

@Component({
  selector: 'app-crear-alumno',
  templateUrl: './crear-alumno.component.html',
  styleUrls: ['./crear-alumno.component.css']
})
export class CrearAlumnoComponent{
    alumno: Alumnos = {
      nombre: '',
      apellido: '',
      rut: 0
    };
    submitted = false;

    constructor(private alumnoService: AlumnosService) { }

    saveAlumno(): void {
      const data = {
        nombre: this.alumno.nombre,
        apellido: this.alumno.apellido,
        rut: this.alumno.rut,
      };

      this.alumnoService.create(data)
        .subscribe({
          next: (res) => {
            console.log(res);
            this.submitted = true;
          },
          error: (e) => console.error(e)
        });
    }

    newAlumno(): void {
      this.submitted = false;
      this.alumno = {
        nombre: '',
        apellido: '',
        rut: 0
      };
    }

  }
