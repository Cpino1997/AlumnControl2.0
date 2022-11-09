import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Alumnos } from 'src/app/modelos/alumnos.model';
import { AlumnosService } from 'src/app/_services/alumnos.service';

@Component({
  selector: 'app-alumno',
  templateUrl: './alumno.component.html',
  styleUrls: ['./alumno.component.css']
})
export class AlumnoComponent implements OnInit {


  @Input() viewMode = false;

  @Input() currentAlumno: Alumnos = {
    nombre: '',
    apellido: '',
    rut: 0
  };

  message = '';

  constructor(
    private alumnoService: AlumnosService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void {
    if (!this.viewMode) {
      this.message = '';
      this.getAlumno(this.route.snapshot.params["id"]);
    }
  }

  getAlumno(id: number): void {
    this.alumnoService.get(id)
      .subscribe({
        next: (data) => {
          this.currentAlumno = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }

  updateAlumno(): void {
    this.message = '';

    this.alumnoService.update(this.currentAlumno.id, this.currentAlumno)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.message = res.message ? res.message : 'Este alumno a sido modificado con exito!';
        },
        error: (e) => console.error(e)
      });
  }
  deleteAlumno(): void {
    this.alumnoService.delete(this.currentAlumno.id)
      .subscribe({
        next: (res) => {
          console.log(res);
          window.location.reload();
        },
        error: (e) => console.error(e)
      });
  }

}
