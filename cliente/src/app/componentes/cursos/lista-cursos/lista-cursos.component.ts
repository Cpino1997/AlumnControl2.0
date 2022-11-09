import { Component, OnInit } from '@angular/core';
import { Cursos } from 'src/app/modelos/curso.model';
import { AuthService } from 'src/app/_services/auth.service';
import { CursoService } from 'src/app/_services/curso.service';
import { StorageService } from 'src/app/_services/storage.service';

@Component({
  selector: 'app-lista-cursos',
  templateUrl: './lista-cursos.component.html',
  styleUrls: ['./lista-cursos.component.css']
})
export class ListaCursosComponent implements OnInit {
  cursos?: Cursos[];
  currentCurso: Cursos = {};
  id = -1;
  nombre = '';
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];
  constructor(private cursoService: CursoService,private authService: AuthService, private storageService: StorageService) { }

  ngOnInit(): void {
    this.recibeCursos();
    if (this.storageService.isLoggedIn()) {
      this.isLoggedIn = true;
      this.roles = this.storageService.getUser().roles;
    }
  }
  recibeCursos(): void {
    this.cursoService.getAll()
    .subscribe({
      next: (data) => {
        this.cursos = data;
        console.log(data);
      },
      error: (e) => console.error(e)
    });
  }
  refreshList(): void {
    this.recibeCursos();
    this.currentCurso = {};
    this.id = -1;
  }
  setActive(curso: Cursos, id: number): void {
    this.currentCurso = curso;
    this.id = id;
  }
  removeProfe(idCurso : number): void {
    this.cursoService.delete(idCurso)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.refreshList();
        },
        error: (e) => console.error(e)
      });
  }
}
