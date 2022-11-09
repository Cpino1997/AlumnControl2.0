import { TestBed } from '@angular/core/testing';

import { ProfeService } from './profe.service';

describe('ProfeService', () => {
  let service: ProfeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ProfeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
