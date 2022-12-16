import { DatePipe } from '@angular/common';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ApiService } from 'src/app/services/api.service';
import { Search } from './search';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent {

  
  constructor(public searchService: ApiService,  private router: Router,  public datepipe: DatePipe) { }

  patientobj: Search = new Search();

  validsearch: boolean = false;
  
  
  show: boolean = false;
  showform: boolean = false;

  data: {} | any;

  searchData: Array<Search> = [];

  patientId: number | any;
  patientName: String | any;
  patientAddress: String | any;
  patientDOB: Date | any;
  patientEmail: String | any;
  patientContactNumber: number | any;
  patientDrugId: String | any;
  patientDrugName: String | any;
  response: any;

  getPatientByName(name: String) {
    this.searchService.getPatientByName(this.patientobj.patientName).subscribe(data => {
      this.data = JSON.stringify(data);
      console.log(this.data);
      this.searchData = Object.values(data);
      this.patientId = this.searchData[0];
      this.patientName = this.searchData[1];
      this.patientAddress = this.searchData[2];
      // this.patientDOB = this.searchData[3];
      let dob: String | any = this.searchData[3];
      // console.log(dob);
      this.patientDOB = this.datepipe.transform(dob, 'MM-dd-yyyy');
      this.patientEmail = this.searchData[4];
      this.patientContactNumber = this.searchData[5];
      this.patientDrugId = this.searchData[6];
      this.patientDrugName = this.searchData[7];
      this.show = true;
      this.showform = false;
      // alert("Patient Details fetched successfully");
    },
      error => {
        console.log("Error " + error);
       // alert("User role not Allowed to perform this operation");
       this.validsearch=true;
      })
}



navigateToSearch() {
  this.router.navigate(['/search']);
}

navigateToPatient() {
  this.router.navigate(['/patient']);
}

public showMyMessage = false

showMessageSoon() {
  setTimeout(() => {
    this.showMyMessage = true
  }, 500)
}

updatePatient() {this.searchService.updateform.setValue({
    patientName: this.patientName,
    patientAddress: this.patientAddress,
    patientDOB: this.patientDOB,
    patientEmail: this.patientEmail,
    patientContactNumber: this.patientContactNumber
  })
  this.showform = true;
  this.show = false;
}

updatePatientDetails() {
  this.searchService.updatePatientByName(this.searchService.updateform.value).subscribe((response: any) => {
    console.log(response);
    this.showform = false;
    // alert("Patient Details updated successfully");
  },
    error => {
      console.log("Error is" + error);
      console.log(error);
      alert("Update Failed");
    })
}

updatedArr: any = {};
//ng model update method

// updatePatientDetails(updatePatientObj:Serachpatient){
// console.log("Inside update-company component "+updatePatientObj);
// // alert("Inside update-company component ");
// this.updatedArr=updatePatientObj;
// this.searchService.getPatientByName(updatePatientObj.patientName).subscribe(
//  (data)=>{
//    console.log("Data"+data);
//    data.patientAddress=updatePatientObj.patientAddress;
//    data.patientDOB=updatePatientObj.patientDOB;
//    data.patientEmail=updatePatientObj.patientEmail;
//    data.patientContactNumber=updatePatientObj.patientContactNumber;
//    this.searchService.updatePatientByName(updatePatientObj).subscribe(
//      (d)=>{
//        this.searchService.getPatientByName(d.patientName);
//      } ,
// (error)=>{
//  console.log(error);
// });
// });
// }


}
