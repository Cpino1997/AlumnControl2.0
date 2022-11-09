import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AlumnoComponent } from './componentes/alumnos/alumno/alumno.component';
import { CrearAlumnoComponent } from './componentes/alumnos/crear-alumno/crear-alumno.component';
import { ListaAlumnosComponent } from './componentes/alumnos/lista-alumnos/lista-alumnos.component';
import { LoginComponent } from './componentes/auth/login/login.component';
import { RegistroComponent } from './componentes/auth/registro/registro.component';
import { BoardComponent } from './componentes/board/board.component';
import { CrearCursoComponent } from './componentes/cursos/crear-curso/crear-curso.component';
import { ListaCursosComponent } from './componentes/cursos/lista-cursos/lista-cursos.component';
import { Error404Component } from './componentes/error404/error404.component';
import { FuncionesComponent } from './componentes/index/funciones/funciones.component';
import { IndexComponent } from './componentes/index/index.component';
import { NosotrosComponent } from './componentes/index/nosotros/nosotros.component';
import { ListaMatriculasComponent } from './componentes/matricula/lista-matriculas/lista-matriculas.component';
import { CrearProfeComponent } from './componentes/profesor/crear-profe/crear-profe.component';
import { ListaProfesComponent } from './componentes/profesor/lista-profes/lista-profes.component';

const routes: Routes = [
  { path: 'home', component: IndexComponent },
  { path: 'error404', component: Error404Component },
  { path: 'funciones', component: FuncionesComponent },
  { path: 'nosotros', component: NosotrosComponent },
  { path: 'login', component: LoginComponent },
  { path: 'registro', component: RegistroComponent },
  { path: 'board', component: BoardComponent },
  { path: 'alumnos', component: ListaAlumnosComponent },
  { path: 'alumnos/:id', component: AlumnoComponent },
  { path: 'crear-alumnos', component: CrearAlumnoComponent },
  { path: 'profesores', component: ListaProfesComponent },
  { path: 'crear-profe', component: CrearProfeComponent },
  { path: 'cursos', component: ListaCursosComponent },
  { path: 'crear-curso', component: CrearCursoComponent },
  { path: 'matriculas', component: ListaMatriculasComponent },
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: '**', redirectTo: 'error404', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
