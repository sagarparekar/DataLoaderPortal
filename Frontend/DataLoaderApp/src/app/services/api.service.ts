import { DatePipe } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { map, Observable } from 'rxjs';
import { Login } from '../login/login/login';
import { Search } from '../search/search/search';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private http: HttpClient, private router:Router, public datepipe: DatePipe) { }

  loginform: FormGroup = new FormGroup({
    username: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required, Validators.minLength(8), Validators.maxLength(20)])

  });

  
  updateform: FormGroup = new FormGroup({
    patientName: new FormControl('', [Validators.required]),
    patientAddress: new FormControl('', [Validators.required]),
    patientDOB: new FormControl('', [Validators.required, Validators.pattern("(?:0[1-9]|1[012])[-/.](?:0[1-9]|[12][0-9]|3[01])[-/.](?:19\d{2}|20[02][0-1]|2020)")]),
    patientEmail: new FormControl('', [Validators.required, Validators.email]),
    patientContactNumber: new FormControl('', [Validators.required, Validators.pattern("^((\\+91-?)|0)?[0-9]{10}$")])
  });

  private apiUpdate: string = 'http://localhost:8080/load/updatepatient';
  private apiGetByName: string = `http://localhost:8080/load/retrieve`;
  private apiLogin: string = `http://localhost:8080/login`;

//  private apiLogin:string=`https://mxt51s1mg5.execute-api.us-west-2.amazonaws.com/Stage1/dataloaderportal`;
//  private apiGetByName: string = `https://mxt51s1mg5.execute-api.us-west-2.amazonaws.com/Stage1`;
 // private apiUpdate: string = 'https://mxt51s1mg5.execute-api.us-west-2.amazonaws.com/Stage1/dataloaderportal';

  login(login: Login): Observable<any> {
    return this.http.post<any>(`${this.apiLogin}`, login).pipe(
      map(
        userData => {
          localStorage.setItem('username', login.username);
          let tokenStr = 'Bearer ' + userData.accessToken;
          // console.log(tokenStr);
          localStorage.setItem('jwttoken', tokenStr);
          // console.log(userData);
          return userData;

        }
      )
    )
  }


  
  IsLoggedIn(){
    return localStorage.getItem('jwttoken')!=null;
  }

  HaveAccess(){
    var loggintoken=localStorage.getItem('jwttoken')||'';
    var _extractedtoken=loggintoken.split('.')[1];
    var _atobdata=atob(_extractedtoken);
    var _finaldata=JSON.parse(_atobdata); 
    if(_finaldata.roles=='[ROLE_ADMIN]'){
      return true;
    }else{
      return false;
    }
  }

  roleChecking(){
    var loggintoken=localStorage.getItem('jwttoken')||'';
    var _extractedtoken=loggintoken.split('.')[1];
    var _atobdata=atob(_extractedtoken);
    var _finaldata=JSON.parse(_atobdata);
    console.log("Final Data after Extract = "+JSON.stringify(_finaldata));
    if(_finaldata.roles=='[ROLE_ADMIN]'){
      return this.router.navigate(['/patient']);
    }else{
      return this.router.navigate(['/upload']);
    }
  }

  
 
 

  getPatientByName(name: String): Observable<Search> {
    return this.http.get<Search>(`${this.apiGetByName}/${name}`);
  }

  
 

  updatePatientByName(updatePatientObj: Search): Observable<Search> {
    // alert("Updating company details..");
    let dob = updatePatientObj.patientDOB;
    let dobnew = this.datepipe.transform(dob, 'yyyy-MM-dd');
    dobnew = dobnew + 'T00:00:00.000+00:00';
    updatePatientObj.patientDOB = dobnew;
    return this.http.put<Search>(this.apiUpdate, updatePatientObj);
  }

}
