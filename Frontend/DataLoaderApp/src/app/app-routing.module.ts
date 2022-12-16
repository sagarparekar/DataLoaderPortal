import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login/login.component';
import { AuthGuard } from './services/auth.guard';

const routes: Routes = [


  { path: '', component: LoginComponent },

  {
    path: 'patient',
    loadChildren: () => import('./patient/patient.module').then(m => m.PatientModule), canActivate:[AuthGuard]
  },
  {
    path: 'upload',
    loadChildren: () => import('./upload/upload.module').then(m => m.UploadModule), canActivate:[AuthGuard]

  },

  {
    path: 'search',
    loadChildren: () => import('./search/search.module').then(m => m.SearchModule), canActivate:[AuthGuard]
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
