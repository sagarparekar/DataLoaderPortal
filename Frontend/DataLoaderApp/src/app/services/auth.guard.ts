import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
 
  constructor( private router: Router){

  }
  canActivate(){
    if(!localStorage.getItem('jwttoken')){
      this.router.navigate(['']);
      return false;
    }
    
   return true;
  }
}
