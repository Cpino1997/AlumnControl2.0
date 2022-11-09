import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListaProfesComponent } from './lista-profes.component';

describe('ListaProfesComponent', () => {
  let component: ListaProfesComponent;
  let fixture: ComponentFixture<ListaProfesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListaProfesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListaProfesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
