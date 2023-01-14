import { TestBed } from '@angular/core/testing';

import { KeycloakinterceptorService } from './keycloakinterceptor.service';

describe('KeycloakinterceptorService', () => {
  let service: KeycloakinterceptorService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(KeycloakinterceptorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
