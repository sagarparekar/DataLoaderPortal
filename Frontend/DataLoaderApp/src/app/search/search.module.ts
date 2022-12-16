import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SearchRoutingModule } from './search-routing.module';
import { SearchComponent } from './search/search.component';
import {MatDialogModule} from '@angular/material/dialog';

import {MatInputModule} from '@angular/material/input';

import {MatIconModule} from '@angular/material/icon';

import { FormsModule } from '@angular/forms';

import { DatePipe } from '@angular/common';

import {MatButtonModule} from '@angular/material/button';
import { ReactiveFormsModule } from '@angular/forms';



@NgModule({
  declarations: [
    SearchComponent
  ],
  imports: [
    CommonModule,
    SearchRoutingModule,
    MatDialogModule,
    MatInputModule,
    MatIconModule,
    FormsModule,
    DatePipe,
    MatButtonModule,
    ReactiveFormsModule
  ],
  providers: [DatePipe],

  exports:[SearchComponent]
})
export class SearchModule { }
