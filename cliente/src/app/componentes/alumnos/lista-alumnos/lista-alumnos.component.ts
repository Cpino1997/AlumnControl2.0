import { Component, OnInit } from '@angular/core';
import { Alumnos } from 'src/app/modelos/alumnos.model';
import { AlumnosService } from 'src/app/_services/alumnos.service';
import { AuthService } from 'src/app/_services/auth.service';
import { StorageService } from 'src/app/_services/storage.service';

@Component({
  selector: 'app-lista-alumnos',
  templateUrl: './lista-alumnos.component.html',
  styleUrls: ['./lista-alumnos.component.css']
})
export class ListaAlumnosComponent implements OnInit {


  alumnos?: Alumnos[];
  currentAlumno: Alumnos = {};
  id = -1;
  nombre = '';
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];

  constructor(private alumnoService: AlumnosService,private authService: AuthService, private storageService: StorageService) { }

  ngOnInit(): void {
    this.recibeAlumnos();
    if (this.storageService.isLoggedIn()) {
      this.isLoggedIn = true;
      this.roles = this.storageService.getUser().roles;
    }
  }
  recibeAlumnos(): void {
    this.alumnoService.getAll()
    .subscribe({
      next: (data) => {
        this.alumnos = data;
        console.log(data);
      },
      error: (e) => console.error(e)
    });
  }
  refreshList(): void {
    this.recibeAlumnos();
    this.currentAlumno = {};
    this.id = -1;
  }

  setActive(alumno: Alumnos, id: number): void {
    this.currentAlumno = alumno;
    this.id = id;
  }

  removeAlumno(idAlumno : number): void {
    this.alumnoService.delete(idAlumno)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.refreshList();
        },
        error: (e) => console.error(e)
      });
  }

}
