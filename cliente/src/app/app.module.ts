import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { MatSliderModule } from '@angular/material/slider';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { IndexComponent } from './componentes/index/index.component';
import { ListaAlumnosComponent } from './componentes/alumnos/lista-alumnos/lista-alumnos.component';
import { CrearAlumnoComponent } from './componentes/alumnos/crear-alumno/crear-alumno.component';
import { AlumnoComponent } from './componentes/alumnos/alumno/alumno.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { BoardComponent } from './componentes/board/board.component';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatCardModule } from '@angular/material/card';
import { MatMenuModule } from '@angular/material/menu';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { LayoutModule } from '@angular/cdk/layout';
import { MenuComponent } from './componentes/menu/menu.component';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatListModule } from '@angular/material/list';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { LoginComponent } from './componentes/auth/login/login.component';
import { RegistroComponent } from './componentes/auth/registro/registro.component';
import { httpInterceptorProviders } from './_helpers/http.interceptor';
import { FuncionesComponent } from './componentes/index/funciones/funciones.component';
import { NosotrosComponent } from './componentes/index/nosotros/nosotros.component';
import { Error404Component } from './componentes/error404/error404.component';
import { ListaProfesComponent } from './componentes/profesor/lista-profes/lista-profes.component';
import { CrearProfeComponent } from './componentes/profesor/crear-profe/crear-profe.component';
import { ProfeComponent } from './componentes/profesor/profe/profe.component';
import { ListaCursosComponent } from './componentes/cursos/lista-cursos/lista-cursos.component';
import { CrearCursoComponent } from './componentes/cursos/crear-curso/crear-curso.component';
import { CursoComponent } from './componentes/cursos/curso/curso.component';
import { ListaMatriculasComponent } from './componentes/matricula/lista-matriculas/lista-matriculas.component';

@NgModule({
  declarations: [
    AppComponent,
    IndexComponent,
    ListaAlumnosComponent,
    CrearAlumnoComponent,
    AlumnoComponent,
    BoardComponent,
    MenuComponent,
    LoginComponent,
    RegistroComponent,
    FuncionesComponent,
    NosotrosComponent,
    Error404Component,
    ListaProfesComponent,
    CrearProfeComponent,
    ProfeComponent,
    ListaCursosComponent,
    CrearCursoComponent,
    CursoComponent,
    ListaMatriculasComponent,
  ],
  imports: [
    MatSliderModule,
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatGridListModule,
    MatCardModule,
    MatMenuModule,
    MatIconModule,
    MatButtonModule,
    LayoutModule,
    MatToolbarModule,
    MatSidenavModule,
    MatListModule,
    NgbModule,
    FormsModule,
    HttpClientModule,
    MatIconModule
  ],
  providers: [httpInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
