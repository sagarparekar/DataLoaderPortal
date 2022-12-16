import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-patient',
  templateUrl: './patient.component.html',
  styleUrls: ['./patient.component.css']
})
export class PatientComponent {

  constructor(private router: Router) {

  }

  navigateToLogout() {
    localStorage.removeItem('jwttoken');
    this.router.navigate(['']);
  }

  navigateToUpload() {
    this.router.navigate(['/upload']);
  }

  navigateToSearch() {
    this.router.navigate(['/search']);
  }


}
