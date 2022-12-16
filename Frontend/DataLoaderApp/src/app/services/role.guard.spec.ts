import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RoleGuard } from './role.guard';
import { HttpClientModule } from '@angular/common/http';
import { ApiService } from './api.service';
import { DatePipe } from '@angular/common';

describe('RoleGuard', () => {
  let guard: RoleGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({imports: [HttpClientTestingModule,HttpClientModule],providers: [ApiService,DatePipe]});
    guard = TestBed.inject(RoleGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
