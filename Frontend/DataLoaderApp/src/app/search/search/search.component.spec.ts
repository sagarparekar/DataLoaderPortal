import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchComponent } from './search.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';

import { DatePipe } from '@angular/common';
import {MatDialogModule} from '@angular/material/dialog';
import {MatInputModule} from '@angular/material/input';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

describe('SearchComponent', () => {
  let component: SearchComponent;
  let fixture: ComponentFixture<SearchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SearchComponent ],
      imports: [HttpClientTestingModule,MatDialogModule,MatInputModule,FormsModule,ReactiveFormsModule],
      providers: [SearchComponent, DatePipe]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    const component: SearchComponent= TestBed.get(SearchComponent);
    expect(component).toBeTruthy();
  });

  
  it('Testing show value',() => {

    expect(component.show).toBe(false);

  });

  it('Testing getPatientByName function',() => {

    let name:String="Test Name";

    expect(component.getPatientByName(name));

  });



});
