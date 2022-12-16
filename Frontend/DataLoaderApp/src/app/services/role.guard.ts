import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { ApiService } from './api.service';

@Injectable({
  providedIn: 'root'
})
export class RoleGuard implements CanActivate {
 
  constructor(private service:ApiService,private route:Router){

  }
  canActivate(){
    if(this.service.IsLoggedIn()){
      this.route.navigate(['/patient']);
    return true;
    }else{
      this.route.navigate([''])
      return false;
    }
  }
}
