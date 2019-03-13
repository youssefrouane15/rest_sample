import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EmloyeeComponent } from './employee.component';

describe('EmloyeeComponent', () => {
  let component: EmloyeeComponent;
  let fixture: ComponentFixture<EmloyeeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EmloyeeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EmloyeeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
