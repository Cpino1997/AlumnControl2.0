import { Component, OnInit } from '@angular/core';
import { Profesores } from 'src/app/modelos/profesores.model';
import { ProfeService } from 'src/app/_services/profe.service';

@Component({
  selector: 'app-crear-profe',
  templateUrl: './crear-profe.component.html',
  styleUrls: ['./crear-profe.component.css']
})
export class CrearProfeComponent{
  profesor: Profesores = {
    rut: 0,
    nombre: '',
    apellido: '',
    correo:'',
    asignatura:'',
    cuenta:'',
    banco:'',
    valor:'',
    numero:0
  };
  submitted = false;

  constructor(private profeService: ProfeService) { }

  saveProfe(): void {
    const data = {
      nombre: this.profesor.nombre,
      apellido: this.profesor.apellido,
      rut: this.profesor.rut,
      correo:this.profesor.correo,
      asignatura:this.profesor.asignatura,
      cuenta:this.profesor.cuenta,
      banco:this.profesor.banco,
      valor:this.profesor.valor,
      numero: this.profesor.numero
    };

    this.profeService.create(data)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.submitted = true;
        },
        error: (e) => console.error(e)
      });
  }

  newProfe(): void {
    this.submitted = false;
    this.profesor = {
      rut: 0,
      nombre: '',
      apellido: '',
      correo:'',
      asignatura:'',
      cuenta:'',
      banco:'',
      valor:'',
      numero:0
    };
  }

}
