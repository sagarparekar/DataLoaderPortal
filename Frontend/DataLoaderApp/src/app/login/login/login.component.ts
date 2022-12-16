import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {


  validpassword: boolean = false;

  constructor(public loginService:ApiService ,public roleService:ApiService, private router:Router){

  }
  ngOnInit(): void {
  }

  LoginUser(){
    // console.log(this.loginService.loginform.value);
    this.loginService.login(this.loginService.loginform.value).subscribe((response:any)=>
      { 
     
        if(this.roleService.HaveAccess()){
          this.navigateToUploadData();
        }  else{  
          this.navigateToUploadcomp();
           }
          
      },
      error=>
      {
        console.log("Error is"+ error);
        this.validpassword=true;
        
       // alert("User login denied, need to register if not already");
      })
  }

  navigateToUploadData() {
    this.router.navigate(['/patient']);
  }

  navigateToUploadcomp() {
    this.router.navigate(['/upload']);
  }

  



}
