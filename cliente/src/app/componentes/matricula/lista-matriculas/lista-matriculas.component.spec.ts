import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListaMatriculasComponent } from './lista-matriculas.component';

describe('ListaMatriculasComponent', () => {
  let component: ListaMatriculasComponent;
  let fixture: ComponentFixture<ListaMatriculasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListaMatriculasComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListaMatriculasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
