import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-upload',
  templateUrl: './upload.component.html',
  styleUrls: ['./upload.component.css']
})
export class UploadComponent implements OnInit{

  ngOnInit(): void {
   
  }
  postapi="http://localhost:8080/load/patientdata";
 // postapi="http://18.236.114.220:8080/load/patientdata";

  response: any;
  constructor(private http:HttpClient, private roleService:ApiService){

  }
 

  file:any;
  flag =false;

  selectFile(event:any){
    console.log(event);
    this.file=event.target.files[0];
    console.log(this.file);
  }

  uploadFile(){
    console.log("Inside the upload method");
    let formData=new FormData()
    formData.append("file",this.file);
    this.http.post(this.postapi,formData).subscribe(
      data=>{
        console.log(data); 
        this.response=data;  
        
      },(error) =>{
        this.flag=true;
        console.log(error);
      }
    )
  }
  
  navigateToPatient() {
   this.roleService.roleChecking(); 
  }

  public showMyMessage = false;
  showUploadMessage() {
    setTimeout(() => {
      this.showMyMessage=true;
    }, 1000)
  }

}
