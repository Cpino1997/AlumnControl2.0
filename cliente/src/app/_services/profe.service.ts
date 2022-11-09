import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Profesores } from '../modelos/profesores.model';

const baseUrl = 'http://localhost:8080/api/profes';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class ProfeService {

  constructor(private http: HttpClient) { }

  getAll(): Observable<Profesores[]> {
    return this.http.get<Profesores[]>(baseUrl, httpOptions);
  }

  get(id: any): Observable<Profesores> {
    return this.http.get<Profesores>(`${baseUrl}/${id}`);
  }

  create(data: any): Observable<any> {
    return this.http.post(baseUrl, data);
  }

  update(id: any, data: any): Observable<any> {
    return this.http.put(`${baseUrl}/${id}`, data);
  }

  delete(id: any): Observable<any> {
    return this.http.delete(`${baseUrl}/${id}`);
  }
}
