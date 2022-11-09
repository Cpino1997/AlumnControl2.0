import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Matricula } from '../modelos/matricula.model';

const baseUrl = 'http://localhost:8080/api/matriculas';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
@Injectable({
  providedIn: 'root'
})
export class MatriculaService {

  constructor(private http: HttpClient) { }

  getAll(): Observable<Matricula[]> {
    return this.http.get<Matricula[]>(baseUrl, httpOptions);
  }

  get(idAlumno: number, idCurso:number): Observable<Matricula> {
    return this.http.get<Matricula>(`${baseUrl}/${idAlumno}${idCurso}`);
  }

  create(data: any): Observable<any> {
    return this.http.post(baseUrl, data);
  }

  update(idAlumno: number, idCurso: number, data: any): Observable<any> {
    return this.http.put(`${baseUrl}/${idAlumno}${idCurso}`, data);
  }

  delete(idAlumno: number, idCurso: number): Observable<any> {
    return this.http.delete(`${baseUrl}/${idAlumno}${idCurso}`);
  }
}
