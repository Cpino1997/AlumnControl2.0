import { Component, OnInit } from '@angular/core';
import { Profesores } from 'src/app/modelos/profesores.model';
import { AuthService } from 'src/app/_services/auth.service';
import { ProfeService } from 'src/app/_services/profe.service';
import { StorageService } from 'src/app/_services/storage.service';

@Component({
  selector: 'app-lista-profes',
  templateUrl: './lista-profes.component.html',
  styleUrls: ['./lista-profes.component.css']
})
export class ListaProfesComponent implements OnInit {
  profesores?: Profesores[];
  currentProfesor: Profesores = {};
  id = -1;
  nombre = '';
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  private roles: string[] = [];
  showAdd = false;

  constructor(private profesorServive: ProfeService,private authService: AuthService, private storageService: StorageService) { }

  ngOnInit(): void {
    this.recibeProfes();
    if (this.storageService.isLoggedIn()) {
      this.isLoggedIn = true;
      this.roles = this.storageService.getUser().roles;

    }

    if (this.isLoggedIn) {
      const user = this.storageService.getUser();
      this.roles = user.roles;
      this.showAdd = this.roles.includes('ROLE_MODERATOR');
      this.showAdd = this.roles.includes('ROLE_ADMIN');
    }
  }
  recibeProfes(): void {
    this.profesorServive.getAll()
    .subscribe({
      next: (data) => {
        this.profesores = data;
        console.log(data);
      },
      error: (e) => console.error(e)
    });
  }
  refreshList(): void {
    this.recibeProfes();
    this.currentProfesor = {};
    this.id = -1;
  }
  setActive(profesor: Profesores, id: number): void {
    this.currentProfesor = profesor;
    this.id = id;
  }
  removeProfe(idProfe : number): void {
    this.profesorServive.delete(idProfe)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.refreshList();
        },
        error: (e) => console.error(e)
      });
  }
}
