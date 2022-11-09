import { Component, OnInit } from '@angular/core';
import { Cursos } from 'src/app/modelos/curso.model';
import { Matricula } from 'src/app/modelos/matricula.model';
import { AuthService } from 'src/app/_services/auth.service';
import { MatriculaService } from 'src/app/_services/matricula.service';
import { StorageService } from 'src/app/_services/storage.service';

@Component({
  selector: 'app-lista-matriculas',
  templateUrl: './lista-matriculas.component.html',
  styleUrls: ['./lista-matriculas.component.css']
})
export class ListaMatriculasComponent implements OnInit {

  matriculas?: Matricula[];
  currentMatricula: Matricula = {};
  id = -1;
  nombre = '';
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];
  constructor(private matriculaService: MatriculaService,private authService: AuthService, private storageService: StorageService) { }

  ngOnInit(): void {
    this.recibeMatriculas();
    if (this.storageService.isLoggedIn()) {
      this.isLoggedIn = true;
      this.roles = this.storageService.getUser().roles;
    }
  }
  recibeMatriculas(): void {
    this.matriculaService.getAll()
    .subscribe({
      next: (data) => {
        this.matriculas = data;
        console.log(data);
      },
      error: (e) => console.error(e)
    });
  }
  refreshList(): void {
    this.recibeMatriculas();
    this.currentMatricula = {};
    this.id = -1;
  }
  setActive(matricula: Matricula, id: number): void {
    this.currentMatricula = matricula;
    this.id = id;
  }
  removeMatricula(idAlumno : number, idCurso : number): void {
    this.matriculaService.delete(idAlumno, idCurso)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.refreshList();
        },
        error: (e) => console.error(e)
      });
  }
}
