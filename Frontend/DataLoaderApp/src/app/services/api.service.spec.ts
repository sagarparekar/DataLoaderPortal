import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { ApiService } from './api.service';
import { DatePipe } from '@angular/common';

import { MatDialogModule } from '@angular/material/dialog';
import { MatInputModule } from '@angular/material/input';

describe('ApiService', () => {
  let service: ApiService;

  beforeEach(() => {
    TestBed.configureTestingModule({imports: [HttpClientTestingModule,MatDialogModule,MatInputModule],providers: [ApiService,DatePipe]});
    service = TestBed.inject(ApiService);
  });

  it('should be created', () => {
    const service: ApiService = TestBed.get(ApiService);
    expect(service).toBeTruthy();
  });


  it('Login form check invalid username',() =>{

    let username:any=service.loginform.controls['username'];

    expect(username.valid).toBeFalsy();

    expect(username.errors[ 'required' ]).toBeTruthy();

  });



  it('Login form check valid username',() =>{

    let username:any=service.loginform.controls['username'];

    username.setValue('user@gmail.com')

    expect(username.valid).toBeTruthy

    expect(username.value).toEqual('user@gmail.com');

  });

  it('Login form check invalid password',() =>{

    let password:any=service.loginform.controls['password'];

    expect(password.valid).toBeFalsy();

    expect(password.errors[ 'required' ]).toBeTruthy();

  });



  it('Login form check valid password',() =>{

    let password:any=service.loginform.controls['password'];

    password.setValue('test@4567')

    expect(password.valid).toBeTruthy

    expect(password.value).toEqual('test@4567');

  });

  it('Login form invalid',() =>{

    expect(service.loginform.invalid).toBeTruthy();

  });



  it('Login form valid',() =>{

    service.loginform.controls['username'].setValue('user@gmail.com');

    service.loginform.controls['password'].setValue('test@4567');

    expect(service.loginform.valid).toBeTruthy();

  });



  // Update test cases
  it('Update form check invalid patientName',() =>{
    let patientName :any=service.updateform.controls['patientName'];
    expect(patientName.valid).toBeFalsy();
    expect(patientName.errors[ 'required' ]).toBeTruthy();
  });


  it('Update form check valid patientName', () => {
    let patientName: any = service.updateform.controls['patientName'];
    patientName.setValue('Test Name')
    expect(patientName.valid).toBeTruthy;
    expect(patientName.value).toBe('Test Name');
  });

  it('Update form check invalid patientAddress',() =>{
    let patientAddress :any=service.updateform.controls['patientAddress'];
    expect(patientAddress.valid).toBeFalsy();
    expect(patientAddress.errors[ 'required' ]).toBeTruthy();
  });

  it('Update form check valid patientAddress', () => {
    let patientAddress: any = service.updateform.controls['patientAddress'];
    patientAddress.setValue('Test, address')
    expect(patientAddress.valid).toBeTruthy;
    expect(patientAddress.value).toBe('Test, address');
  });

  it('Update form check invalid patientDOB',() =>{
    let patientDOB :any=service.updateform.controls['patientDOB'];
    expect(patientDOB.valid).toBeFalsy();
    expect(patientDOB.errors[ 'required' ]).toBeTruthy();
  });

  it('Update form check valid patientDOB', () => {
    let patientDOB: any = service.updateform.controls['patientDOB'];
    patientDOB.setValue('04-11-2021')
    expect(patientDOB.valid).toBeTruthy;
    expect(patientDOB.value).toBe('04-11-2021');
  });

  it('Update form check invalid patientEmail',() =>{
    let patientDOB :any=service.updateform.controls['patientDOB'];
    expect(patientDOB.valid).toBeFalsy();
    expect(patientDOB.errors[ 'required' ]).toBeTruthy();
  });

  it('Update form check valid patientEmail', () => {
    let patientEmail: any = service.updateform.controls['patientEmail'];
    patientEmail.setValue('test@gmail.com')
    expect(patientEmail.valid).toBeTruthy;
    expect(patientEmail.value).toBe('test@gmail.com');
  });

  it('Update form check invalid patientContactNumber',() =>{
    let patientContactNumber :any=service.updateform.controls['patientContactNumber'];
    expect(patientContactNumber.valid).toBeFalsy();
    expect(patientContactNumber.errors[ 'required' ]).toBeTruthy();
  });

  it('Update form check valid patientContactNumber', () => {
    let patientContactNumber: any = service.updateform.controls['patientContactNumber'];
    patientContactNumber.setValue('test@gmail.com')
    expect(patientContactNumber.valid).toBeTruthy;
    expect(patientContactNumber.value).toBe('test@gmail.com');
  });




});
